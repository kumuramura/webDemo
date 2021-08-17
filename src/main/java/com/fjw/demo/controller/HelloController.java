package com.fjw.demo.controller;

import com.fjw.demo.exception.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

@Controller
public class HelloController {

    /*
    @RequestMapping({"/","/index.html"})
    public String index()
    {
        return "index";
    }

     */

    @ResponseBody
    @RequestMapping("/web-hello")
    public  String Helloworld(@RequestParam("user") String user)
    {
        if (user.equals("aaa")){
            throw  new UserNotExistException();
        }
        return "hello,web-development;";
    }

    //查出一些数据，在页面展示
    @RequestMapping("/success")
    //classpath:/templates/success.html
    public  String success(Map<String,Object> map)
    {
        map.put("Hello","<h1>你好</h1>");
        map.put("Users", Arrays.asList("zhangsan","lisi","wangwu"));
        return "success1";
    }

    @RequestMapping("/shoot")
    //classpath:/templates/success.html
    public  String shoot(){return "shoot1"; }
}
