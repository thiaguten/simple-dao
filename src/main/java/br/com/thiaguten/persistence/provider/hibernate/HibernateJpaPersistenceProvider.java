/**
 * Copyright 2015 Thiago Gutenberg Carvalho da Costa
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package br.com.thiaguten.persistence.provider.hibernate;

import javax.persistence.EntityManager;
import org.hibernate.Session;

/**
 * Hibernate EntityManager implementation of the PersistenceProvider.
 *
 * @author Thiago Gutenberg
 */
public abstract class HibernateJpaPersistenceProvider extends HibernatePersistenceProvider {

    /**
     * Get entity manager
     *
     * @return entity manager
     */
    public abstract EntityManager getEntityManager();

    /**
     * Get session
     * 
     * @return session
     */
    @Override
    public final Session getSession() {
        return (Session) getEntityManager().getDelegate();
    }
    
}