package com.example.movie.controller;

import com.example.movie.entity.VideoSet;
import com.example.movie.entity.videoList;
import com.example.movie.service.IVideoService;
import com.example.user.service.IUserService;
import com.example.user.vo.Result;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.example.user.vo.Result.fail;
import static com.example.user.vo.Result.success;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author rdx
 * @since 2023-11-02
 */
@RestController
@RequestMapping("/api/videoSetDO")
@CrossOrigin
public class VideoController {
    @Resource
    private IVideoService iVideoService;

    @GetMapping("/videoSetHostList")
    public Result<Object> videoSetHostList(int limit,int carefullyChosen){
        ArrayList<videoList> list = iVideoService.getVideoList();
        System.out.println(list);
        if (list.isEmpty()){
           return fail("获取资源出错");
        }
            return success(list);
    }



    @GetMapping("/videoSetHostListByWeek")
    public Result<Object> videoSetHostListByWeek(int limit,int carefullyChosen){
        ArrayList<videoList> videoListByWeek = iVideoService.getVideoListByWeek();
        if (videoListByWeek.isEmpty()){
            return fail("获取资源出错");
        }
        return success(videoListByWeek);
    }

    @GetMapping("/videoSetAll")
    public Result<Object> videoSetAll(int limit){
        System.out.println("limit:"+limit);
        ArrayList<videoList> videoLists = iVideoService.videoSetAll(limit);
        if (videoLists.isEmpty()){
            return fail("获取资源出错");
        }
        return success(videoLists);
    }

    @GetMapping("/recommend")
    public Result<Object> recommend(int pageNo,int pageSize) {
        ArrayList<videoList> recommendList = iVideoService.recommend(pageNo, pageSize);
        if (recommendList.isEmpty()) {
           return fail("资源加载失败");
        }
        return success(recommendList);
    }
    @GetMapping("/queryById")
    public Result<Object> getVideoSetById(int id){
        VideoSet videoset = iVideoService.getVideoSetById(id);
        System.out.println(videoset);
        if (videoset == null){
            return fail("获取合集失败");
        }
        return success(videoset);
    }

}
