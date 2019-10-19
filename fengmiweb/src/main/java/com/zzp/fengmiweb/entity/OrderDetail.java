package com.zzp.fengmiweb.entity;

import lombok.Data;

@Data
public class OrderDetail {
    private int id;
    private String oid;
    private int gid;
    private int num;
    private double money;
}
