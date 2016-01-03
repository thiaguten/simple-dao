/*
 * #%L
 * %%
 * Copyright (C) 2015 - 2016 Thiago Gutenberg Carvalho da Costa.
 * %%
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * 3. Neither the name of the Thiago Gutenberg Carvalho da Costa. nor the names of its contributors
 *    may be used to endorse or promote products derived from this software without
 *    specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */
package br.com.thiaguten.persistence.provider.hibernate;

import br.com.thiaguten.persistence.Persistable;
import br.com.thiaguten.persistence.provider.PersistenceProvider;
import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.List;

/**
 * Define operation for a hibernate criteria persistence provider implementation.
 *
 * @author Thiago Gutenberg
 */
public interface HibernateCriteriaPersistenceProvider extends PersistenceProvider {

    /**
     * Find by criteria
     *
     * @param entityClazz the entity class
     * @param criterions  the criterions
     * @param <T>         entity
     * @return the list of entities
     */
    <T extends Persistable<? extends Serializable>> List<T> findByCriteria(Class<T> entityClazz, List<Criterion> criterions);

    /**
     * Find by criteria
     *
     * @param entityClazz the entity class
     * @param criterions  the criterions
     * @param firstResult first result
     * @param maxResults  max result
     * @param <T>         entity
     * @return the list of entities
     */
    <T extends Persistable<? extends Serializable>> List<T> findByCriteria(Class<T> entityClazz, int firstResult, int maxResults, List<Criterion> criterions);

    /**
     * Find by criteria
     *
     * @param entityClazz the entity class
     * @param criterions  the criterions
     * @param cacheable   cacheable
     * @param firstResult first result
     * @param maxResults  max result
     * @param <T>         entity
     * @return the list of entities
     */
    <T extends Persistable<? extends Serializable>> List<T> findByCriteria(Class<T> entityClazz, boolean cacheable, int firstResult, int maxResults, List<Criterion> criterions);

    /**
     * Find unique result by criteria
     *
     * @param entityClazz the entity class
     * @param criterions  the criterions
     * @param <T>         entity
     * @return the entity
     */
    <T extends Persistable<? extends Serializable>> T findUniqueResultByCriteria(Class<T> entityClazz, List<Criterion> criterions);

    /**
     * Find unique result by criteria
     *
     * @param entityClazz the entity class
     * @param criterions  the criterions
     * @param cacheable   cacheable
     * @param <T>         entity
     * @return the entity
     */
    <T extends Persistable<? extends Serializable>> T findUniqueResultByCriteria(Class<T> entityClazz, boolean cacheable, List<Criterion> criterions);

    /**
     * Count by criteria
     *
     * @param entityClazz the entity class
     * @param resultClazz the result class
     * @param criterions  the criterions
     * @param <T>         entity
     * @param <N>         number pojo
     * @return the count
     */
    <T extends Persistable<? extends Serializable>, N extends Number> N countByCriteria(Class<T> entityClazz, Class<N> resultClazz, List<Criterion> criterions);

}