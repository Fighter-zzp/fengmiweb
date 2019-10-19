package com.zzp.fengmiweb.entity;

import lombok.Data;

@Data
public class GoodsType {
    private int id;
    private String name;
    private int level;
    private int flag;
    private String parentName;
}
