package com.liulu.sell.Enums;

import lombok.Getter;
import sun.print.DialogOwner;

/**
 * 刘璐
 * 2017/8/24
 */
@Getter
public enum ProductStatusEnum {

    UP(1,"上架")
    ,DOWN(0,"下架")
    ;

    private Integer code;

    private String msg;

    ProductStatusEnum( Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
