package com.nchl.connectips.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nchl.connectips.dao.CustomerDao;
import com.nchl.connectips.model.CustomerDetails;
import com.nchl.connectips.service.CustomerService;

@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public CustomerDetails getCustomerDetailByVPA(String virtualPrivateAddress) {
		return customerDao.getCustomerDetailByVPA(virtualPrivateAddress);
	}

}
