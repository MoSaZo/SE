package edu.znu.library.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Loan implements Serializable {
    public final UUID id;
    public UUID requestId;
    public UUID studentId;
    public UUID bookId;
    public LocalDateTime takeDate;
    public LocalDateTime returnDate; // null if not returned
    public LocalDate dueDate;

    public Loan(UUID requestId, UUID studentId, UUID bookId, LocalDate dueDate) {
        this.id = UUID.randomUUID();
        this.requestId = requestId;
        this.studentId = studentId;
        this.bookId = bookId;
        this.takeDate = LocalDateTime.now();
        this.dueDate = dueDate;
    }
}
