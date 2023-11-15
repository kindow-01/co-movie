package com.example.comment.service;

import com.example.comment.entity.comment;
import com.example.comment.entity.returnComment;
import com.example.comment.vo.CommentList;

import java.util.Date;
import java.util.List;

public interface commentService {
    int addComment(comment comment);
    List<comment> getCommentList(int setid);
    void deleteById(int id);
    List<comment> getPage(int setid,int pageno,int pagesize);
}
