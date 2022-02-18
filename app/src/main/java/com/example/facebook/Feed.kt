package com.example.facebook

class Feed {

    var isHeader: Boolean = false
    var post: Post? = null
    var stories: ArrayList<Story> = ArrayList()

    constructor() {
        this.isHeader = true
    }

    constructor(post: Post?) {
        this.isHeader = false
        this.post = post
    }

    constructor(stories: ArrayList<Story>) {
        this.isHeader = false
        this.stories = stories
    }

}