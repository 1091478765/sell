package com.liulu.sell.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by 刘璐
 * 2017/8/29 19:36
 */
@Data
public class CartDTO  implements Serializable{

    private static final long serialVersionUID = -5708090702985463109L;
    private String productId;

    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
