package br.com.thiaguten.persistence.dao;

import br.com.thiaguten.persistence.Persistable;
import br.com.thiaguten.persistence.provider.PersistenceProvider;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract class GenericBaseDAO<T extends Persistable<?>, PK extends Serializable> implements BaseDAO<T, PK> {

    private final Class<T> persistentClass;
    private final Class<PK> primaryKeyClass;

    @SuppressWarnings("unchecked")
    public GenericBaseDAO() {
        java.lang.reflect.ParameterizedType genericSuperClass = (java.lang.reflect.ParameterizedType) getClass().getGenericSuperclass();
        this.persistentClass = (Class<T>) genericSuperClass.getActualTypeArguments()[0];
        this.primaryKeyClass = (Class<PK>) genericSuperClass.getActualTypeArguments()[1];
    }

    @Override
    public abstract PersistenceProvider getPersistenceProvider();

    @Override
    public Class<T> getEntityClass() {
        return persistentClass;
    }

    @Override
    public Class<PK> getPrimaryKeyClass() {
        return primaryKeyClass;
    }

    @Override
    public T findById(PK id) {
        return getPersistenceProvider().findById(persistentClass, id);
    }

    @Override
    public List<T> findAll() {
        return getPersistenceProvider().findAll(persistentClass);
    }

    @Override
    public List<T> find(int firstResult, int maxResults) {
        return getPersistenceProvider().findAll(persistentClass, firstResult, maxResults);
    }

    @Override
    public List<T> findByNamedQuery(String queryName, Object... params) {
        return getPersistenceProvider().findByNamedQuery(persistentClass, queryName, params);
    }

    @Override
    public List<T> findByNamedQueryAndNamedParams(String queryName, Map<String, ?> params) {
        return getPersistenceProvider().findByNamedQueryAndNamedParams(persistentClass, queryName, params);
    }

    @Override
    public long countAll() {
        return getPersistenceProvider().countAll(persistentClass);
    }

    @Override
    public T save(T entity) {
        return getPersistenceProvider().save(entity);
    }

    @Override
    public T update(T entity) {
        return getPersistenceProvider().update(entity);
    }

    @Override
    public void delete(T entity) {
        getPersistenceProvider().delete(persistentClass, entity);
    }

    @Override
    public void deleteById(PK id) {
        getPersistenceProvider().deleteById(persistentClass, id);
    }

}
