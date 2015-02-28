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
package br.com.thiaguten.persistence.demo.manual.jpa.dao.strategy;

import br.com.thiaguten.persistence.Persistable;
import br.com.thiaguten.persistence.provider.jpa.JPAPersistenceProvider;
import br.com.thiaguten.persistence.provider.jpa.JPAUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * JPA persistence strategy
 *
 * @author Thiago Gutenberg
 */
public class JPAPersistenceStrategy extends JPAPersistenceProvider {

    static {
        JPAUtils.createEntityManagerFactory("jpaPU");
    }

    @Override
    public EntityManager getEntityManager() {
        return JPAUtils.getEntityManager();
    }

    @Override
    public <T extends Persistable<? extends Serializable>, PK extends Serializable> void deleteById(Class<T> entityClazz, PK pk) {
        try {
            JPAUtils.beginTransaction();
            super.deleteById(entityClazz, pk);
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
    public <T extends Persistable<? extends Serializable>, PK extends Serializable> T findById(Class<T> entityClazz, PK pk) {
        try {
            return super.findById(entityClazz, pk);
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
