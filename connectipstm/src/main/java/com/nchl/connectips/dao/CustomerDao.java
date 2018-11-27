package com.nchl.connectips.dao;

import com.nchl.connectips.model.CustomerDetails;

public interface CustomerDao {
	public CustomerDetails getCustomerDetailByVPA(String virtualPrivateAddress);
}
