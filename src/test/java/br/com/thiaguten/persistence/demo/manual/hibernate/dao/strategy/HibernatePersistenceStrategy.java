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
package br.com.thiaguten.persistence.demo.manual.hibernate.dao.strategy;

import br.com.thiaguten.persistence.Persistable;
import br.com.thiaguten.persistence.provider.hibernate.HibernatePersistenceProvider;
import br.com.thiaguten.persistence.provider.hibernate.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Hibernate persistence strategy
 *
 * @author Thiago Gutenberg
 */
public class HibernatePersistenceStrategy extends HibernatePersistenceProvider {

    static {
        HibernateUtils.buildSessionFactory();
    }

    @Override
    public Session getSession() {
        return HibernateUtils.getSession();
    }

//    @Override
//    public <T extends Persistable<? extends Serializable>> List<T> findByCriteria(Class<T> entityClazz, List<Criterion> criterions) {
//        try {
//            return super.findByCriteria(entityClazz, criterions);
//        } finally {
//            HibernateUtils.closeSession();
//        }
//    }
//
//    @Override
//    public <T extends Persistable<? extends Serializable>> List<T> findByCriteria(Class<T> entityClazz, List<Criterion> criterions, int firstResult, int maxResults) {
//        try {
//            return super.findByCriteria(entityClazz, criterions, firstResult, maxResults);
//        } finally {
//            HibernateUtils.closeSession();
//        }
//    }
//
//    @Override
//    public <T extends Persistable<? extends Serializable>> List<T> findByCriteria(Class<T> entityClazz, List<Criterion> criterions, boolean cacheable, int firstResult, int maxResults) {
//        try {
//            return super.findByCriteria(entityClazz, criterions, cacheable, firstResult, maxResults);
//        } finally {
//            HibernateUtils.closeSession();
//        }
//    }
//
//    @Override
//    public <T extends Persistable<? extends Serializable>> T findUniqueResultByCriteria(Class<T> entityClazz, List<Criterion> criterions) {
//        try {
//            return super.findUniqueResultByCriteria(entityClazz, criterions);
//        } finally {
//            HibernateUtils.closeSession();
//        }
//    }
//
//    @Override
//    public <T extends Persistable<? extends Serializable>> T findUniqueResultByCriteria(Class<T> entityClazz, List<Criterion> criterions, boolean cacheable) {
//        try {
//            return super.findUniqueResultByCriteria(entityClazz, criterions, cacheable);
//        } finally {
//            HibernateUtils.closeSession();
//        }
//    }
//
//    @Override
//    public <T extends Persistable<? extends Serializable>, N extends Number> N countByCriteria(Class<T> entityClazz, Class<N> resultClazz, List<Criterion> criterions) {
//        try {
//            return super.countByCriteria(entityClazz, resultClazz, criterions);
//        } finally {
//            HibernateUtils.closeSession();
//        }
//    }

    @Override
    public <T extends Persistable<? extends Serializable>, PK extends Serializable> T findById(Class<T> entityClazz, PK pk) {
        T t = null;
        try {
            HibernateUtils.beginTransaction();
            t = super.findById(entityClazz, pk);
            HibernateUtils.commitTransaction();
        } catch (Exception e) {
            HibernateUtils.rollbackTransaction();
            throw new HibernateException("Erro ao obter: " + e.getLocalizedMessage());
        } finally {
            HibernateUtils.closeSession();
        }
        return t;
    }

    @Override
    public <T extends Persistable<? extends Serializable>> List<T> findAll(Class<T> entityClazz) {
        try {
            return super.findAll(entityClazz);
        } finally {
            HibernateUtils.closeSession();
        }
    }

    @Override
    public <T extends Persistable<? extends Serializable>> List<T> findAll(Class<T> entityClazz, int firstResult, int maxResults) {
        try {
            return super.findAll(entityClazz, firstResult, maxResults);
        } finally {
            HibernateUtils.closeSession();
        }
    }

    @Override
    public <T extends Persistable<? extends Serializable>> List<T> findByNamedQuery(Class<T> entityClazz, String queryName, Object... params) {
        try {
            return super.findByNamedQuery(entityClazz, queryName, params);
        } finally {
            HibernateUtils.closeSession();
        }
    }

    @Override
    public <T extends Persistable<? extends Serializable>> List<T> findByNamedQueryAndNamedParams(Class<T> entityClazz, String queryName, Map<String, ?> params) {
        try {
            return super.findByNamedQueryAndNamedParams(entityClazz, queryName, params);
        } finally {
            HibernateUtils.closeSession();
        }
    }

    @Override
    public <T extends Persistable<? extends Serializable>> List<T> findByQueryAndNamedParams(Class<T> entityClazz, String query, Map<String, ?> params) {
        try {
            return super.findByQueryAndNamedParams(entityClazz, query, params);
        } finally {
            HibernateUtils.closeSession();
        }
    }

