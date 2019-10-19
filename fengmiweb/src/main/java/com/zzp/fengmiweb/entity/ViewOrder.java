package com.zzp.fengmiweb.entity;

import lombok.Data;

import java.util.List;

@Data
public class ViewOrder {
    private String id;
    private String createtime;
    private double money;
    private int flag;
    private String name;
    private String phone;
    private String detail;
    private List<ViewOrderDetail> list;//订单依赖
}
