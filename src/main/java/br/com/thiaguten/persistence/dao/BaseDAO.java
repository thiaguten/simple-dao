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