    @Override
    public <T extends Persistable<? extends Serializable>> long countAll(Class<T> entityClazz) {
        try {
            return super.countAll(entityClazz);
        } finally {
            HibernateUtils.closeSession();
        }
    }

    @Override
    public <T extends Number> T countByNamedQueryAndNamedParams(Class<T> resultClazz, String queryName, Map<String, ?> params) {
        try {
            return super.countByNamedQueryAndNamedParams(resultClazz, queryName, params);
        } finally {
            HibernateUtils.closeSession();
        }
    }

    @Override
    public <T extends Number> T countByQueryAndNamedParams(Class<T> resultClazz, String query, Map<String, ?> params) {
        try {
            return super.countByQueryAndNamedParams(resultClazz, query, params);
        } finally {
            HibernateUtils.closeSession();
        }
    }

    @Override
    public <T extends Persistable<? extends Serializable>> T save(T entity) {
        T t = null;
        try {
            HibernateUtils.beginTransaction();
            t = super.save(entity);
            HibernateUtils.commitTransaction();
        } catch (Exception e) {
            HibernateUtils.rollbackTransaction();
            throw new HibernateException("Erro ao salvar: " + e.getLocalizedMessage());
        } finally {
            HibernateUtils.closeSession();
        }
        return t;
    }

    @Override
    public <T extends Persistable<? extends Serializable>> T update(T entity) {
        T t = null;
        try {
            HibernateUtils.beginTransaction();
            t = super.update(entity);
            HibernateUtils.commitTransaction();
        } catch (Exception e) {
            HibernateUtils.rollbackTransaction();
            throw new HibernateException("Erro ao atualizar: " + e.getLocalizedMessage());
        } finally {
            HibernateUtils.closeSession();
        }
        return t;
    }

    @Override
    public <T extends Persistable<? extends Serializable>> void delete(Class<T> entityClazz, T entity) {
        try {
            HibernateUtils.beginTransaction();
            super.delete(entityClazz, entity);
            HibernateUtils.commitTransaction();
        } catch (Exception e) {
            HibernateUtils.rollbackTransaction();
            throw new HibernateException("Erro ao deletar: " + e.getLocalizedMessage());
        } finally {
            HibernateUtils.closeSession();
        }
    }

    @Override
    public <T extends Persistable<? extends Serializable>, PK extends Serializable> void deleteById(Class<T> entityClazz, PK id) {
        try {
            HibernateUtils.beginTransaction();
            super.deleteById(entityClazz, id);
            HibernateUtils.commitTransaction();
        } catch (Exception e) {
            HibernateUtils.rollbackTransaction();
            throw new HibernateException("Erro ao deletar: " + e.getLocalizedMessage());
        } finally {
            HibernateUtils.closeSession();
        }
    }

    @Override
    public <T extends Persistable<? extends Serializable>, N extends Number> N countByCriteria(Class<T> entityClazz, Class<N> resultClazz, Criterion... criterions) {
        try {
            return super.countByCriteria(entityClazz, resultClazz, criterions);
        } finally {
            HibernateUtils.closeSession();
        }
    }

    @Override
    public <T extends Persistable<? extends Serializable>> List<T> findByCriteria(Class<T> entityClazz, Criterion... criterions) {
        try {
            return super.findByCriteria(entityClazz, criterions);
        } finally {
            HibernateUtils.closeSession();
        }
    }

    @Override
    public <T extends Persistable<? extends Serializable>> List<T> findByCriteria(Class<T> entityClazz, int firstResult, int maxResults, Criterion... criterions) {
        try {
            return super.findByCriteria(entityClazz, firstResult, maxResults, criterions);
        } finally {
            HibernateUtils.closeSession();
        }
    }

    @Override
    public <T extends Persistable<? extends Serializable>> List<T> findByCriteria(Class<T> entityClazz, boolean cacheable, int firstResult, int maxResults, Criterion... criterions) {
        try {
            return super.findByCriteria(entityClazz, cacheable, firstResult, maxResults, criterions);
        } finally {
            HibernateUtils.closeSession();
        }
    }

    @Override
    public <T extends Persistable<? extends Serializable>> T findUniqueResultByCriteria(Class<T> entityClazz, Criterion... criterions) {
        try {
            return super.findUniqueResultByCriteria(entityClazz, criterions);
        } finally {
            HibernateUtils.closeSession();
        }
    }

    @Override
    public <T extends Persistable<? extends Serializable>> T findUniqueResultByCriteria(Class<T> entityClazz, boolean cacheable, Criterion... criterions) {
        try {
            return super.findUniqueResultByCriteria(entityClazz, cacheable, criterions);
        } finally {
            HibernateUtils.closeSession();
        }
    }
}
