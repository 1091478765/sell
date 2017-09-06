package com.liulu.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.liulu.sell.Enums.ResultEnum;
import com.liulu.sell.dataobject.OrderDetail;
import com.liulu.sell.dto.OrderDTO;
import com.liulu.sell.exception.SellException;
import com.liulu.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘璐
 * 2017/8/30 8:09
 */
@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderFrom){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerPhone(orderFrom.getPhone());
        orderDTO.setBuyerName(orderFrom.getName());
        orderDTO.setBuyerOpenid(orderFrom.getOpenid());
        orderDTO.setBuyerAddress(orderDTO.getBuyerAddress());
        Gson gson = new Gson();
        List<OrderDetail> orderDetails = new ArrayList<>();
        try{
            orderDetails = gson.fromJson(orderFrom.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());
        }catch(Exception e){
            log.info("JSON格式转换异常");
            throw new SellException(ResultEnum.JSON_CONVERTER_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetails);
        return orderDTO;
    }
}
