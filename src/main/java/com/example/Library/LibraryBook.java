package com.example.Library;


import jakarta.persistence.*;

@Entity
public class LibraryBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "author")
    private String author;

    public LibraryBook(String author) {
        this.author = author;
    }

    public LibraryBook() {

    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
