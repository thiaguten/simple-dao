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
 * JPA implementation of the PersistenceProvider
 */
public abstract class JPAPersistenceProvider implements PersistenceProvider {

    public abstract EntityManager getEntityManager();

    @Override
    public <T extends Persistable<? extends Serializable>> T save(T entity) {
        if (entity.getId() != null) {
            entity = getEntityManager().merge(entity);
        } else {
            getEntityManager().persist(entity);
        }
        return entity;
    }

    @Override
    public <T extends Persistable<? extends Serializable>> T update(T entity) {
        return save(entity);
    }

    @Override
    public <T extends Persistable<? extends Serializable>> void delete(Class<T> entityClazz, T entity) {
        deleteByEntityOrId(entityClazz, entity, null);
    }

    @Override
    public <T extends Persistable<? extends Serializable>, PK extends Serializable> void deleteById(Class<T> entityClazz, PK id) {
        deleteByEntityOrId(entityClazz, null, id);
    }

    @Override
    public <T extends Persistable<? extends Serializable>, PK extends Serializable> T findById(Class<T> entityClazz, PK id) {
        return getEntityManager().find(entityClazz, id);
    }

    @Override
    public <T extends Persistable<? extends Serializable>> List<T> findAll(Class<T> entityClazz) {
        return findAll(entityClazz, -1, -1);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Persistable<? extends Serializable>> List<T> findAll(Class<T> entityClazz, int firstResult, final int maxResults) {
        CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder().createQuery(entityClazz);
        TypedQuery<T> createQuery = getEntityManager().createQuery(cq.select(cq.from(entityClazz)));
        return JPAUtils.queryRange(createQuery, firstResult, maxResults).getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Persistable<? extends Serializable>> List<T> findByNamedQuery(Class<T> entityClazz, String queryName, Object... params) {
        TypedQuery<T> typedQuery = getEntityManager().createNamedQuery(queryName, entityClazz);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                typedQuery.setParameter(i + 1, params[i]);
            }
        }
        return typedQuery.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Persistable<? extends Serializable>> List<T> findByNamedQueryAndNamedParams(Class<T> entityClazz, String queryName, Map<String, ?> params) {
        TypedQuery<T> typedQuery = getEntityManager().createNamedQuery(queryName, entityClazz);
        if (params != null) {
            for (final Map.Entry<String, ?> param : params.entrySet()) {
                typedQuery.setParameter(param.getKey(), param.getValue());
            }
        }
        return typedQuery.getResultList();
    }

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

    @Override
    public <T extends Persistable<? extends Serializable>> long countAll(Class<T> entityClazz) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(entityClazz)));
        return getEntityManager().createQuery(criteriaQuery).getSingleResult();
    }

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

    @SuppressWarnings("unchecked")
    private <T extends Persistable<? extends Serializable>, PK extends Serializable> void deleteByEntityOrId(Class<T> entityClazz, T entity, PK id) {
        if (id == null && (entity == null || entity.getId() == null)) {
            throw new PersistenceException("Não foi possível deletar. ID não informado");
        }

        PK pk = id;
        if (pk == null) {
            pk = (PK) entity.getId();
        }
//        T t = findById(entityClazz, pk); //Lanca a excecao: entity must be managed to call remove: try merging the detached and try the remove again.
        T t = getEntityManager().getReference(entityClazz, pk);
        getEntityManager().remove(t);
    }

}
