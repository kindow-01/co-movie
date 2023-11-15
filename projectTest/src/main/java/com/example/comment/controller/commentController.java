package com.example.comment.controller;

import com.example.comment.entity.comment;
import com.example.comment.service.commentService;
import com.example.user.mapper.UserMapper;
import com.example.user.service.IUserService;
import com.example.user.vo.Result;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.example.user.vo.Result.fail;
import static com.example.user.vo.Result.success;
@RestController
@CrossOrigin
@RequestMapping("api/comment")
public class commentController {
    @Resource
    private IUserService iUserService;
    @Resource
    private commentService commentService;

    @Resource
    private UserMapper userMapper;

    @PostMapping
    public Result<Object> addComment(HttpServletRequest request, @RequestBody comment comment) {
        String accessToken = request.getHeader("X-Access-Token");
        String[] split = accessToken.split(":");
        String str = split[1].replace("\"", "");
        String str1 = str.replaceAll("\"", "");
        String token = str1.replaceAll("}", "");
        Map<String, Object> map = iUserService.getUserInfo(token);
        String userName = (String) map.get("username");
        comment.setUsername(userName);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        comment.setTime(simpleDateFormat.format(date));
        int i = commentService.addComment(comment);
        if (i == 0) {
            return fail("评论失败");
        }
        return success();
    }

    @GetMapping("/foreign/{setid}")
    public Result<Object> getCommentList(@PathVariable int setid) {
        List<comment> list = findByForeign(setid);
        if (list.isEmpty()) {
            return fail("获取资源失败");
        }
        return success(list);
    }

    @DeleteMapping("/{id}")
    public Result<Object> delete(@PathVariable int id) {
        commentService.deleteById(id);
        return success();
    }
    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") int setid,
                              @RequestParam(required = false, defaultValue = "1") int pageNum,
                              @RequestParam(required = false, defaultValue = "10") int pageSize) {
        List<comment> list = commentService.getPage(setid, pageNum, pageSize);
        if (list.isEmpty()){
            return fail("获取资源失败");
        }
         return success(list);
    }

    private List<comment> findByForeign(int setid) {
        List<comment> list = commentService.getCommentList(setid);
        for (comment comment : list) {
            String avatar = userMapper.getAvatar(comment.getUsername());
            if (avatar == null) {
                comment.setAvatar("https://puui.qpic.cn/vcover_hz_pic/0/mzc00200c2y0bad1675690775371/332?max_age=7776001");
            } else {
                comment.setAvatar(avatar);
            }
            int parentId = comment.getParentId();
            for (com.example.comment.entity.comment c : list) {
                if (c.getId() == parentId) {
                    comment.setParentComment(c);
                    break;
                }
            }
        }
        return list;
    }

}


