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
package br.com.thiaguten.persistence.provider.jpa;

import br.com.thiaguten.persistence.Persistable;
import br.com.thiaguten.persistence.provider.PersistenceProvider;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * JPA implementation of the PersistenceProvider.
 *
 * @author Thiago Gutenberg
 */
public abstract class JPAPersistenceProvider implements PersistenceProvider {

    /**
     * Get entity manager
     *
     * @return entity manager
     */
    public abstract EntityManager getEntityManager();

    /**
     * Find an entity by its primary key.
     *
     * @param entityClazz the entity class
     * @param pk          the primary key
     * @return the entity
     */
    @Override
    public <T extends Persistable<? extends Serializable>, PK extends Serializable> T findById(Class<T> entityClazz, PK pk) {
        return getEntityManager().find(entityClazz, pk);
    }

    /**
     * Load all entities.
     *
     * @param entityClazz the entity class
     * @return the list of entities
     */
    @Override
    public <T extends Persistable<? extends Serializable>> List<T> findAll(Class<T> entityClazz) {
        return findAll(entityClazz, -1, -1);
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
        CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder().createQuery(entityClazz);
        TypedQuery<T> createQuery = getEntityManager().createQuery(cq.select(cq.from(entityClazz)));
        return JPAUtils.queryRange(createQuery, firstResult, maxResults).getResultList();
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
        TypedQuery<T> typedQuery = getEntityManager().createNamedQuery(queryName, entityClazz);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                typedQuery.setParameter(i + 1, params[i]);
            }
        }
        return typedQuery.getResultList();
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
        TypedQuery<T> typedQuery = getEntityManager().createNamedQuery(queryName, entityClazz);
        if (params != null) {
            for (final Map.Entry<String, ?> param : params.entrySet()) {
                typedQuery.setParameter(param.getKey(), param.getValue());
            }
        }
        return typedQuery.getResultList();
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
        TypedQuery<T> typedQuery = getEntityManager().createQuery(query, entityClazz);
        if (params != null) {
            for (final Map.Entry<String, ?> param : params.entrySet()) {
                typedQuery.setParameter(param.getKey(), param.getValue());
            }
        }
        return typedQuery.getResultList();
    }

    /**
     * Count all entities.
     *
     * @param entityClazz the entity class
     * @return the number of entities
     */
    @Override
    public <T extends Persistable<? extends Serializable>> long countAll(Class<T> entityClazz) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(entityClazz)));
        return getEntityManager().createQuery(criteriaQuery).getSingleResult();
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
        TypedQuery<T> query = getEntityManager().createNamedQuery(queryName, resultClazz);
        if (params != null && !params.isEmpty()) {
            for (final Map.Entry<String, ?> param : params.entrySet()) {
                query.setParameter(param.getKey(), param.getValue());
            }
        }
        return query.getSingleResult();
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
        TypedQuery<T> typedQuery = getEntityManager().createQuery(query, resultClazz);
        if (params != null && !params.isEmpty()) {
            for (final Map.Entry<String, ?> param : params.entrySet()) {
                typedQuery.setParameter(param.getKey(), param.getValue());
            }
        }
        return typedQuery.getSingleResult();
    }

    /**
     * Save an entity.
     *
     * @param entity the entity to save
     * @return the saved entity
     */
    @Override
    public <T extends Persistable<? extends Serializable>> T save(T entity) {
        if (entity.getId() != null) {
            entity = getEntityManager().merge(entity);
        } else {
            getEntityManager().persist(entity);
        }
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
        return save(entity);
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

    @SuppressWarnings("unchecked")
    private <T extends Persistable<? extends Serializable>, PK extends Serializable> void deleteByEntityOrId(Class<T> entityClazz, T entity, PK pk) {
        if (pk == null && (entity == null || entity.getId() == null)) {
            throw new PersistenceException("Não foi possível deletar. ID não informado");
        }

        PK id = pk;
        if (id == null) {
            id = (PK) entity.getId();
        }
//        T t = findById(entityClazz, id); // throws exception: entity must be managed to call remove: try merging the detached and try the remove again.
        T t = getEntityManager().getReference(entityClazz, id);
        getEntityManager().remove(t);
    }
}
