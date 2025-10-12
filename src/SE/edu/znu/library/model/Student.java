package edu.znu.library.model;

public class Student extends User {
    public String name;
    public String email;

    public Student(String username, String passwordHash, String name, String email) {
        super(username, passwordHash);
        this.name = name;
        this.email = email;
    }
}
