package com.housie.db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DatabaseUtil {
    @Bean
    public static SessionFactory getSessionFactory() {
        System.out.println("hibernate session factory initialized");
        return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }
}
