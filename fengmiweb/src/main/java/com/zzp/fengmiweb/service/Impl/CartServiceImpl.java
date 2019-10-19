package com.zzp.fengmiweb.service.Impl;

import com.zzp.fengmiweb.dao.ICartDao;
import com.zzp.fengmiweb.entity.Cart;
import com.zzp.fengmiweb.entity.CartDetail;
import com.zzp.fengmiweb.entity.Goods;
import com.zzp.fengmiweb.entity.ViewCart;
import com.zzp.fengmiweb.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private ICartDao icd;
    @Override
    public boolean createCart(Cart cart) {
        return icd.insert(cart)>0;
    }

    @Override
    public boolean add(int cid, Goods gds, int num) {
        CartDetail cartDetail = icd.queryByCdid(cid, gds.getId());
        if (cartDetail == null){
            //第一次
            var cart = new Cart();
            CartDetail cd = new CartDetail();
            cd.setCid(cid);
            cd.setGid(gds.getId());
            cd.setNum(num);
            cd.setMoney(num*gds.getPrice());
            return icd.insertDetail(cd)>0;
        }else {
            //第一次之后
            cartDetail.setMoney(cartDetail.getMoney()+gds.getPrice());
            cartDetail.setNum(cartDetail.getNum()+1);
            return icd.updateDetail(cartDetail)>0;
        }
    }

    //更改数量
    @Override
    public boolean changeNum(int cid, Goods gds, int num) {
        CartDetail detail=icd.queryByCdid(cid,gds.getId());
        if(num==-1) {
            gds.setPrice(-gds.getPrice());
            detail.setNum(detail.getNum()-1);
        }else {
            detail.setNum(detail.getNum()+1);
        }

        detail.setMoney(detail.getMoney()+gds.getPrice());

        return icd.updateDetail(detail)>0;
    }
    //查询
    @Override
    public Cart queryByUid(int uid) {
        return icd.queryByUid(uid);
    }
    //详情查询
    @Override
    public List<ViewCart> queryCart(int cid) {
        return icd.queryCart(cid);
    }
    //删除购物车中的商品
    @Override
    public int deleteDetail(int cid, int gid) {
        if(gid == 0) {//清空购物车
            return icd.deleteDetailByCid(cid);
        }else {
            return icd.deleteDetail(cid,gid);
        }
    }
}
