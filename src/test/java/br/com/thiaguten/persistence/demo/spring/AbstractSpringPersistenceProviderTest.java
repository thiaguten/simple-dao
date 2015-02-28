package br.com.thiaguten.persistence.demo.spring;

import br.com.thiaguten.persistence.demo.User;
import br.com.thiaguten.persistence.demo.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public abstract class AbstractSpringPersistenceProviderTest extends AbstractTransactionalTestNGSpringContextTests {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractSpringPersistenceProviderTest.class);

    protected static final String[] INSERT_PARAMS = {"THIAGO", "DAYANA", "VALENTINA"};
    protected static final String[] UPDATE_PARAMS = {"THIAGO2", "DAYANA2", "VALENTINA2"};
    protected static final String[] DELETE_PARAMS = UPDATE_PARAMS;

    protected abstract UserDAO getUserDAO();

    @Test
    public void testIt() {
        create();
        read(INSERT_PARAMS, false);
        update();
        read(UPDATE_PARAMS, false);
        delete();
        read(DELETE_PARAMS, true);
    }

    private void create() {
        logInfoHandler("Creating...");
        for (int i = 0; i < INSERT_PARAMS.length; i++) {

            User user = new User();
            user.setId(i + 1L);
            user.setName(INSERT_PARAMS[i]);

            logInfoHandler(user.toString());

            getUserDAO().save(user);
        }
    }

    private void read(String[] params, boolean deleteCheck) {
        logInfoHandler("Reading...");
        for (int i = 0; i < params.length; i++) {

            User user = getUserDAO().findById(i + 1L);
            String name = user != null ? user.getName() : null;

            String mensagem;
            if (deleteCheck) {
                mensagem = "assertNull";
                Assert.assertNull(name);
            } else {
                mensagem = "assertEquals";
                Assert.assertEquals(params[i], name);
            }

            if (user != null) {
                logInfoHandler(user.toString());
            }

            logInfoHandler(mensagem + ":" + params[i] + " -> " + name);
        }
    }

    private void update() {
        logInfoHandler("Updating...");
        for (int i = 0; i < UPDATE_PARAMS.length; i++) {

            User user = getUserDAO().findById(i + 1L);
            user.setName(UPDATE_PARAMS[i]);

            logInfoHandler(user.toString());

            getUserDAO().update(user);
        }
    }

    private void delete() {
        logInfoHandler("Deleting...");
        for (int i = 0; i < DELETE_PARAMS.length; i++) {

            User user = getUserDAO().findById(i + 1L);
            user.setName(DELETE_PARAMS[i]);

            logInfoHandler(user.toString());

            getUserDAO().delete(user);
        }
    }

    protected void logInfoHandler(String str) {
        LOG.info(str);
//        System.out.println(str);
    }

}
