package com.muratcangzm.trcomics.models;

import java.util.Date;

public class UserModel {


    private String username;
    private String email;
    private String profilePicUrl;
    private Date registerDate;

    public UserModel(){

    }

    public UserModel(String username, String email, String profilePicUrl, Date registerDate) {
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

    public Date getRegisterDate() {
        return registerDate;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
}
