package com.kapilsony.libraryservice.repositories;

import com.kapilsony.libraryservice.entities.LibraryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends JpaRepository<LibraryEntity,Long> {
}
