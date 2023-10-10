package com.muratcangzm.trcomics.entities;

import com.google.firebase.firestore.FieldValue;

public class UserEntity {


    private final String username;
    private final String email;
    private final String profilePicUrl;
    private final FieldValue registerDate;

    public UserEntity(String username, String email, String profilePicUrl, FieldValue registerDate) {
        this.username = username;
        this.email = email;
        this.profilePicUrl = profilePicUrl;
        this.registerDate = registerDate;
    }


    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public FieldValue getRegisterDate() {
        return registerDate;
    }
}
