package com.nchl.connectips.security;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

//import com.fasterxml.jackson.databind.ObjectMapper;

@Component("customTokenEnhancer")
public class CustomTokenEnhancer implements TokenEnhancer {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	/*
	@Autowired
	private CustomerService customerService;
	@Autowired
	private JasonWebToken jsonWebToken;*/
	@Autowired
	private HttpServletRequest request;


	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		User user = (User) authentication.getPrincipal();

		String ipAddress = request.getHeader("X-FORWARDED-FOR");
	    if (ipAddress == null) {  
	    	ipAddress = request.getRemoteAddr();
	    } 
	    
	    logger.info("REQUEST IP: " +ipAddress);
	    logger.info("REQUEST USER: " +user.getUsername());
		
		//ObjectMapper mapper = new ObjectMapper();
		/*CustomerDetailsAll custdetailsall = new CustomerDetailsAll();
		CustomerDetails customerDetails = customerService.getCustomerDetailByVPA(user.getUsername());
		String jwt = null;
		try {
			boolean result = customerService.isUserAccountExists(customerDetails.getCustId());
			
			if(!result) {
				custdetailsall = customerService.getCustomerDetails(user.getUsername());
				if(!custdetailsall.getMpin().equals("N")) {
					custdetailsall.setMpin("Y");
				}
				customerDetails.setLoginStatus("Y");
				customerService.updateCustomerDetails(customerDetails);
				custdetailsall.setAccounExists("NO");
				String jsonInString = mapper.writeValueAsString(custdetailsall);
				jwt = jsonWebToken.createJWT(jsonInString, null);
			}else {
				custdetailsall = customerService.getCustomerDetails(user.getUsername());
				if(!custdetailsall.getMpin().equals("N")) {
					custdetailsall.setMpin("Y");
				}
				customerDetails.setLoginStatus("Y");
				customerService.updateCustomerDetails(customerDetails);
				custdetailsall.setAccounExists("YES");
				String jsonInString = mapper.writeValueAsString(custdetailsall);
				jwt = jsonWebToken.createJWT(jsonInString, null);
			}
		}catch(Exception ex) {
			logger.error(ex);
		}*/
		final Map<String, Object> additionalInfo = new HashMap<>();

		additionalInfo.put("customerdetails", null);

		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
		
		return accessToken;
	}

}
