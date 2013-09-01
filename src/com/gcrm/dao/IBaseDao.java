/**
 * Copyright (C) 2012, Grass CRM Studio
 *
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
 */
package com.gcrm.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.gcrm.vo.SearchCondition;
import com.gcrm.vo.SearchResult;

/**
 * Base Dao Interface
 */
public interface IBaseDao<T extends Serializable> {

    /**
     * Gets all entity instances
     * 
     * @param clazz
     *            class name
     * @return result list
     */
    public List<T> getAllObjects(String clazz);

    /**
     * Finds records according to name
     * 
     * @param clazz
     *            class name
     * @param name
     *            entity name
     * @return entity instance
     * @throws Exception
     */
    public T findByName(String clazz, String name);

    /**
     * Finds records by hql
     * 
     * @param hql
     *            hql with parameters
     * @return result list
     * @throws Exception
     */
    public List<T> findByHQL(String hql);

    /**
     * Finds records by hql with parameters
     * 
     * @param hql
     *            hql with parameters
     * @param paramValue
     *            parameter value
     * @return result list
     * @throws Exception
     */
    public List<T> findByParam(String hql, Object paramValue);

    /**
     * Persists entity
     * 
     * @param entity
     *            entity instance
     */
    public T makePersistent(T entity);

    /**
     * Batch updates entities
     * 
     * @param entities
     *            entity instance collection
     */
    public void batchUpdate(Collection<T> entities);

    /**
     * Deletes entity by id
     * 
     * @param entityClass
     *            entity class
     * @param id
     *            entity id
     */
    public void deleteEntity(Class<T> entityClass, Integer id);

    /**
     * Gets entity by id
     * 
     * @param entityClass
     *            entity class
     * @param id
     *            entity instance id
     * @return entity instance
     */
    public T getEntityById(Class<T> entityClass, Integer id);

    /**
     * Gets object count
     * 
     * @param clazz
     *            entity class name
     * @return object count
     */
    public long getObjectsCount(String clazz);

    /**
     * Gets pagination objects
     * 
     * @param clazz
     *            entity class name
     * @param searchCondition
     *            search condition
     * @return search result
     */
    public SearchResult<T> getPaginationObjects(final String clazz,
            final SearchCondition searchCondition);

    public SearchResult<T> getPaginationObjectsWithHql(final String clazz,
            final String hql, final SearchCondition searchCondition);

    public List<T> getAllSortedObjects(String clazz, String sortColumn,
            String order);

    public List<T> getObjects(final String clazz, final String condition);
}
