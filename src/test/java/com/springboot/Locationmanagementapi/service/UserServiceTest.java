package com.springboot.Locationmanagementapi.service;


import com.springboot.Locationmanagementapi.convertor.UserConvertor;
import com.springboot.Locationmanagementapi.entity.UserEntity;
import com.springboot.Locationmanagementapi.exceptions.BusinessException;
import com.springboot.Locationmanagementapi.exceptions.ErrorModel;
import com.springboot.Locationmanagementapi.model.UserModel;
import com.springboot.Locationmanagementapi.repository.UserEntityRepository;
import com.springboot.Locationmanagementapi.validation.UserValidator;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserServiceImpl userService;
    @Mock
    UserValidator userValidator;

    @Mock
    UserEntityRepository userRepository;
    @Mock
    UserConvertor userConvertor;

    @Test
    void login_empty_credential() {
        UserModel userModel=new UserModel();
        List<ErrorModel>errorModels=new ArrayList<>();
        ErrorModel errorModel=new ErrorModel();
        errorModel.setMessage("Email not valid");
        errorModel.setCode("Email not valid");
        errorModels.add(errorModel);
        Mockito.when(userValidator.validateRequest(userModel)).thenReturn( errorModels);
        Assertions.assertThrows(BusinessException.class,()->{
           userService.login(userModel);
        });
    }


    @Test
    void login_wrong_credential() {
        UserModel userModel=new UserModel();
        userModel.setEmail("abc@gmail.com");
        userModel.setPassword("password");

        UserEntity userEntity=null;
        List<ErrorModel>errorModels=new ArrayList<>();
        Mockito.when(userValidator.validateRequest(userModel)).thenReturn( errorModels);
        Mockito.when(userRepository.findByEmailAndPassword(userModel.getEmail(),userModel.getPassword())).thenReturn(userEntity);

        ErrorModel errorModel=new ErrorModel();
        errorModel.setMessage("Email not authorized");
        errorModel.setCode("Unauthorized");
        List<ErrorModel>errorModelList=new ArrayList<>();
        errorModelList.add(errorModel);

        Assertions.assertThrows(BusinessException.class,()->{
            userService.login(userModel);
        });
    }

    @Test
    void login_with_correct_credential() throws BusinessException {
        UserModel userModel=new UserModel();
        userModel.setEmail("abc@gmail.com");
        userModel.setPassword("password");

        UserEntity userEntity=new UserEntity();
        userEntity.setPassword("password");
        userEntity.setEmail("a@gmail.com");
        List<ErrorModel>errorModels=new ArrayList<>();
        Mockito.when(userValidator.validateRequest(userModel)).thenReturn( errorModels);
        Mockito.when(userRepository.findByEmailAndPassword(userModel.getEmail(),userModel.getPassword())).thenReturn(userEntity);
        Boolean login = userService.login(userModel);
        Assertions.assertTrue(login);
    }

    @Test
    void register_with_empty_credential() {
        UserModel userModel=new UserModel();
        List<ErrorModel>errorModels=new ArrayList<>();
        ErrorModel errorModel=new ErrorModel();
        errorModel.setMessage("Email not valid");
        errorModel.setCode("Email not valid");
        errorModels.add(errorModel);
        Mockito.when(userValidator.validateRequest(userModel)).thenReturn( errorModels);
        Assertions.assertThrows(BusinessException.class,()->{
            userService.register(userModel);
        });
    }

    @Test
    void register_already_present_credential() {
        UserModel userModel=new UserModel();
        userModel.setEmail("abc@gmail.com");
        userModel.setPassword("password");

        UserEntity userEntity=new UserEntity();
        userEntity.setEmail("abc@gmail.com");
        userEntity.setPassword("password");
        List<ErrorModel>errorModels=new ArrayList<>();
        Mockito.when(userValidator.validateRequest(userModel)).thenReturn( errorModels);
        Mockito.when(userRepository.findByEmail(userModel.getEmail())).thenReturn(userEntity);

        ErrorModel errorModel=new ErrorModel();
        errorModel.setMessage("Email already registered");
        errorModel.setCode("Already registered");
        List<ErrorModel>errorModelList=new ArrayList<>();
        errorModelList.add(errorModel);

        Assertions.assertThrows(BusinessException.class,()->{
            userService.register(userModel);
        });
    }
    @Test
    void register_with_correct_credential() throws BusinessException {
        UserModel userModel=new UserModel();
        userModel.setEmail("abc@gmail.com");
        userModel.setPassword("password");
        userModel.setPhoneNumber("9899248610");
        userModel.setFullName("abc");

        UserEntity userEntity=new UserEntity();
        userEntity=null;
/*
        userEntity.setPhoneNumber("9899");
        userEntity.setFullName("aaaaaaa");*/


        UserEntity userEntity1=new UserEntity();
        userEntity1.setPassword("new password");
        userEntity1.setEmail("a@gmail.com");
        userEntity1.setId(1l);

        UserEntity userEntity2=new UserEntity();
        userEntity2.setPassword("password");
        userEntity2.setEmail("abc@gmail.com");
        userEntity2.setPhoneNumber("9899248610");
        userEntity2.setFullName("abcd");
        userEntity2.setId(1L);

        List<ErrorModel>errorModels=new ArrayList<>();

        Mockito.when(userValidator.validateRequest(userModel)).thenReturn( errorModels);
        Mockito.when(userRepository.findByEmail(userModel.getEmail())).thenReturn(userEntity);

        Mockito.when(userConvertor.convertModelToEntity(userModel)).thenReturn(userEntity1);
        lenient().when(userRepository.save(userEntity1)).thenReturn(userEntity2);
        Long register = userService.register(userModel);

        Assertions.assertEquals(1l,register);
    }
}
