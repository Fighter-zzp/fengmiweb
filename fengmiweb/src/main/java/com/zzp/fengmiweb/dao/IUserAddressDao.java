package com.zzp.fengmiweb.dao;

import java.util.List;

import com.zzp.fengmiweb.entity.UserAddress;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface IUserAddressDao {
	//新增
	@Insert("insert into t_useraddress(name,phone,detail,uid,flag ) values(#{name},#{phone},#{detail},#{uid},1)")
	int insert(UserAddress ua);
	//修改
	@Update("update t_useraddress set name=#{name},phone=#{phone},detail=#{detail} where id=#{id}")
	int update(UserAddress ua);
	//修改
	@Update("update t_useraddress set flag=3 where id=#{id}")
	int updateDea(int id);
	//查询地址
	@Select("select * from t_useraddress where uid=#{uid} order by flag desc")
	@ResultType(UserAddress.class)
	List<UserAddress> queryByUid(int uid);
}