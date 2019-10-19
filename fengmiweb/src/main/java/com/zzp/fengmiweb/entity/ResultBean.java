package com.zzp.fengmiweb.entity;

import lombok.Data;

/**
 * 结果返回类，
 * 可设置结果返回的内容
 */
@Data
public class ResultBean {
	private int code;//编码  1 成功 2失败
	private String msg;//内容
}
