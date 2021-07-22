package com.kapilsony.libraryservice.services;

import com.kapilsony.libraryservice.configs.LoadBalancerConfiguration;
import com.kapilsony.libraryservice.dto.BookRequest;
import com.kapilsony.libraryservice.dto.BookResponse;
import com.kapilsony.libraryservice.dto.UserRequest;
import com.kapilsony.libraryservice.dto.UserResponse;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "book-service")
@LoadBalancerClient(name = "book-service",
        configuration= LoadBalancerConfiguration.class)
@RequestMapping("/books")
public interface BookRemoteService {
    @PostMapping
    public void createBook(@RequestBody BookRequest bookRequest);

    @PutMapping("/{id}")
    public void updateBook(@PathVariable("id") Long id,@RequestBody BookRequest bookRequest);

    @GetMapping
    public List<BookResponse> getAllBooks();

    @GetMapping("/{id}")
    public BookResponse getBook(@PathVariable("id") Long id);

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") Long id);
}
