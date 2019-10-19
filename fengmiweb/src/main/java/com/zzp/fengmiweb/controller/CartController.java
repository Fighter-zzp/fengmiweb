package com.zzp.fengmiweb.controller;

import com.zzp.fengmiweb.entity.Cart;
import com.zzp.fengmiweb.entity.Goods;
import com.zzp.fengmiweb.entity.User;
import com.zzp.fengmiweb.service.ICartService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private ICartService ics;

    //添加购物车
    @RequestMapping("addCart")
    public String add(int gid, double price , HttpSession session){
        val goods = new Goods();
        goods.setId(gid);
        goods.setPrice(price);
        Cart cart = (Cart) session.getAttribute("cart");
        if (ics.add(cart.getId(),goods,1)){
            //添加成功 跳转到购物车页面
            return "cartSuccess";
        }else {
            return "index";
        }
    }

    //查询购物车
    @RequestMapping("getCart")
    public String get(HttpServletRequest req){
        var user = req.getSession().getAttribute("user");
        if (user==null) return "login";
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        req.setAttribute("carts",ics.queryCart(cart.getId()));
        //转发到购物车页面
        return "cart";
    }

    //购物车删除商品
    @RequestMapping("clearCart")
    public String clearCart(HttpSession session,int gid){
        Cart cart = (Cart) session.getAttribute("cart");
        int i = ics.deleteDetail(cart.getId(), gid);
        //重定向
        return "redirect:getCart";
    }

    //新增购物车
    @RequestMapping("updateCartNum")
    @ResponseBody
    public int update(int gid,double price,int num,HttpServletRequest req){
        //把数据传入goods中
        val goods = new Goods();
        goods.setId(gid);
        goods.setPrice(price);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        ics.changeNum(cart.getId(), goods, num);
        return 1;
    }

}
