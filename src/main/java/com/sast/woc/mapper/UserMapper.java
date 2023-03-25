package com.sast.woc.mapper;

import com.sast.woc.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.HashMap;


/**
 * @Author xun
 * @create 2022/12/26 14:47
 */
@Mapper
@Repository
public interface UserMapper {
    void AddUser(User user);
    void deleteByName (String name);
    User findByName (String name);
    void UserChangeInfo(Map<String,String> map);
    List<User> findAllUsers();
    int IfNameExisted(String name);
    int IfNamePasswordMatch(Map<String,Object> params);

    void SaveTokenByName(Map<String,String> map);


    User ReturnUserByToken(String token);



}
