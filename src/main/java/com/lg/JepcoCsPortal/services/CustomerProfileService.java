package com.lg.JepcoCsPortal.services;

import com.lg.JepcoCsPortal.entities.CustomerProfile;

import java.util.List;
import java.util.Optional;

public interface CustomerProfileService {

    public List<CustomerProfile> findAll();
    public Optional<CustomerProfile> find(Long customerId);
    public CustomerProfile save(CustomerProfile customerProfile);

}
