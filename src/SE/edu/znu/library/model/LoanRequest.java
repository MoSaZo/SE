package edu.znu.library.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class LoanRequest implements Serializable {
    public final UUID id;
    public UUID studentId;
    public UUID bookId;
    public LocalDate startDate;
    public LocalDate endDate;
    public Status status;
    public LocalDateTime requestedAt;

    public enum Status { PENDING, APPROVED, REJECTED }

    public LoanRequest(UUID studentId, UUID bookId, LocalDate startDate, LocalDate endDate) {
        this.id = UUID.randomUUID();
        this.studentId = studentId;
        this.bookId = bookId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = Status.PENDING;
        this.requestedAt = LocalDateTime.now();
    }
}
