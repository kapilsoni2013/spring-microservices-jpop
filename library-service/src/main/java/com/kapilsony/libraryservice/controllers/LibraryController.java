package com.kapilsony.libraryservice.controllers;

import com.kapilsony.libraryservice.dto.BookRequest;
import com.kapilsony.libraryservice.dto.BookResponse;
import com.kapilsony.libraryservice.dto.UserResponse;
import com.kapilsony.libraryservice.services.LibraryService;
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
public class LibraryController {

    private final LibraryService libraryService;

    @PostMapping("/users/{user_id}/books/{book_id}")
    public ResponseEntity<Void> issueBookToUser(@PathVariable("user_id") Long user_id,
                                           @PathVariable("book_id") Long book_id, HttpServletRequest request) {
        libraryService.issueBookToUser(user_id, book_id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{user_id}/books/{book_id}")
    public ResponseEntity<Void> releaseBookFromUser(@PathVariable("user_id") Long user_id,
                                           @PathVariable("book_id") Long book_id, HttpServletRequest request) {
        libraryService.releaseBookFromUser(user_id, book_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }






}
