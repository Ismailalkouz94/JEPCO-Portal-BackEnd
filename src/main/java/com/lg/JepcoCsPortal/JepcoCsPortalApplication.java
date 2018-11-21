package com.lg.JepcoCsPortal;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;

@SpringBootApplication
public class JepcoCsPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JepcoCsPortalApplication.class, args);
	}

//	@Bean
//	public HibernateJpaSessionFactoryBean sessionFactory(){
//            return new HibernateJpaSessionFactoryBean();
//	}

//    @Bean
//    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory);
//        return transactionManager;
//    }

//    @Bean
//    public HibernateJpaSessionFactoryBean sessionFactory(EntityManagerFactory emf) {
//        HibernateJpaSessionFactoryBean fact = new HibernateJpaSessionFactoryBean();
//        fact.setEntityManagerFactory(emf);
//        return fact;
//    }




}
