package com.example.facebook

class Story {
    var profile = 0
    var fullname: String? = null
    var isAddStroyView = false

    constructor(profile: Int, fullname: String?) {
        this.profile = profile
        this.fullname = fullname
    }

    constructor() {
        isAddStroyView = true
    }
}
