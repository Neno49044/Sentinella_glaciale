package com.example.sentinellaglaciale.database;

public class User {
    private final String email;
    private final String username;
    private final String imageUri;

    public User(String email, String username, String imageUri) {
        this.email = email;
        this.username = username;
        this.imageUri = imageUri;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getImageUri() {
        return imageUri;
    }
}

