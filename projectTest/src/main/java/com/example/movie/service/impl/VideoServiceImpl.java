package com.example.movie.service.impl;

import com.example.movie.entity.Video;
import com.example.movie.entity.VideoSet;
import com.example.movie.entity.videoList;
import com.example.comment.mapper.VideoCommentMapper;
import com.example.movie.service.IVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

import com.example.movie.mapper.videoListMapper;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rdx
 * @since 2023-11-02
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoCommentMapper, Video> implements IVideoService {
    @Resource
    private videoListMapper videoListMapper;
    @Resource
    private VideoCommentMapper videoCommentMapper;
    @Override
    public ArrayList<videoList> getVideoList() {
        ArrayList<videoList> videoList = videoListMapper.getVideoList();
        return videoList;
    }

    @Override
    public ArrayList<videoList> getVideoListByWeek() {
        ArrayList<videoList> videoListByWeek = videoListMapper.getVideoListByWeek();
        return videoListByWeek;
    }

    @Override
    public ArrayList<videoList> videoSetAll(int limit) {
        ArrayList<videoList> videoLists = videoListMapper.videoSetAll(limit);
        return videoLists;
    }
    public ArrayList<videoList> recommend(int pageNo,int pageSize){
        int start=(pageNo-1)*pageSize;
        int end=pageNo*pageSize-1;
        ArrayList<videoList> recommendList = videoListMapper.recommend(start, end);
        return recommendList;
    }

    @Override
    public VideoSet getVideoSetById(int setid) {
        VideoSet videoset = videoListMapper.getVideoSetById(setid);
        return videoset;
    }


}
