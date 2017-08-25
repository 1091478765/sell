package com.liulu.sell.ResultVO;

import lombok.Data;

/**
 * Created by 刘璐
 * 21:35
 */

@Data
public class ResultBody<T> {

    private String code;

    private String msg;

    private T data;
}
