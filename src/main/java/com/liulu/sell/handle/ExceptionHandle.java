package com.liulu.sell.handle;

import com.liulu.sell.Enums.ResultEnum;
import com.liulu.sell.ResultVO.ResultBody;
import com.liulu.sell.exception.SellException;
import com.liulu.sell.util.ResultUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 2017/8/30
 * Time: 10:46
 * To change this template use File | Settings | File Templates.
 */
@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultBody handle(Exception e){
        e.printStackTrace();
        if (e instanceof SellException){
            SellException sellException = (SellException) e;
            return ResultUtils.getErrorResult(sellException.getCode(),sellException.getMessage());
        }
        return ResultUtils.getErrorResult(ResultEnum.EXCEPON);
    }
}
