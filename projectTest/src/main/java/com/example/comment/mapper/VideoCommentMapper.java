package com.example.comment.mapper;

import com.example.movie.entity.Video;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;

import java.util.Date;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author rdx
 * @since 2023-11-02
 */
public interface VideoCommentMapper extends BaseMapper<Video> {
       @Insert("insert into comment (user_name,content,createTime,videoId,setId) values (#{user_name},#{content},#{createTime},#{videoId},#{setId})")
       int addComment(int setId, int videoId, String content, String user_name, Date createTime);
}
