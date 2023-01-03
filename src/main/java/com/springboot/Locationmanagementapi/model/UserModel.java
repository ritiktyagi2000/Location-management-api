package com.springboot.Locationmanagementapi.model;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserModel {

    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;
}
