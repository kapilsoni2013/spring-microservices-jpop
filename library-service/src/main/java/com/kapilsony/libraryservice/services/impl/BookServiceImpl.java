package com.kapilsony.libraryservice.services.impl;

import brave.Span;
import brave.Tracer;
import com.kapilsony.libraryservice.dto.BookRequest;
import com.kapilsony.libraryservice.dto.BookResponse;
import com.kapilsony.libraryservice.services.BookRemoteService;
import com.kapilsony.libraryservice.services.BookService;
import com.kapilsony.libraryservice.services.LibraryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.cloud.sleuth.annotation.SpanTag;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRemoteService bookRemoteService;
    private final LibraryService libraryService;
    private final Tracer tracer;
    @Override
    public List<BookResponse> getAllBooks() {
        log.info("Original span going on");
        Span newSpan = tracer.nextSpan()
                .name("new sleuth span TEST")
                .tag("Key1","Val1")
                .annotate("Annotate method")
                .remoteServiceName("Book Remote Service")
                .start();
        try(Tracer.SpanInScope span = tracer.withSpanInScope(newSpan.start())){
            log.info("This work is being done in the new span");
        }finally {
            newSpan.finish();
        }
        log.info("Back to original span");
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
