package com.example.comment.vo;

public class addcommentVO {
    String setId;
    String sourceId;
    String content;

    public addcommentVO() {
    }

    public addcommentVO(String setId, String sourceId, String content) {
        this.setId = setId;
        this.sourceId = sourceId;
        this.content = content;
    }

    /**
     * 获取
     *
     * @return setId
     */
    public String getSetId() {
        return setId;
    }

    /**
     * 设置
     * @param setId
     */
    public void setSetId(String setId) {
        this.setId = setId;
    }

    /**
     * 获取
     *
     * @return sourceId
     */
    public String getSourceId() {
        return sourceId;
    }

    /**
     * 设置
     * @param sourceId
     */
    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
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

    public String toString() {
        return "addcommentVO{setId = " + setId + ", sourceId = " + sourceId + ", content = " + content + "}";
    }
}
