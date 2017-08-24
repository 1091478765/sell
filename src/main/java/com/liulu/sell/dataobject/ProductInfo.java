package com.liulu.sell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class ProductInfo {

    @Id
    private String productId;

    private String ProductName;

    private String ProductPrice;

    /**库存*/
    private String productStock;

    private String productDescription;

    private String productIcon;

    /**商品状态 0 正常 1 ： 下架*/
    private Integer productStatus;

    /**类目编号*/
    private Integer categoryType;
}
