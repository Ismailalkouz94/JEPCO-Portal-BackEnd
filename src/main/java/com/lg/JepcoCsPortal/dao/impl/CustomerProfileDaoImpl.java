package com.lg.JepcoCsPortal.dao.impl;

import com.lg.JepcoCsPortal.dao.CustomerProfileDao;
import com.lg.JepcoCsPortal.entities.CustomerProfile;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerProfileDaoImpl implements CustomerProfileDao {

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public List<CustomerProfile> findAll() {
        return null;
    }

    @Override
    public CustomerProfile find(Long customerId) {
        return null;
    }

    @Override
    public CustomerProfile save(CustomerProfile customerProfile) {
        return null;
    }
}
