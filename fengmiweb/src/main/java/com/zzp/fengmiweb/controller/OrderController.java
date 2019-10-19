package com.zzp.fengmiweb.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.zzp.fengmiweb.entity.*;
import com.zzp.fengmiweb.service.ICartService;
import com.zzp.fengmiweb.service.IOrderService;
import com.zzp.fengmiweb.service.IUserAddressService;
import com.zzp.fengmiweb.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderController {
	@Autowired
	private IOrderService ios;
	@Autowired
	private IUserAddressService ias;
	@Autowired
	private ICartService ics;
	// 下单
	@RequestMapping("addOrder")
	public String add(int t, int aid, HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		String oid = RandomUtils.createOrderId();
		boolean res = false;
		if (t == 1) {// 直接下单
			res = ios.insertDirect(user.getId(), oid, aid, (CartDetail) session.getAttribute("direct"));
		} else {// 购物车下单
			res = ios.save(oid, user.getId(), aid);
		}
		if (res) {
			// 下单成功
			model.addAttribute("oid", oid);
			return "pay";
		} else {
			return "index";
		}
	}
	// 列表
	@RequestMapping("getAllOrder")
	public String all(Integer t,Integer aid,Model model) {
		model.addAttribute("orderList", ios.queryAll());
		return "admin/showAllOrder";
	}
	//直接下单
	@RequestMapping("getDirectOrder")
	public String direct(Goods gs, Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		List<ViewCart> cds = new ArrayList<>();
		ViewCart cd = new ViewCart();
		cd.setGid(gs.getId());
		cd.setMoney(gs.getPrice());
		cd.setPrice(gs.getPrice());
		cd.setNum(1);
		cd.setName(gs.getName());
		cds.add(cd);
		CartDetail detail = new CartDetail();
		detail.setGid(gs.getId());
		detail.setMoney(gs.getPrice());
		detail.setNum(1);
		session.setAttribute("direct", detail);
		model.addAttribute("cartList", cds);
		model.addAttribute("addList", ias.queryByUid(user.getId()));
		model.addAttribute("type", 1);
		// 转发
		return "order";
	}
	// 查询用户的所有订单
	@RequestMapping("getOrderList")
	public String olist(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		model.addAttribute("orderList", ios.queryByUid(user.getId()));
		return "orderList";
	}
	// 订单预览
	@RequestMapping("getOrderView")
	public String viewlist(HttpServletRequest request, Model model) {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		User user = (User) request.getSession().getAttribute("user");
		model.addAttribute("type", 2);
		request.setAttribute("cartList", ics.queryCart(cart.getId()));
		request.setAttribute("addList", ias.queryByUid(user.getId()));
		return "order";
	}
	// 列表
	@RequestMapping("getOrderDetail")
	public String list(String oid,HttpSession session, Model model) {
		model.addAttribute("od", ios.queryOrderDetailById(oid));
		return "orderDetail";
	}
	//根据用户姓名和订单的支付状态查询订单（admin）
	@RequestMapping("selectOrderByNameAndFlag")
	public String selectByNameAndFlag(String username,Integer status, Model model) {
		model.addAttribute("orderList", ios.selectByNameAndFlag(username, status));
		return "admin/showAllOrder";
	}
	//删除订单（admin）
	@RequestMapping("deleteOrder")
	public String deleteById(int id, Model model) {
		model.addAttribute("od", ios.deleteById(id));
		return "orderDetail";
	}
	
	//修改订单（admin）
	@RequestMapping("sendOrder")
	public String sendOrder(String oid, Model model) {
		 ios.update(oid,3);
		return "redirect:getAllOrder";
	}
}