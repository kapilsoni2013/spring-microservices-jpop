package com.kapilsony.libraryservice.controllers;

import com.kapilsony.libraryservice.dto.BookRequest;
import com.kapilsony.libraryservice.dto.BookResponse;
import com.kapilsony.libraryservice.dto.UserRequest;
import com.kapilsony.libraryservice.dto.UserResponse;
import com.kapilsony.libraryservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/library")
@RequiredArgsConstructor
@RefreshScope
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> login(@PathVariable Long id, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(id));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @GetMapping("/users/{user_id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("user_id") Long user_id, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserById(user_id));
    }

    @PostMapping("/users")
    public ResponseEntity<Void> createUser(@RequestBody UserRequest userRequest, HttpServletRequest request) {
        userService.createUser(userRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/users/{user_id}")
    public ResponseEntity<Void> updateUser(@PathVariable("user_id") Long user_id,
                                                 @RequestBody UserRequest userRequest, HttpServletRequest request) {
        userService.updateUser(user_id,userRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/users/{user_id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("user_id") Long user_id, HttpServletRequest request) {
        userService.deleteUser(user_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
