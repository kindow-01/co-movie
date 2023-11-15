package com.example.user.support;

import com.example.file.commons.exception.ConditionException;
import com.example.file.utils.TokenUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @auther Kindow
 * @date 2023/11/2
 * @project imooc-bilibili
 */

// 这个请求类是用来根据token获取该用户的id的，需要前端每次请求带上请求头token，用于唯一识别用户
@Component
public class UserSupport {
    public Long getCurrentUserId() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String token = requestAttributes.getRequest().getHeader("token");
        Long userId = TokenUtil.verifyToken(token);
        if(userId < 0){
            throw new ConditionException("非法用户");
        }
        return userId;
    }
}
