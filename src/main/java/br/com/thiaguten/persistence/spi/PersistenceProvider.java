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
package br.com.thiaguten.persistence.spi;

import br.com.thiaguten.persistence.Persistable;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Define operation for a persistence provider implementation.
 *
 * @author Thiago Gutenberg
 */
public interface PersistenceProvider {

    /**
     * Find an entity by its primary key.
     *
     * @param <T>         the entity
     * @param <PK>        the primary key
     * @param entityClazz the entity class
     * @param pk          the primary key
     * @return the entity
     */
    <T extends Persistable<? extends Serializable>, PK extends Serializable> T findById(
            final Class<T> entityClazz,
            final PK pk);

    /**
     * Load all entities.
     *
     * @param <T>         the entity
     * @param entityClazz the entity class
     * @return the list of entities
     */
    <T extends Persistable<? extends Serializable>> List<T> findAll(final Class<T> entityClazz);

    /**
     * Load entities.
     *
     * @param <T>         the entity
     * @param entityClazz the entity class
     * @param firstResult the value of first result
     * @param maxResults  the value of max result
     * @return the list of entities
     */
    <T extends Persistable<? extends Serializable>> List<T> findAll(
            final Class<T> entityClazz,
            final int firstResult,
            final int maxResults);

    /**
     * Find by named query.
     *
     * @param <T>         the entity
     * @param entityClazz the entity class
     * @param queryName   the name of the query
     * @param params      the query parameters
     * @return the list of entities
     */
    <T extends Persistable<? extends Serializable>> List<T> findByNamedQuery(
            final Class<T> entityClazz,
            final String queryName,
            final Object... params
    );

    /**
     * Find by named query.
     *
     * @param <T>         the entity
     * @param entityClazz the entity class
     * @param queryName   the name of the query
     * @param params      the query parameters
     * @return the list of entities
     */
    <T extends Persistable<? extends Serializable>> List<T> findByNamedQueryAndNamedParams(
            final Class<T> entityClazz,
            final String queryName,
            final Map<String, ?> params
    );

    /**
     * Find by query (JPQL/HQL, etc) and parameters.
     *
     * @param <T>         the entity
     * @param entityClazz the entity class
     * @param query       the typed query
     * @param params      the typed query parameters
     * @return the list of entities
     */
    <T extends Persistable<? extends Serializable>> List<T> findByQueryAndNamedParams(
            final Class<T> entityClazz,
            final String query,
            final Map<String, ?> params);

    /**
     * Count all entities.
     *
     * @param <T>         the entity
     * @param entityClazz the entity class
     * @return the number of entities
     */
    <T extends Persistable<? extends Serializable>> long countAll(final Class<T> entityClazz);

    /**
     * Count by named query and parameters.
     *
     * @param <T>         the persistable pojo
     * @param resultClazz the number class
     * @param queryName   the named query
     * @param params      the named query parameters
     * @return the count of entities
     */
    <T extends Number> T countByNamedQueryAndNamedParams(
            final Class<T> resultClazz,
            final String queryName,
            final Map<String, ?> params);

    /**
     * Count by (JPQL/HQL, etc) and parameters.
     *
     * @param <T>         the number pojo
     * @param resultClazz the number class
     * @param query       the typed query
     * @param params      the typed query parameters
     * @return the count of entities
     */
    <T extends Number> T countByQueryAndNamedParams(
            final Class<T> resultClazz,
            final String query,
            final Map<String, ?> params);

    /**
     * Save an entity.
     *
     * @param <T>    the entity
     * @param entity the entity to save
     * @return the saved entity
     */
    <T extends Persistable<? extends Serializable>> T save(final T entity);

    /**
     * Update an entity.
     *
     * @param <T>    the entity
     * @param entity the entity to update
     * @return the updated entity
     */
    <T extends Persistable<? extends Serializable>> T update(final T entity);

    /**
     * Delete an entity.
     *
     * @param <T>         the entity
     * @param entityClazz the entity class
     * @param entity      the entity to delete
     */
    <T extends Persistable<? extends Serializable>> void delete(final Class<T> entityClazz, final T entity);

    /**
     * Delete an entity.
     *
     * @param <T>         the entity
     * @param <PK>        the primary key
     * @param entityClazz the entity class
     * @param pk          primary key of the entity to delete
     */
    <T extends Persistable<? extends Serializable>, PK extends Serializable> void deleteById(final Class<T> entityClazz, final PK pk);
}
