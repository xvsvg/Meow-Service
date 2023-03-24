package org.myaukalki.factories;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.myaukalki.domain.contracts.*;
import org.myaukalki.domain.implementations.CatImpl;
import org.myaukalki.domain.implementations.OwnerImpl;

import java.io.File;

public class HibernateFactory {

    private static SessionFactory sessionFactory;

    private HibernateFactory() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Owner.class);
                configuration.addAnnotatedClass(Pet.class);
                configuration.addAnnotatedClass(CatImpl.class);
                configuration.addAnnotatedClass(OwnerImpl.class);

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());


                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return sessionFactory;
    }
}
