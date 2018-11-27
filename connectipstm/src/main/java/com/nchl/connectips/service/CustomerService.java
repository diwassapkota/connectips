package com.nchl.connectips.service;

import com.nchl.connectips.model.CustomerDetails;

public interface CustomerService {
	public CustomerDetails getCustomerDetailByVPA(String virtualPrivateAddress);
}
