package org.homework.spring.dao;

import org.homework.spring.dto.Book;

import java.util.List;

public interface BookDao {

    int insert(Book book);

    int update(Book book);

    int deleteById(long id);

    Book getBook(long id);

    List<Book> getAllBooks();
}