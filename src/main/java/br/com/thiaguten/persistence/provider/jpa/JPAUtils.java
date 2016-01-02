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
package br.com.thiaguten.persistence.provider.jpa;

import br.com.thiaguten.persistence.Persistable;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Utility class to create an entity manager factory and an entity manager.
 *
 * @author Thiago Gutenberg
 */
public final class JPAUtils {

    private static EntityManagerFactory emf;

    @PersistenceContext
    private static EntityManager em;

    private JPAUtils() {
        // suppress default constructor
        // for noninstantiability
        throw new AssertionError();
    }

    /**
     * Create entity manager factory
     *
     * @param persistenceUnitName persistence unit name
     * @return entity manager factory
     */
    public static EntityManagerFactory createEntityManagerFactory(String persistenceUnitName) {
        emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        em = emf.createEntityManager();
        return emf;
    }

    /**
     * Get entity manager
     *
     * @return entity manager
     */
    public static EntityManager getEntityManager() {
        if (emf != null && emf.isOpen()) {
            if (!em.isOpen()) {
                em = emf.createEntityManager();
            }
            return em;
        }
        throw new ExceptionInInitializerError("EntityManagerFactory not created or is closed. Should be re-created through the method createEntityManagerFactory");

    }

    /**
     * Get entity manager transaction
     *
     * @return entity manager transaction
     */
    public static EntityTransaction getTransaction() {
        return getEntityManager().getTransaction();
    }

    /**
     * Close entity manager
     */
    public static void closeEntityManager() {
        if (getEntityManager().isOpen()) {
            getEntityManager().close();
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
     * Apply typed query range
     *
     * @param query       query
     * @param firstResult first result
     * @param maxResults  max result
     * @param <T>         entity
     * @return typed query
     */
    public static <T extends Persistable<? extends Serializable>> TypedQuery<T> queryRange(TypedQuery<T> query, int firstResult, int maxResults) {
        if (query != null) {
            if (maxResults >= 0) {
                query.setMaxResults(maxResults);
            }
            if (firstResult >= 0) {
                query.setFirstResult(firstResult);
            }
        }
        return query;
    }
}
