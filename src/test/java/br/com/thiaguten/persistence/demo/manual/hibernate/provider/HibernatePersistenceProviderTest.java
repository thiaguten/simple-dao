package br.com.thiaguten.persistence.demo.manual.hibernate.provider;

import br.com.thiaguten.persistence.demo.manual.AbstractManualPersistenceProviderTest;
import br.com.thiaguten.persistence.demo.UserDAO;
import br.com.thiaguten.persistence.demo.manual.hibernate.dao.UserDAOHibernateImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;

public class HibernatePersistenceProviderTest extends AbstractManualPersistenceProviderTest {

    private static final Logger LOG = LoggerFactory.getLogger(HibernatePersistenceProviderTest.class);

    private static UserDAO userDAO;

    @BeforeClass
    public static void init() {
        LOG.info("******************************************");
        LOG.info("HIBERNATE - Transactions Management Manual");
        LOG.info("******************************************");

        userDAO = new UserDAOHibernateImpl();
    }

    @Override
    public UserDAO getUserDAO() {
        return userDAO;
    }

}
