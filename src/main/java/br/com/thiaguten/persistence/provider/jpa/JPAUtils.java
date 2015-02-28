package br.com.thiaguten.persistence.provider.jpa;

import br.com.thiaguten.persistence.Persistable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Thiago Gutenberg
 */
public abstract class JPAUtils {

    private static final Logger LOG = LoggerFactory.getLogger(JPAUtils.class);

    //    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-webapp");
    private static EntityManagerFactory emf;

    @PersistenceContext
//    private static EntityManager em = emf.createEntityManager();
    private static EntityManager em;

    public static EntityManagerFactory createEntityManagerFactory(String persistenceUnitName) {
        emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        em = emf.createEntityManager();
        return emf;
    }

    public static EntityManager getEntityManager() {
        if (emf != null && emf.isOpen()) {
            if (!em.isOpen()) {
                em = emf.createEntityManager();
            }
            return em;
        }
        throw new ExceptionInInitializerError("EntityManagerFactory não criado ou está fechado. Deverá ser criado novamente através da chamada do método createEntityManagerFactory");

    }

    public static EntityTransaction getTransaction() {
        return getEntityManager().getTransaction();
    }

    public static void closeEntityManager() {
        if (getEntityManager().isOpen()) {
            getEntityManager().close();
        }
    }

    public static void beginTransaction() {
        if (!getTransaction().isActive()) {
            getTransaction().begin();
        }
    }

    public static void commitTransaction() {
        if (getTransaction().isActive()) {
            getTransaction().commit();
        }
    }

    public static void rollbackTransaction() {
        if (getTransaction().isActive()) {
            getTransaction().rollback();
        }
    }

    public static <T extends Persistable<? extends Serializable>> TypedQuery<T> queryRange(TypedQuery<T> query, int firstResult, int maxResults) {
        if (query != null) {
            if (maxResults >= 0) {
                query.setMaxResults(maxResults);
            }
            if (firstResult >= 0) {
                query.setFirstResult(firstResult);
            }
        }
        return query;
    }
}
