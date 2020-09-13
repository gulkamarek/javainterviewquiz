package com.jackwise.util;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    private static StandardServiceRegistry standardServiceRegistry;
    private static SessionFactory sessionFactory;

    static {
        try {

            if (sessionFactory == null) {

                Map<String,String> jdbcUrlSettings = new HashMap<>();
                String jdbcDbUrl = System.getenv("CLEARDB_DATABASE_URL");
                if (null != jdbcDbUrl) {
                    jdbcUrlSettings.put("hibernate.connection.url", jdbcDbUrl);
                }
                standardServiceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
                        .applySettings(jdbcUrlSettings).build();
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

    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
//
//    private static SessionFactory sessionFactory;
//    public static SessionFactory getSessionFactory() {
//        if (sessionFactory == null) {
//            try {
//                Configuration configuration = new Configuration();
//                // Hibernate settings equivalent to hibernate.cfg.xml's properties
//
//                Properties settings = new Properties();
//                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
//                settings.put(Environment.URL, "jdbc:mysql://u3y93bv513l7zv6o.chr7pe7iynqr.eu-west-1.rds.amazonaws.com:3306/mlx2kgg7o1p43a56");
//                settings.put(Environment.USER, "slgw0xkr0gxmi8d0");
//                settings.put(Environment.PASS, "jqu4tt9ozgvzzu3q");
//                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
//                settings.put(Environment.SHOW_SQL, "true");
//                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
//                settings.put(Environment.HBM2DDL_AUTO, "update");
//
//                configuration.setProperties(settings);
//                configuration.addAnnotatedClass(Question.class);
//                configuration.addAnnotatedClass(Answer.class);
//                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                        .applySettings(configuration.getProperties()).build();
//                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return sessionFactory;
//    }
}
