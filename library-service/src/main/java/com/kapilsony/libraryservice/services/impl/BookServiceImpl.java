package com.kapilsony.libraryservice.services.impl;

import com.kapilsony.libraryservice.dto.BookRequest;
import com.kapilsony.libraryservice.dto.BookResponse;
import com.kapilsony.libraryservice.services.BookRemoteService;
import com.kapilsony.libraryservice.services.BookService;
import com.kapilsony.libraryservice.services.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRemoteService bookRemoteService;
    private final LibraryService libraryService;
    @Override
    public List<BookResponse> getAllBooks() {
        return bookRemoteService.getAllBooks();
    }

    @Override
    public BookResponse findBookById(Long book_id) {
        return bookRemoteService.getBook(book_id);
    }

    @Override
    public void addBookToLibrary(BookRequest bookRequest) {
        bookRemoteService.createBook(bookRequest);
    }



    @Override
    public void deleteBookFromLibrary(Long book_id) {
        bookRemoteService.deleteBook(book_id);
        libraryService.releaseAllUsersFromBook(book_id);

    }

    @Override
    public void updateBookInLibrary(Long book_id, BookRequest bookRequest) {
        bookRemoteService.updateBook(book_id,bookRequest);
    }
}
