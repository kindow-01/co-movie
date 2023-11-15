package com.example.comment.mapper;

import com.example.comment.entity.comment;
import com.example.comment.entity.returnComment;
import com.example.comment.vo.CommentList;
import com.example.movie.entity.Video;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author rdx
 * @since 2023-11-02
 */
public interface VideoCommentMapper extends BaseMapper<Video> {
       @Insert("insert into comment (username,content,time,parentId,foreignId) values (#{username},#{content},#{time},#{parentId},#{foreignId})")
       int addComment(comment comment);
       @Select("select * from comment where foreignId =#{setid}")
       List<comment> getCommentList(int setid);
       @Select("select *from comment where foreignId=#{setid} order by id limit #{start},#{end}")
       List<comment> selectPage(int start,int end,int setid);

}
