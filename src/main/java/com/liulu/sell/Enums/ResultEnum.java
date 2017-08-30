package com.liulu.sell.Enums;

import lombok.Data;

/**
 * Created by 刘璐
 * 2017/8/25 7:28
 */
public enum ResultEnum {

    SUCCESS("0000","处理成功")
    ,PARAM_ERROR("0001","参数输入有误")




    //异常
    ,PRODUCT_NO_FOUND("1000","商品不存在")
    ,PRODUCT_STOCK_ERROR("1001","商品数量不足")
    ,ORDER_NO_FOUND("1002","订单未找到")
    ,ORDER_DETAIL_NO_FOUND("1003","订单详情为找到")
    ,ORDER_STATE_ERROR("1004","订单状态有误")
    ,ORDER_CANCLE_ERROR("1005","订单状态更新失败")
    ,ORDER_DETAIL_EMPTY("1006","订单无商品")
    ,ORDER_ERROR_PAY_STATE("1007","支付状态有误")
    ,ORDER_PAY_ERROR("1008","支付状态有误")




    ,EXCEPON("9999","系统异常")
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
