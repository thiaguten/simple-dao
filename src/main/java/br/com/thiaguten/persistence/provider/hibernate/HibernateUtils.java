/**
 * Copyright 2015 Thiago Gutenberg Carvalho da Costa
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.thiaguten.persistence.provider.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
//import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Utility class to create a sesson factory and a session.
 *
 * @author Thiago Gutenberg
 */
public final class HibernateUtils {

    private static Session session;
    private static SessionFactory sessionFactory;

    private HibernateUtils() {
        // suppress default constructor
        // for noninstantiability
        throw new AssertionError();
    }

    /**
     * Build session factory with hibernate resource ("hibernate.cfg.xml")
     *
     * @return session factory
     */
    public static SessionFactory buildSessionFactory() {
        return buildSessionFactory("hibernate.cfg.xml");
    }

    /**
     * Build session factory with the specified hibernate resource
     *
     * @param resource The hibernate resource to use
     * @return session factory
     */
    public static SessionFactory buildSessionFactory(String resource) {
        Configuration cfg = new Configuration();
        sessionFactory = cfg.configure(resource)
                .buildSessionFactory(new StandardServiceRegistryBuilder()
                        .applySettings(toMap(cfg.getProperties())).build());

        session = sessionFactory.getCurrentSession();
        return sessionFactory;
    }

    private static Map toMap(Properties properties) {
        Map map = new HashMap();
        for (String name : properties.stringPropertyNames()) {
            map.put(name, properties.getProperty(name));
        }
        return map;
    }

    /**
     * Get session
     *
     * @return session
     */
    public static Session getSession() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            if (!session.isOpen()) {
                session = sessionFactory.getCurrentSession();
            }
            return session;
        }
        throw new ExceptionInInitializerError("SessionFactory not created or is closed. Should be re-created through the method buildSessionFactory");
    }

    /**
     * Get session transaction
     *
     * @return session transaction
     */
    public static Transaction getTransaction() {
        return getSession().getTransaction();
    }

    /**
     * Close session
     */
    public static void closeSession() {
        if (getSession().isOpen()) {
            getSession().close();
        }
    }

    /**
     * Begin transaction
     */
    public static void beginTransaction() {
        if (!isTransactionActive()) {
            getTransaction().begin();
        }
    }

    /**
     * Commit transaction
     */
    public static void commitTransaction() {
        if (isTransactionActive()) {
            getTransaction().commit();
        }
    }

    /**
     * Rollback transaction
     */
    public static void rollbackTransaction() {
        if (isTransactionActive()) {
            getTransaction().rollback();
        }
    }

    /**
     * Checks if a transaction is active
     *
     * @return true if the transaction is active, otherwise false
     */
    public static boolean isTransactionActive() {
        return getTransaction().isActive(); // hibernate 4 approach
//        return getTransaction().getStatus().isOneOf(TransactionStatus.ACTIVE); // hibernate 5 approach
    }

    /**
     * Apply criteria range
     *
     * @param criteria    criteriaQuery
     * @param firstResult first result
     * @param maxResults  max result
     * @return criteriaQuery
     */
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
