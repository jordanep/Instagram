package com.example.instagram;

import android.app.Application;

import com.example.instagram.models.Post;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);
        final Parse.Configuration configuration = new Parse.Configuration.Builder(this)
                .applicationId("fbu-eng-instagram")
                .clientKey("fbu-eng-ig-master")
                .server("https://fbu-eng-instagram.herokuapp.com/parse")
                .build();
        Parse.initialize(configuration);
    }
}
