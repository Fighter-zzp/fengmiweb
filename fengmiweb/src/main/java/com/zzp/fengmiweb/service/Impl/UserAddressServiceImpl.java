package com.zzp.fengmiweb.service.Impl;
import java.util.List;

import com.zzp.fengmiweb.dao.IUserAddressDao;
import com.zzp.fengmiweb.entity.UserAddress;
import com.zzp.fengmiweb.service.IUserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAddressServiceImpl implements IUserAddressService {
	@Autowired
	private IUserAddressDao iuad;
	//新增收货地址
	@Override
	public boolean insert(UserAddress ua) {
		return iuad.insert(ua)>0;
	}
	//查询收货地址
	@Override
	public List<UserAddress> queryByUid(int uid) {
		return iuad.queryByUid(uid);
	}
	//修改地址
	@Override
	public boolean update(UserAddress ua) {
		return iuad.update(ua)>0;
	}

}
