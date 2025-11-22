package edu_2.znu.library.service;

import edu_2.znu.library.model.*;
import java.time.LocalDate;
import java.util.*;

/**
 * سرویس مدیریت درخواست‌ها و امانت‌ها
 */
public class LoanService {
    private final List<LoanRequest> requests;
    private final List<Loan> loans;
    private final List<Book> books;

    public LoanService(List<LoanRequest> requests, List<Loan> loans, List<Book> books) {
        this.requests = requests;
        this.loans = loans;
        this.books = books;
    }

    public void createRequest(Student s, Book b, LocalDate start, LocalDate end) {
        requests.add(new LoanRequest(s, b, start, end));
    }

    public List<LoanRequest> getPendingRequests() {
        return requests.stream().filter(r -> r.status == LoanRequest.Status.PENDING).toList();
    }

    public void approveRequest(LoanRequest r) {
        if (r.book.availableCopies > 0) {
            r.status = LoanRequest.Status.APPROVED;
            r.book.availableCopies--;
            loans.add(new Loan(r.student, r.book, r.startDate, r.endDate));
        } else {
            r.status = LoanRequest.Status.REJECTED;
        }
    }
}
