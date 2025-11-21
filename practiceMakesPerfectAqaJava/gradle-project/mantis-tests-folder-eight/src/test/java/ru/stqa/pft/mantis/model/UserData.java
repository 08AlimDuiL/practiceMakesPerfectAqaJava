package ru.stqa.pft.mantis.model;

public class UserData {
    private String username;
    private String email;

    public UserData withUsername(String username) {
        this.username = username;

        return this;
    }

    public UserData withEmail(String email) {
        this.email = email;

        return this;
    }

    public String getUsername() {

        return username;
    }

    public String getEmail() {

        return email;
    }
}