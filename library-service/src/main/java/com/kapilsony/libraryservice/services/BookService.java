package com.kapilsony.libraryservice.services;

import com.kapilsony.libraryservice.dto.BookRequest;
import com.kapilsony.libraryservice.dto.BookResponse;

import java.util.List;

public interface BookService {
    List<BookResponse> getAllBooks();

    BookResponse findBookById(Long book_id);

    void addBookToLibrary(BookRequest bookRequest);

    void deleteBookFromLibrary(Long book_id);

    void updateBookInLibrary(Long book_id, BookRequest bookRequest);
}
