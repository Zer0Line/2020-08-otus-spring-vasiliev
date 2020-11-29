package org.homework.spring.dao;

import org.homework.spring.dto.Book;
import org.homework.spring.mapper.BookMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"SqlNoDataSourceInspection", "SqlDialectInspection"})
@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public int insert(Book book) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", book.getName(), Types.VARCHAR)
                .addValue("authorId", book.getAuthor().getId(), Types.BIGINT)
                .addValue("genreId", book.getGenre().getId(), Types.BIGINT);

        return namedParameterJdbcOperations.update(
                "insert into books (`AUTHOR_ID`, `GENRE_ID`, `NAME`) values (:authorId, :genreId, :name)",
                params
        );
    }

    @Override
    public int update(Book book) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("bookId", book.getId(), Types.BIGINT)
                .addValue("name", book.getName(), Types.VARCHAR)
                .addValue("authorId", book.getAuthor().getId(), Types.BIGINT)
                .addValue("genreId", book.getGenre().getId(), Types.BIGINT);

        return namedParameterJdbcOperations.update(
                "update books set AUTHOR_ID = :authorId, GENRE_ID = :genreId, NAME = :name " +
                        "where ID = :bookId", params);
    }

    @Override
    public int deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.update(
                "delete from books where id = :id", params
        );
    }

    @Override
    public Book getBook(long id) {
        Map<String, Long> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select books.id, books.name, authors.id, authors.name, genres.id, genres.genre from books " +
                        "left join authors on books.author_id = authors.id " +
                        "left join genres on books.genre_id = genres.id" +
                        " where books.id = :id", params, new BookMapper());
    }

    @Override
    public List<Book> getAllBooks() {
        return namedParameterJdbcOperations.query(
                "select books.id, books.name, authors.id, authors.name, genres.id, genres.genre from books " +
                        "left join authors on books.author_id = authors.id " +
                        "left join genres on books.genre_id = genres.id", Collections.emptyMap(), new BookMapper());
    }
}
