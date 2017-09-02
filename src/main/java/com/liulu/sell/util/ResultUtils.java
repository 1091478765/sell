package com.liulu.sell.util;

import com.liulu.sell.Enums.ResultEnum;
import com.liulu.sell.ResultVO.ResultBody;
import net.sf.json.JSONObject;

/**
 * Created by 刘璐
 * 2017/8/25 7:37
 */
public class ResultUtils {

    /**
     * 获取成功返回
     * @param resultEnum
     * @param data
     * @return
     */
    public static ResultBody getSuccessResult(ResultEnum resultEnum,Object data){
        ResultBody body = new ResultBody();
        body.setCode(resultEnum.getCode());
        body.setMsg(resultEnum.getMsg());
        body.setData(data);
        return body;
    }

    /**
     * 获取错误返回
     * @param resultEnum
     * @return
     */
    public static ResultBody getErrorResult (ResultEnum resultEnum){
        ResultBody body =  new ResultBody();
        body.setCode(resultEnum.getCode());
        body.setMsg(resultEnum.getMsg());
        return body;
    }

    /**
     * 获取错误代码
     * @param resultEnum
     * @param data
     * @return
     */
    public static ResultBody getErrorResult (ResultEnum resultEnum, String data){
        ResultBody body = new ResultBody();
        body.setCode(ResultEnum.PARAM_ERROR.getCode());
        body.setMsg(ResultEnum.PARAM_ERROR.getMsg());
        body.setData(data);
        return body;
    }

    /**
     * 获取错误返回
     * @param code
     * @param msg
     * @return
     */
    public static ResultBody getErrorResult(String code,String msg){
        ResultBody body =  new ResultBody();
        body.setCode(code);
        body.setMsg(msg);
        return body;
    }
}
