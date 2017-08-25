package com.liulu.sell.util;

import com.liulu.sell.Enums.ResultEnum;
import com.liulu.sell.ResultVO.ResultBody;

/**
 * Created by 刘璐
 * 2017/8/25 7:37
 */
public class ResultUtils {

    public static ResultBody getSuccessResult(ResultEnum resultEnum,Object data){
        ResultBody body = new ResultBody();
        body.setCode(resultEnum.getCode());
        body.setMsg(resultEnum.getState());
        body.setData(data);
        return body;
    }
}
