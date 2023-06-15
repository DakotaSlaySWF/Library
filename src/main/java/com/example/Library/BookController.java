package com.example.Library;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class BookController {

    private final LibraryBookRepository repository;

    public BookController(LibraryBookRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/api/books")
    public ResponseEntity<String> postBook(){
        return ResponseEntity.ok("{'author': 'James Brown'}");
    }

    @GetMapping("/api/books/{author}")
    public ResponseEntity<Optional<LibraryBook>> getBook(@PathVariable String author) {

        Optional<LibraryBook> results = repository.findByAuthor(author);

        return ResponseEntity.ok(results);
    }
}
