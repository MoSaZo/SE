package edu.znu.library.model;

import java.io.Serializable;
import java.util.UUID;

public abstract class User implements Serializable {
    public final UUID id;
    public String username;
    public String passwordHash; // برای سادگی نگهداری رشته، می‌توان hash ساده استفاده کرد
    public boolean active = true;

    public User(String username, String passwordHash) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.passwordHash = passwordHash;
    }
}
