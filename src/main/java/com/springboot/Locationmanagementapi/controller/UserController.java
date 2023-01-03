package com.springboot.Locationmanagementapi.controller;

import com.springboot.Locationmanagementapi.exceptions.BusinessException;
import com.springboot.Locationmanagementapi.model.UserModel;
import com.springboot.Locationmanagementapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<Boolean> login(@RequestBody UserModel userModel) throws BusinessException {
        Boolean result = userService.login(userModel);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/users/registration")
    public ResponseEntity<Long> register(@RequestBody UserModel userModel) throws BusinessException {
        Long result = userService.register(userModel);
      return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
