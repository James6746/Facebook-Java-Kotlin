package com.example.facebookjava;

import java.util.ArrayList;

public class Feed {

    private boolean isHeader = false;
    private Post post = null;
    private ArrayList<Story> stories = new ArrayList<Story>();



    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public ArrayList<Story> getStories() {
        return stories;
    }

    public void setStories(ArrayList<Story> stories) {
        this.stories = stories;
    }

    public Feed(ArrayList<Story> stories) {
        this.isHeader = false;
        this.stories = stories;
    }

    public Feed(Post post) {
        this.isHeader = false;
        this.post = post;
    }

    public Feed() {
        this.isHeader = true;
    }


}
