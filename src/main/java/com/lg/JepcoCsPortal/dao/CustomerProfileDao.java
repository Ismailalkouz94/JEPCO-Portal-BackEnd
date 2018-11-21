package com.lg.JepcoCsPortal.dao;

import com.lg.JepcoCsPortal.entities.CustomerProfile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerProfileDao extends CrudRepository<CustomerProfile, Long> {


}
