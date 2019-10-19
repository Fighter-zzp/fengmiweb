package com.zzp.fengmiweb.service;

import com.zzp.fengmiweb.entity.GoodsType;

import java.util.List;

public interface IGoodTypeService {
    boolean save(GoodsType gt);

    List<GoodsType> queryByLevel1();

    List<GoodsType> queryAll();

    List<GoodsType> queryByLAndN(int flag, String name);

    int deleteById(int id);
}
