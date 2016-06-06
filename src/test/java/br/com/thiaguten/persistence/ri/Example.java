/*
 * #%L
 * %%
 * Copyright (C) 2015 - 2016 Thiago Gutenberg Carvalho da Costa.
 * %%
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * 3. Neither the name of the Thiago Gutenberg Carvalho da Costa. nor the names of its contributors
 *    may be used to endorse or promote products derived from this software without
 *    specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */
package br.com.thiaguten.persistence.ri;

import br.com.thiaguten.persistence.core.BasePersistence;
import br.com.thiaguten.persistence.core.Persistable;
import br.com.thiaguten.persistence.core.Persistence;
import br.com.thiaguten.persistence.spi.PersistenceProvider;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Reference implementation.
 */
public class Example {

    public static void main(String[] args) {

        final User user = new User("Thiago Gutenberg");
        final PersistenceProvider myPersistenceProvider = new MyPersistenceProvider();

        System.out.println("Concrete class implementation approach:\n");
        final UserDAO userDAO = new UserDAOImpl(myPersistenceProvider);

        userDAO.create(user);
        userDAO.update(user);
        userDAO.delete(user);
        userDAO.customOperation(user);

        System.out.println("\nInner class implementation approach:\n");
        final Persistence<Long, User> persistence = new BasePersistence<Long, User>() {
            @Override
            public PersistenceProvider getPersistenceProvider() {
                return myPersistenceProvider;
            }
        };

        persistence.create(user);
        persistence.update(user);
        persistence.delete(user);

    }

    private static final class User implements Persistable<Long> {

        private Long id;
        private String name;

        public User(String name) {
            this.name = name;
        }

        @Override
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{ name=" + name + " }";
        }
    }

    public interface UserDAO extends Persistence<Long, User> {

        void customOperation(User user);
    }

    private static final class UserDAOImpl extends BasePersistence<Long, User> implements UserDAO {

        private final PersistenceProvider persistenceProvider;

        public UserDAOImpl(PersistenceProvider persistenceProvider) {
            this.persistenceProvider = persistenceProvider;
        }

        @Override
        public PersistenceProvider getPersistenceProvider() {
            return persistenceProvider;
        }

        @Override
        public void customOperation(User user) {
            System.out.println("Running custom operation " + user);
        }
    }

    private static final class MyPersistenceProvider implements PersistenceProvider {

        @Override
        public <ID extends Serializable, T extends Persistable<ID>> T findById(Class<T> entityClazz, ID id) {
            throw new UnsupportedOperationException("Operation not supported");
        }

        @Override
        public <ID extends Serializable, T extends Persistable<ID>> List<T> findAll(Class<T> entityClazz) {
            throw new UnsupportedOperationException("Operation not supported");
        }

        @Override
        public <ID extends Serializable, T extends Persistable<ID>> List<T> findAll(Class<T> entityClazz, int firstResult, int maxResults) {
            throw new UnsupportedOperationException("Operation not supported");
        }

        @Override
        public <ID extends Serializable, T extends Persistable<ID>> List<T> findByNamedQuery(Class<T> entityClazz, String queryName, Object... params) {
            throw new UnsupportedOperationException("Operation not supported");
        }

        @Override
        public <ID extends Serializable, T extends Persistable<ID>> List<T> findByNamedQueryAndNamedParams(Class<T> entityClazz, String queryName, Map<String, ?> params) {
            throw new UnsupportedOperationException("Operation not supported");
        }

        @Override
        public <ID extends Serializable, T extends Persistable<ID>> List<T> findByQueryAndNamedParams(Class<T> entityClazz, String query, Map<String, ?> params) {
            throw new UnsupportedOperationException("Operation not supported");
        }

        @Override
        public <ID extends Serializable, T extends Persistable<ID>> long countAll(Class<T> entityClazz) {
            throw new UnsupportedOperationException("Operation not supported");
        }

        @Override
        public <T extends Number> T countByNamedQueryAndNamedParams(Class<T> resultClazz, String queryName, Map<String, ?> params) {
            throw new UnsupportedOperationException("Operation not supported");
        }

        @Override
        public <T extends Number> T countByQueryAndNamedParams(Class<T> resultClazz, String query, Map<String, ?> params) {
            throw new UnsupportedOperationException("Operation not supported");
        }

        @Override
        public <ID extends Serializable, T extends Persistable<ID>> T save(T entity) {
            System.out.println("Saving " + entity);
            return entity;
        }

        @Override
        public <ID extends Serializable, T extends Persistable<ID>> T update(T entity) {
            System.out.println("Updating " + entity);
            return entity;
        }

        @Override
        public <ID extends Serializable, T extends Persistable<ID>> void delete(Class<T> entityClazz, T entity) {
            System.out.println("Deleting " + entity);
        }

        @Override
        public <ID extends Serializable, T extends Persistable<ID>> void deleteById(Class<T> entityClazz, ID id) {
            throw new UnsupportedOperationException("Operation not supported");
        }
    }

}
