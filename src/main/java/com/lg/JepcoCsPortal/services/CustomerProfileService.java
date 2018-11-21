package com.lg.JepcoCsPortal.services;

import com.lg.JepcoCsPortal.entities.CustomerProfile;

import java.util.List;

public interface CustomerProfileService {

    public List<CustomerProfile> findAll();
    public CustomerProfile find(Long customerId);
    public CustomerProfile save(CustomerProfile customerProfile);

}
