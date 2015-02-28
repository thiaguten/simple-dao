package br.com.thiaguten.persistence.dao;

import br.com.thiaguten.persistence.Persistable;
import br.com.thiaguten.persistence.provider.PersistenceProvider;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Generic class, providing basic CRUD operations.
 *
 * @param <T>
 * @param <PK>
 */
public interface BaseDAO<T extends Persistable<?>, PK extends Serializable> {

    /**
     * Get the Class of the entity.
     *
     * @return the class
     */
    Class<T> getEntityClass();

    /**
     * Get the Class of the entity primary key.
     *
     * @return the class
     */
    Class<PK> getPrimaryKeyClass();

    /**
     * Get the persistence provider (Jpa, Hibernate, etc)
     *
     * @return the persistence provider implementation
     */
    PersistenceProvider getPersistenceProvider();

    /**
     * Find an entity by its primary key
     *
     * @param id the primary key
     * @return the entity
     */
    T findById(final PK id);

    /**
     * Load all entities.
     *
     * @return the list of entities
     */
    List<T> findAll();

    /**
     * Load entites
     *
     * @param firstResult firstResult
     * @param maxResults maxResults
     * @return the list of entities
     */
    List<T> find(final int firstResult, final int maxResults);

    /**
     * Find using a named query.
     *
     * @param queryName the name of the query
     * @param params    the query parameters
     * @return the list of entities
     */
    List<T> findByNamedQuery(
            final String queryName,
            Object... params
    );

    /**
     * Find using a named query.
     *
     * @param queryName the name of the query
     * @param params    the query parameters
     * @return the list of entities
     */
    List<T> findByNamedQueryAndNamedParams(
            final String queryName,
            final Map<String, ?> params
    );

    /**
     * Count all entities.
     *
     * @return the number of entities
     */
    long countAll();

    /**
     * Save an entity. This can be either a INSERT or UPDATE in the database.
     *
     * @param entity the entity to save
     * @return the saved entity
     */
    T save(final T entity);

    /**
     * Update an entity from the database.
     *
     * @param entity the entity to update
     * @return the updated entity
     */
    T update(final T entity);

    /**
     * Delete an entity from the database.
     *
     * @param entity the entity to delete
     */
    void delete(final T entity);

    /**
     * Delete an entity from the database.
     *
     * @param id primary key of the entity to delete
     */
    void deleteById(final PK id);

}
