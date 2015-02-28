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
 * Hibernate implementation of the PersistenceProvider
 */
public abstract class HibernatePersistenceProvider implements PersistenceProvider {

    protected abstract Session getSession();

    @Override
    public <T extends Persistable<? extends Serializable>, PK extends Serializable> T findById(Class<T> entityClazz, PK id) {
        return (T) getSession().get(entityClazz, id);
    }

    @Override
    public <T extends Persistable<? extends Serializable>> List<T> findAll(Class<T> entityClazz) {
        return findByCriteria(entityClazz, null);
    }

    @Override
    public <T extends Persistable<? extends Serializable>> List<T> findAll(Class<T> entityClazz, int firstResult, int maxResults) {
        return findByCriteria(entityClazz, null, firstResult, maxResults);
    }

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

    @Override
    public <T extends Persistable<? extends Serializable>> long countAll(Class<T> entityClazz) {
        return countByCriteria(entityClazz, Long.class, null);
    }

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

    @Override
    public <T extends Persistable<? extends Serializable>> T save(T entity) {
        Serializable id = getSession().save(entity);
        entity = (T) findById(entity.getClass(), id);
        return entity;
    }

    @Override
    public <T extends Persistable<? extends Serializable>> T update(T entity) {
        getSession().update(entity);
        return null;
    }

    @Override
    public <T extends Persistable<? extends Serializable>> void delete(Class<T> entityClazz, T entity) {
        deleteByEntityOrId(entityClazz, entity, null);
    }

    @Override
    public <T extends Persistable<? extends Serializable>, PK extends Serializable> void deleteById(Class<T> entityClazz, PK id) {
        deleteByEntityOrId(entityClazz, null, id);
    }

    @SuppressWarnings("unchecked")
    private <T extends Persistable<? extends Serializable>, PK extends Serializable> void deleteByEntityOrId(Class<T> entityClazz, T entity, PK id) {
        if (id == null && (entity == null || entity.getId() == null)) {
            throw new HibernateException("Não foi possível deletar. ID não informado");
        }

        T t;
        if (id != null) {
            t = findById(entityClazz, id);
        } else {
            t = entity;
        }
        getSession().delete(t);
    }

    public <T extends Persistable<? extends Serializable>> List<T> findByCriteria(Class<T> entityClazz, List<Criterion> criterions) {
        return findByCriteria(entityClazz, criterions, -1, -1);
    }

    public <T extends Persistable<? extends Serializable>> List<T> findByCriteria(Class<T> entityClazz, List<Criterion> criterions, int firstResult, int maxResults) {
        return findByCriteria(entityClazz, criterions, false, firstResult, maxResults);
    }

    public <T extends Persistable<? extends Serializable>> List<T> findByCriteria(Class<T> entityClazz, List<Criterion> criterions, boolean cacheable, int firstResult, int maxResults) {
        Criteria criteria = getSession().createCriteria(entityClazz);
        if (criterions != null) {
            for (Criterion c : criterions) {
                criteria.add(c);
            }
        }
        return HibernateUtils.criteriaRange(criteria, firstResult, maxResults).setCacheable(cacheable).list();
    }

    public <T extends Persistable<? extends Serializable>> T findUniqueResultByCriteria(Class<T> entityClazz, List<Criterion> criterions) {
        return findUniqueResultByCriteria(entityClazz, criterions, false);
    }

    public <T extends Persistable<? extends Serializable>> T findUniqueResultByCriteria(Class<T> entityClazz, List<Criterion> criterions, boolean cacheable) {
        Criteria criteria = getSession().createCriteria(entityClazz);
        if (criterions != null) {
            for (Criterion c : criterions) {
                criteria.add(c);
            }
        }
        return (T) criteria.setCacheable(cacheable).uniqueResult();
    }

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
}
