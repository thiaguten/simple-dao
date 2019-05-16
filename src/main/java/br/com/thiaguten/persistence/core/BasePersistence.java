/*
 * #%L
 * %%
 * Copyright (C) 2015 - 2016 Thiago Gutenberg Carvalho da Costa.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
public abstract class BasePersistence<ID extends Serializable, T extends Persistable<ID>> implements Persistence<ID, T> {

    private final Class<T> persistenceClass;
    private final Class<ID> identifierClass;

    /**
     * Construct a BasePersistence.
     */
    @SuppressWarnings("unchecked")
    public BasePersistence() {
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
    public List<T> findByQuery(String query, Object... params) {
    	return getPersistenceProvider().findByQuery(persistenceClass, query, params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> findByQueryAndNamedParams(String query, Map<String, ?> params) {
    	return getPersistenceProvider().findByQueryAndNamedParams(persistenceClass, query, params);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public long countAll() {
        return getPersistenceProvider().countAll(persistenceClass);
    }

}
