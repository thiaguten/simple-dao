package br.com.thiaguten.persistence.demo.spring.hibernate.dao;

import br.com.thiaguten.persistence.dao.GenericBaseDAO;
import br.com.thiaguten.persistence.demo.User;
import br.com.thiaguten.persistence.demo.UserDAO;
import br.com.thiaguten.persistence.provider.PersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

//@Repository("userHibernateDAO")
public class UserDAOHibernateImpl extends GenericBaseDAO<User, Long> implements UserDAO {

    @Autowired
    @Qualifier("hibernatePersistenceService")
    private PersistenceProvider persistenceProvider;

    @Override
    public PersistenceProvider getPersistenceProvider() {
        return persistenceProvider;
    }

    public void setPersistenceProvider(PersistenceProvider persistenceProvider) {
        this.persistenceProvider = persistenceProvider;
    }
}
