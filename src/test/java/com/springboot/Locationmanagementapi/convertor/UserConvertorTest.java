package com.springboot.Locationmanagementapi.convertor;


import com.springboot.Locationmanagementapi.entity.UserEntity;
import com.springboot.Locationmanagementapi.model.UserModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserConvertorTest {
 @InjectMocks
    private UserConvertor userConvertor;
    @Test
public void test_convertModelToEntity(){
        UserModel userModel=new UserModel();
        userModel.setEmail("abc@gmail.com");
        userModel.setPassword("password");
        userModel.setPhoneNumber("9899248610");
        userModel.setFullName("abc");
        UserEntity userEntity = userConvertor.convertModelToEntity(userModel);
        Assertions.assertEquals(userModel.getEmail(),userEntity.getEmail());

    }
}
