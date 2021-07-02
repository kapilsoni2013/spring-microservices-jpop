package com.kapilsony.libraryservice.services;

import com.kapilsony.libraryservice.dto.BookRequest;
import com.kapilsony.libraryservice.dto.BookResponse;
import com.kapilsony.libraryservice.dto.UserRequest;
import com.kapilsony.libraryservice.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "BOOK-SERVICE")
@RequestMapping("/books")
public interface BookRemoteService {
    @PostMapping
    public void createBook(@RequestBody BookRequest bookRequest);

    @PutMapping("/{id}")
    public void updateBook(@PathVariable Long id,@RequestBody BookRequest bookRequest);

    @GetMapping
    public List<BookResponse> getAllBooks();

    @GetMapping("/{id}")
    public BookResponse getBook(@PathVariable Long id);

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id);
}
