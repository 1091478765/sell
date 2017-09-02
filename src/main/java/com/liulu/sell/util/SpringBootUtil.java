package com.liulu.sell.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

    /**
     * 获取HttpServletReuqest对象
     * @return
     */
    public static HttpServletRequest getHttpServletRequest(){
        return getServletRequestAttributes().getRequest();
    }

    /**
     * 获取HttpSession对象
     * @return
     */
    public static HttpSession getHttpSession(){
        return getHttpServletRequest().getSession();
    }

    /**
     * 获取HttpServletResponse对象
     * @return
     */
    public static HttpServletResponse getHttpServletResponse(){
        return ((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();
    }

}
