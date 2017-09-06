package com.liulu.sell.service.impl;

import com.liulu.sell.Enums.OrderStatusEnum;
import com.liulu.sell.Enums.PayStatusEnum;
import com.liulu.sell.Enums.ResultEnum;
import com.liulu.sell.converter.OrderMaster2OrderDTOConverter;
import com.liulu.sell.dataobject.OrderDetail;
import com.liulu.sell.dataobject.OrderMaster;
import com.liulu.sell.dataobject.ProductInfo;
import com.liulu.sell.dto.CartDTO;
import com.liulu.sell.dto.OrderDTO;
import com.liulu.sell.exception.SellException;
import com.liulu.sell.repository.OrderDetailRepository;
import com.liulu.sell.repository.OrderMasterRepository;
import com.liulu.sell.service.OrderService;
import com.liulu.sell.service.ProductInfoService;
import com.liulu.sell.util.CommonUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 刘璐
 * 2017/8/28 7:32
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        Page<OrderMaster> page = orderMasterRepository.findAll(pageable);
        List<OrderDTO> orderDTOS = OrderMaster2OrderDTOConverter.converter(page.getContent());
        Page<OrderDTO> dtoPage = new PageImpl<OrderDTO>(orderDTOS,pageable,page.getTotalElements());
        return dtoPage;
    }

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = CommonUtils.getUuid().substring(0,3);
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        //查询商品（价格，数量）
        for(OrderDetail orderDetail : orderDTO.getOrderDetailList()){
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if (productInfo == null){
                throw new SellException(ResultEnum.SUCCESS);
            }
            //计算总价
            orderAmount = productInfo.getProductPrice().multiply(
                    new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
            //订单详情入库
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetail.setDetailId(CommonUtils.getUuid());
            orderDetail.setOrderId(orderId);
            orderDetailRepository.save(orderDetail);
        }

        //写入订单
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMasterRepository.save(orderMaster);

        //扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
        new CartDTO(e.getProductId(),e.getProductQuantity())
        ).collect(Collectors.toList());
        productInfoService.decreaseStock(cartDTOList);
        return orderDTO ;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if(orderMaster == null){
            throw new SellException(ResultEnum.ORDER_NO_FOUND);
        }
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderMaster.getOrderId());
        if(CollectionUtils.isEmpty(orderDetails)){
            throw new SellException(ResultEnum.ORDER_DETAIL_NO_FOUND);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetails);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> page = orderMasterRepository.findByBuyerOpenid(buyerOpenid,pageable);
        List<OrderDTO> orderDTOS = OrderMaster2OrderDTOConverter.converter(page.getContent());
        Page<OrderDTO> dtoPage = new PageImpl<OrderDTO>(orderDTOS,pageable,page.getTotalElements());
        return dtoPage;
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {

        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            throw new SellException(ResultEnum.ORDER_STATE_ERROR);
        }
        //修改订单状态
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderStatus(OrderStatusEnum.CANCLE.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster orderMaster1 = orderMasterRepository.save(orderMaster);
        if (orderMaster1 == null){
            throw new SellException(ResultEnum.ORDER_CANCLE_ERROR);
        }
        //返回库存
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new CartDTO(e.getProductId(),e.getProductQuantity())
        ).collect(Collectors.toList());
        productInfoService.increaseStock(cartDTOList);
        //TODO 退款
        return null;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            throw new SellException(ResultEnum.ORDER_STATE_ERROR);
        }
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderStatus(OrderStatusEnum.FINISH.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster orderMaster1 = orderMasterRepository.save(orderMaster);
        if(orderMaster1 == null){
            throw new SellException(ResultEnum.ORDER_CANCLE_ERROR);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            throw new SellException(ResultEnum.ORDER_STATE_ERROR);
        }
        //判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            throw new SellException(ResultEnum.ORDER_ERROR_PAY_STATE);
        }
        //修改支付状态
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster orderMaster1 = orderMasterRepository.save(orderMaster);
        if (orderMaster1 == null){
            throw new SellException(ResultEnum.ORDER_PAY_ERROR);
        }
        return orderDTO;
    }
}
