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
 * This is a generic DAO he saw delegation calls features implemented by a specific persistence provider.
 *
 * @param <T>  entity
 * @param <PK> primary key
 * @author Thiago Gutenberg
 */
public abstract class GenericBaseDAO<T extends Persistable<?>, PK extends Serializable> implements BaseDAO<T, PK> {

    private final Class<T> persistentClass;
    private final Class<PK> primaryKeyClass;

    /**
     * Construct a GenericBaseDAO.
     */
    @SuppressWarnings("unchecked")
    public GenericBaseDAO() {
        java.lang.reflect.ParameterizedType genericSuperClass = (java.lang.reflect.ParameterizedType) getClass().getGenericSuperclass();
        this.persistentClass = (Class<T>) genericSuperClass.getActualTypeArguments()[0];
        this.primaryKeyClass = (Class<PK>) genericSuperClass.getActualTypeArguments()[1];
    }

    /**
     * Get a persistence provider (Jpa, Hibernate, etc).
     *
     * @return the persistence provider implementation
     */
    @Override
    public abstract PersistenceProvider getPersistenceProvider();

    /**
     * Get an entity class.
     *
     * @return entity class
     */
    @Override
    public Class<T> getEntityClass() {
        return persistentClass;
    }

    /**
     * Get an entity primary key.
     *
     * @return entity primary key
     */
    @Override
    public Class<PK> getPrimaryKeyClass() {
        return primaryKeyClass;
    }

    /**
     * Find an entity by its primary key.
     *
     * @param pk the primary key
     * @return the entity
     */
    @Override
    public T findById(PK pk) {
        return getPersistenceProvider().findById(persistentClass, pk);
    }

    /**
     * Load all entities.
     *
     * @return the list of entities
     */
    @Override
    public List<T> findAll() {
        return getPersistenceProvider().findAll(persistentClass);
    }

    /**
     * Load entites.
     *
     * @param firstResult first result
     * @param maxResults  max results
     * @return the list of entities
     */
    @Override
    public List<T> findAll(int firstResult, int maxResults) {
        return getPersistenceProvider().findAll(persistentClass, firstResult, maxResults);
    }

    /**
     * Find by named query.
     *
     * @param queryName the name of the query
     * @param params    the query parameters
     * @return the list of entities
     */
    @Override
    public List<T> findByNamedQuery(String queryName, Object... params) {
        return getPersistenceProvider().findByNamedQuery(persistentClass, queryName, params);
    }

    /**
     * Find by named query.
     *
     * @param queryName the name of the query
     * @param params    the query parameters
     * @return the list of entities
     */
    @Override
    public List<T> findByNamedQueryAndNamedParams(String queryName, Map<String, ?> params) {
        return getPersistenceProvider().findByNamedQueryAndNamedParams(persistentClass, queryName, params);
    }

    /**
     * Count all entities.
     *
     * @return the number of entities
     */
    @Override
    public long countAll() {
        return getPersistenceProvider().countAll(persistentClass);
    }

    /**
     * Save an entity.
     *
     * @param entity the entity to save
     * @return the saved entity
     */
    @Override
    public T save(T entity) {
        return getPersistenceProvider().save(entity);
    }

    /**
     * Update an entity.
     *
     * @param entity the entity to update
     * @return the updated entity
     */
    @Override
    public T update(T entity) {
        return getPersistenceProvider().update(entity);
    }

    /**
     * Delete an entity.
     *
     * @param entity the entity to delete
     */
    @Override
    public void delete(T entity) {
        getPersistenceProvider().delete(persistentClass, entity);
    }

    /**
     * Delete an entity by pk.
     *
     * @param pk primary key of the entity to delete
     */
    @Override
    public void deleteById(PK pk) {
        getPersistenceProvider().deleteById(persistentClass, pk);
    }
}
