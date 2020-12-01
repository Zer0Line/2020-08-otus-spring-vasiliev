package org.homework.spring.service;

import org.homework.spring.dao.BookDaoJdbc;
import org.homework.spring.dto.Author;
import org.homework.spring.dto.Book;
import org.homework.spring.dto.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Сервис для работы с книгами должен:")
@JdbcTest
@Import(BookDaoJdbc.class)
class BookServiceJDBCTest {

    private static final long EXPECTED_LIB_BOOKS = 2;
    private static final long DB_FAILED_STATUS = 0;
    private static final long TEST_BOOK_ID = 1;

    @Autowired
    private BookDaoJdbc bookDaoJdbc;

    @DisplayName("получать данные о библиотеке")
    @Test
    void shouldGetLibrary() {
        List<Book> allBooks = bookDaoJdbc.getAllBooks();
        assertThat(allBooks.size()).isEqualTo(EXPECTED_LIB_BOOKS);
    }

    @DisplayName("создавать книгу")
    @Test
    void shouldCreateBook() {
        Book testBook = new Book("test book", new Author(1L), new Genre(1L));
        int status = bookDaoJdbc.insert(testBook);
        assertThat(status).isNotEqualTo(DB_FAILED_STATUS);
    }

    @DisplayName("возвращать книгу по ID")
    @Test
    void shouldGetBookById() {
        Book book = bookDaoJdbc.getBook(TEST_BOOK_ID);
        assertThat(book.getId()).isEqualTo(TEST_BOOK_ID);
    }

    @DisplayName("обновлять книгу")
    @Test
    void shouldUpdateBook() {
        Book testBook = new Book(TEST_BOOK_ID, "test book", new Author(1L), new Genre(1L));
        int status = bookDaoJdbc.update(testBook);
        assertThat(status).isNotEqualTo(DB_FAILED_STATUS);
    }

    @DisplayName("удалять книку по ID")
    @Test
    void shouldDeleteBookById() {
        int status = bookDaoJdbc.deleteById(TEST_BOOK_ID);
        assertThat(status).isNotEqualTo(DB_FAILED_STATUS);
    }
}