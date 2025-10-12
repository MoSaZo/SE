package edu.znu.library.service;


import edu.znu.library.model.Student;
import edu.znu.library.model.User;


import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class AuthService {
    private final List<Student> students; // در نسخه کامل باید لیست User ها نگهداری شود شامل employees


    public AuthService(List<Student> students) {
        this.students = students;
    }


    public Optional<Student> loginStudent(String username, String password) {
// برای سادگی hash نمیکنیم
        return students.stream()
                .filter(s -> s.username.equals(username) && s.passwordHash.equals(password) && s.active)
                .findFirst();
    }


    public Student registerStudent(String username, String password, String name, String email) {
        Student s = new Student(username, password, name, email);
        students.add(s);
        return s;
    }
}