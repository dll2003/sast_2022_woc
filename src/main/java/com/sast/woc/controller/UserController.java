package com.sast.woc.controller;

import com.sast.woc.entity.User;
import com.sast.woc.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author xun
 * @create 2023/1/3 17:00
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 完成注册功能
     * 选做：对密码进行加密
     * @param user 用户实体类
     * @return 注册成功返回 success
     */
    @RequestMapping("/register")
    public ResultData<String> addUser(User user) {
        // todo 这里需要你补全
        userService.AddUser(user);
        return ResultData.success("success");
    }

    @GetMapping("/info")
    public ResultData<User> UserRequestInfo(HttpServletRequest request){
        String token =  AuthFilter.getTokenFromRequest(request);
        User user =userService.ReturnUserByToken(token);
        if (user == null)
        {
            throw new RuntimeException("This token is invalid");
        }
        return ResultData.success(user);
    }

    @PostMapping("/edit_info")
    public ResultData<String> UserChangeInfo(HttpServletRequest request, @RequestParam(defaultValue = "") String newname, @RequestParam(defaultValue = "") String newpassword, @RequestParam(defaultValue = "") String newemail)
    {
        String token =  AuthFilter.getTokenFromRequest(request);
        String oldname = JwtUtil.getUserNameFromToken(token);
        userService.UserChangeInfo(oldname,newname,newpassword,newemail);
        return ResultData.success("Change Success");
    }


}
