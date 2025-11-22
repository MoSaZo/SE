package edu_2.znu.library.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class LoanRequestAndLoanTest {

    @Test
    void loanRequestAndToString() {
        Student s = new Student("stu", "p", "Reza", "r@example.com");
        Book b = new Book("Title", "Author", 2020, 2);
        LocalDate from = LocalDate.now();
        LocalDate to = from.plusDays(7);
        LoanRequest r = new LoanRequest(s, b, from, to);
        assertNotNull(r.id);
        assertEquals(LoanRequest.Status.PENDING, r.status);
        String str = r.toString();
        assertTrue(str.contains("Reza") || str.contains("Title"));
    }

    @Test
    void loanCreateAndToString() {
        Student s = new Student("stu2", "p", "Nima", "n@example.com");
        Book b = new Book("B2", "A2", 2019, 1);
        LocalDate take = LocalDate.now();
        LocalDate due = take.plusDays(10);
        Loan loan = new Loan(s, b, take, due);
        assertNotNull(loan.id);
        assertEquals(s, loan.student);
        assertEquals(b, loan.book);
        assertNull(loan.returnDate);
        String out = loan.toString();
        assertTrue(out.contains("Nima") || out.contains("B2"));
    }
}
