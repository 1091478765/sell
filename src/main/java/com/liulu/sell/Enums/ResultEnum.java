package com.liulu.sell.Enums;

import lombok.Data;

/**
 * Created by 刘璐
 * 2017/8/25 7:28
 */
public enum ResultEnum {

    SUCCESS("0000","处理成功")



    //异常
    ,PRODUCT_NO_FOUND("1000","商品不存在")

    ;
    private String code;

    private String msg;

    ResultEnum(String code, String msg) {
        code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static ResultEnum getEnumByKey(String key){
        for (ResultEnum enums : ResultEnum.values()){
            if (enums.code.equals(key)){
                return enums;
            }
        }
        return null;
    }
}
