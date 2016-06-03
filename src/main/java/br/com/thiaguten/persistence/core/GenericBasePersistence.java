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
 * Generic Base Persistence class that makes delegation calls features
 * implemented by a specific persistence provider.
 *
 * @param <T>  the type of the persistent class
 * @param <ID> the type of the identifier
 * @author Thiago Gutenberg Carvalho da Costa
 */
public abstract class GenericBasePersistence<ID extends Serializable, T extends Persistable<ID>> implements BasePersistence<ID, T> {

    private final Class<T> persistenceClass;
    private final Class<ID> identifierClass;

    /**
     * Construct a GenericBasePersistence.
     */
    @SuppressWarnings("unchecked")
    public GenericBasePersistence() {
        java.lang.reflect.ParameterizedType genericSuperClass = (java.lang.reflect.ParameterizedType) getClass().getGenericSuperclass();
        this.identifierClass = (Class<ID>) genericSuperClass.getActualTypeArguments()[0];
        this.persistenceClass = (Class<T>) genericSuperClass.getActualTypeArguments()[1];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract PersistenceProvider getPersistenceProvider();

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<T> getPersistenceClass() {
        return persistenceClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<ID> getIdentifierClass() {
        return identifierClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T create(T entity) {
        return getPersistenceProvider().save(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T read(ID id) {
        return getPersistenceProvider().findById(persistenceClass, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T update(T entity) {
        return getPersistenceProvider().update(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(T entity) {
        getPersistenceProvider().delete(persistenceClass, entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(ID id) {
        getPersistenceProvider().deleteById(persistenceClass, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> findAll() {
        return getPersistenceProvider().findAll(persistenceClass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> findAll(int firstResult, int maxResults) {
        return getPersistenceProvider().findAll(persistenceClass, firstResult, maxResults);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> findByNamedQuery(String queryName, Object... params) {
        return getPersistenceProvider().findByNamedQuery(persistenceClass, queryName, params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> findByNamedQueryAndNamedParams(String queryName, Map<String, ?> params) {
        return getPersistenceProvider().findByNamedQueryAndNamedParams(persistenceClass, queryName, params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long countAll() {
        return getPersistenceProvider().countAll(persistenceClass);
    }

}
