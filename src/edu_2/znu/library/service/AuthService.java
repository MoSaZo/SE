package edu_2.znu.library.service;

import edu_2.znu.library.model.*;
import java.util.*;

/**
 * سرویس احراز هویت و ثبت‌نام
 */
public class AuthService {
    private final List<Student> students;
    private final List<Employee> employees;
    private final List<Manager> managers;

    public AuthService(List<Student> s, List<Employee> e, List<Manager> m) {
        this.students = s;
        this.employees = e;
        this.managers = m;
    }

    public Optional<Student> loginStudent(String username, String password) {
        return students.stream()
                .filter(s -> s.username.equals(username) && s.password.equals(password) && s.active)
                .findFirst();
    }

    public Optional<Employee> loginEmployee(String username, String password) {
        return employees.stream()
                .filter(e -> e.username.equals(username) && e.password.equals(password))
                .findFirst();
    }

    public Optional<Manager> loginManager(String username, String password) {
        return managers.stream()
                .filter(m -> m.username.equals(username) && m.password.equals(password))
                .findFirst();
    }

    public Student registerStudent(String username, String password, String name, String email) {
        Student s = new Student(username, password, name, email);
        students.add(s);
        return s;
    }

    public List<Student> getStudents() {
        return students;
    }
}
