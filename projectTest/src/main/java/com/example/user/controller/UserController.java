package com.example.user.controller;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.user.entity.User;
import com.example.user.mapper.UserMapper;
import com.example.user.service.IUserService;
import com.example.user.vo.Result;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.PanelUI;

import java.util.Map;

import static com.example.user.vo.Result.fail;
import static com.example.user.vo.Result.success;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author rdx
 * @since 2023-10-24
 */
@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {
    @Resource
     private IUserService iUserService;

    //用户登录
     @PostMapping("/login")
    public Result<Object> login( User user){
         System.out.println(user);
         Map<String, Object> map = iUserService.login(user);
//         System.out.println(map);
         if (map != null){
            return success(map);
         }else{
           return fail("账号或密码错误");
         }
     }

//     用户注册
    @PostMapping("/register")
    public Result<Object> register(User user){
        System.out.println(user);
        Map<String, Integer> map = iUserService.register(user);
        Integer cnt = map.get("user");
        System.out.println(cnt);
        if (cnt != 0){
            return success();
        }
        else {
            return fail("用户名已存在");
        }
    }
    @GetMapping("/getUserInfoById")
    public Result<Object> getUserInfo(HttpServletRequest request){
        String accessToken = request.getHeader("X-Access-Token");
        String[] split = accessToken.split(":");
        String str = split[1].replace("\"", "");
        String str1 = str.replaceAll("\"", "");
        String token = str1.replaceAll("}", "");
        System.out.println(token);
        Map<String, Object> map = iUserService.getUserInfo(token);
        if (map != null){
            return success(map);
        }else{
            return fail("登录信息失效");
        }
    }
    @PostMapping("/update")
    public Result<Object> updateUser(@RequestBody User user,HttpServletRequest request){
        System.out.println("update");
        String accessToken = request.getHeader("X-Access-Token");
        String[] split = accessToken.split(":");
        String str = split[1].replace("\"", "");
        String str1 = str.replaceAll("\"", "");
        String token = str1.replaceAll("}", "");
        Map<String, Object> map = iUserService.getUserInfo(token);
        if (map.get("username") == user.getUserName()){
            return success();
        }else{
            return fail();
        }
    }
}
