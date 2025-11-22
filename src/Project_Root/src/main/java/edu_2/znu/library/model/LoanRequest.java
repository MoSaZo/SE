package edu_2.znu.library.model;

import java.time.LocalDate;
import java.util.UUID;

/**
 * درخواست امانت کتاب
 */
public class LoanRequest {
    public final String id;
    public Student student;
    public Book book;
    public LocalDate startDate;
    public LocalDate endDate;
    public Status status = Status.PENDING;

    public enum Status { PENDING, APPROVED, REJECTED }

    public LoanRequest(Student s, Book b, LocalDate start, LocalDate end) {
        this.id = UUID.randomUUID().toString();
        this.student = s;
        this.book = b;
        this.startDate = start;
        this.endDate = end;
    }

    @Override
    public String toString() {
        return "[" + status + "] " + student.name + " → " + book.title +
" (" + startDate + " تا " + endDate + ")";
    }
}
