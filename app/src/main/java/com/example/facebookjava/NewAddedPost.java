package com.example.facebookjava;

import android.os.Parcel;
import android.os.Parcelable;

public class NewAddedPost implements Parcelable {

    private String content;
    private String contentSource;
    private String contentTitle;
    private String contentImage;

    public NewAddedPost(String content, String contentSource, String contentTitle, String contentImage) {
        this.content = content;
        this.contentSource = contentSource;
        this.contentTitle = contentTitle;
        this.contentImage = contentImage;
    }

    protected NewAddedPost(Parcel in) {
        content = in.readString();
        contentSource = in.readString();
        contentTitle = in.readString();
        contentImage = in.readString();
    }

    public static final Creator<NewAddedPost> CREATOR = new Creator<NewAddedPost>() {
        @Override
        public NewAddedPost createFromParcel(Parcel in) {
            return new NewAddedPost(in);
        }

        @Override
        public NewAddedPost[] newArray(int size) {
            return new NewAddedPost[size];
        }
    };


    public String getContent() {
        return content;
    }

    public String getContentSource() {
        return contentSource;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public String getContentImage() {
        return contentImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(content);
        parcel.writeString(contentSource);
        parcel.writeString(contentTitle);
        parcel.writeString(contentImage);
    }
}
