package org.homework.spring.mapper;

import org.homework.spring.dto.Author;
import org.homework.spring.dto.Book;
import org.homework.spring.dto.Genre;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Long bookId = resultSet.getLong("books.id");

        String bookName = resultSet.getString("books.name");

        Long authorId = resultSet.getLong("authors.id");
        String authorName = resultSet.getString("authors.name");

        String genresGenre = resultSet.getString("genres.genre");
        Long genresId = resultSet.getLong("genres.id");

        Author author = new Author(authorId, authorName);
        Genre genre = new Genre(genresId, genresGenre);

        return new Book(bookId, bookName, author, genre);
    }

}
