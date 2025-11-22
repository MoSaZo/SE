package edu_2.znu.library.service;

import edu_2.znu.library.model.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ReportServiceTest {

    @Test
    void showStatsRunsWithoutException() {
        List<Student> students = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        List<Book> books = new ArrayList<>();
        List<LoanRequest> requests = new ArrayList<>();
        List<Loan> loans = new ArrayList<>();

        ReportService rep = new ReportService(students, employees, books, requests, loans);
        // just ensure it doesn't throw
        rep.showStats();
    }
}
