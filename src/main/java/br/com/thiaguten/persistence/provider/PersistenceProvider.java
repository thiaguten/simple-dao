package br.com.thiaguten.persistence.provider;

import br.com.thiaguten.persistence.Persistable;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface PersistenceProvider {

    /**
     * Find an entity by its primary key.
     *
     * @param <T>         the persistable pojo
     * @param <PK>        the primary key
     * @param entityClazz the entity class
     * @param id          the primary key
     * @return the entity
     */
    <T extends Persistable<? extends Serializable>, PK extends Serializable> T findById(
            final Class<T> entityClazz,
            final PK id);

    /**
     * Load all entities.
     *
     * @param <T>         the persistable pojo
     * @param entityClazz the entity class
     * @return the list of entities
     */
    <T extends Persistable<? extends Serializable>> List<T> findAll(final Class<T> entityClazz);

    /**
     * Load entities.
     *
     * @param <T>         the persistable pojo
     * @param entityClazz the entity class
     * @param firstResult the value of first result
     * @param maxResults  the value of max result
     * @return the list of entities
     */
    <T extends Persistable<? extends Serializable>> List<T> findAll(
            final Class<T> entityClazz,
            final int firstResult,
            final int maxResults);

    /**
     * Find using a named query.
     *
     * @param <T>         the persistable pojo the persistable pojo
     * @param entityClazz the entity class the entity class
     * @param queryName   the name of the query
     * @param params      the query parameters
     * @return the list of entities
     */
    <T extends Persistable<? extends Serializable>> List<T> findByNamedQuery(
            final Class<T> entityClazz,
            final String queryName,
            final Object... params
    );

    /**
     * Find using a named query.
     *
     * @param <T>         the persistable pojo
     * @param entityClazz the entity class
     * @param queryName   the name of the query
     * @param params      the query parameters
     * @return the list of entities
     */
    <T extends Persistable<? extends Serializable>> List<T> findByNamedQueryAndNamedParams(
            final Class<T> entityClazz,
            final String queryName,
            final Map<String, ?> params
    );

    /**
     * Find by JPQL/HQL...
     *
     * @param <T>         the persistable pojo
     * @param entityClazz the entity class
     * @param query       the typed query
     * @param params      the typed query parameters
     * @return the list of entities
     */
    <T extends Persistable<? extends Serializable>> List<T> findByQueryAndNamedParams(
            final Class<T> entityClazz,
            final String query,
            final Map<String, ?> params);

    /**
     * Count all entities.
     *
     * @param <T>         the persistable pojo
     * @param entityClazz the entity class
     * @return the number of entities
     */
    <T extends Persistable<? extends Serializable>> long countAll(final Class<T> entityClazz);

    /**
     * Count by named query and parameters.
     *
     * @param <T>         the persistable pojo
     * @param resultClazz the number class
     * @param queryName   the named query
     * @param params      the named query parameters
     * @return the count of entities
     */
    <T extends Number> T countByNamedQueryAndNamedParams(
            final Class<T> resultClazz,
            final String queryName,
            final Map<String, ?> params);

    /**
     * Count by JPQL/HQL...
     *
     * @param <T>         the number pojo
     * @param resultClazz the number class
     * @param query       the typed query
     * @param params      the typed query parameters
     * @return the count of entities
     */
    <T extends Number> T countByQueryAndNamedParams(
            final Class<T> resultClazz,
            final String query,
            final Map<String, ?> params);

    /**
     * Save an entity. This can be either a INSERT or UPDATE in the database.
     *
     * @param <T>    the persistable pojo
     * @param entity the entity to save
     * @return the saved entity
     */
    <T extends Persistable<? extends Serializable>> T save(final T entity);

    /**
     * Update an entity from the database.
     *
     * @param <T>    the persistable pojo
     * @param entity the entity to update
     * @return the updated entity
     */
    <T extends Persistable<? extends Serializable>> T update(final T entity);

    /**
     * Delete an entity from the database.
     *
     * @param <T>         the persistable pojo
     * @param entityClazz the entity class
     * @param entity      the entity to delete
     */
    <T extends Persistable<? extends Serializable>> void delete(final Class<T> entityClazz, final T entity);

    /**
     * Delete an entity from the database.
     *
     * @param <T>         the persistable pojo
     * @param <PK>        the primary key
     * @param entityClazz the entity class
     * @param id          primary key of the entity to delete
     */
    <T extends Persistable<? extends Serializable>, PK extends Serializable> void deleteById(final Class<T> entityClazz, final PK id);
}