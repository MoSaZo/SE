package edu_2.znu.library.service;

import edu_2.znu.library.model.Book;
import java.util.*;
import java.util.stream.Collectors;

/**
 * سرویس مدیریت کتاب‌ها
 */
public class BookService {
    private final List<Book> books;

    public BookService(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> searchBooks(String title, String author, Integer year) {
        return books.stream().filter(b -> {
            boolean ok = true;
            if (title != null && !title.isBlank()) ok &= b.title.toLowerCase().contains(title.toLowerCase());
            if (author != null && !author.isBlank()) ok &= b.author.toLowerCase().contains(author.toLowerCase());
            if (year != null) ok &= b.year == year;
            return ok;
        }).collect(Collectors.toList());
    }

    public List<Book> searchByTitleOnly(String title) {
        return books.stream()
                .filter(b -> b.title.toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }
}
