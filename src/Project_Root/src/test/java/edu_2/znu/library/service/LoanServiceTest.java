package edu_2.znu.library.service;

import edu_2.znu.library.model.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class LoanServiceTest {

    @Test
    void approveRequestReducesAvailableAndCreatesLoan() {
        List<LoanRequest> requests = new ArrayList<>();
        List<Loan> loans = new ArrayList<>();
        List<Book> books = new ArrayList<>();

        Student s = new Student("stu", "p", "A", "a@x");
        Book b = new Book("T", "Auth", 2021, 1);
        books.add(b);

        LoanRequest r = new LoanRequest(s, b, LocalDate.now(), LocalDate.now().plusDays(7));
        requests.add(r);

        LoanService svc = new LoanService(requests, loans, books);
        assertEquals(1, svc.getPendingRequests().size());
        svc.approveRequest(r);
        assertEquals(LoanRequest.Status.APPROVED, r.status);
        assertEquals(0, b.availableCopies);
        assertEquals(1, loans.size());
    }

    @Test
    void approveRequestRejectsWhenNoCopies() {
        List<LoanRequest> requests = new ArrayList<>();
        List<Loan> loans = new ArrayList<>();
        List<Book> books = new ArrayList<>();

        Student s = new Student("stu2", "p", "B", "b@x");
        Book b = new Book("T2", "A2", 2021, 0); // zero copies
        books.add(b);

        LoanRequest r = new LoanRequest(s, b, LocalDate.now(), LocalDate.now().plusDays(7));
        requests.add(r);

        LoanService svc = new LoanService(requests, loans, books);
        svc.approveRequest(r);
        assertEquals(LoanRequest.Status.REJECTED, r.status);
        assertEquals(0, loans.size());
    }
}
