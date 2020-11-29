package org.homework.spring.service;

import org.homework.spring.dao.BookDao;
import org.homework.spring.dto.Author;
import org.homework.spring.dto.Book;
import org.homework.spring.dto.Genre;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceJDBC implements BookService {

    private final BookDao bookDao;

    public BookServiceJDBC(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public String getLibrary() {

        List<Book> allBooks = bookDao.getAllBooks();
        StringBuilder sb = new StringBuilder();

        allBooks.forEach(book -> {
            sb.append(book.getBookData());
            sb.append("---------------\n");
        });

        return sb.toString();
    }

    @Override
    public String getBookById(long id) {
        Book book = bookDao.getBook(id);
        return book.getBookData();
    }

    @Override
    public String deleteBookById(long id) {
        int status = bookDao.deleteById(id);
        return queryResult(status);
    }

    @Override
    public String createBook(Book book) {
        int status = bookDao.insert(book);
        return queryResult(status);
    }

    @Override
    public String updateBook(long bookId, long genreId, long authorId, String name) {
        Author author = new Author(authorId);
        Genre genre = new Genre(genreId);
        Book book = new Book(bookId, name, author, genre);
        int status = bookDao.update(book);
        return queryResult(status);
    }

    private String queryResult(int status) {
        return (status != 0) ? "operation success" : "operation failed";
    }
}
