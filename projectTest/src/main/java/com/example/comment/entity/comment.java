package com.example.comment.entity;

import java.util.Date;

public class comment {
    int id;
    String username;
    String content;
    String time;
    int parentId;
    comment parentComment;
    int foreignId;

    String avatar;


    public comment() {
    }

    public comment(int id, String username, String content, String time, int parentId, comment parentComment, int foreignId, String avatar) {
        this.id = id;
        this.username = username;
        this.content = content;
        this.time = time;
        this.parentId = parentId;
        this.parentComment = parentComment;
        this.foreignId = foreignId;
        this.avatar = avatar;
    }

    /**
     * 获取
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取
     * @return time
     */
    public String getTime() {
        return time;
    }

    /**
     * 设置
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * 获取
     * @return parentId
     */
    public int getParentId() {
        return parentId;
    }

    /**
     * 设置
     * @param parentId
     */
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取
     * @return parentComment
     */
    public comment getParentComment() {
        return parentComment;
    }

    /**
     * 设置
     * @param parentComment
     */
    public void setParentComment(comment parentComment) {
        this.parentComment = parentComment;
    }

    /**
     * 获取
     * @return foreignId
     */
    public int getForeignId() {
        return foreignId;
    }

    /**
     * 设置
     * @param foreignId
     */
    public void setForeignId(int foreignId) {
        this.foreignId = foreignId;
    }

    /**
     * 获取
     * @return avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置
     * @param avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String toString() {
        return "comment{id = " + id + ", username = " + username + ", content = " + content + ", time = " + time + ", parentId = " + parentId + ", parentComment = " + parentComment + ", foreignId = " + foreignId + ", avatar = " + avatar + "}";
    }
}
