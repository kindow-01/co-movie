package com.example.user.service.impl;

import com.alibaba.fastjson2.JSON;
import com.example.user.entity.User;
import com.example.user.mapper.UserMapper;
import com.example.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rdx
 * @since 2023-10-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Map<String, Object> login(User user) {
        User result = userMapper.UserLogin(user);
        System.out.println(result);
        if (result != null){
            //表示登录成功，生成token返回给前端
            UUID uuid =  UUID.randomUUID();
            String key="User"+uuid;
            //存入redis

            //这里可以将密码设置为null再存入redis
            //user.setPassword(null);

            //设置存活时间为30min
            redisTemplate.opsForValue().set(key,result,30, TimeUnit.MINUTES);
            //返回数据
            HashMap<String, Object> map = new HashMap<>();
            map.put("token",key);
            return map;
        }
        return null;
    }

    @Override
    public Map<String, Integer> register(User user) {
        User result = userMapper.IsExist(user);
        HashMap<String, Integer> map = new HashMap<>();
        //没有查到 代表可以注册
        if (result == null){
            System.out.println("执行前");
            int cnt = userMapper.UserRegister(user);
            System.out.println("执行后");
            System.out.println(cnt);
            map.put("user",cnt);
        }
        else{
            map.put("user",0);
        }
        return map;
    }




    @Override
    public Map<String, Object> getUserInfo(String token) {
        Object obj = redisTemplate.opsForValue().get(token);
        if (obj != null){
            User user = JSON.parseObject(JSON.toJSONString(obj), User.class);
            HashMap<String, Object> map = new HashMap<>();
            map.put("username",user.getUserName());
            map.put("id",user.getUserName());
            return map;
        }
        return null;
    }


}
