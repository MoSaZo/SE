package edu.znu.library.service;

import edu.znu.library.model.Book;
import edu.znu.library.model.Loan;
import edu.znu.library.model.LoanRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class LoanService {
    private final List<LoanRequest> requests;
    private final List<Loan> loans;
    private final List<Book> books;

    public LoanService(List<LoanRequest> requests, List<Loan> loans, List<Book> books) {
        this.requests = requests;
        this.loans = loans;
        this.books = books;
    }

    public LoanRequest createRequest(UUID studentId, UUID bookId, LocalDate start, LocalDate end) {
        LoanRequest r = new LoanRequest(studentId, bookId, start, end);
        requests.add(r);
        return r;
    }

    public List<LoanRequest> pendingForTodayOrBefore(LocalDate today) {
        return requests.stream().filter(r -> r.status == LoanRequest.Status.PENDING && ( !r.startDate.isAfter(today) ))
                .collect(Collectors.toList());
    }

    public Optional<LoanRequest> approveRequest(UUID requestId) {
        Optional<LoanRequest> opt = requests.stream().filter(r -> r.id.equals(requestId)).findFirst();
        if (opt.isEmpty()) return Optional.empty();
        LoanRequest r = opt.get();
        if (r.status != LoanRequest.Status.PENDING) return Optional.empty();
        Optional<Book> bOpt = books.stream().filter(b -> b.id.equals(r.bookId)).findFirst();
        if (bOpt.isEmpty()) return Optional.empty();
        Book b = bOpt.get();
        if (b.availableCopies <= 0) {
            r.status = LoanRequest.Status.REJECTED;
            return Optional.of(r);
        }
        b.availableCopies -= 1;
        r.status = LoanRequest.Status.APPROVED;
        Loan loan = new Loan(r.id, r.studentId, r.bookId, r.endDate);
        loans.add(loan);
        return Optional.of(r);
    }

    public boolean receiveReturn(UUID loanId) {
        Optional<Loan> opt = loans.stream().filter(l -> l.id.equals(loanId)).findFirst();
        if (opt.isEmpty()) return false;
        Loan l = opt.get();
        if (l.returnDate != null) return false; // already returned
        l.returnDate = java.time.LocalDateTime.now();
        books.stream().filter(b -> b.id.equals(l.bookId)).findFirst().ifPresent(b -> b.availableCopies += 1);
        return true;
    }
}
