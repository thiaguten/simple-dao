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
package br.com.thiaguten.persistence.core;

import br.com.thiaguten.persistence.spi.PersistenceProvider;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Base class that provides basic CRUD operations.
 *
 * @param <T> the type of the persistent class
 * @param <ID> the type of the identifier
 * @author Thiago Gutenberg Carvalho da Costa
 */
public interface Persistence<ID extends Serializable, T extends Persistable<ID>> {

  /**
   * Get a persistence provider.
   *
   * @return the persistence provider implementation
   */
  PersistenceProvider getPersistenceProvider();

  /**
   * Get a persistence class.
   *
   * @return the persistence class
   */
  Class<T> getPersistenceClass();

  /**
   * Get an the identifier class.
   *
   * @return the identifier class
   */
  Class<ID> getIdentifierClass();

  /**
   * Create an entity.
   *
   * @param entity entity to be created
   * @return the created entity
   */
  T create(final T entity);

  /**
   * Read an entity by its identifier.
   *
   * @param id entity identifier to be read
   * @return the entity
   */
  T read(final ID id);

  /**
   * Update an entity.
   *
   * @param entity entity to be updated
   * @return the updated entity
   */
  T update(final T entity);

  /**
   * Delete an entity.
   *
   * @param entity entity to be deleted
   */
  void delete(final T entity);

  /**
   * Delete an entity by its identifier.
   *
   * @param id entity identifier to be deleted
   */
  void deleteById(final ID id);


  /**
   * Load all entities.
   *
   * @return the list of entities
   */
  List<T> findAll();

  /**
   * Load entities.
   *
   * @param firstResult first result
   * @param maxResults max results
   * @return the list of entities
   */
  List<T> findAll(final int firstResult, final int maxResults);

  /**
   * Find by named query.
   *
   * @param queryName the name of the query
   * @param params the query parameters
   * @return the list of entities
   */
  List<T> findByNamedQuery(
      final String queryName,
      final Object... params);

  /**
   * Find by named query.
   *
   * @param cacheable enable cache
   * @param queryName the name of the query
   * @param params the query parameters
   * @return the list of entities
   */
  List<T> findByNamedQuery(
      final boolean cacheable,
      final String queryName,
      final Object... params);

  /**
   * Find by named query.
   *
   * @param queryName the name of the query
   * @param params the query parameters
   * @return the list of entities
   */
  List<T> findByNamedQueryAndNamedParams(
      final String queryName,
      final Map<String, ?> params);

  /**
   * Find by named query.
   *
   * @param cacheable enable query cache
   * @param queryName the name of the query
   * @param params the query parameters
   * @return the list of entities
   */
  List<T> findByNamedQueryAndNamedParams(
      final boolean cacheable,
      final String queryName,
      final Map<String, ?> params);

  /**
   * Find by query.
   *
   * @param query the query string
   * @param params the query string positional parameters
   * @return the list of entities
   */
  List<T> findByQuery(
      final String query,
      final Object... params);

  /**
   * Find by query.
   *
   * @param cacheable enable query cache
   * @param query the query string
   * @param params the query string positional parameters
   * @return the list of entities
   */
  List<T> findByQuery(
      final boolean cacheable,
      final String query,
      final Object... params);

  /**
   * Find by query.
   *
   * @param query the query string
   * @param params the query string parameters
   * @return the list of entities
   */
  List<T> findByQueryAndNamedParams(
      final String query,
      final Map<String, ?> params);

  /**
   * Find by query.
   *
   * @param cacheable enable query cache
   * @param query the query string
   * @param params the query string parameters
   * @return the list of entities
   */
  List<T> findByQueryAndNamedParams(
      final boolean cacheable,
      final String query,
      final Map<String, ?> params);

  /**
   * Count all entities.
   *
   * @return the number of entities
   */
  long countAll();

}
