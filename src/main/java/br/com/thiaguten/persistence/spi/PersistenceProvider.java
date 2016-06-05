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

import br.com.thiaguten.persistence.core.Persistable;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Define operation for a persistence provider implementation.
 *
 * @author Thiago Gutenberg Carvalho da Costa
 */
public interface PersistenceProvider {

    /**
     * Find an entity by its identifier.
     *
     * @param <T>         the type of the entity
     * @param <ID>        the type of the identifier
     * @param entityClazz the entity class
     * @param id          the entity identifier
     * @return the entity
     */
    <ID extends Serializable, T extends Persistable<ID>> T findById(
            final Class<T> entityClazz,
            final ID id);

    /**
     * Load all entities.
     *
     * @param entityClazz the entity class
     * @param <ID>        the type of the identifier
     * @param <T>         the type of the entity
     * @return the list of entities
     */
    <ID extends Serializable, T extends Persistable<ID>> List<T> findAll(
            final Class<T> entityClazz);

    /**
     * Load entities.
     *
     * @param entityClazz the entity class
     * @param firstResult the value of first result
     * @param maxResults  the value of max result
     * @param <ID>        the type of the identifier
     * @param <T>         the type of the entity
     * @return the list of entities
     */
    <ID extends Serializable, T extends Persistable<ID>> List<T> findAll(
            final Class<T> entityClazz,
            final int firstResult,
            final int maxResults);

    /**
     * Find by named query.
     *
     * @param entityClazz the entity class
     * @param queryName   the name of the query
     * @param params      the query parameters
     * @param <ID>        the type of the identifier
     * @param <T>         the type of the entity
     * @return the list of entities
     */
    <ID extends Serializable, T extends Persistable<ID>> List<T> findByNamedQuery(
            final Class<T> entityClazz,
            final String queryName,
            final Object... params
    );

    /**
     * Find by named query.
     *
     * @param entityClazz the entity class
     * @param queryName   the name of the query
     * @param params      the query parameters
     * @param <ID>        the type of the identifier
     * @param <T>         the type of the entity
     * @return the list of entities
     */
    <ID extends Serializable, T extends Persistable<ID>> List<T> findByNamedQueryAndNamedParams(
            final Class<T> entityClazz,
            final String queryName,
            final Map<String, ?> params
    );

    /**
     * Find by query (SQL/JPQL/HQL...) and parameters.
     *
     * @param entityClazz the entity class
     * @param query       the typed query
     * @param params      the typed query parameters
     * @param <ID>        the type of the identifier
     * @param <T>         the type of the entity
     * @return the list of entities
     */
    <ID extends Serializable, T extends Persistable<ID>> List<T> findByQueryAndNamedParams(
            final Class<T> entityClazz,
            final String query,
            final Map<String, ?> params);

    /**
     * Count all entities.
     *
     * @param entityClazz the entity class
     * @param <ID>        the type of the identifier
     * @param <T>         the type of the entity
     * @return the number of entities
     */
    <ID extends Serializable, T extends Persistable<ID>> long countAll(
            final Class<T> entityClazz);

    /**
     * Count by named query and parameters.
     *
     * @param <T>         the type of the entity
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
     * Count by query (SQL/JPQL/HQL...) and parameters.
     *
     * @param <T>         the type of the entity
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
     * @param entity the entity to be saved
     * @param <ID>   the type of the identifier
     * @param <T>    the type of the entity
     * @return the saved entity
     */
    <ID extends Serializable, T extends Persistable<ID>> T save(
            final T entity);

    /**
     * Update an entity.
     *
     * @param entity the entity to be updated
     * @param <ID>   the type of the identifier
     * @param <T>    the type of the entity
     * @return the updated entity
     */
    <ID extends Serializable, T extends Persistable<ID>> T update(
            final T entity);

    /**
     * Delete an entity.
     *
     * @param entityClazz the entity class
     * @param entity      the entity to be deleted
     * @param <ID>        the type of the identifier
     * @param <T>         the type of the entity
     */
    <ID extends Serializable, T extends Persistable<ID>> void delete(
            final Class<T> entityClazz, final T entity);

    /**
     * Delete an entity by its identifier.
     *
     * @param <T>         the type of the entity
     * @param <ID>        the type of the identifier
     * @param entityClazz the entity class
     * @param id          the entity identifier to be deleted
     */
    <ID extends Serializable, T extends Persistable<ID>> void deleteById(
            final Class<T> entityClazz, final ID id);
}
