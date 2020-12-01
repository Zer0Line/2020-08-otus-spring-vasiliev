package org.homework.spring.service;

import org.homework.spring.dto.Book;

public interface BookService {

    String getBookById(long id);

    String deleteBookById(long id);

    String createBook(Book book);

    String getLibrary();

    String updateBook(long bookId, long genreId, long authorId, String name);

}
