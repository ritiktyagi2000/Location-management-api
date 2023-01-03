package com.springboot.Locationmanagementapi.convertor;

import com.springboot.Locationmanagementapi.entity.UserEntity;
import com.springboot.Locationmanagementapi.model.UserModel;

public class EntityToModel {

    public UserModel convertEntitytoModel(UserEntity userEntity){

        UserModel userModel=new UserModel();
        userModel.setEmail(userEntity.getEmail());
        userModel.setPassword(userEntity.getPassword());
        userModel.setFullName(userEntity.getFullName());
        userModel.setPhoneNumber(userEntity.getPhoneNumber());

        return userModel;

    }

}
