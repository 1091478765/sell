package com.liulu.sell.util;

import net.sf.json.JSONObject;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * 根据名称获取Cookie
     * @param request
     * @param name
     * @return
     */
    public static Cookie getCookie(HttpServletRequest request,String name){
        Cookie[] cookies = request.getCookies();
        Map<String,Cookie> map = new HashMap<>();
        for (Cookie cookie :cookies){
            map.put(cookie.getName(),cookie);
        }
        if (map.containsKey(name)){
            return map.get(name);
        } else {
            return null;
        }
    }
}
