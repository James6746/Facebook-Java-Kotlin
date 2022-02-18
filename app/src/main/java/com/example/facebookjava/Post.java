package com.example.facebookjava;

public class Post {
    private int profile;
    private String fullname;
    private int photo;
    public boolean isEditedView = false;
    boolean moreThan5 = false;

    public int getProfile() { return profile; }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public Post(int profile, String fullname, int photo) {
        this.profile = profile;
        this.fullname = fullname;
        this.photo = photo;
    }

    public Post() {
        isEditedView = true;
    }

    public Post(boolean moreThan5) {
        this.moreThan5 = moreThan5;
    }
}
