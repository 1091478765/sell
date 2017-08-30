package com.liulu.sell.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 2017/8/30
 * Time: 14:57
 * To change this template use File | Settings | File Templates.
 */
public class SpringBootUtil {

    public static ServletRequestAttributes getServletRequestAttributes(){
        return  (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    public
}
