package com.example.user.mapper;

import com.example.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author rdx
 * @since 2023-10-24
 */
public interface UserMapper extends BaseMapper<User> {
    @Select("select *from cl_user where user_name=#{userName} and password=#{password}")
    User UserLogin(User user);
    @Insert("insert into cl_user(user_name,password) values (#{userName},#{password}) ")
    int UserRegister(User user);

    @Select("select * from cl_user where user_name=#{userName}")
    User IsExist(User user);
}
