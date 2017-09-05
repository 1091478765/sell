package com.liulu.sell.util;

import com.liulu.sell.Enums.ResultEnum;
import net.sf.json.JSONObject;
import com.liulu.sell.ResultVO.ResultBody;

import java.util.UUID;

/**
 * Created by 刘璐
 * 2017/8/28 8:07
 */
public class CommonUtils {

    /**
     * 生成id
     *
     * @return
     */
    public static synchronized String getUuid() {
        return System.currentTimeMillis() + UUID.randomUUID().toString().substring(26, 32);
    }

    /**
     * 检查如参不能为空
     *
     * @param joParam
     * @param param
     * @return
     */
    public static ResultBody ckeckParam(JSONObject joParam, String... param) {

        StringBuffer reuslt = new StringBuffer("");
        for (String key : param) {
            String value = joParam.optString(key);
            if (value == null || "".equals(value)) {
                reuslt.append(key).append(",");
            }
        }
        if (!"".equals(reuslt.toString())) {
            ResultBody body = new ResultBody();
            JSONObject json = new JSONObject();
            json.put("缺少参数为", reuslt.toString().substring(0, reuslt.toString().length() - 1));
            body.setMsg("");
            body.setData("");
            ResultUtils.getErrorResult(ResultEnum.PARAM_ERROR, json.toString());
            return body;
        } else {
            return null;
        }

    }
}
