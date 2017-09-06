package com.liulu.sell.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 刘璐
 * 2017/9/6 7:52
 */
public class CookieUtil {

    /**
     * 设置cookie
     * @param name
     * @param value
     * @param maxAge
     */
    public static void setCookie(HttpServletResponse response,String name,String value,int maxAge){
        Cookie cookie = new Cookie(name,value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        //SpringBootUtil.getHttpServletResponse().addCookie(cookie);
        response.addCookie(cookie);
        
    }
}
