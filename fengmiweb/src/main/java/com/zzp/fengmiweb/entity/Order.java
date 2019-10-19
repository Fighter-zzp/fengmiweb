package com.zzp.fengmiweb.entity;

import lombok.Data;

@Data
public class Order {
    private String id;
    private int uid;
    private int uaid;
    private String createtime;
    private double money;
    private int flag;
    private String username;
    private String address;
}
