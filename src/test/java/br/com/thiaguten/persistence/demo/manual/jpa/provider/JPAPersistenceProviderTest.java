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
package br.com.thiaguten.persistence.demo.manual.jpa.provider;

import br.com.thiaguten.persistence.demo.UserDAO;
import br.com.thiaguten.persistence.demo.manual.AbstractManualPersistenceProviderTest;
import br.com.thiaguten.persistence.demo.manual.jpa.dao.UserDAOJpaImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;

/**
 * JPA persistence provider test
 *
 * @author Thiago Gutenberg
 */
public class JPAPersistenceProviderTest extends AbstractManualPersistenceProviderTest {

    private static final Logger LOG = LoggerFactory.getLogger(JPAPersistenceProviderTest.class);

    private static UserDAO userDAO;

    @BeforeClass
    public static void init() {
        LOG.info("************************************");
        LOG.info("JPA - Transactions Management Manual");
        LOG.info("************************************");

        userDAO = new UserDAOJpaImpl();
    }

    @Override
    public UserDAO getUserDAO() {
        return userDAO;
    }

}
