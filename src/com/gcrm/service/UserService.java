/**
 * Copyright (C) 2012, Grass CRM Inc
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
package com.gcrm.service;

import com.gcrm.dao.IUserDao;
import com.gcrm.domain.User;
import com.gcrm.exception.DaoException;
import com.gcrm.exception.ServiceException;

/**
 * User service
 */
public class UserService extends BaseService<User> implements IUserService {

    private IUserDao userDao;

    /*
     * (non-Javadoc)
     * 
     * @see com.gcrm.service.IUserService#findByName(java.lang.String)
     */
    public User findByName(String userName) throws ServiceException {
        User user;
        try {
            user = this.userDao.findByName(userName);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return user;
    }

    /**
     * @return the userDao
     */
    public IUserDao getUserDao() {
        return userDao;
    }

    /**
     * @param userDao
     *            the userDao to set
     */
    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

}
