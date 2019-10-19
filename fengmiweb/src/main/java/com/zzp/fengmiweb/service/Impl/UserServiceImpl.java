package com.zzp.fengmiweb.service.Impl;

import com.zzp.fengmiweb.dao.IUserDao;
import com.zzp.fengmiweb.entity.User;
import com.zzp.fengmiweb.service.IUserService;
import com.zzp.fengmiweb.utils.Base64Utils;
import com.zzp.fengmiweb.utils.MD5Utils;
import com.zzp.fengmiweb.utils.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao iud;

    @Override
    public boolean save(User user) {
        //使用密码文
        user.setPassword(MD5Utils.md5(user.getPassword()));
        return iud.insert(user)>0;
    }

    @Override
    public User getUserByName(String name) {
        return iud.select(name);
    }

    @Override
    public List<User> selectAll() {
        return iud.selectAll();
    }

    @Override
    public boolean checkName(String name) {
        return iud.checkName(name)>0;
    }

    @Override
    public boolean checkEmail(String email) {
        return iud.checkEmail(email)>0;
    }

    @Override
    public boolean checkLogin(String name) {
        return iud.select(name)!=null;
    }

    @Override
    public boolean activeUser(String email, String code) {
        if (!StrUtils.isEmpty(email,code))
            return iud.updateAcode(Base64Utils.decode(email),Base64Utils.decode(code))>0;
        return false;
    }

    @Override
    public int deleteById(int id) {
        return iud.deleteById(id);
    }

    @Override
    public List<User> userSearch(String username, String gender) {
        return iud.selectSearch(username,gender);
    }
}
