package com.liulu.sell.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.liulu.sell.Enums.OrderStatusEnum;
import com.liulu.sell.Enums.PayStatusEnum;
import com.liulu.sell.dataobject.OrderDetail;
import com.liulu.sell.util.serializer.Date2LongSerilizer;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by 刘璐
 * 2017/8/26 8:52
 */
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus;

    private Integer payStatus;

    @JsonSerialize(using = Date2LongSerilizer.class)
    private Date createTime;

    private Date updateTime;

    private List<OrderDetail> orderDetailList;
}
