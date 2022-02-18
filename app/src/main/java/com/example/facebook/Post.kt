package com.example.facebook

class Post {
    var profile = 0
    var fullname: String? = null
    var photo = 0
    var isEditedView = false
    var moreThan5 = false

    constructor(profile: Int, fullname: String?, photo: Int) {
        this.profile = profile
        this.fullname = fullname
        this.photo = photo
    }

    constructor() {
        isEditedView = true
    }

    constructor(moreThan5: Boolean) {
        this.moreThan5 = moreThan5
    }
}
