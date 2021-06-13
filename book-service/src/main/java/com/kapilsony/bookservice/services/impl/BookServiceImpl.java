package com.kapilsony.bookservice.services.impl;

import com.kapilsony.bookservice.dto.BookRequest;
import com.kapilsony.bookservice.dto.BookResponse;
import com.kapilsony.bookservice.entities.BookEntity;
import com.kapilsony.bookservice.exceptions.BookNotFoundException;
import com.kapilsony.bookservice.mappers.BookMapper;
import com.kapilsony.bookservice.repositories.BookRepository;
import com.kapilsony.bookservice.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public void save(BookRequest bookRequest){
        bookRepository.save(bookMapper.toEntity(bookRequest));
    }

    @Override
    public void update(Long id, BookRequest bookRequest){
        BookEntity bookEntity = bookRepository.findById(id)
                .orElseThrow(()->new BookNotFoundException("Book Not exist by id"));
        bookEntity=bookMapper.updateBookFromDTO(bookEntity,bookRequest);
        bookRepository.save(bookEntity);
    }

    @Override
    public List<BookResponse> findAll(){
        return bookRepository.findAll().stream().map(bookMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public BookResponse findById(Long bookId){
        return bookRepository.findById(bookId).map(bookMapper::toDTO)
                .orElseThrow(()->new BookNotFoundException("Book Not exist by id"));
    }

    @Override
    public void delete(Long bookId){
        bookRepository.deleteById(bookId);
    }


}
