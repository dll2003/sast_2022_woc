package com.sast.woc.controller;

import com.sast.woc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;


    /**
     * 完成登录功能
     * @param userName 用户名
     * @param password 密码
     * @return 如果登录成功返回 {@code true}, 否则 {@code false}
     */

    @PostMapping("/login")
    public ResultData<String> login(@RequestParam(defaultValue = "") String userName, @RequestParam(defaultValue = "") String password) {
        if (userName.equals("")  || password.equals("") ) {
            throw new RuntimeException("username and password should both not empty");
        }
        if (userService.IfNameExisted(userName)){
            if (userService.IfNamePasswordMatch(userName,password)){
                String token = JwtUtil.generateToken(userService.findByName(userName));
                userService.SaveToken(token,userName);
                return ResultData.success(token);
            }else{
                throw new RuntimeException("UserName and password do not match");
            }
        }else{
            throw new RuntimeException("This username doesn't exist");
        }
    }
}
