package com.example.user.service;

import com.example.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rdx
 * @since 2023-10-24
 */@Service
public interface IUserService extends IService<User> {
   public String login(User user);

   public Map<String,Object> getUserInfo(Long id);
   public Map<String,Integer> register(User user);
}
