package com.kapilsony.libraryservice.services;

import com.kapilsony.libraryservice.configs.LoadBalancerConfiguration;
import com.kapilsony.libraryservice.dto.UserRequest;
import com.kapilsony.libraryservice.dto.UserResponse;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "user-service")
@LoadBalancerClient(name = "user-service",
        configuration= LoadBalancerConfiguration.class)
@RequestMapping("/users")
public interface UserRemoteService {
    @GetMapping
    List<UserResponse> getAllUsers();

    @GetMapping("/{user_id}")
    UserResponse findUserById(@PathVariable("user_id") Long user_id);

    @PostMapping
    void createUser(@RequestBody UserRequest userRequest);

    @PutMapping("/{user_id}")
    void updateUser(@PathVariable("user_id") Long user_id,@RequestBody UserRequest userRequest);

    @DeleteMapping("/{user_id}")
    void deleteUser(@PathVariable("user_id") Long user_id);
}
