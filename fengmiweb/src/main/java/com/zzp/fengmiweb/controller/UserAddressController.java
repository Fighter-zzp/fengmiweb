package com.zzp.fengmiweb.controller;

import javax.servlet.http.HttpServletRequest;

import com.zzp.fengmiweb.entity.User;
import com.zzp.fengmiweb.entity.UserAddress;
import com.zzp.fengmiweb.service.IUserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class UserAddressController {
	@Autowired
	private IUserAddressService iuas;
	
	@RequestMapping("userAddressAdd")
	public String add(UserAddress ua, HttpServletRequest req) {
		//获取登录信息
		ua.setUid(((User)req.getSession().getAttribute("user")).getId());
		//保存地址
		if(iuas.insert(ua)) {
			//保存成功 刷新地址列表
			req.setAttribute("addList", iuas.queryByUid(ua.getUid()));
		}
		return "self_info"; 
	}
	
	@RequestMapping("useraddressupdate")
	public String update(UserAddress ua,HttpServletRequest req) {
		//保存地址
		if(iuas.update(ua)) {
			//保存成功 刷新地址列表
			req.setAttribute("addList", iuas.queryByUid(ua.getUid()));
		}
		return "self_info"; 
	}
	
	@RequestMapping("userAddressShow")
	public String show(UserAddress ua,HttpServletRequest req) {
		req.setAttribute("addList", iuas.queryByUid(((User)req.getSession().getAttribute("user")).getId()));
		return "self_info";
	
	}

}
