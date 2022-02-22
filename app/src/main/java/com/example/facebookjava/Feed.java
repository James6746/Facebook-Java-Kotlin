package com.example.facebookjava;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.UUID;

public class Feed implements Parcelable {

    private boolean isHeader = false;
    private Post post = null;
    private NewAddedPost newAddedPost = null;
    private ArrayList<Story> stories = new ArrayList<Story>();
    private String id = UUID.randomUUID().toString();

    protected Feed(Parcel in) {
        isHeader = in.readByte() != 0;
    }

    public static final Creator<Feed> CREATOR = new Creator<Feed>() {
        @Override
        public Feed createFromParcel(Parcel in) {
            return new Feed(in);
        }

        @Override
        public Feed[] newArray(int size) {
            return new Feed[size];
        }
    };

    public NewAddedPost getNewAddedPost() {
        return newAddedPost;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public Post getPost() {
        return post;
    }

    public ArrayList<Story> getStories() {
        return stories;
    }

    public Feed(ArrayList<Story> stories) {
        this.isHeader = false;
        this.stories = stories;
    }

    public Feed(Post post) {
        this.isHeader = false;
        this.post = post;
    }

    public Feed(NewAddedPost newAddedPost) {
        this.isHeader = false;
        this.newAddedPost = newAddedPost;
    }

    public Feed() {
        this.isHeader = true;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (isHeader ? 1 : 0));
    }

    public String getId() {
        return id;
    }
}
