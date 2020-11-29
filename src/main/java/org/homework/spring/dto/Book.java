package org.homework.spring.dto;

public class Book {

    private final Author author;
    private final Genre genre;
    private Long id;
    private String name;

    public Book(Long id, String name, Author author, Genre genre) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    public Book(String name, Author author, Genre genre) {
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getBookData() {
        return String.format(" book id: %s\n book name: %s\n author: %s\n genre: %s\n",
                id, name, author.getName(), genre.getName());
    }
}