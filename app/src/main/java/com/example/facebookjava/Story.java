package com.example.facebookjava;

import java.util.UUID;

public class Story {
    private int profile;
    private String fullname;
    public boolean isAddStroyView = false;

    public Story(int profile, String fullname) {
        this.profile = profile;
        this.fullname = fullname;
    }

    public Story() {
        isAddStroyView = true;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

}
