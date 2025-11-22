package edu_2.znu.library.model;

import java.io.Serializable;
import java.util.UUID;

/**
 * کلاس کتاب
 */
public class Book implements Serializable {
    public final String id;
    public String title;
    public String author;
    public int year;
    public int totalCopies;
    public int availableCopies;

    public Book(String title, String author, int year, int copies) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.author = author;
        this.year = year;
        this.totalCopies = copies;
        this.availableCopies = copies;
    }

    @Override
    public String toString() {
        return title + " | " + author + " | سال " + year + " | موجودی: " + availableCopies + "/" + totalCopies;
    }
}
