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
package br.com.thiaguten.persistence.provider.hibernate;

import br.com.thiaguten.persistence.Persistable;
import br.com.thiaguten.persistence.provider.PersistenceProvider;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Hibernate implementation of the PersistenceProvider.
 *
 * @author Thiago Gutenberg
 */
public abstract class HibernatePersistenceProvider implements PersistenceProvider {

    /**
     * Get session
     *
     * @return session
     */
    public abstract Session getSession();

    /**
     * Find an entity by its primary key.
     *
     * @param entityClazz the entity class
     * @param pk          the primary key
     * @return the entity
     */
    @Override
    public <T extends Persistable<? extends Serializable>, PK extends Serializable> T findById(Class<T> entityClazz, PK pk) {
        return (T) getSession().get(entityClazz, pk);
    }

    /**
     * Load all entities.
     *
     * @param entityClazz the entity class
     * @return the list of entities
     */
    @Override
    public <T extends Persistable<? extends Serializable>> List<T> findAll(Class<T> entityClazz) {
        return findByCriteria(entityClazz, null);
    }

    /**
     * Load entities.
     *
     * @param entityClazz the entity class
     * @param firstResult the value of first result
     * @param maxResults  the value of max result
     * @return the list of entities
     */
    @Override
    public <T extends Persistable<? extends Serializable>> List<T> findAll(Class<T> entityClazz, int firstResult, int maxResults) {
        return findByCriteria(entityClazz, null, firstResult, maxResults);
    }

