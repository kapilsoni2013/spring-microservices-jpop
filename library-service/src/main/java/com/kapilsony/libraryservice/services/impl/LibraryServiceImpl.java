package com.kapilsony.libraryservice.services.impl;

import com.kapilsony.libraryservice.dto.BookRequest;
import com.kapilsony.libraryservice.dto.BookResponse;
import com.kapilsony.libraryservice.dto.UserResponse;
import com.kapilsony.libraryservice.entities.LibraryEntity;
import com.kapilsony.libraryservice.repositories.LibraryRepository;
import com.kapilsony.libraryservice.services.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {

    private final LibraryRepository libraryRepository;

    @Override
    public void issueBookToUser(Long user_id, Long book_id) {
        LibraryEntity libraryEntity=new LibraryEntity(user_id,book_id);
        libraryRepository.save(libraryEntity);
    }

    @Override
    public void releaseBookFromUser(Long user_id, Long book_id) {
        libraryRepository.releaseBookFromUser(user_id,book_id);
    }

}
