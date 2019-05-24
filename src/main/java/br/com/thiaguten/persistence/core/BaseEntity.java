/*-
 * #%L
 * Simple DAO
 * %%
 * Copyright (C) 2015 - 2019 Thiago Gutenberg Carvalho da Costa
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

import java.io.Serializable;
import java.util.Objects;

import br.com.thiaguten.persistence.core.Persistable;

/**
 * Abstract Base Entity class that provide convenient methods and can be extended by Entities
 * classes.
 *
 * @param <ID> primary key
 * @author Thiago Gutenberg Carvalho da Costa
 */
public abstract class BaseEntity<ID extends Serializable> implements Persistable<ID> {

  private static final long serialVersionUID = 9149914419520367894L;

  /**
   * Checks if this instance has a valid id.
   *
   * @return true if this instance has id, otherwise false.
   */
  public boolean hasID() {
    return this.getId() != null;
  }

  /**
   * {@inheritDoc} Overridden to implements the method behavior.
   */
  @Override
  public String toString() {
    return getClass().getSimpleName() + "{id=" + getId() + "}";
  }

  /**
   * {@inheritDoc} Overridden to implements the method behavior.
   */
  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }

  /**
   * {@inheritDoc} Overridden to implements the method behavior.
   */
  @SuppressWarnings("rawtypes")
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    BaseEntity other = (BaseEntity) obj;
    return Objects.equals(getId(), other.getId());
  }

}

