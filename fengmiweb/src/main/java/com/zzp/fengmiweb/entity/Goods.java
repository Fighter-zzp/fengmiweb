package com.zzp.fengmiweb.entity;

import lombok.Data;

@Data
public class Goods {
    private int id;
    private String name;
    private double price;
    private String pubdate;
    private String typeName;
    private String intro;
    private String picture;
    private int flag;
    private int star;
}
