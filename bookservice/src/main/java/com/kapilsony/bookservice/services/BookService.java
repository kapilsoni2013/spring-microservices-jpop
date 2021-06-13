package com.kapilsony.bookservice.services;

import com.kapilsony.bookservice.dto.BookRequest;
import com.kapilsony.bookservice.dto.BookResponse;

import java.util.List;

public interface BookService {

    void save(BookRequest bookRequest);

    void update(Long id, BookRequest bookRequest);

    List<BookResponse> findAll();

    BookResponse findById(Long bookId);

    void delete(Long bookId);
}
