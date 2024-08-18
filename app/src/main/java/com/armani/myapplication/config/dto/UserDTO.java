package com.armani.myapplication.config.dto;

public class UserDTO {
    private final String userName;
    private final String name;
    private final String family;

    public UserDTO( String userName, String name, String family) {
        this.userName = userName;
        this.name = name;
        this.family = family;
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public String getFamily() {
        return family;
    }
}
