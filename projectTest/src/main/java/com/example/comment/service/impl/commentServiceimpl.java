package com.example.comment.service.impl;

import com.example.comment.mapper.VideoCommentMapper;
import com.example.comment.service.commentService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class commentServiceimpl implements commentService {
    @Resource
    private VideoCommentMapper videoCommentMapper;
    @Override
    public int addComment(int setId, int videoId, String content, String userName, Date createTime) {
        int cnt = videoCommentMapper.addComment(setId, videoId, content, userName, createTime);
        return cnt;
    }
}
