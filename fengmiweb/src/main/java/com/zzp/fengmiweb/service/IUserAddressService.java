package com.zzp.fengmiweb.service;

import com.zzp.fengmiweb.entity.UserAddress;

import java.util.List;

public interface IUserAddressService {
    // 新增
    public boolean insert(UserAddress ua);
    // 修改
    public boolean update(UserAddress ua);
    // 查询地址
    public List<UserAddress> queryByUid(int uid);
}
