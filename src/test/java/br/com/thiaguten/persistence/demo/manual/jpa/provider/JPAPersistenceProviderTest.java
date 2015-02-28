package br.com.thiaguten.persistence.demo.manual.jpa.provider;

import br.com.thiaguten.persistence.demo.manual.AbstractManualPersistenceProviderTest;
import br.com.thiaguten.persistence.demo.UserDAO;
import br.com.thiaguten.persistence.demo.manual.jpa.dao.UserDAOJpaImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;

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
