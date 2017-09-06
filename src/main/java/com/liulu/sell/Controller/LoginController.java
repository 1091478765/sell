package com.liulu.sell.Controller;

import com.liulu.sell.constant.CookieContant;
import com.liulu.sell.constant.RedisConstant;
import com.liulu.sell.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by 刘璐
 * 2017/9/5 22:46
 */
@RestController
@RequestMapping("/loginController")
public class LoginController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/login")
    public ModelAndView login(@RequestParam(value = "openid",defaultValue = "123") String openid
    , HttpServletResponse response){
        //将数据设置到redis
        Integer expire = RedisConstant.EXPIRE;
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX,token)
                ,openid,expire, TimeUnit.SECONDS);
        //将数据设置到cookie
        CookieUtil.setCookie(response,CookieContant.TOKEN,token,expire);
        return null;
    }

    @RequestMapping("/layout")
    public ModelAndView layout(HttpServletRequest request,HttpServletResponse response){
        //获取Cookie
        Cookie cookie = CookieUtil.getCookie(request,CookieContant.TOKEN);
        if(cookie != null){
            //清除redis记录
            redisTemplate.opsForValue().getOperations().delete(CookieContant.TOKEN);
        }
        //清除cookie
        CookieUtil.setCookie(response,CookieContant.TOKEN,null,0);
        return null;
    }


}
