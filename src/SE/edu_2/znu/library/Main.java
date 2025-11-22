package edu_2.znu.library;

import edu_2.znu.library.model.*;
import edu_2.znu.library.service.*;
import edu_2.znu.library.store.DataStore;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

/**
 * کلاس اصلی – اجرای CLI با ذخیره‌سازی فایل
 */
public class Main {

    private static final Scanner sc = new Scanner(System.in);

    private static List<Student> students;
    private static List<Employee> employees;
    private static List<Manager> managers;
    private static List<Book> books;
    private static List<LoanRequest> requests;
    private static List<Loan> loans;

    private static AuthService auth;
    private static BookService bookService;
    private static LoanService loanService;
    private static ReportService reportService;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // بارگذاری داده‌ها از فایل
        loadData();

        // سرویس‌ها
        auth = new AuthService(students, employees, managers);
        bookService = new BookService(books);
        loanService = new LoanService(requests, loans, books);
        reportService = new ReportService(students, employees, books, requests, loans);

        initSampleData(); // داده نمونه اولیه در صورت خالی بودن

        while (true) {
            System.out.println("\n=== سیستم مدیریت کتابخانه دانشگاه ===");
            System.out.println("1. ورود به عنوان مهمان");
            System.out.println("2. ورود به عنوان دانشجو");
            System.out.println("3. ورود به عنوان کارمند");
            System.out.println("4. ورود به عنوان مدیر");
            System.out.println("0. خروج");
            System.out.print("گزینه را انتخاب کنید: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> guestMenu();
                case "2" -> studentLogin();
                case "3" -> employeeLogin();
                case "4" -> managerLogin();
                case "0" -> {
                    System.out.println("خروج از برنامه و ذخیره‌سازی داده‌ها...");
                    saveData();
                    return;
                }
                default -> System.out.println("گزینه نامعتبر!");
            }
        }
    }

    // ===================== بارگذاری و ذخیره‌سازی =====================

    private static void loadData() throws IOException, ClassNotFoundException {
        students = DataStore.loadList("students.dat");
        employees = DataStore.loadList("employees.dat");
        managers = DataStore.loadList("managers.dat");
        books = DataStore.loadList("books.dat");
        requests = DataStore.loadList("loanRequests.dat");
        loans = DataStore.loadList("loans.dat");
    }

    private static void saveData() throws IOException {
        DataStore.saveList("students.dat", students);
        DataStore.saveList("employees.dat", employees);
        DataStore.saveList("managers.dat", managers);
        DataStore.saveList("books.dat", books);
        DataStore.saveList("loanRequests.dat", requests);
        DataStore.saveList("loans.dat", loans);
    }

    // ===================== داده نمونه اولیه =====================

    private static void initSampleData() {
        if (managers.isEmpty()) managers.add(new Manager("admin", "admin", "مدیر سامانه"));
        if (employees.isEmpty()) employees.add(new Employee("emp", "1234", "کارمند کتابخانه"));
        if (books.isEmpty()) {
            books.add(new Book("مهندسی نرم‌افزار", "پرسمن", 2020, 5));
            books.add(new Book("ساختمان داده‌ها", "هوروویتز", 2019, 3));
        }
    }

    // ===================== منوی مهمان =====================

    private static void guestMenu() {
        while (true) {
            System.out.println("\n--- منوی مهمان ---");
            System.out.println("1. جستجوی کتاب بر اساس عنوان");
            System.out.println("2. مشاهده آمار کلی");
            System.out.println("0. بازگشت");
            System.out.print("انتخاب: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> {
                    System.out.print("عنوان کتاب را وارد کنید: ");
                    String t = sc.nextLine();
                    List<Book> results = bookService.searchByTitleOnly(t);
                    if (results.isEmpty()) System.out.println("کتابی یافت نشد.");
                    else results.forEach(System.out::println);
                }
                case "2" -> reportService.showStats();
                case "0" -> { return; }
                default -> System.out.println("گزینه نامعتبر!");
            }
        }
    }

    // ===================== منوی دانشجو =====================

    private static void studentLogin() {
        System.out.println("\n--- ورود دانشجو ---");
        System.out.print("نام کاربری: ");
        String u = sc.nextLine();
        System.out.print("رمز عبور: ");
        String p = sc.nextLine();

        Optional<Student> opt = auth.loginStudent(u, p);
        if (opt.isEmpty()) {
            System.out.println("ورود ناموفق!");
            return;
        }
        studentMenu(opt.get());
    }

    private static void studentMenu(Student s) {
        while (true) {
            System.out.println("\n--- منوی دانشجو (" + s.name + ") ---");
            System.out.println("1. جستجوی کتاب");
            System.out.println("2. درخواست امانت کتاب");
            System.out.println("3. مشاهده وضعیت درخواست‌ها");
            System.out.println("0. خروج");
            System.out.print("انتخاب: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> {
                    System.out.print("عنوان: "); String t = sc.nextLine();
                    System.out.print("نویسنده: "); String a = sc.nextLine();
                    System.out.print("سال: "); String y = sc.nextLine();
                    Integer year = y.isBlank() ? null : Integer.parseInt(y);
                    List<Book> found = bookService.searchBooks(t, a, year);
                    if (found.isEmpty()) System.out.println("کتابی یافت نشد.");
                    else found.forEach(System.out::println);
                }
                case "2" -> {
                    System.out.print("عنوان کتاب: "); String title = sc.nextLine();
                    List<Book> found = bookService.searchByTitleOnly(title);
                    if (found.isEmpty()) { System.out.println("کتاب یافت نشد."); break; }
                    Book b = found.get(0);
                    System.out.print("تاریخ شروع (YYYY-MM-DD): "); LocalDate start = LocalDate.parse(sc.nextLine());
                    System.out.print("تاریخ پایان (YYYY-MM-DD): "); LocalDate end = LocalDate.parse(sc.nextLine());
                    loanService.createRequest(s, b, start, end);
                    System.out.println("درخواست ثبت شد و منتظر تأیید است.");
                }
                case "3" -> requests.stream()
                        .filter(r -> r.student.equals(s))
                        .forEach(System.out::println);
                case "0" -> { return; }
                default -> System.out.println("گزینه نامعتبر!");
            }
        }
    }

    // ===================== منوی کارمند =====================

    private static void employeeLogin() {
        System.out.println("\n--- ورود کارمند ---");
        System.out.print("نام کاربری: ");
        String u = sc.nextLine();
        System.out.print("رمز عبور: ");
        String p = sc.nextLine();

        Optional<Employee> opt = auth.loginEmployee(u, p);
        if (opt.isEmpty()) { System.out.println("ورود ناموفق!"); return; }
        employeeMenu(opt.get());
    }

    private static void employeeMenu(Employee e) {
        while (true) {
            System.out.println("\n--- منوی کارمند (" + e.name + ") ---");
            System.out.println("1. ثبت کتاب جدید");
            System.out.println("2. مشاهده درخواست‌های در انتظار");
            System.out.println("3. تأیید درخواست");
            System.out.println("0. خروج");
            System.out.print("انتخاب: "); String choice = sc.nextLine();

            switch (choice) {
                case "1" -> {
                    System.out.print("عنوان: "); String t = sc.nextLine();
                    System.out.print("نویسنده: "); String a = sc.nextLine();
                    System.out.print("سال: "); int y = Integer.parseInt(sc.nextLine());
                    System.out.print("تعداد نسخه: "); int c = Integer.parseInt(sc.nextLine());
                    Book b = new Book(t, a, y, c);
                    bookService.addBook(b);
                    e.registeredBooks++;
                    System.out.println("کتاب ثبت شد.");
                }
                case "2" -> {
                    var pending = loanService.getPendingRequests();
                    if (pending.isEmpty()) System.out.println("درخواستی وجود ندارد.");
                    else pending.forEach(System.out::println);
                }
                case "3" -> {
                    System.out.print("شماره درخواست: "); String id = sc.nextLine();
                    Optional<LoanRequest> req = requests.stream().filter(r -> r.id.equals(id)).findFirst();
                    if (req.isEmpty()) { System.out.println("درخواست یافت نشد!"); break; }
                    loanService.approveRequest(req.get());
                    e.approvedLoans++;
                    System.out.println("درخواست تأیید شد.");
                }
                case "0" -> { return; }
                default -> System.out.println("گزینه نامعتبر!");
            }
        }
    }

    // ===================== منوی مدیر =====================

    private static void managerLogin() {
        System.out.println("\n--- ورود مدیر ---");
        System.out.print("نام کاربری: "); String u = sc.nextLine();
        System.out.print("رمز عبور: "); String p = sc.nextLine();

        Optional<Manager> opt = auth.loginManager(u, p);
        if (opt.isEmpty()) { System.out.println("ورود ناموفق!"); return; }
        managerMenu(opt.get());
    }

    private static void managerMenu(Manager m) {
        while (true) {
            System.out.println("\n--- منوی مدیر (" + m.name + ") ---");
            System.out.println("1. مشاهده آمار سیستم");
            System.out.println("2. فهرست دانشجویان");
            System.out.println("3. فهرست کارمندان");
            System.out.println("0. خروج");
            System.out.print("انتخاب: "); String choice = sc.nextLine();

            switch (choice) {
                case "1" -> reportService.showStats();
                case "2" -> students.forEach(System.out::println);
                case "3" -> employees.forEach(e ->
                        System.out.println(e.name + " | ثبت کتاب: " + e.registeredBooks + " | تأیید امانت: " + e.approvedLoans));
                case "0" -> { return; }
                default -> System.out.println("گزینه نامعتبر!");
            }
        }
    }
}
