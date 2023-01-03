package com.springboot.Locationmanagementapi.service;

import com.springboot.Locationmanagementapi.ErrorType;
import com.springboot.Locationmanagementapi.convertor.ModelToEntity;
import com.springboot.Locationmanagementapi.entity.UserEntity;
import com.springboot.Locationmanagementapi.exceptions.BusinessException;
import com.springboot.Locationmanagementapi.exceptions.ErrorModel;
import com.springboot.Locationmanagementapi.model.UserModel;
import com.springboot.Locationmanagementapi.repository.UserEntityRepository;
import com.springboot.Locationmanagementapi.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
 UserEntityRepository userEntityRepository;
    @Autowired
    UserValidator userValidator;

    @Override
    public Boolean login(UserModel userModel) throws BusinessException {
        List<ErrorModel> errorModelList = userValidator.validateRequest(userModel);

        if (errorModelList != null && !errorModelList.isEmpty()) {
            throw new BusinessException(errorModelList);
        }
        UserEntity userEntity = userEntityRepository.findByEmailAndPassword(userModel.getEmail(), userModel.getPassword());

        Boolean result=false;
            if (userEntity == null) {
                ErrorModel errorModel = new ErrorModel();
                errorModel.setCode(ErrorType.AUTH_INVALID_CREDENTIALS.toString());
                errorModel.setMessage("Email or Mobile number is invalid");
                List<ErrorModel> errorModels = new ArrayList<>();
                errorModels.add(errorModel);
                throw new BusinessException(errorModels);
        }else {
                result = true;
            }
            return result;
        }

    @Override
    public Long register(UserModel userModel) throws BusinessException {

        ModelToEntity modelToEntity = new ModelToEntity();
        List<ErrorModel> errorModelList = userValidator.validateRequest(userModel);

        if (errorModelList != null && !errorModelList.isEmpty()) {
            throw new BusinessException(errorModelList);
        }
        if (userEntityRepository.findByEmail(userModel.getEmail()) != null) {

            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode(ErrorType.ALREADY_EXIST.toString());
            errorModel.setMessage("Email is already exist,Please try with another email");
            List<ErrorModel> errorModels = new ArrayList<>();
            errorModels.add(errorModel);
            throw new BusinessException(errorModels);
        } else {
            UserEntity userEntity = modelToEntity.convertModelToEntity(userModel);
            UserEntity userEntity1 = userEntityRepository.save(userEntity);
            return userEntity1.getId();
        }
    }


}

