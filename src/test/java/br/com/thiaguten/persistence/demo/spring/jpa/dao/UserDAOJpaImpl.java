package br.com.thiaguten.persistence.demo.spring.jpa.dao;

import br.com.thiaguten.persistence.dao.GenericBaseDAO;
import br.com.thiaguten.persistence.demo.User;
import br.com.thiaguten.persistence.demo.UserDAO;
import br.com.thiaguten.persistence.provider.PersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository("userJpaDAO")
public class UserDAOJpaImpl extends GenericBaseDAO<User, Long> implements UserDAO {

    @Autowired
    @Qualifier("jpaPersistenceService")
    private PersistenceProvider persistenceProvider;

    @Override
    public PersistenceProvider getPersistenceProvider() {
        return persistenceProvider;
    }
}
