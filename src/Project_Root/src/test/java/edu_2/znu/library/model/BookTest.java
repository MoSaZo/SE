package edu_2.znu.library.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void createAndToString() {
        Book b = new Book("Clean Code", "Robert C. Martin", 2008, 3);
        assertNotNull(b.id);
        assertEquals("Clean Code", b.title);
        assertEquals("Robert C. Martin", b.author);
        assertEquals(2008, b.year);
        assertEquals(3, b.totalCopies);
        assertEquals(3, b.availableCopies);
        String s = b.toString();
        assertTrue(s.contains("Clean Code"));
        assertTrue(s.contains("Robert C. Martin"));
        assertTrue(s.contains("موجودی") || s.contains("available"));
    }
}
