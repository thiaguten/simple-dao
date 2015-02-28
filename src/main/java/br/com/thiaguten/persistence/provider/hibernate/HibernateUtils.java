package br.com.thiaguten.persistence.provider.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public abstract class HibernateUtils {

    private static Session session;
    private static SessionFactory sessionFactory;

    public static SessionFactory buildSessionFactory() {

        Configuration cfg = new Configuration();
        sessionFactory = cfg.configure("hibernate.cfg.xml")
                .buildSessionFactory(new StandardServiceRegistryBuilder()
                        .applySettings(toMap(cfg.getProperties())).build());

        session = sessionFactory.getCurrentSession();

        return sessionFactory;
    }

    private static Map toMap(Properties properties) {
        Map map = new HashMap<>();
        for (String name : properties.stringPropertyNames()) {
            map.put(name, properties.getProperty(name));
        }
        return map;
    }

    public static Session getSession() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            if (!session.isOpen()) {
                session = sessionFactory.getCurrentSession();
            }
            return session;
        }
        throw new ExceptionInInitializerError("SessionFactory não criado ou está fechado. Deverá ser criado novamente através da chamada do método buildSessionFactory");
    }


    public static Transaction getTransaction() {
        return getSession().getTransaction();
    }

    public static void closeSession() {
        if (getSession().isOpen()) {
            getSession().close();
        }
    }

    public static void beginTransaction() {
        if (!getTransaction().isActive()) {
            getTransaction().begin();
        }
    }

    public static void commitTransaction() {
        if (getTransaction().isActive()) {
            getTransaction().commit();
        }
    }

    public static void rollbackTransaction() {
        if (getTransaction().isActive()) {
            getTransaction().rollback();
        }
    }

    public static Criteria criteriaRange(Criteria criteria, int firstResult, int maxResults) {
        if (criteria != null) {
            if (maxResults >= 0) {
                criteria.setMaxResults(maxResults);
            }
            if (firstResult >= 0) {
                criteria.setFirstResult(firstResult);
            }
        }
        return criteria;
    }

}
