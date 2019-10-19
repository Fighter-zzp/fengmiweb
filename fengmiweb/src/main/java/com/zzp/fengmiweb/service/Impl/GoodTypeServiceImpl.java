package com.zzp.fengmiweb.service.Impl;

import com.zzp.fengmiweb.dao.IGoodsTypeDao;
import com.zzp.fengmiweb.entity.GoodsType;
import com.zzp.fengmiweb.service.IGoodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GoodTypeServiceImpl implements IGoodTypeService {
    @Autowired
    private IGoodsTypeDao igd;

    @Override
    public boolean save(GoodsType gt) {
        return igd.insert(gt)>0;
    }

    @Override
    public List<GoodsType> queryByLevel1() {
        return igd.queryByLevel1();
    }

    @Override
    public List<GoodsType> queryAll() {
        return igd.queryAll();
    }

    @Override
    public List<GoodsType> queryByLAndN(int flag, String name) {
        return igd.queryByLAndN(flag,name);
    }

    @Override
    public int deleteById(int id) {
        return igd.deleteById(id);
    }
}
