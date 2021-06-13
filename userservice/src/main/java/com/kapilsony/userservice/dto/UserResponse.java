package com.kapilsony.userservice.dto;

import lombok.Data;

@Data
public class UserResponse {
    private Long userId;

    private String username;
    private String email;
    private Integer age;
    private String firstName;
    private String lastName;
    private String address;
}
