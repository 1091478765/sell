package com.liulu.sell.aspect;

import com.liulu.sell.Enums.ResultEnum;
import com.liulu.sell.constant.CookieContant;
import com.liulu.sell.constant.RedisConstant;
import com.liulu.sell.exception.SellException;
import com.liulu.sell.util.CookieUtil;
import com.liulu.sell.util.SpringBootUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by 刘璐
 * 2017/9/6 22:31
 */
@Aspect
@Component
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /*@Pointcut("execution(public * com.liulu.sell.Controller.Seller*.*(..))" +
    "&& !execution(public * com.liulu.sell.Controller.SellerOrderController.*(..))" )*/
    @Pointcut("execution(public * com.liulu.sell.Controller.Seller*.*(..))")
    public void verify(){}

    @Before("verify()")
    public void doVerify(){
        HttpServletRequest request = SpringBootUtil.getHttpServletRequest();
        Cookie cookie = CookieUtil.getCookie(request, CookieContant.TOKEN);
        if (cookie == null){
            throw new SellException(ResultEnum.LOGIN_TIMEOUT);
        }
        //redis中查询
        String tokenValue = stringRedisTemplate.opsForValue().get(
                String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
        if (StringUtils.isEmpty(tokenValue)){
            throw new SellException(ResultEnum.LOGIN_TIMEOUT);
        }
    }
}
