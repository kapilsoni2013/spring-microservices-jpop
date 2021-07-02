package com.kapilsony.libraryservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class UserResponse {
    private Long userId;

    private String username;
    private String email;
    private Integer age;
    private String firstName;
    private String lastName;
    private String address;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<BookResponse> books;
}
