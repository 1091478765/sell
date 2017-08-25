package com.liulu.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@DynamicUpdate
@Data
public class ProductInfo {

    @Id
    private String productId;

    private String productName;

    private BigDecimal productPrice;

    /**库存*/
    private Integer productStock;

    private String productDescription;

    private String productIcon;

    /**商品状态 0 正常 1 ： 下架*/
    private Integer productStatus;

    /**类目编号*/
    private Integer categoryType;
}
