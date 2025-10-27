package edu_2.znu.library.model;

/**
 * کلاس مدیر سیستم
 */
public class Manager extends User {
    public String name;

    public Manager(String username, String password, String name) {
        super(username, password);
        this.name = name;
    }

    @Override
    public String toString() {
        return "مدیر: " + name;
    }
}
