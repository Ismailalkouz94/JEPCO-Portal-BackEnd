package com.lg.JepcoCsPortal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

@SpringBootApplication
public class JepcoCsPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JepcoCsPortalApplication.class, args);
	}

	@Bean
	public HibernateJpaSessionFactoryBean sessionFactory(){
		return new HibernateJpaSessionFactoryBean();
	}
}
