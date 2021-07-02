package com.kapilsony.libraryservice.services;

import com.kapilsony.libraryservice.dto.BookRequest;
import com.kapilsony.libraryservice.dto.BookResponse;
import com.kapilsony.libraryservice.dto.UserResponse;

import java.util.List;

public interface LibraryService {
    void issueBookToUser(Long user_id, Long book_id);

    void releaseBookFromUser(Long user_id, Long book_id);


}
