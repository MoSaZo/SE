package edu_2.znu.library.model;

/**
 * کلاس دانشجو
 */
public class Student extends User {
    public String name;
    public String email;

    public Student(String username, String password, String name, String email) {
        super(username, password);
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return name + " (" + username + ")";
    }
}
