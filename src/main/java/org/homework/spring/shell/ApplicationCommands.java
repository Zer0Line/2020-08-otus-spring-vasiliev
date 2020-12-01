package org.homework.spring.shell;

import org.homework.spring.dto.Author;
import org.homework.spring.dto.Book;
import org.homework.spring.dto.Genre;
import org.homework.spring.service.BookService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ApplicationCommands {

    private final BookService bookService;

    public ApplicationCommands(BookService bookService) {
        this.bookService = bookService;
    }

    @ShellMethod(value = "show library", key = "sl")
    public String showLibrary() {
        return bookService.getLibrary();
    }

    @ShellMethod(value = "create book (book name, genre id, author id)", key = "cb")
    public String createBook(@ShellOption({"book name", "name"}) String name,
                             @ShellOption({"genre id", "id"}) long genreId,
                             @ShellOption({"author id", "id"}) long authorId) {

        Author author = new Author(authorId);
        Genre genre = new Genre(genreId);
        return bookService.createBook(new Book(name, author, genre));
    }

    @ShellMethod(value = "get book (book id)", key = "gb")
    public String getBookById(@ShellOption({"book id", "id"}) long id) {
        return bookService.getBookById(id);
    }

    @ShellMethod(value = "update book (book id, genre id, author id, book name)", key = "ub")
    public String updateBook(@ShellOption({"book id", "id"}) long bookId,
                             @ShellOption({"genre id", "id"}) long genreId,
                             @ShellOption({"author id", "id"}) long authorId,
                             @ShellOption({"book name", "name"}) String name) {
        return bookService.updateBook(bookId, genreId, authorId, name);
    }

    @ShellMethod(value = "delete book (book id)", key = "db")
    public String deleteBook(@ShellOption({"book id", "id"}) long id) {
        return bookService.deleteBookById(id);
    }

}