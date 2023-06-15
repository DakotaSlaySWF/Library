package com.example.Library;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibraryBookRepository extends JpaRepository<LibraryBook,Long> {
    Optional<LibraryBook> findByAuthor(String author);
}
