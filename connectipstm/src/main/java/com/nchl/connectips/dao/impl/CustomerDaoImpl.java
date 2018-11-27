package com.nchl.connectips.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nchl.connectips.dao.CustomerDao;
import com.nchl.connectips.model.CustomerDetails;

@Repository("customerDao")
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
    private SessionFactory sessionFactory;

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }
    
	@Override
	public CustomerDetails getCustomerDetailByVPA(String virtualPrivateAddress) {
		try {
			CriteriaBuilder builder = getSession().getCriteriaBuilder();
			CriteriaQuery<CustomerDetails> query = builder.createQuery(CustomerDetails.class);
			Root<CustomerDetails> root = query.from(CustomerDetails.class);
			query.where(builder.equal(root.get("virtualPrivateAddress"), virtualPrivateAddress));
			Query<CustomerDetails> q = getSession().createQuery(query);
			return q.getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}

}
