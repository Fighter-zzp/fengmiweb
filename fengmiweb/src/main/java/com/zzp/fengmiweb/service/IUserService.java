package com.zzp.fengmiweb.service;

import com.zzp.fengmiweb.entity.User;

import java.util.List;

public interface IUserService {
    boolean save(User user);

    //根据用户名或邮件查找
    User getUserByName(String name);

    //查询全部
    List<User> selectAll();

    //校验用户名是否存在 -- 注册页面
    boolean checkName(String name);

    //校验邮箱是否存在 -- 注册页面
    boolean checkEmail(String email);

    //检查登录用户是否存在 用户名or邮箱
    boolean checkLogin(String name);

    //激活
    boolean activeUser(String email, String code);

    //删除用户
    int deleteById(int id);

    //用户搜索
    List<User> userSearch(String username,String gender);
}
