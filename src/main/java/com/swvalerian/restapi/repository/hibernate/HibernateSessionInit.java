package com.swvalerian.restapi.repository.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionInit {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }
}