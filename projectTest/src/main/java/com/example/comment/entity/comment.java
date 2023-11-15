package com.example.comment.entity;

import java.util.Date;

public class comment {
    String id;
    String userName;
    String content;
    Date createTime;
    int videoId;
    int setId;


    public comment() {
    }

    public comment(String id, String userName, String content, Date createTime, int videoId, int setId) {
        this.id = id;
        this.userName = userName;
        this.content = content;
        this.createTime = createTime;
        this.videoId = videoId;
        this.setId = setId;
    }

    /**
     * 获取
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
     * @return createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取
     * @return videoId
     */
    public int getVideoId() {
        return videoId;
    }

    /**
     * 设置
     * @param videoId
     */
    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    /**
     * 获取
     * @return setId
     */
    public int getSetId() {
        return setId;
    }

    /**
     * 设置
     * @param setId
     */
    public void setSetId(int setId) {
        this.setId = setId;
    }

    public String toString() {
        return "comment{id = " + id + ", userName = " + userName + ", content = " + content + ", createTime = " + createTime + ", videoId = " + videoId + ", setId = " + setId + "}";
    }
}
