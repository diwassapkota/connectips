package com.nchl.connectips.configuration;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nchl.connectips.util.Utilities;

@Converter
public class CryptoConverter implements AttributeConverter<String, String>{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public String convertToDatabaseColumn(String arg0) {
		try {
			Utilities util = new Utilities();
			return util.encrypt(arg0);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new RuntimeException(e);
		}
	}

	@Override
	public String convertToEntityAttribute(String arg0) {
		try {
			Utilities util = new Utilities();
			return util.decrypt(arg0);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new RuntimeException(e);
		}
	}

}
