package com.example.movie.entity;

public class VideoSet {
    private String title;
    private String cover;
    private String tags;
    private String describe;
    private String videoSrc;
    private int resolution;


    public VideoSet() {
    }

    public VideoSet(String title, String tags, String describe, String videoSrc, int resolution) {
        this.title = title;
        this.tags = tags;
        this.describe = describe;
        this.videoSrc = videoSrc;
        this.resolution = resolution;
    }

    public VideoSet(String title, String cover, String tags, String describe, String videoSrc, int resolution) {
        this.title = title;
        this.cover = cover;
        this.tags = tags;
        this.describe = describe;
        this.videoSrc = videoSrc;
        this.resolution = resolution;
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
     * @return tags
     */
    public String getTags() {
        return tags;
    }

    /**
     * 设置
     * @param tags
     */
    public void setTags(String tags) {
        this.tags = tags;
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
     * @return videoSrc
     */
    public String getVideoSrc() {
        return videoSrc;
    }

    /**
     * 设置
     * @param videoSrc
     */
    public void setVideoSrc(String videoSrc) {
        this.videoSrc = videoSrc;
    }

    /**
     * 获取
     * @return resolution
     */
    public int getResolution() {
        return resolution;
    }

    /**
     * 设置
     * @param resolution
     */
    public void setResolution(int resolution) {
        this.resolution = resolution;
    }

    public String toString() {
        return "VideoSet{title = " + title + ", tags = " + tags + ", describe = " + describe + ", videoSrc = " + videoSrc + ", resolution = " + resolution + "}";
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
}
