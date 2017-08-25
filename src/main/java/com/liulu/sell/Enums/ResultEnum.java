package com.liulu.sell.Enums;

import lombok.Data;

/**
 * Created by 刘璐
 * 2017/8/25 7:28
 */
public enum ResultEnum {

    SUCCESS("0000","处理成功")

    ;
    private String code;

    private String state;

    ResultEnum(String code, String state) {
        code = code;
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public String getState() {
        return state;
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
