package com.zzp.fengmiweb.entity;

import lombok.Data;

@Data
public class User {
    private int id;
    private String username;
    private String password;
    private String createtime;
    private int flag;
    private String email;
    private String gender;
    private String activatecode;
    private int role;
}
