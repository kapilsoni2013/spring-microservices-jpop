package com.kapilsony.libraryservice.services.impl;

import com.kapilsony.libraryservice.dto.BookResponse;
import com.kapilsony.libraryservice.dto.UserResponse;
import com.kapilsony.libraryservice.entities.LibraryEntity;
import com.kapilsony.libraryservice.repositories.LibraryRepository;
import com.kapilsony.libraryservice.services.BookService;
import com.kapilsony.libraryservice.services.LibraryService;
import com.kapilsony.libraryservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor=@__(@Lazy))
public class LibraryServiceImpl implements LibraryService {

    private final LibraryRepository libraryRepository;
    private final UserService userService;
    private final BookService bookService;

    @Override
    public void issueBookToUser(Long user_id, Long book_id) {
        UserResponse user = userService.findUserById(user_id);
        BookResponse book = bookService.findBookById(book_id);
        LibraryEntity libraryEntity=new LibraryEntity(user.getUserId(),book.getBookId());
        libraryRepository.save(libraryEntity);
    }

    @Override
    public void releaseBookFromUser(Long user_id, Long book_id) {
        libraryRepository.releaseBookFromUser(user_id,book_id);
    }

    @Override
    public void releaseAllBooksFromUser(Long user_id) {
        libraryRepository.releaseAllBooksFromUser(user_id);
    }

    @Override
    public List<BookResponse> findUserBooks(Long user_id){
        return libraryRepository.findBooksByUserId(user_id)
                .stream()
                .map(bookId->{
                    try {
                        return bookService.findBookById(bookId);
                    } catch (Exception e){
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public void releaseAllUsersFromBook(Long book_id) {
        libraryRepository.releaseAllUsersFromBook(book_id);
    }


}
