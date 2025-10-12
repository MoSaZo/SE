package edu.znu.library.service;

import edu.znu.library.model.Book;
import java.util.List;
import java.util.stream.Collectors;

public class BookService {
    private final List<Book> books;

    public BookService(List<Book> books) { this.books = books; }

    public Book addBook(String title, String author, int year, int copies) {
        Book b = new Book(title, author, year, copies);
        books.add(b);
        return b;
    }

    public List<Book> searchByTitleAuthorYear(String title, String author, Integer year) {
        return books.stream().filter(b -> {
            boolean ok = true;
            if (title != null && !title.isBlank()) ok &= b.title.toLowerCase().contains(title.toLowerCase());
            if (author != null && !author.isBlank()) ok &= b.author.toLowerCase().contains(author.toLowerCase());
            if (year != null) ok &= b.year == year;
            return ok;
        }).collect(Collectors.toList());
    }

    public List<Book> searchByTitleOnly(String title) {
        return books.stream().filter(b -> b.title.toLowerCase().contains(title.toLowerCase())).collect(Collectors.toList());
    }
}
