package com.example.comment.service.impl;

import com.example.comment.entity.comment;
import com.example.comment.entity.returnComment;
import com.example.comment.mapper.VideoCommentMapper;
import com.example.comment.service.commentService;
import com.example.comment.vo.CommentList;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class commentServiceimpl implements commentService {
    @Resource
    private VideoCommentMapper videoCommentMapper;


    @Override
    public int addComment(comment comment) {
        int i = videoCommentMapper.addComment(comment);
        return i;
    }

    @Override
    public List<comment> getCommentList(int setid) {
        List<comment> list = videoCommentMapper.getCommentList(setid);
        return list;
    }

    @Override
    public void deleteById(int id) {
        videoCommentMapper.deleteById(id);
    }


}
