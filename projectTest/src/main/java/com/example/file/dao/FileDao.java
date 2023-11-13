package com.example.file.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.file.entity.File;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


/**
 * @auther Kindow
 * @date 2023/11/9
 * @project imooc-bilibili
 */

public interface FileDao extends BaseMapper<File> {


    Integer addFile(File file);

    File getFileByMD5(@Param("fileMd5") String fileMd5);
}
