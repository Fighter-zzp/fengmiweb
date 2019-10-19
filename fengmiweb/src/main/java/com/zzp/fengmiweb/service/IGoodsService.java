package com.zzp.fengmiweb.service;

import com.zzp.fengmiweb.entity.Goods;

import java.util.List;

public interface IGoodsService {
    boolean save(Goods goods);

    List<Goods> queryAll();

    List<Goods> queryByName(String name);

    List<Goods> queryByType(String type);

    Goods querySingle(int id);

    List<List<Goods>> queryIndex();

    List<Goods> queryNameAndPub(String name, String pubdate);

    int deleteById(int id);
}
