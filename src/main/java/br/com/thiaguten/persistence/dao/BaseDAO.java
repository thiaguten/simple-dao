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

package br.com.thiaguten.persistence.dao;

import br.com.thiaguten.persistence.Persistable;
import br.com.thiaguten.persistence.provider.PersistenceProvider;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Generic class, providing basic CRUD operations.
 *
 * @param <T>  entity
 * @param <PK> primary key
 * @author Thiago Gutenberg
 */
public interface BaseDAO<T extends Persistable<?>, PK extends Serializable> {

    /**
     * Get an entity class.
     *
     * @return entity class
     */
    Class<T> getEntityClass();

    /**
     * Get an entity primary key.
     *
     * @return entity primary key
     */
    Class<PK> getPrimaryKeyClass();

    /**
     * Get a persistence provider (Jpa, Hibernate, etc).
     *
     * @return the persistence provider implementation
     */
    PersistenceProvider getPersistenceProvider();

    /**
     * Find an entity by its primary key.
     *
     * @param pk the primary key
     * @return the entity
     */
    T findById(final PK pk);

    /**
     * Load all entities.
     *
     * @return the list of entities
     */
    List<T> findAll();

    /**
     * Load entites.
     *
     * @param firstResult first result
     * @param maxResults  max results
     * @return the list of entities
     */
    List<T> findAll(final int firstResult, final int maxResults);

    /**
     * Find by named query.
     *
     * @param queryName the name of the query
     * @param params    the query parameters
     * @return the list of entities
     */
    List<T> findByNamedQuery(
            final String queryName,
            Object... params
    );

    /**
     * Find by named query.
     *
     * @param queryName the name of the query
     * @param params    the query parameters
     * @return the list of entities
     */
    List<T> findByNamedQueryAndNamedParams(
            final String queryName,
            final Map<String, ?> params
    );

    /**
     * Count all entities.
     *
     * @return the number of entities
     */
    long countAll();

    /**
     * Save an entity.
     *
     * @param entity the entity to save
     * @return the saved entity
     */
    T save(final T entity);

    /**
     * Update an entity.
     *
     * @param entity the entity to update
     * @return the updated entity
     */
    T update(final T entity);

    /**
     * Delete an entity.
     *
     * @param entity the entity to delete
     */
    void delete(final T entity);

    /**
     * Delete an entity by pk.
     *
     * @param pk primary key of the entity to delete
     */
    void deleteById(final PK pk);

}
