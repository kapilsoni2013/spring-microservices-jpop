package com.kapilsony.bookservice.controllers;

import com.kapilsony.bookservice.dto.BookRequest;
import com.kapilsony.bookservice.dto.BookResponse;
import com.kapilsony.bookservice.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@RefreshScope
public class BookController {

    private final BookService bookService;
    private final RestTemplate restTemplate;
    @Value("${testprop}")
    private String testprop;

    @GetMapping("/test")
    public String test(){
        String res = restTemplate.getForObject("http://user-service/users", String.class);
        return res;
    }

    @PostMapping
    public ResponseEntity<Void> createBook(@RequestBody BookRequest bookRequest, HttpServletRequest request) {
        bookService.save(bookRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBook(@PathVariable Long id,@RequestBody BookRequest bookRequest, HttpServletRequest request) {
        bookService.update(id,bookRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks(HttpServletRequest request) {
        List<BookResponse> books = bookService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable Long id, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id, HttpServletRequest request){
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
