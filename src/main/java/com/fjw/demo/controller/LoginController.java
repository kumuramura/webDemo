package com.fjw.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    //@RequestMapping(value ="/user/login",method = RequestMethod.POST)
    @PostMapping(value ="/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession session){
        if(!ObjectUtils.isEmpty(username)&&"123".equals(password))
        {
            //return "dashboard";//登录成功
            session.setAttribute("loginUser",username);//前往interceptor中断器
            return "redirect:/main.html";
        }
        else
        {
            map.put("msg","登录失败！");
            //登录失败
            return "login";
        }

    }
}
