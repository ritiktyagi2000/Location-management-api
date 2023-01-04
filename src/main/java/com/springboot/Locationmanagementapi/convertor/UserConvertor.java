package com.springboot.Locationmanagementapi.convertor;

import com.springboot.Locationmanagementapi.entity.UserEntity;
import com.springboot.Locationmanagementapi.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserConvertor {

    public UserModel convertEntitytoModel(UserEntity userEntity){

        UserModel userModel=new UserModel();
        userModel.setEmail(userEntity.getEmail());
        userModel.setPassword(userEntity.getPassword());
        userModel.setFullName(userEntity.getFullName());
        userModel.setPhoneNumber(userEntity.getPhoneNumber());

        return userModel;

    }

    public UserEntity convertModelToEntity(UserModel userModel){

        UserEntity userEntity=new UserEntity();
        userEntity.setEmail(userModel.getEmail());
        userEntity.setPassword(userModel.getPassword());
        userEntity.setFullName(userModel.getFullName());
        userEntity.setPhoneNumber(userModel.getPhoneNumber());

        return userEntity;

    }
}
