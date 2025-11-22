package edu_2.znu.library.service;

import edu_2.znu.library.model.Book;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    @Test
    void searchByTitleOnlyFindsMatches() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Java Programming", "A", 2015, 2));
        books.add(new Book("Python Cookbook", "B", 2018, 3));
        BookService svc = new BookService(books);
        var res = svc.searchByTitleOnly("java");
        assertEquals(1, res.size());
        assertTrue(res.get(0).title.toLowerCase().contains("java"));
    }
}
