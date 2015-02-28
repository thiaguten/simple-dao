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

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Utility class to create a sesson factory and a session.
 *
 * @author Thiago Gutenberg
 */
public abstract class HibernateUtils {

    private static Session session;
    private static SessionFactory sessionFactory;

    /**
     * Build session factory
     *
     * @return session factory
     */
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
        throw new ExceptionInInitializerError("SessionFactory não criado ou está fechado. Deverá ser criado novamente através da chamada do método buildSessionFactory");
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
        if (!getTransaction().isActive()) {
            getTransaction().begin();
        }
    }

    /**
     * Commit transaction
     */
    public static void commitTransaction() {
        if (getTransaction().isActive()) {
            getTransaction().commit();
        }
    }

    /**
     * Rollback transaction
     */
    public static void rollbackTransaction() {
        if (getTransaction().isActive()) {
            getTransaction().rollback();
        }
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
