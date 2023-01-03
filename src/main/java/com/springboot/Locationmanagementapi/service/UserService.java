package com.springboot.Locationmanagementapi.service;

import com.springboot.Locationmanagementapi.exceptions.BusinessException;
import com.springboot.Locationmanagementapi.model.UserModel;

public interface UserService {

    public Boolean login(UserModel userModel) throws BusinessException;

    Long register(UserModel userModel) throws BusinessException;
}
