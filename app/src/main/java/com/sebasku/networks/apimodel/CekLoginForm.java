package com.sebasku.networks.apimodel;

public class CekLoginForm {
    private String email;
    private String password;

    public CekLoginForm(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
