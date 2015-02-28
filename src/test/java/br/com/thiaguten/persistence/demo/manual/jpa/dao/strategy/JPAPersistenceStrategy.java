package br.com.thiaguten.persistence.demo.manual.jpa.dao.strategy;

import br.com.thiaguten.persistence.Persistable;
import br.com.thiaguten.persistence.provider.jpa.JPAPersistenceProvider;
import br.com.thiaguten.persistence.provider.jpa.JPAUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class JPAPersistenceStrategy extends JPAPersistenceProvider {

    static {
        JPAUtils.createEntityManagerFactory("jpaPU");
    }

    @Override
    public EntityManager getEntityManager() {
        return JPAUtils.getEntityManager();
    }

    @Override
    public <T extends Persistable<? extends Serializable>, PK extends Serializable> void deleteById(Class<T> entityClazz, PK id) {
        try {
            JPAUtils.beginTransaction();
            super.deleteById(entityClazz, id);
            JPAUtils.commitTransaction();
        } catch (Exception e) {
            JPAUtils.rollbackTransaction();
            throw new PersistenceException("Erro ao deletar: " + e.getLocalizedMessage());
        } finally {
            JPAUtils.closeEntityManager();
        }
    }

    @Override
    public <T extends Persistable<? extends Serializable>> void delete(Class<T> entityClazz, T entity) {
        try {
            JPAUtils.beginTransaction();
            super.delete(entityClazz, entity);
            JPAUtils.commitTransaction();
        } catch (Exception e) {
            JPAUtils.rollbackTransaction();
            throw new PersistenceException("Erro ao deletar: " + e.getLocalizedMessage());
        } finally {
            JPAUtils.closeEntityManager();
        }
    }

    @Override
    public <T extends Persistable<? extends Serializable>> T update(T entity) {
        return this.save(entity);
    }

    @Override
    public <T extends Persistable<? extends Serializable>> T save(T entity) {
        try {
            JPAUtils.beginTransaction();
            T t = super.save(entity);
            JPAUtils.commitTransaction();
            return t;
        } catch (Exception e) {
            JPAUtils.rollbackTransaction();
            throw new PersistenceException("Erro ao salvar/atualizar: " + e.getLocalizedMessage());
        } finally {
            JPAUtils.closeEntityManager();
        }
    }

    @Override
    public <T extends Persistable<? extends Serializable>> long countAll(Class<T> entityClazz) {
        try {
            return super.countAll(entityClazz);
        } finally {
            JPAUtils.closeEntityManager();
        }
    }

    @Override
    public <T extends Persistable<? extends Serializable>> List<T> findByNamedQueryAndNamedParams(Class<T> entityClazz, String queryName, Map<String, ?> params) {
        try {
            return super.findByNamedQueryAndNamedParams(entityClazz, queryName, params);
        } finally {
            JPAUtils.closeEntityManager();
        }
    }

    @Override
    public <T extends Persistable<? extends Serializable>> List<T> findByNamedQuery(Class<T> entityClazz, String queryName, Object... params) {
        try {
            return super.findByNamedQuery(entityClazz, queryName, params);
        } finally {
            JPAUtils.closeEntityManager();
        }
    }

    @Override
    public <T extends Persistable<? extends Serializable>> List<T> findAll(Class<T> entityClazz, int firstResult, int maxResults) {
        try {
            return super.findAll(entityClazz, firstResult, maxResults);
        } finally {
            JPAUtils.closeEntityManager();
        }
    }

    @Override
    public <T extends Persistable<? extends Serializable>> List<T> findAll(Class<T> entityClazz) {
        try {
            return super.findAll(entityClazz);
        } finally {
            JPAUtils.closeEntityManager();
        }
    }

    @Override
    public <T extends Persistable<? extends Serializable>, PK extends Serializable> T findById(Class<T> entityClazz, PK id) {
        try {
            return super.findById(entityClazz, id);
        } finally {
            JPAUtils.closeEntityManager();
        }
    }

    @Override
    public <T extends Persistable<? extends Serializable>> List<T> findByQueryAndNamedParams(Class<T> entityClazz, String query, Map<String, ?> params) {
        try {
            return super.findByQueryAndNamedParams(entityClazz, query, params);
        } finally {
            JPAUtils.closeEntityManager();
        }
    }

    @Override
    public <T extends Number> T countByNamedQueryAndNamedParams(Class<T> resultClazz, String queryName, Map<String, ?> params) {
        try {
            return super.countByNamedQueryAndNamedParams(resultClazz, queryName, params);
        } finally {
            JPAUtils.closeEntityManager();
        }
    }

    @Override
    public <T extends Number> T countByQueryAndNamedParams(Class<T> resultClazz, String query, Map<String, ?> params) {
        try {
            return super.countByQueryAndNamedParams(resultClazz, query, params);
        } finally {
            JPAUtils.closeEntityManager();
        }
    }
}
