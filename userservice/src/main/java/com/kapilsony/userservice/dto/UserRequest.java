package com.kapilsony.userservice.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UserRequest {
    private Long userId;

    private String username;
    private String email;
    private Integer age;
    private String firstName;
    private String lastName;
    private String address;
}
