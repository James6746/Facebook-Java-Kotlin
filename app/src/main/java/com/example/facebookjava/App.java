package com.example.facebookjava;

import android.app.Application;

public class App extends Application {
    static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

    }
}
