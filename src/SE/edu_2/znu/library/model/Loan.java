package edu_2.znu.library.model;

import java.time.LocalDate;
import java.util.UUID;

/**
 * کلاس امانت کتاب (پس از تأیید)
 */
public class Loan {
    public final String id;
    public Student student;
    public Book book;
    public LocalDate takeDate;
    public LocalDate dueDate;
    public LocalDate returnDate;

    public Loan(Student s, Book b, LocalDate take, LocalDate due) {
        this.id = UUID.randomUUID().toString();
        this.student = s;
        this.book = b;
        this.takeDate = take;
        this.dueDate = due;
    }

    @Override
    public String toString() {
        return student.name + " → " + book.title +
" | از " + takeDate + " تا " + dueDate +
(returnDate != null ? " | بازگردانده شد" : " | هنوز بازنگشته");
    }
}
