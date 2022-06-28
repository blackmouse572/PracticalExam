package com.fptedu.practicalexam.Models;

public class User {
    private String username;
    private String password;
    private String fullname;
    private boolean status;
    private boolean admin;

    public User() {

    }

    public User(String username, String password, String fullname, boolean status, boolean admin) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.status = status;
        this.admin = admin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
