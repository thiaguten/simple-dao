/*
 * #%L
 * %%
 * Copyright (C) 2015 - 2016 Thiago Gutenberg Carvalho da Costa.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package br.com.thiaguten.persistence.spi;

import br.com.thiaguten.persistence.core.Persistable;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Define operation for a persistence provider implementation.
 *
 * @author Thiago Gutenberg Carvalho da Costa
 */
public interface PersistenceProvider {

  /**
   * Find an entity by its identifier.
   *
   * @param <T> the type of the entity
   * @param <ID> the type of the identifier
   * @param entityClazz the entity class
   * @param id the entity identifier
   * @return the entity
   */
  <ID extends Serializable, T extends Persistable<ID>> T findById(
      final Class<T> entityClazz,
      final ID id);

  /**
   * Load all entities.
   *
   * @param entityClazz the entity class
   * @param <ID> the type of the identifier
   * @param <T> the type of the entity
   * @return the list of entities
   */
  <ID extends Serializable, T extends Persistable<ID>> List<T> findAll(
      final Class<T> entityClazz);

  /**
   * Load entities.
   *
   * @param entityClazz the entity class
   * @param firstResult the value of first result
   * @param maxResults the value of max result
   * @param <ID> the type of the identifier
   * @param <T> the type of the entity
   * @return the list of entities
   */
  <ID extends Serializable, T extends Persistable<ID>> List<T> findAll(
      final Class<T> entityClazz,
      final int firstResult,
      final int maxResults);

  /**
   * Find by named query.
   *
   * @param entityClazz the entity class
   * @param queryName the name of the query
   * @param params the query positional parameters
   * @param <ID> the type of the identifier
   * @param <T> the type of the entity
   * @return the list of entities
   */
  <ID extends Serializable, T extends Persistable<ID>> List<T> findByNamedQuery(
      final Class<T> entityClazz,
      final String queryName,
      final Object... params);

  /**
   * Find by named query.
   *
   * @param entityClazz the entity class
   * @param queryName the name of the query
   * @param params the query parameters
   * @param <ID> the type of the identifier
   * @param <T> the type of the entity
   * @return the list of entities
   */
  <ID extends Serializable, T extends Persistable<ID>> List<T> findByNamedQueryAndNamedParams(
      final Class<T> entityClazz,
      final String queryName,
      final Map<String, ?> params);

  /**
   * Find by query (JPQL/HQL, etc).
   *
   * @param entityClazz the entity class
   * @param query the query string
   * @param params the query string positional parameters
   * @param <ID> the type of the identifier
   * @param <T> the type of the entity
   * @return the list of entities
   */
  <ID extends Serializable, T extends Persistable<ID>> List<T> findByQuery(
      final Class<T> entityClazz,
      final String query,
      final Object... params);

  /**
   * Find by query (SQL/JPQL/HQL...) and parameters.
   *
   * @param entityClazz the entity class
   * @param query the query string
   * @param params the query string parameters
   * @param <ID> the type of the identifier
   * @param <T> the type of the entity
   * @return the list of entities
   */
  <ID extends Serializable, T extends Persistable<ID>> List<T> findByQueryAndNamedParams(
      final Class<T> entityClazz,
      final String query,
      final Map<String, ?> params);

  /**
   * Count all entities.
   *
   * @param entityClazz the entity class
   * @param <ID> the type of the identifier
   * @param <T> the type of the entity
   * @return the number of entities
   */
  <ID extends Serializable, T extends Persistable<ID>> long countAll(
      final Class<T> entityClazz);

  /**
   * Count by named query and parameters.
   *
   * @param <T> the type of the entity
   * @param resultClazz the number class
   * @param queryName the named query
   * @param params the named query parameters
   * @return the count of entities
   */
  <T extends Number> T countByNamedQueryAndNamedParams(
      final Class<T> resultClazz,
      final String queryName,
      final Map<String, ?> params);

  /**
   * Count by query (SQL/JPQL/HQL...) and parameters.
   *
   * @param <T> the type of the entity
   * @param resultClazz the number class
   * @param query the query string
   * @param params the query parameters
   * @return the count of entities
   */
  <T extends Number> T countByQueryAndNamedParams(
      final Class<T> resultClazz,
      final String query,
      final Map<String, ?> params);

  /**
   * Save an entity.
   *
   * @param entity the entity to be saved
   * @param <ID> the type of the identifier
   * @param <T> the type of the entity
   * @return the saved entity
   */
  <ID extends Serializable, T extends Persistable<ID>> T save(
      final T entity);

  /**
   * Update an entity.
   *
   * @param entity the entity to be updated
   * @param <ID> the type of the identifier
   * @param <T> the type of the entity
   * @return the updated entity
   */
  <ID extends Serializable, T extends Persistable<ID>> T update(
      final T entity);

  /**
   * Delete an entity.
   *
   * @param entityClazz the entity class
   * @param entity the entity to be deleted
   * @param <ID> the type of the identifier
   * @param <T> the type of the entity
   */
  <ID extends Serializable, T extends Persistable<ID>> void delete(
      final Class<T> entityClazz, final T entity);

  /**
   * Delete an entity by its identifier.
   *
   * @param <T> the type of the entity
   * @param <ID> the type of the identifier
   * @param entityClazz the entity class
   * @param id the entity identifier to be deleted
   */
  <ID extends Serializable, T extends Persistable<ID>> void deleteById(
      final Class<T> entityClazz, final ID id);
}
