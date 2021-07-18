package com.kapilsony.libraryservice.controllers;

import com.kapilsony.libraryservice.dto.BookRequest;
import com.kapilsony.libraryservice.dto.BookResponse;
import com.kapilsony.libraryservice.dto.UserResponse;
import com.kapilsony.libraryservice.services.BookService;
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
public class BookController {
    private final BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<BookResponse>> getAllBooks(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
    }

    @GetMapping("/books/{book_id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable("book_id") Long book_id, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findBookById(book_id));
    }

    @PostMapping("/books")
    public ResponseEntity<Void> addBookToLibrary(@RequestBody BookRequest bookRequest, HttpServletRequest request) {
        bookService.addBookToLibrary(bookRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/books/{book_id}")
    public ResponseEntity<Void> updateBookInLibrary(@PathVariable("book_id") Long book_id,@RequestBody BookRequest bookRequest, HttpServletRequest request) {
        bookService.updateBookInLibrary(book_id,bookRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/books/{book_id}")
    public ResponseEntity<Void> deleteBookFromLibrary(@PathVariable("book_id") Long book_id, HttpServletRequest request) {
        bookService.deleteBookFromLibrary(book_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
