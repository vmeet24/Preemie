package com.example.preemie.Model;

import java.io.Serializable;

/**
 * Created by brad on 2017/02/05.
 */

public class Comment implements Serializable {
    //private String userName;
    private String commentId;
    //private long timeCreated;
    private String comment;

    public Comment() {
    }

    public Comment(String commentId, String comment) {

        //this.user = user;
        this.commentId = commentId;
       // this.timeCreated = timeCreated;
        this.comment = comment;
    }

   /* public User getUser() {

        return user;
    }*/

   /* public void setUser(User user) {
        this.user = user;
    }*/

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    /*public long getTimeCreated() {
        return timeCreated;
    }*/

   /* public void setTimeCreated(long timeCreated) {
        this.timeCreated = timeCreated;
    }*/

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
