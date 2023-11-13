package com.example.movie.entity;

public class videoList {
    private Integer id;

    //视频封面
    private String cover;

   //视频标题
    private String title;

   //视频简介
    private String describe;

    private Integer setid;


    public videoList() {
    }

    public videoList(Integer id, String cover, String title, String describe, Integer setid) {
        this.id = id;
        this.cover = cover;
        this.title = title;
        this.describe = describe;
        this.setid = setid;
    }

    /**
     * 获取
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取
     * @return cover
     */
    public String getCover() {
        return cover;
    }

    /**
     * 设置
     * @param cover
     */
    public void setCover(String cover) {
        this.cover = cover;
    }

    /**
     * 获取
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取
     * @return describe
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * 设置
     * @param describe
     */
    public void setDescribe(String describe) {
        this.describe = describe;
    }

    /**
     * 获取
     * @return setid
     */
    public Integer getSetid() {
        return setid;
    }

    /**
     * 设置
     * @param setid
     */
    public void setSetid(Integer setid) {
        this.setid = setid;
    }

    public String toString() {
        return "videoList{id = " + id + ", cover = " + cover + ", title = " + title + ", describe = " + describe + ", setid = " + setid + "}";
    }
}
