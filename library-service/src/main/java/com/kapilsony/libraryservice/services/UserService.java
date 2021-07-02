package com.kapilsony.libraryservice.services;

import com.kapilsony.libraryservice.dto.BookRequest;
import com.kapilsony.libraryservice.dto.BookResponse;
import com.kapilsony.libraryservice.dto.UserRequest;
import com.kapilsony.libraryservice.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse login(Long id);

    UserResponse findUserById(Long user_id);

    List<UserResponse> getAllUsers();

    void createUser(UserRequest userRequest);
    void updateUser(Long user_id, UserRequest userRequest);

    void deleteUser(Long user_id);
}
