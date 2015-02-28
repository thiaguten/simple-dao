package br.com.thiaguten.persistence.demo.spring.hibernate.dao.service;

import br.com.thiaguten.persistence.Persistable;
import br.com.thiaguten.persistence.provider.hibernate.HibernatePersistenceProvider;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Transactional(readOnly = true)
@Service("hibernatePersistenceService")
public class HibernatePersistenceService extends HibernatePersistenceProvider {

    private SessionFactory sessionFactory;

    @Override
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public <T extends Persistable<? extends Serializable>> T save(T entity) {
        return super.save(entity);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public <T extends Persistable<? extends Serializable>> T update(T entity) {
        return super.update(entity);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public <T extends Persistable<? extends Serializable>> void delete(Class<T> entityClazz, T entity) {
        super.delete(entityClazz, entity);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public <T extends Persistable<? extends Serializable>, PK extends Serializable> void deleteById(Class<T> entityClazz, PK pk) {
        super.deleteById(entityClazz, pk);
    }

    @Override
    public <T extends Persistable<? extends Serializable>, PK extends Serializable> T findById(Class<T> entityClazz, PK pk) {
        return super.findById(entityClazz, pk);
    }

    @Override
    public <T extends Persistable<? extends Serializable>> List<T> findAll(Class<T> entityClazz) {
        return super.findAll(entityClazz);
    }

    @Override
    public <T extends Persistable<? extends Serializable>> List<T> findAll(Class<T> entityClazz, int firstResult, int maxResults) {
        return super.findAll(entityClazz, firstResult, maxResults);
    }

    @Override
    public <T extends Persistable<? extends Serializable>> List<T> findByNamedQuery(Class<T> entityClazz, String queryName, Object... params) {
        return super.findByNamedQuery(entityClazz, queryName, params);
    }

    @Override
    public <T extends Persistable<? extends Serializable>> List<T> findByNamedQueryAndNamedParams(Class<T> entityClazz, String queryName, Map<String, ?> params) {
        return super.findByNamedQueryAndNamedParams(entityClazz, queryName, params);
    }

    @Override
    public <T extends Persistable<? extends Serializable>> List<T> findByQueryAndNamedParams(Class<T> entityClazz, String query, Map<String, ?> params) {
        return super.findByQueryAndNamedParams(entityClazz, query, params);
    }

    @Override
    public <T extends Persistable<? extends Serializable>> long countAll(Class<T> entityClazz) {
        return super.countAll(entityClazz);
    }

    @Override
    public <T extends Number> T countByNamedQueryAndNamedParams(Class<T> resultClazz, String queryName, Map<String, ?> params) {
        return super.countByNamedQueryAndNamedParams(resultClazz, queryName, params);
    }

    @Override
    public <T extends Number> T countByQueryAndNamedParams(Class<T> resultClazz, String query, Map<String, ?> params) {
        return super.countByQueryAndNamedParams(resultClazz, query, params);
    }

    ///////

    @Override
    public <T extends Persistable<? extends Serializable>> List<T> findByCriteria(Class<T> entityClazz, List<Criterion> criterions) {
        return super.findByCriteria(entityClazz, criterions);
    }

    @Override
    public <T extends Persistable<? extends Serializable>> List<T> findByCriteria(Class<T> entityClazz, List<Criterion> criterions, int firstResult, int maxResults) {
        return super.findByCriteria(entityClazz, criterions, firstResult, maxResults);
    }

    @Override
    public <T extends Persistable<? extends Serializable>> List<T> findByCriteria(Class<T> entityClazz, List<Criterion> criterions, boolean cacheable, int firstResult, int maxResults) {
        return super.findByCriteria(entityClazz, criterions, cacheable, firstResult, maxResults);
    }

    @Override
    public <T extends Persistable<? extends Serializable>> T findUniqueResultByCriteria(Class<T> entityClazz, List<Criterion> criterions) {
        return super.findUniqueResultByCriteria(entityClazz, criterions);
    }

    @Override
    public <T extends Persistable<? extends Serializable>> T findUniqueResultByCriteria(Class<T> entityClazz, List<Criterion> criterions, boolean cacheable) {
        return super.findUniqueResultByCriteria(entityClazz, criterions, cacheable);
    }

    @Override
    public <T extends Persistable<? extends Serializable>, N extends Number> N countByCriteria(Class<T> entityClazz, Class<N> resultClazz, List<Criterion> criterions) {
        return super.countByCriteria(entityClazz, resultClazz, criterions);
    }
}
