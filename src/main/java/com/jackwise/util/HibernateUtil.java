package com.jackwise.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

public class HibernateUtil {

    private static StandardServiceRegistry standardServiceRegistry;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {

        try {
            if (sessionFactory == null) {
                Configuration cfg = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.URL, System.getenv("CLEARDB_URL"));
                settings.put(Environment.USER, System.getenv("CLEARDB_USER"));
                settings.put(Environment.PASS, System.getenv("CLEARDB_PASS"));
                cfg.setProperties(settings);

                standardServiceRegistry = new StandardServiceRegistryBuilder().configure()
                        .applySettings(cfg.getProperties())
                        .build();
                MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);
                Metadata metadata = metadataSources.getMetadataBuilder().build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (standardServiceRegistry != null) {
                StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
            }
        }
        return sessionFactory;
    }
}
