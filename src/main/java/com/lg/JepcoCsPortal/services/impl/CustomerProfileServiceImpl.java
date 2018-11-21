package com.lg.JepcoCsPortal.services.impl;

import com.lg.JepcoCsPortal.dao.CustomerProfileDao;
import com.lg.JepcoCsPortal.entities.CustomerProfile;
import com.lg.JepcoCsPortal.services.CustomerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerProfileServiceImpl implements CustomerProfileService {

    @Autowired
    private CustomerProfileDao customerProfileDao;

    @Override
    public List<CustomerProfile> findAll() {
        return customerProfileDao.findAll();
    }

    @Override
    public CustomerProfile find(Long customerId) {
        return customerProfileDao.find(customerId);
    }

    @Override
    public CustomerProfile save(CustomerProfile customerProfile) {
        return customerProfileDao.save(customerProfile);
    }
}
