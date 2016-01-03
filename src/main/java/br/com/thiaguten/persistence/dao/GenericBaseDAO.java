/*
 * #%L
 * %%
 * Copyright (C) 2015 - 2016 Thiago Gutenberg Carvalho da Costa.
 * %%
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * 3. Neither the name of the Thiago Gutenberg Carvalho da Costa. nor the names of its contributors
 *    may be used to endorse or promote products derived from this software without
 *    specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 * #L%
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
