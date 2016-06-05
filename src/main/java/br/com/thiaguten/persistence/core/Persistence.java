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
package br.com.thiaguten.persistence.core;

import br.com.thiaguten.persistence.spi.PersistenceProvider;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Base class that provides basic CRUD operations.
 *
 * @param <T>  the type of the persistent class
 * @param <ID> the type of the identifier
 * @author Thiago Gutenberg Carvalho da Costa
 */
public interface Persistence<ID extends Serializable, T extends Persistable<ID>> {

    /**
     * Get a persistence provider.
     *
     * @return the persistence provider implementation
     */
    PersistenceProvider getPersistenceProvider();

    /**
     * Get a persistence class.
     *
     * @return the persistence class
     */
    Class<T> getPersistenceClass();

    /**
     * Get an the identifier class.
     *
     * @return the identifier class
     */
    Class<ID> getIdentifierClass();

    /**
     * Create an entity.
     *
     * @param entity entity to be created
     * @return the created entity
     */
    T create(final T entity);

    /**
     * Read an entity by its identifier.
     *
     * @param id entity identifier to be read
     * @return the entity
     */
    T read(final ID id);

    /**
     * Update an entity.
     *
     * @param entity entity to be updated
     * @return the updated entity
     */
    T update(final T entity);

    /**
     * Delete an entity.
     *
     * @param entity entity to be deleted
     */
    void delete(final T entity);

    /**
     * Delete an entity by its identifier.
     *
     * @param id entity identifier to be deleted
     */
    void deleteById(final ID id);


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

}
