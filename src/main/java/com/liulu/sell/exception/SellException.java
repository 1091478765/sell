package com.liulu.sell.exception;

import com.liulu.sell.Enums.ResultEnum;
import lombok.Getter;

/**
 * Created by 刘璐
 * 2017/8/28 7:41
 */
@Getter
public class SellException extends RuntimeException{

    private String code;

    private String data;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public SellException(String code) {
        this.code = code;
    }

    public SellException(String message, String code, String data) {
        super(message);
        this.code = code;
        this.data = data;
    }
}
