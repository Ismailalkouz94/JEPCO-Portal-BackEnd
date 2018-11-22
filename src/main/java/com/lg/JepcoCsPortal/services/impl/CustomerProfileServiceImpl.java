package com.lg.JepcoCsPortal.services.impl;

import com.lg.JepcoCsPortal.dao.CustomerProfileDao;
import com.lg.JepcoCsPortal.entities.CustomerProfile;
import com.lg.JepcoCsPortal.helpers.Login;
import com.lg.JepcoCsPortal.services.CustomerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerProfileServiceImpl implements CustomerProfileService {

    @Autowired
    private CustomerProfileDao customerProfileDao;

    @Override
    public List<CustomerProfile> findAll() {
        return (List<CustomerProfile>) customerProfileDao.findAll();
    }

    @Override
    public Optional<CustomerProfile> findById(Long customerId) {
        return customerProfileDao.findById(customerId);
    }

    @Override
    public CustomerProfile save(CustomerProfile customerProfile) {
        return customerProfileDao.save(customerProfile);
    }

    @Override
    public CustomerProfile login(Login login) {

        return customerProfileDao.findByEmailAndPassword(login.getEmail(), login.getPassword());
    }
}
