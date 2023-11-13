package com.example.movie.service;

import com.example.movie.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.movie.entity.VideoSet;
import com.example.movie.entity.videoList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rdx
 * @since 2023-11-02
 */
public interface IVideoService extends IService<Video> {
        ArrayList<videoList> getVideoList();

        ArrayList<videoList> getVideoListByWeek();

        ArrayList<videoList> videoSetAll(int limit);

        ArrayList<videoList> recommend(int pageNo,int pageSize);

        VideoSet getVideoSetById(int setid);



}
