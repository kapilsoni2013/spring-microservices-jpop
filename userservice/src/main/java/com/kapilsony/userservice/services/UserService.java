package com.kapilsony.userservice.services;

import com.kapilsony.userservice.dto.UserRequest;
import com.kapilsony.userservice.dto.UserResponse;

import java.util.List;

public interface UserService {

    void save(UserRequest userRequest);

    void update(Long id, UserRequest userRequest);

    List<UserResponse> findAll();

    UserResponse findById(Long userId);

    void delete(Long userId);
}
