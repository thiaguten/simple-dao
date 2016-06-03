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

import br.com.thiaguten.persistence.Persistable;
import br.com.thiaguten.persistence.dao.BaseDAO;
import br.com.thiaguten.persistence.dao.GenericBaseDAO;
import br.com.thiaguten.persistence.entity.BaseEntity;
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

        userDAO.save(user);
        userDAO.update(user);
        userDAO.delete(user);
        userDAO.customOperation(user);

        System.out.println("\nInner class implementation approach:\n");
        final BaseDAO<User, Long> baseDAO = new GenericBaseDAO<User, Long>() {
            @Override
            public PersistenceProvider getPersistenceProvider() {
                return myPersistenceProvider;
            }
        };

        baseDAO.save(user);
        baseDAO.update(user);
        baseDAO.delete(user);

//        System.out.println("\nJava 8 function class with concrete class implementation approach:\n");
//        BaseDAOFuntion<User, UserDAO> concreteBaseDAOFuntion = (User u) -> userDAO;
//
//        UserDAO concreteUserDAO = daoFor(user, concreteBaseDAOFuntion);
//
//        concreteUserDAO.save(user);
//        concreteUserDAO.update(user);
//        concreteUserDAO.delete(user);
//        concreteUserDAO.customOperation(user);
//
//        System.out.println("\nJava 8 function class with inner class implementation approach:\n");
//        BaseDAOFuntion<User, BaseDAO<User, Long>> baseDAOFuntion = (User u) -> baseDAO;
//
//        BaseDAO<User, Long> genericBaseDAO = daoFor(user, baseDAOFuntion);
//
//        genericBaseDAO.save(user);
//        genericBaseDAO.update(user);
//        genericBaseDAO.delete(user);

    }

//    @FunctionalInterface
//    interface BaseDAOFuntion<T extends Persistable<? extends Serializable>, R extends BaseDAO<T, ? extends Serializable>> {
//        R applyToBaseDAO(T t);
//    }
//
//    private static final <T extends Persistable<? extends Serializable>, R extends BaseDAO<T, ? extends Serializable>> R daoFor(T t, Function<T, R> f) {
//        return f.apply(t);
//    }
//
//    private static final <T extends Persistable<? extends Serializable>, R extends BaseDAO<T, ? extends Serializable>> R daoFor(T t, BaseDAOFuntion<T, R> f) {
//        return f.applyToBaseDAO(t);
//    }

    /**
     * Persistable class.
     *
     * @author Thiago Gutenberg Carvalho da Costa
     */
    private static final class User extends BaseEntity<Long> {

        private Long id;
        private String name;

        public User(String name) {
            this.name = name;
        }

        // for clone proposes
        public User(User user) {
            this.id = user.getId();
            this.name = user.getName();
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

    /**
     * Optional User DAO interface to declare custom operations.
     *
     * @author Thiago Gutenberg Carvalho da Costa
     */
    private interface UserDAO extends BaseDAO<User, Long> {

        void customOperation(User user);
    }

    /**
     * User DAO Implementation.
     *
     * @author Thiago Gutenberg Carvalho da Costa
     */
    private static final class UserDAOImpl extends GenericBaseDAO<User, Long> implements UserDAO {

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

    /**
     * My persistence provider implementation.
     *
     * @author Thiago Gutenberg Carvalho da Costa
     */
    private static final class MyPersistenceProvider implements PersistenceProvider {

        @Override
        public <T extends Persistable<? extends Serializable>, PK extends Serializable> T findById(Class<T> entityClazz, PK pk) {
            throw new UnsupportedOperationException("Operation not supported");
        }

        @Override
        public <T extends Persistable<? extends Serializable>> List<T> findAll(Class<T> entityClazz) {
            throw new UnsupportedOperationException("Operation not supported");
        }

        @Override
        public <T extends Persistable<? extends Serializable>> List<T> findAll(Class<T> entityClazz, int firstResult, int maxResults) {
            throw new UnsupportedOperationException("Operation not supported");
        }

        @Override
        public <T extends Persistable<? extends Serializable>> List<T> findByNamedQuery(Class<T> entityClazz, String queryName, Object... params) {
            throw new UnsupportedOperationException("Operation not supported");
        }

        @Override
        public <T extends Persistable<? extends Serializable>> List<T> findByNamedQueryAndNamedParams(Class<T> entityClazz, String queryName, Map<String, ?> params) {
            throw new UnsupportedOperationException("Operation not supported");
        }

        @Override
        public <T extends Persistable<? extends Serializable>> List<T> findByQueryAndNamedParams(Class<T> entityClazz, String query, Map<String, ?> params) {
            throw new UnsupportedOperationException("Operation not supported");
        }

        @Override
        public <T extends Persistable<? extends Serializable>> long countAll(Class<T> entityClazz) {
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
        public <T extends Persistable<? extends Serializable>> T save(T entity) {
            System.out.println("Saving " + entity);
            return entity;
        }

        @Override
        public <T extends Persistable<? extends Serializable>> T update(T entity) {
            System.out.println("Updating " + entity);
            return entity;
        }

        @Override
        public <T extends Persistable<? extends Serializable>> void delete(Class<T> entityClazz, T entity) {
            System.out.println("Deleting " + entity);
        }

        @Override
        public <T extends Persistable<? extends Serializable>, PK extends Serializable> void deleteById(Class<T> entityClazz, PK pk) {
            throw new UnsupportedOperationException("Operation not supported");
        }
    }

}
