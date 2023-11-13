package com.example.movie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author rdx
 * @since 2023-11-02
 */
@TableName("v_video")
public class Video implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 视频管理
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 视频名称
     */
    private String name;

    /**
     * 视频合集id
     */
    private Long videoSetId;

    /**
     * 视频本地存储路径
     */
    private String playUrl;

    /**
     * 简短简介
     */
    private String introductionShort;

    /**
     * 在合集内的排序
     */
    private Integer sort;

    /**
     * 视频时长 秒
     */
    private Integer videoDuration;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    /**
     * 预览图 垂直
     */
    private String coverVertical;

    /**
     * 预览图 水平
     */
    private String coverLevel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getVideoSetId() {
        return videoSetId;
    }

    public void setVideoSetId(Long videoSetId) {
        this.videoSetId = videoSetId;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public String getIntroductionShort() {
        return introductionShort;
    }

    public void setIntroductionShort(String introductionShort) {
        this.introductionShort = introductionShort;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getVideoDuration() {
        return videoDuration;
    }

    public void setVideoDuration(Integer videoDuration) {
        this.videoDuration = videoDuration;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getCoverVertical() {
        return coverVertical;
    }

    public void setCoverVertical(String coverVertical) {
        this.coverVertical = coverVertical;
    }

    public String getCoverLevel() {
        return coverLevel;
    }

    public void setCoverLevel(String coverLevel) {
        this.coverLevel = coverLevel;
    }

    @Override
    public String toString() {
        return "Video{" +
            "id = " + id +
            ", name = " + name +
            ", videoSetId = " + videoSetId +
            ", playUrl = " + playUrl +
            ", introductionShort = " + introductionShort +
            ", sort = " + sort +
            ", videoDuration = " + videoDuration +
            ", createTime = " + createTime +
            ", updateTime = " + updateTime +
            ", coverVertical = " + coverVertical +
            ", coverLevel = " + coverLevel +
        "}";
    }
}
