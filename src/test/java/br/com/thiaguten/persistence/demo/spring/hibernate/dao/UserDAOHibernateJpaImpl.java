/*
 * Copyright 2015 thiago.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.thiaguten.persistence.demo.spring.hibernate.dao;

import br.com.thiaguten.persistence.dao.GenericBaseDAO;
import br.com.thiaguten.persistence.demo.User;
import br.com.thiaguten.persistence.demo.UserDAO;
import br.com.thiaguten.persistence.provider.PersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author Thiago Gutenberg
 */
//@Repository("userHibernateJpaDAO")
public class UserDAOHibernateJpaImpl extends GenericBaseDAO<User, Long> implements UserDAO {
    
    @Autowired
    @Qualifier("hibernateJpaPersistenceService")
    private PersistenceProvider persistenceProvider;

    @Override
    public PersistenceProvider getPersistenceProvider() {
        return persistenceProvider;
    }

    public void setPersistenceProvider(PersistenceProvider persistenceProvider) {
        this.persistenceProvider = persistenceProvider;
    }
    
}
