package com.example.user.service;

import com.example.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Stack;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rdx
 * @since 2023-10-24
 */@Service
public interface IUserService extends IService<User> {
   public Map<String,Object> login(User user);

   public Map<String,Object> getUserInfo(String token);
   public Map<String,Integer> register(User user);
}
