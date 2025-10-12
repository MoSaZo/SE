package edu.znu.library.model;

import java.io.Serializable;
import java.util.UUID;

public class Book implements Serializable {
    public final UUID id;
    public String title;
    public String author;
    public int year;
    public int totalCopies;
    public int availableCopies;

    public Book(String title, String author, int year, int copies) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.author = author;
        this.year = year;
        this.totalCopies = copies;
        this.availableCopies = copies;
    }
}
