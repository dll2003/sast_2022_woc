package com.sast.woc.service;

import com.sast.woc.entity.User;

import java.util.List;

/**
 * @Author xun
 * @create 2023/1/3 16:35
 */
public interface UserService {
    void AddUser(User user);
    void deleteByName (String name);
    User findByName (String name);
    void UserChangeInfo(String oldname,String newname,String newpassword,String newemail);
    List<User> findAllUsers();
    Boolean IfNameExisted(String name);
    Boolean IfNamePasswordMatch(String name,String password);
    void SaveToken(String token,String name);
    User ReturnUserByToken(String token);
}
