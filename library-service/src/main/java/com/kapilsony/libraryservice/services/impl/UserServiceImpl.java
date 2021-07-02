package com.kapilsony.libraryservice.services.impl;

import com.kapilsony.libraryservice.dto.UserRequest;
import com.kapilsony.libraryservice.dto.UserResponse;
import com.kapilsony.libraryservice.services.UserRemoteService;
import com.kapilsony.libraryservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRemoteService userRemoteService;

    @Override
    public UserResponse login(Long id) {
        return userRemoteService.findUserById(id);
    }

    @Override
    public UserResponse findUserById(Long user_id) {
        return userRemoteService.findUserById(user_id);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRemoteService.getAllUsers();
    }

    @Override
    public void createUser(UserRequest userRequest) {
        userRemoteService.createUser(userRequest);
    }

    @Override
    public void updateUser(Long user_id, UserRequest userRequest) {
        userRemoteService.updateUser(user_id,userRequest);
    }

    @Override
    public void deleteUser(Long user_id) {
        userRemoteService.deleteUser(user_id);
    }


}
