package com.zzp.fengmiweb.service;

import com.zzp.fengmiweb.entity.CartDetail;
import com.zzp.fengmiweb.entity.Order;
import com.zzp.fengmiweb.entity.ViewOrder;

import java.util.List;

public interface IOrderService {
    //下单
    public boolean save(String oid, int uid, int uaid);
    //直接下单
    public boolean insertDirect(int uid, String oid, int uaid, CartDetail cd);
    // 修改订单状态
    public boolean update(String oid, int flag);
    // 查询订单列表
    public List<Order> queryByUid(int uid);
    // 查询详情
    public ViewOrder queryOrderDetailById(String oid);
    // 查询全部订单
    public List<Order> queryAll();
    //根据用户姓名和订单的支付状态查询订单（admin）
    List<Order> selectByNameAndFlag(String username,Integer flag);
    //删除订单（admin）
    int deleteById(int id);
}
