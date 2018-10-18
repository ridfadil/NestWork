package com.sebasku.networks.apimodel;

/**
 * Created by fadil on 4/1/18.
 */

public class Profile {
    private String authorization;

    public Profile(String authorization) {
        this.authorization = authorization;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
}