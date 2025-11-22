package edu_2.znu.library.model;

import java.io.Serializable;
import java.util.UUID;

/**
 * کلاس پایه برای کاربران سیستم (دانشجو، کارمند، مدیر)
 */
public abstract class User implements Serializable {
    public final String id;
    public String username;
    public String password;
    public boolean active = true;

    public User(String username, String password) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
    }
}
