package com.example.comment.service;

import java.util.Date;

public interface commentService {
    int addComment(int setId, int videoId, String content, String userName, Date createTime);
}
