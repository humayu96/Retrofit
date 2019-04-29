package com.example.retrofit;

import com.google.gson.annotations.SerializedName;

public class CommentC {

    String postId ;

    String id ;

    String name ;

    String email ;

    @SerializedName("body")
    String text ;

    public String getPostid() {
        return postId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getText() {
        return text;
    }
}
