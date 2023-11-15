package com.example.user.service.impl;

import com.alibaba.fastjson2.JSON;
import com.example.file.commons.exception.ConditionException;
import com.example.file.utils.TokenUtil;
import com.example.user.entity.User;
import com.example.user.mapper.UserMapper;
import com.example.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.user.support.UserSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

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
    public String login(User user) {
        User result = userMapper.UserLogin(user);
        System.out.println(result);
        if (result != null){
            // 生成token,返回前端
            try {
                // 根据用户id生成对应的token，这样就能通过token知道是哪个用户发送的请求了
                String token = TokenUtil.generateToken(Long.valueOf(result.getId()));
                return token;
            } catch (Exception e) {
                throw new ConditionException("token生成失败");
            }
            // 不需要使用redis了，token自带时间验证功能，可以点进去看，我设置了一天失效
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
