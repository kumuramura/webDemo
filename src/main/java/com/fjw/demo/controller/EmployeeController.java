package com.fjw.demo.controller;

import com.fjw.demo.dao.EmployeeDao;
import com.fjw.demo.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;
    //查询所有员工，返回列表
    @GetMapping("/emps")
    public String lists(Model model){
        Collection<Employee> employees= employeeDao.getAll();
        //放在请求域中
        model.addAttribute("emps",employees);

        // thymeleaf默认会拼串
        //classpath:/templates/xxx.html
         return"/emp/list";
    }
}
