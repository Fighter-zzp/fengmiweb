package com.zzp.fengmiweb.service;

import com.zzp.fengmiweb.entity.Cart;
import com.zzp.fengmiweb.entity.Goods;
import com.zzp.fengmiweb.entity.ViewCart;

import java.util.List;

public interface ICartService {
    // 创建购物车
    boolean createCart(Cart cart);
    // 加入购物车 详情页
    boolean add(int cid, Goods gds, int num);
    // 修改数量 购物车页面
    boolean changeNum(int cid, Goods gds, int num);
    // 获取购物车对象
    Cart queryByUid(int uid);
    // 购物车的数据
    List<ViewCart> queryCart(int cid);
    // 删除购物车的商品
    int deleteDetail(int cid,int gid);
}