    /**
     * Find by named query.
     *
     * @param entityClazz the entity class
     * @param queryName   the name of the query
     * @param params      the query parameters
     * @return the list of entities
     */
    @Override
    public <T extends Persistable<? extends Serializable>> List<T> findByNamedQuery(Class<T> entityClazz, String queryName, Object... params) {
        Query hibernateQuery = getSession().getNamedQuery(queryName);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                hibernateQuery.setParameter(i + 1, params[i]);
            }
        }
        return hibernateQuery.list();
    }

    /**
     * Find by named query.
     *
     * @param entityClazz the entity class
     * @param queryName   the name of the query
     * @param params      the query parameters
     * @return the list of entities
     */
    @Override
    public <T extends Persistable<? extends Serializable>> List<T> findByNamedQueryAndNamedParams(Class<T> entityClazz, String queryName, Map<String, ?> params) {
        Query hibernateQuery = getSession().getNamedQuery(queryName);
        if (params != null && !params.isEmpty()) {
            for (final Map.Entry<String, ?> param : params.entrySet()) {
                hibernateQuery.setParameter(param.getKey(), param.getValue());
            }
        }
        return hibernateQuery.list();
    }

    /**
     * Find by query (JPQL/HQL, etc) and parameters.
     *
     * @param entityClazz the entity class
     * @param query       the typed query
     * @param params      the typed query parameters
     * @return the list of entities
     */
    @Override
    public <T extends Persistable<? extends Serializable>> List<T> findByQueryAndNamedParams(Class<T> entityClazz, String query, Map<String, ?> params) {
        Query hibernateQuery = getSession().createQuery(query);
        if (params != null && !params.isEmpty()) {
            for (final Map.Entry<String, ?> param : params.entrySet()) {
                hibernateQuery.setParameter(param.getKey(), param.getValue());
            }
        }
        return hibernateQuery.list();
    }

    /**
     * Count all entities.
     *
     * @param entityClazz the entity class
     * @return the number of entities
     */
    @Override
    public <T extends Persistable<? extends Serializable>> long countAll(Class<T> entityClazz) {
        return countByCriteria(entityClazz, Long.class, null);
    }

    /**
     * Count by named query and parameters.
     *
     * @param resultClazz the number class
     * @param queryName   the named query
     * @param params      the named query parameters
     * @return the count of entities
     */
    @Override
    public <T extends Number> T countByNamedQueryAndNamedParams(Class<T> resultClazz, String queryName, Map<String, ?> params) {
        Query hibernateQuery = getSession().getNamedQuery(queryName);
        if (params != null && !params.isEmpty()) {
            for (final Map.Entry<String, ?> param : params.entrySet()) {
                hibernateQuery.setParameter(param.getKey(), param.getValue());
            }
        }
        return (T) hibernateQuery.uniqueResult();
    }

    /**
     * Count by (JPQL/HQL, etc) and parameters.
     *
     * @param resultClazz the number class
     * @param query       the typed query
     * @param params      the typed query parameters
     * @return the count of entities
     */
    @Override
    public <T extends Number> T countByQueryAndNamedParams(Class<T> resultClazz, String query, Map<String, ?> params) {
        Query hibernateQuery = getSession().createQuery(query);
        if (params != null && !params.isEmpty()) {
            for (final Map.Entry<String, ?> param : params.entrySet()) {
                hibernateQuery.setParameter(param.getKey(), param.getValue());
            }
        }
        return (T) hibernateQuery.uniqueResult();
    }

    /**
     * Save an entity.
     *
     * @param entity the entity to save
     * @return the saved entity
     */
    @Override
    public <T extends Persistable<? extends Serializable>> T save(T entity) {
        Serializable id = getSession().save(entity);
        entity = (T) findById(entity.getClass(), id);
        return entity;
    }

    /**
     * Update an entity.
     *
     * @param entity the entity to update
     * @return the updated entity
     */
    @Override
    public <T extends Persistable<? extends Serializable>> T update(T entity) {
        getSession().update(entity);
        return null;
    }

    /**
     * Delete an entity.
     *
     * @param entityClazz the entity class
     * @param entity      the entity to delete
     */
    @Override
    public <T extends Persistable<? extends Serializable>> void delete(Class<T> entityClazz, T entity) {
        deleteByEntityOrId(entityClazz, entity, null);
    }

    /**
     * Delete an entity.
     *
     * @param entityClazz the entity class
     * @param pk          primary key of the entity to delete
     */
    @Override
    public <T extends Persistable<? extends Serializable>, PK extends Serializable> void deleteById(Class<T> entityClazz, PK pk) {
        deleteByEntityOrId(entityClazz, null, pk);
    }

    /**
     * Find by criteria
     *
     * @param entityClazz the entity class
     * @param criterions  the criterions
     * @param <T>         entity
     * @return the list of entities
     */
    public <T extends Persistable<? extends Serializable>> List<T> findByCriteria(Class<T> entityClazz, List<Criterion> criterions) {
        return findByCriteria(entityClazz, criterions, -1, -1);
    }

    /**
     * Find by criteria
     *
     * @param entityClazz the entity class
     * @param criterions  the criterions
     * @param firstResult first result
     * @param maxResults  max result
     * @param <T>         entity
     * @return the list of entities
     */
    public <T extends Persistable<? extends Serializable>> List<T> findByCriteria(Class<T> entityClazz, List<Criterion> criterions, int firstResult, int maxResults) {
        return findByCriteria(entityClazz, criterions, false, firstResult, maxResults);
    }

    /**
     * Find by criteria
     *
     * @param entityClazz the entity class
     * @param criterions  the criterions
     * @param cacheable   cacheable
     * @param firstResult first result
     * @param maxResults  max result
     * @param <T>         entity
     * @return the list of entities
     */
    public <T extends Persistable<? extends Serializable>> List<T> findByCriteria(Class<T> entityClazz, List<Criterion> criterions, boolean cacheable, int firstResult, int maxResults) {
        Criteria criteria = getSession().createCriteria(entityClazz);
        if (criterions != null) {
            for (Criterion c : criterions) {
                criteria.add(c);
            }
        }
        return HibernateUtils.criteriaRange(criteria, firstResult, maxResults).setCacheable(cacheable).list();
    }

    /**
     * Find unique result by criteria
     *
     * @param entityClazz the entity class
     * @param criterions  the criterions
     * @param <T>         entity
     * @return the entity
     */
    public <T extends Persistable<? extends Serializable>> T findUniqueResultByCriteria(Class<T> entityClazz, List<Criterion> criterions) {
        return findUniqueResultByCriteria(entityClazz, criterions, false);
    }

    /**
     * Find unique result by criteria
     *
     * @param entityClazz the entity class
     * @param criterions  the criterions
     * @param cacheable   cacheable
     * @param <T>         entity
     * @return the entity
     */
    public <T extends Persistable<? extends Serializable>> T findUniqueResultByCriteria(Class<T> entityClazz, List<Criterion> criterions, boolean cacheable) {
        Criteria criteria = getSession().createCriteria(entityClazz);
        if (criterions != null) {
            for (Criterion c : criterions) {
                criteria.add(c);
            }
        }
        return (T) criteria.setCacheable(cacheable).uniqueResult();
    }

    /**
     * Count by criteria
     *
     * @param entityClazz the entity class
     * @param resultClazz the result class
     * @param criterions  the criterions
     * @param <T>         entity
     * @param <N>         number pojo
     * @return the count
     */
    public <T extends Persistable<? extends Serializable>, N extends Number> N countByCriteria(Class<T> entityClazz, Class<N> resultClazz, List<Criterion> criterions) {
        Criteria criteria = getSession().createCriteria(entityClazz);
        criteria.setProjection(Projections.rowCount());
        if (criterions != null) {
            for (Criterion c : criterions) {
                criteria.add(c);
            }
        }
        return (N) criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    private <T extends Persistable<? extends Serializable>, PK extends Serializable> void deleteByEntityOrId(Class<T> entityClazz, T entity, PK pk) {
        if (pk == null && (entity == null || entity.getId() == null)) {
            throw new HibernateException("Could not delete. ID is null.");
        }

        T t;
        if (pk != null) {
            t = findById(entityClazz, pk);
        } else {
            t = entity;
        }
        getSession().delete(t);
    }
}
