package com.nchl.connectips.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.nchl.connectips.util.Utilities;

@PropertySource(value = { "classpath:application.properties" })
public class EncryptedDataSource extends DriverManagerDataSource{

	@Autowired
	private Utilities util;
	@Autowired
    private Environment environment;

	@Override
	 public String getPassword() {
	  String password = super.getPassword();
	  return decode(password);
	 }

	 /***
	  * Decode Password
	  */
	 private String decode(String password) {
		 password = password.substring(4, password.length()-1);
		 password = util.jasyptDecrypt(password,"TKCUL"+environment.getRequiredProperty("jdbc.password.code")+"HIEFX");
		 return  password;
	 }

}
