package com.liulu.sell.Enums;

import lombok.Getter;

/**
 * Created by 刘璐
 * 2017/8/25 21:17
 */
@Getter
public enum OrderStatusEnum {

    NEW(0,"新订单")
    ,FINISH(1,"完结")
    ,CANCLE(2,"已取消")

    ;
    private Integer code;

    private String msg;

    OrderStatusEnum( Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

