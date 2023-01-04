package com.springboot.Locationmanagementapi.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "User_entity_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "user_id")
    private Long id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "email_address")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "mobile_number")
    private String phoneNumber;
}
