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
package br.com.thiaguten.persistence.demo.spring.hibernate.provider;

import br.com.thiaguten.persistence.demo.UserDAO;
import br.com.thiaguten.persistence.demo.spring.AbstractSpringPersistenceProviderTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeClass;

/**
 *
 * @author Thiago Gutenberg
 */
@ContextConfiguration(locations = {"classpath:spring/persistence-hibernate-entitymanager-appContext.xml"})
public class HibernateEntityManagerPersistenceProviderTest extends AbstractSpringPersistenceProviderTest {

    private static final Logger LOG = LoggerFactory.getLogger(HibernateEntityManagerPersistenceProviderTest.class);

    @Autowired
    @Qualifier("userHibernateJpaDAO")
    private UserDAO userDAO;

    @BeforeClass
    public static void init() {
        LOG.info("********************************************************");
        LOG.info("HIBERNATE ENTITYMANAGER - Transactions Management Spring");
        LOG.info("********************************************************");
    }

    @Override
    public UserDAO getUserDAO() {
        return userDAO;
    }

}
