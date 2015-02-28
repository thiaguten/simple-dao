package br.com.thiaguten.persistence.demo.spring.jpa.provider;

import br.com.thiaguten.persistence.demo.UserDAO;
import br.com.thiaguten.persistence.demo.spring.AbstractSpringPersistenceProviderTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeClass;

@ContextConfiguration(locations = {"classpath:spring/persistence-jpa-appContext.xml"})
public class JPAPersistenceProviderTest extends AbstractSpringPersistenceProviderTest {

    private static final Logger LOG = LoggerFactory.getLogger(JPAPersistenceProviderTest.class);

    @Autowired
    @Qualifier("userJpaDAO")
    private UserDAO userDAO;

    @BeforeClass
    public static void init() {
        LOG.info("************************************");
        LOG.info("JPA - Transactions Management Spring");
        LOG.info("************************************");
    }

    @Override
    public UserDAO getUserDAO() {
        return userDAO;
    }

}
