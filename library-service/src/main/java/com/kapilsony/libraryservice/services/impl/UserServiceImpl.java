package com.kapilsony.libraryservice.services.impl;

import com.kapilsony.libraryservice.dto.UserRequest;
import com.kapilsony.libraryservice.dto.UserResponse;
import com.kapilsony.libraryservice.services.BookService;
import com.kapilsony.libraryservice.services.LibraryService;
import com.kapilsony.libraryservice.services.UserRemoteService;
import com.kapilsony.libraryservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRemoteService userRemoteService;
    private final LibraryService libraryService;
    private final BookService bookService;

    @Override
    public UserResponse login(Long id) {
        return userRemoteService.findUserById(id);
    }

    @Override
    public UserResponse findUserById(Long user_id) {
        UserResponse user = userRemoteService.findUserById(user_id);
        user.setBooks(libraryService.findUserBooks(user_id));
        return user;
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

    @Transactional
    @Override
    public void deleteUser(Long user_id) {
        userRemoteService.deleteUser(user_id);
        libraryService.releaseAllBooksFromUser(user_id);

    }


}
