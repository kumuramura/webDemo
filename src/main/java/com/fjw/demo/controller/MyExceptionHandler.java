package com.fjw.demo.controller;

import com.fjw.demo.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class MyExceptionHandler {

    //@ResponseBody
    //@ExceptionHandler(UserNotExistException.class)
    //public Map<String,Object> handleException(Exception e){
       // Map<String,Object> map=new HashMap<>();
      // map.put("code", "user.notexist");
       // map.put("message", e.getMessage());
      //  return map;
    //}
    //没用自适应效果

    //传入我们自己的错误状态码 4xx 5xx 否则就不会进入定制错误页面的解析流程
    @ResponseBody
    @ExceptionHandler(UserNotExistException.class)
    public String handleException(Exception e , HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        //传入我们自己的错误状态码 4xx 5xx
        request.setAttribute("javax.servlet.error.status_code", 500);
        map.put("code", "user.notexist");
        map.put("message", e.getMessage());

        request.setAttribute("ext",map);
        return "forward:/error";
    }
    //



}
