package com.liulu.sell.util;

import java.util.UUID;

/**
 * Created by 刘璐
 * 2017/8/28 8:07
 */
public class CommonUtils {

    /**
     * 生成id
     * @return
     */
    public static synchronized String getUuid(){
        return System.currentTimeMillis() + UUID.randomUUID().toString().substring(26,32);
    }
}
