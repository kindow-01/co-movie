package com.example.comment.controller;

import com.example.comment.service.commentService;
import com.example.comment.vo.addcommentVO;
import com.example.movie.service.IVideoService;
import com.example.user.service.IUserService;
import com.example.user.vo.Result;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static com.example.user.vo.Result.fail;
import static com.example.user.vo.Result.success;
@RestController
@CrossOrigin
@RequestMapping("api/comment")
public class commentController {
    @Resource
    private IUserService iUserService;
    @Resource
    private commentService commentService;
    @PostMapping("/add")
    public Result<Object> addComment(HttpServletRequest request, @RequestBody addcommentVO vo) {
        String accessToken = request.getHeader("X-Access-Token");
        String[] split = accessToken.split(":");
        String str = split[1].replace("\"", "");
        String str1 = str.replaceAll("\"", "");
        String token = str1.replaceAll("}", "");
        Map<String, Object> map = iUserService.getUserInfo(token);
        String userName = (String) map.get("username");
        System.out.println(userName);
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        int i = commentService.addComment(Integer.parseInt(vo.getSetId()),Integer.parseInt(vo.getSourceId()), vo.getContent(), userName, date);
        if (i == 0){
            return fail("评论失败");
        }
        return success();
    }

    }

