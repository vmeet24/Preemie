package com.example.preemie.Model;

import com.google.firebase.firestore.Exclude;

import androidx.annotation.NonNull;

public class BlogPostId {

    @Exclude
    public String BlogPostId;

    public <T extends BlogPostId> T withId(@NonNull final String id) {
        this.BlogPostId = id;
        return (T) this;
    }

}
