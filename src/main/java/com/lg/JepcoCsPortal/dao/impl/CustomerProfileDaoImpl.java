package com.lg.JepcoCsPortal.dao.impl;

import com.lg.JepcoCsPortal.dao.CustomerProfileDao;
import com.lg.JepcoCsPortal.entities.CustomerProfile;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CustomerProfileDaoImpl  {


//    @PersistenceContext
//    private EntityManager entityManager;
//
//
//    @Override
//    public List<CustomerProfile> findAll() {
//
//        return entityManager.createQuery("SELECT c FROM CustomerProfile c ").getResultList();
//    }
//
}
