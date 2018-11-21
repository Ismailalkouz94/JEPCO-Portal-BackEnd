package com.lg.JepcoCsPortal.dao;

import com.lg.JepcoCsPortal.entities.CustomerProfile;

import java.util.List;

public interface CustomerProfileDao {

    public List<CustomerProfile> findAll();
    public CustomerProfile find(Long customerId);
    public CustomerProfile save(CustomerProfile customerProfile);

}
