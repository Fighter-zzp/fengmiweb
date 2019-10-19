package com.zzp.fengmiweb.entity;

import com.zzp.fengmiweb.utils.StrUtils;
import lombok.Data;

@Data
public class ViewCart {
    private String name;
    private double price;
    private int num;
    private double money;
    private int gid;
}
