package br.com.thiaguten.persistence.demo.manual.jpa.dao;

import br.com.thiaguten.persistence.dao.GenericBaseDAO;
import br.com.thiaguten.persistence.demo.UserDAO;
import br.com.thiaguten.persistence.demo.manual.jpa.dao.strategy.JPAPersistenceStrategy;
import br.com.thiaguten.persistence.demo.User;
import br.com.thiaguten.persistence.provider.PersistenceProvider;

public class UserDAOJpaImpl extends GenericBaseDAO<User, Long> implements UserDAO {

    @Override
    public PersistenceProvider getPersistenceProvider() {
        return new JPAPersistenceStrategy();
    }
}
