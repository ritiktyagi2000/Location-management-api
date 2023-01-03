package com.springboot.Locationmanagementapi.convertor;

import com.springboot.Locationmanagementapi.entity.UserEntity;
import com.springboot.Locationmanagementapi.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class ModelToEntity {

public UserEntity convertModelToEntity(UserModel userModel){

    UserEntity userEntity=new UserEntity();
    userEntity.setEmail(userModel.getEmail());
    userEntity.setPassword(userModel.getPassword());
    userEntity.setFullName(userModel.getFullName());
    userEntity.setPhoneNumber(userModel.getPhoneNumber());

    return userEntity;

}

}
