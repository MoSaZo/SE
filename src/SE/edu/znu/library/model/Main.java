package edu.znu.library.model;
import java.util.List;
import edu.znu.library.service.*;
import edu.znu.library.store.DataStore;

public class Main {
    public static void main(String[] args) throws Exception {
        // بارگذاری از فایل
        List<Student> students = DataStore.loadList("students.dat");
        List<Book> books = DataStore.loadList("books.dat");
        List<LoanRequest> requests = DataStore.loadList("loanRequests.dat");
        List<Loan> loans = DataStore.loadList("loans.dat");

        AuthService auth = new AuthService(students);
        BookService bookService = new BookService(books);
        LoanService loanService = new LoanService(requests, loans, books);

        // نمونه داده اولیه (اگر فایل خالی بود)
        if (books.isEmpty()) {
            bookService.addBook("شبی که ماه کامل شد", "نویسنده نمونه", 1399, 3);
            bookService.addBook("ساختمان داده‌ها", "مؤلف علم", 1395, 2);
        }

        // برای کوتاهی: یک مثال ثبت نام و جستجو
        Student s = auth.registerStudent("ali", "1234", "علی رضایی", "ali@example.com");
        var results = bookService.searchByTitleAuthorYear("ساختمان", null, null);
        System.out.println("نتایج جستجو:");
        results.forEach(b -> System.out.println(b.title + " — موجود: " + (b.availableCopies>0)));

        // ذخیره‌سازی پیش از خروج
        DataStore.saveList("students.dat", students);
        DataStore.saveList("books.dat", books);
        DataStore.saveList("loanRequests.dat", requests);
        DataStore.saveList("loans.dat", loans);
    }
}
