package edu_2.znu.library.service;

import edu_2.znu.library.model.*;
import java.util.*;

/**
 * سرویس تولید گزارش و آمار سیستم
 */
public class ReportService {
    private final List<Student> students;
    private final List<Employee> employees;
    private final List<Book> books;
    private final List<LoanRequest> requests;
    private final List<Loan> loans;

    public ReportService(List<Student> s, List<Employee> e, List<Book> b, List<LoanRequest> r, List<Loan> l) {
        this.students = s;
        this.employees = e;
        this.books = b;
        this.requests = r;
        this.loans = l;
    }

    public void showStats() {
        System.out.println("\n--- آمار سیستم ---");
        System.out.println("تعداد دانشجویان: " + students.size());
        System.out.println("تعداد کارمندان: " + employees.size());
        System.out.println("تعداد کتاب‌ها: " + books.size());
        System.out.println("تعداد درخواست‌ها: " + requests.size());
        System.out.println("تعداد امانت‌های فعال: " + loans.stream().filter(l -> l.returnDate == null).count());
    }
}
