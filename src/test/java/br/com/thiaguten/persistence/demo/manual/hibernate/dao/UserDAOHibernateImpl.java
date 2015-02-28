package br.com.thiaguten.persistence.demo.manual.hibernate.dao;

import br.com.thiaguten.persistence.dao.GenericBaseDAO;
import br.com.thiaguten.persistence.demo.UserDAO;
import br.com.thiaguten.persistence.demo.manual.hibernate.dao.strategy.HibernatePersistenceStrategy;
import br.com.thiaguten.persistence.demo.User;
import br.com.thiaguten.persistence.provider.PersistenceProvider;

public class UserDAOHibernateImpl extends GenericBaseDAO<User, Long> implements UserDAO {

    @Override
    public PersistenceProvider getPersistenceProvider() {
        return new HibernatePersistenceStrategy();
    }
}
