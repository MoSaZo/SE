package edu_2.znu.library.model;

/**
 * کلاس کارمند کتابخانه
 */
public class Employee extends User {
    public String name;
    public int registeredBooks = 0;
    public int approvedLoans = 0;

    public Employee(String username, String password, String name) {
        super(username, password);
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " (" + username + ")";
    }
}
