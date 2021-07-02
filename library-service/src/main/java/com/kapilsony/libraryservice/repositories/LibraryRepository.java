package com.kapilsony.libraryservice.repositories;

import com.kapilsony.libraryservice.entities.LibraryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LibraryRepository extends JpaRepository<LibraryEntity,Long> {

    @Transactional
    @Modifying
    @Query("delete from LibraryEntity where user_id=:user_id and book_id=:book_id")
    int releaseBookFromUser(@Param("user_id") Long user_id,@Param("book_id") Long book_id);

    @Query("select l.book_id from LibraryEntity l where l.user_id=:user_id")
    List<Long> findBooksByUserId(@Param("user_id") Long user_id);

    @Transactional
    @Modifying
    @Query("delete from LibraryEntity where user_id=:user_id")
    void releaseAllBooksFromUser(Long user_id);

    @Transactional
    @Modifying
    @Query("delete from LibraryEntity where book_id=:book_id")
    void releaseAllUsersFromBook(Long book_id);
}
