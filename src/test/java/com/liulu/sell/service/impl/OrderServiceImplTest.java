package com.liulu.sell.service.impl;

import com.liulu.sell.dataobject.OrderDetail;
import com.liulu.sell.dataobject.OrderMaster;
import com.liulu.sell.dto.OrderDTO;
import com.liulu.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 刘璐
 * 2017/8/29 20:14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void create() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("刘璐");
        orderDTO.setBuyerAddress("上海");
        orderDTO.setBuyerPhone("17621881872");
        orderDTO.setBuyerOpenid("1242134");

        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail o = new OrderDetail();
        o.setProductId("ed-a729-00d28a9b");
        o.setProductQuantity(1);
        orderDetails.add(o);
        orderDTO.setOrderDetailList(orderDetails);
        orderService.create(orderDTO);

    }

    @Test
    public void findOne() throws Exception {
       log.info(orderService.findOne("150").toString());
    }

    @Test
    public void findLis() throws Exception {
        PageRequest pageRequest = new PageRequest(0,2);
        int a =  orderService.findList(pageRequest).getTotalPages();
        log.info((String.valueOf(a)));
    }

    @Test
    public void cancel() throws Exception {
        orderService.cancel( orderService.findOne("150"));
    }

    @Test
    public void finish() throws Exception {
        orderService.finish(orderService.findOne("150"));
    }

    @Test
    public void paid() throws Exception {
        orderService.paid(orderService.findOne("150"));
    }


}