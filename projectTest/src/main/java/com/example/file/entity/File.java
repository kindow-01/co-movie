package com.example.file.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("t_file")
public class File {

    private Long id;

    private String url;

    private String type;

    private String md5;

    private Date createTime;

}
