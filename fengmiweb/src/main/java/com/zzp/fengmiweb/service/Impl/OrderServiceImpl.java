package com.zzp.fengmiweb.service.Impl;

import com.zzp.fengmiweb.dao.ICartDao;
import com.zzp.fengmiweb.dao.IOrderDao;
import com.zzp.fengmiweb.entity.CartDetail;
import com.zzp.fengmiweb.entity.Order;
import com.zzp.fengmiweb.entity.OrderDetail;
import com.zzp.fengmiweb.entity.ViewOrder;
import com.zzp.fengmiweb.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private IOrderDao iod;
    @Autowired
    private ICartDao icd;
    //新增订单
    @Override
    public boolean save(String oid, int uid, int uaid) {
        List<CartDetail> cds= icd.queryByDetail(uid);
        double sum = 0;
        // 添加到订单详情表
        for (int i = 0; i < cds.size(); i++) {
            OrderDetail detail=new OrderDetail();
            detail.setGid(cds.get(i).getGid());
            detail.setOid(oid);
            detail.setNum(cds.get(i).getNum());
            detail.setMoney(cds.get(i).getMoney()*100);
            iod.insertDetail(detail);
            sum+=cds.get(i).getMoney();
        }
        Order order=new Order();
        order.setId(oid);
        order.setUaid(uaid);
        order.setUid(uid);
        order.setMoney(sum);
        iod.insert(order);
        icd.deleteDetailByCid(cds.get(0).getCid());
        icd.updateEmpty(cds.get(0).getCid());
        return true;
    }
    //更改订单状态
    @Override
    public boolean update(String oid, int flag) {
        // TODO Auto-generated method stub
        return iod.update(oid, flag)>0;
    }
    //查询用户的所有订单
    @Override
    public List<Order> queryByUid(int uid) {
        // TODO Auto-generated method stub
        return iod.queryByUid(uid);
    }
    //查询订单详情
    @Override
    public ViewOrder queryOrderDetailById(String oid) {
        // TODO Auto-generated method stub
        ViewOrder vo= iod.queryOrder(oid);
        vo.setList(iod.queryDetailList(oid));
        return vo;
    }
    //查询所有订单
    @Override
    public List<Order> queryAll() {
        // TODO Auto-generated method stub
        return iod.queryAll();
    }
    //直接下单
    @Override
    public boolean insertDirect(int uid, String oid, int uaid, CartDetail cd) {
        Order order=new Order();
        order.setId(oid);
        order.setUaid(uaid);
        order.setUid(uid);
        order.setMoney(cd.getMoney());
        iod.insert(order);
        OrderDetail detail=new OrderDetail();
        detail.setGid(cd.getGid());
        detail.setOid(oid);
        detail.setNum(cd.getNum());
        detail.setMoney(cd.getMoney());
        iod.insertDetail(detail);
        return true;
    }
    @Override
    public List<Order> selectByNameAndFlag(String username, Integer flag) {
        if(username != null && "".equals(username)) {
            username = null;
        }
        if(flag != null && 0 == flag) {
            flag = null;
        }

        //根据用户姓名和订单的支付状态查询订单（admin）
        return iod.selectByNameAndFlag(username, flag);
    }
    @Override
    public int deleteById(int id) {
        // 删除订单（admin）
        return iod.deleteById(id);
    }
}
