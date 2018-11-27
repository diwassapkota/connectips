package com.nchl.connectips.security;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	static final String CLIENT_ID = "nchlmobapp";
	static final String CLIENT_SECRET = "mobappnchl";
	static final String GRANT_TYPE = "password";
	static final String AUTHORIZATION_CODE = "authorization_code";
	static final String REFRESH_TOKEN = "refresh_token";
	static final String IMPLICIT = "implicit";
	static final String SCOPE_READ = "read";
	static final String SCOPE_WRITE = "write";
	static final String TRUST = "trust";
	static final int ACCESS_TOKEN_VALIDITY_SECONDS = 30;
	static final int REFRESH_TOKEN_VALIDITY_SECONDS = 6*60*60;
	//private static String REALM="MY_OAUTH_REALM";
	
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    private CustomTokenEnhancer customTokenEnhancer;
    @Autowired
	private DataSource dataSource;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .passwordEncoder(new BCryptPasswordEncoder());
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    	clients.jdbc(dataSource);
    /*	clients
    	.inMemory()
    	.withClient(CLIENT_ID)
    	.secret(new BCryptPasswordEncoder().encode(CLIENT_SECRET))
    	.authorizedGrantTypes(GRANT_TYPE, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT )
    	.scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
    	.accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
    	.refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS);
    	*/
    
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    	endpoints
    		.authenticationManager(authenticationManager)
    		.tokenStore(tokenStore())
    		.userDetailsService(userDetailsService)
    		.tokenEnhancer(customTokenEnhancer)
    		.exceptionTranslator(e -> {
            if (e instanceof OAuth2Exception) {
                OAuth2Exception oAuth2Exception = (OAuth2Exception) e;
                return ResponseEntity
                			.status(oAuth2Exception.getHttpErrorCode())
                        .body(new OAuth2Exception(oAuth2Exception.getMessage()));
            } else {
                throw e;
            }
        });
    }

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }
}
