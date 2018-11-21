package com.lg.JepcoCsPortal.services;

import com.lg.JepcoCsPortal.entities.CustomerProfile;
import com.lg.JepcoCsPortal.helpers.Login;

import java.util.List;
import java.util.Optional;

public interface CustomerProfileService {

    List<CustomerProfile> findAll();
    Optional<CustomerProfile> findById(Long customerId);
    CustomerProfile save(CustomerProfile customerProfile);
    CustomerProfile login(Login login);

}
