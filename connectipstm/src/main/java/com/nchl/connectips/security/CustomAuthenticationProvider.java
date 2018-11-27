package com.nchl.connectips.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.nchl.connectips.model.CustomerDetails;
import com.nchl.connectips.service.CustomerService;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private CustomerService customerService;
	@Autowired 
	private CustomPasswordEncoder customPasswordEncoder;
	
	private boolean enabled = true;
	private boolean accountNonExpired = true;
	private boolean credentialNonExpired = true;
	private boolean accountNonLocked = true;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		
		String userId = auth.getName();
		String password = auth.getCredentials().toString();

		CustomerDetails customerDetails =  customerService.getCustomerDetailByVPA(userId.trim().toUpperCase());

		
		if((customerDetails==null)){
			throw new UsernameNotFoundException("Invalid Username or Password");
		}else if (customerDetails.getEnabled()==0) {
			throw new DisabledException("Account Disabled.");
		}else if(customerDetails.getAccountNonLocked()==0) {
			throw new LockedException("Account Locked.");
		}else if(!customPasswordEncoder.matches(password, customerDetails.getPassword())) {
			//build logic for account lock.
			String message = "Invalid Password.";
			/*
			int noOfattempts = customerService.invalidLoginPasswrodHandler(userId);
			if(noOfattempts<1){
				CustomerStatusDetail customerStatusDetail = new CustomerStatusDetail(userId, "account_non_locked", new Date());
				customerService.addCustomerStatusDetail(customerStatusDetail);
				message = "Account Locked. Please try after 10 minutes.";
			}else{
				message = "Invalid User Id or Password. You are left with "+noOfattempts+" Attempts";
			}
			*/
			
			throw new BadCredentialsException(message);
		}else if(customPasswordEncoder.matches(password, customerDetails.getPassword())) {
			//Build logic to update max login attempt
			//customerService.updateOnLogin(customerDetails.getVirtualPrivateAddress());
		}
		
		if(customerDetails.getEnabled()==0){enabled=false;}else{enabled=true;}
		if(customerDetails.getAccountNonLocked()==0){accountNonLocked=false;}else{accountNonLocked=true;}
		if(customerDetails.getCredentialNonExpired()==0){credentialNonExpired=false;}else{credentialNonExpired=true;}

		
		UserDetails userdetails = new User(customerDetails.getVirtualPrivateAddress(),customerDetails.getPassword(), enabled,accountNonExpired,credentialNonExpired,accountNonLocked, getAuthorities(1));
		return new UsernamePasswordAuthenticationToken(userdetails, password, getAuthorities(customerDetails.getRoleCode()));
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	public Collection<? extends GrantedAuthority> getAuthorities(Integer role) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
		return authList;
	}
	
	public List<String> getRoles(Integer role) {

		List<String> roles = new ArrayList<String>();

		if (role.intValue() == 1) {
			roles.add("ROLE_RETAILUSER");
		}
		return roles;
	}
	
	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}
}
