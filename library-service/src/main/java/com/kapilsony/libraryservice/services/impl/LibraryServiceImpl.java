package com.kapilsony.libraryservice.services.impl;

import com.kapilsony.libraryservice.dto.BookRequest;
import com.kapilsony.libraryservice.dto.BookResponse;
import com.kapilsony.libraryservice.dto.UserResponse;
import com.kapilsony.libraryservice.services.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {

    @Override
    public void issueBookToUser(Long user_id, Long book_id) {

    }

    @Override
    public void releaseBookFromUser(Long user_id, Long book_id) {

    }

}
