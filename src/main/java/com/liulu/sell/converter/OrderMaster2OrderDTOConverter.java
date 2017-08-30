package com.liulu.sell.converter;

import com.liulu.sell.dataobject.OrderMaster;
import com.liulu.sell.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 刘璐
 * 2017/8/29 22:34
 */
public class OrderMaster2OrderDTOConverter {

    public static OrderDTO converter(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> converter(List<OrderMaster> orderMasters){
        List<OrderDTO> orderDTOS = orderMasters.stream().map(e ->
            converter(e)
        ).collect(Collectors.toList());
        return orderDTOS;
    }
}
