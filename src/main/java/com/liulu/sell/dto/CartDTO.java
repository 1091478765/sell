package com.liulu.sell.dto;

import lombok.Data;

/**
 * Created by 刘璐
 * 2017/8/29 19:36
 */
@Data
public class CartDTO {

    private String productId;

    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
