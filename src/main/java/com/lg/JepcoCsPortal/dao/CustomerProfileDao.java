package com.lg.JepcoCsPortal.dao;

import com.lg.JepcoCsPortal.entities.CustomerProfile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerProfileDao extends CrudRepository<CustomerProfile, Long> {

    CustomerProfile findByEmailAndPassword(String email, String password);

}
