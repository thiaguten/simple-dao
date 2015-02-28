package br.com.thiaguten.persistence.demo.spring.hibernate.provider;

import br.com.thiaguten.persistence.demo.UserDAO;
import br.com.thiaguten.persistence.demo.spring.AbstractSpringPersistenceProviderTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeClass;

@ContextConfiguration(locations = {"classpath:spring/persistence-hibernate-appContext.xml"})
public class HibernatePersistenceProviderTest extends AbstractSpringPersistenceProviderTest {

    private static final Logger LOG = LoggerFactory.getLogger(HibernatePersistenceProviderTest.class);

    @Autowired
    @Qualifier("userHibernateDAO")
    private UserDAO userDAO;

    @BeforeClass
    public static void init() {
        LOG.info("******************************************");
        LOG.info("HIBERNATE - Transactions Management Spring");
        LOG.info("******************************************");
    }

    @Override
    public UserDAO getUserDAO() {
        return userDAO;
    }
}
