package com.liulu.sell.Controller;

import com.liulu.sell.Enums.ResultEnum;
import com.liulu.sell.ResultVO.ResultBody;
import com.liulu.sell.exception.SellException;
import com.liulu.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by 刘璐
 * 2017/8/30 7:57
 */
@RestController
@RequestMapping(value = "/buyer/order")
@Slf4j
public class BuyerOrderController {

    //创建订单
    public ResultBody<? extends Object> create(@Valid OrderForm orderForm, BindingResult bindingResult) throws Exception{
        if(bindingResult.hasErrors()){
            throw new SellException(ResultEnum.PARAM_ERROR.getCode()
            ,bindingResult.getFieldError().getDefaultMessage());
        }

        return null;
    }

}
