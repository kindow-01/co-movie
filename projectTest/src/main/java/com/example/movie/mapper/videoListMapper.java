package com.example.movie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.movie.entity.VideoSet;
import com.example.movie.entity.videoList;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;


public interface videoListMapper extends BaseMapper<videoList> {
    @Select("select * from videolist join videoset on videolist.setid =videoset.setid limit 4")
     ArrayList<videoList> getVideoList();

    @Select("select * from videolist join videoset on videolist.setid =videoset.setid limit 4")
    ArrayList<videoList> getVideoListByWeek();

    @Select("select * from videolist join videoset on videolist.setid =videoset.setid limit #{limit}")
    ArrayList<videoList> videoSetAll(int limit);

    @Select("select * from videolist join videoset on videolist.setid =videoset.setid limit #{start},#{end}")
    ArrayList<videoList> recommend(int start,int end);
    @Select("select * from comic where setid=#{setid}")
    VideoSet getVideoSetById(int setid);
    }
