package com.liulu.sell.Enums;

import lombok.Getter;

/**
 * Created by 刘璐
 * 2017/8/25 21:24
 */
@Getter
public enum PayStatusEnum {

    WAIT(0,"等待支付")
    ,SUCCESS(1,"支付成功")

    ;

    private Integer code;

    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
