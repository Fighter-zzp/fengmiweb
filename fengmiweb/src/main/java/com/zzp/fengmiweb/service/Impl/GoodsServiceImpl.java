package com.zzp.fengmiweb.service.Impl;

import com.zzp.fengmiweb.dao.IGoodsDao;
import com.zzp.fengmiweb.entity.Goods;
import com.zzp.fengmiweb.service.IGoodsService;
import com.zzp.fengmiweb.utils.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsServiceImpl implements IGoodsService {
    @Autowired
    private IGoodsDao igd;


    @Override
    public boolean save(Goods goods) {
        return igd.save(goods)>0;
    }

    @Override
    public List<Goods> queryAll() {
        return igd.queryAll();
    }

    @Override
    public List<Goods> queryByName(String name) {
        return igd.queryName(name);
    }

    @Override
    public List<Goods> queryByType(String type) {
        return igd.queryByType(type);
    }

    @Override
    public Goods querySingle(int id) {
        return igd.querySingle(id);
    }

    @Override
    public List<List<Goods>> queryIndex() {
        List<List<Goods>> list=new ArrayList<List<Goods>>();
        list.add(igd.queryIndex("酒水饮料"));
        list.add(igd.queryIndex("饼干糕点"));
        list.add(igd.queryIndex("休闲零食"));
        return list;
    }

    @Override
    public List<Goods> queryNameAndPub(String name, String pubdate) {
        if ("".equals(name)) name=null;
        if ("".equals(pubdate)) pubdate=null;
        return igd.queryNameAndPub(name,pubdate);
    }

    @Override
    public int deleteById(int id) {
        return igd.deleteById(id);
    }
}
