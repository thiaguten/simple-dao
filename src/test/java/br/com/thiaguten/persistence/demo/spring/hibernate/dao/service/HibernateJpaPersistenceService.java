package br.com.thiaguten.persistence.demo.spring.hibernate.dao.service;

import br.com.thiaguten.persistence.Persistable;
import br.com.thiaguten.persistence.provider.hibernate.HibernateJpaPersistenceProvider;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.criterion.Criterion;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
//@Service("hibernateJpaPersistenceService")
public class HibernateJpaPersistenceService extends HibernateJpaPersistenceProvider {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public <T extends Persistable<? extends Serializable>> T save(T entity) {
        return super.save(entity);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public <T extends Persistable<? extends Serializable>> T update(T entity) {
        return super.update(entity);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public <T extends Persistable<? extends Serializable>> void delete(Class<T> entityClazz, T entity) {
        super.delete(entityClazz, entity);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public <T extends Persistable<? extends Serializable>, PK extends Serializable> void deleteById(Class<T> entityClazz, PK pk) {
        super.deleteById(entityClazz, pk);
    }
}
