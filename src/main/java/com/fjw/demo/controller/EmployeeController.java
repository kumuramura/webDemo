package com.fjw.demo.controller;

import com.fjw.demo.dao.DepartmentDao;
import com.fjw.demo.dao.EmployeeDao;
import com.fjw.demo.entities.Department;
import com.fjw.demo.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;
    //查询所有员工，返回列表
    @GetMapping("/emps")
    public String lists(Model model){
        Collection<Employee> employees= employeeDao.getAll();
        //放在请求域中
        model.addAttribute("emps",employees);

        // thymeleaf默认会拼串
        //classpath:/templates/xxx.html
         return"emp/list";
    }

    //来到员工添加页面
    @GetMapping("/emp")
    public String toAddPage(Model model)
    {
        //来到添加页面
        //查出所有的部门，然后在页面显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }

    //员工添加功能
    //springmvc自动将请求参数和入参对象的属性进行一一绑定；要求了请求参数的名字和javaBean入参对象里面的属性名是一样的
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        //来到员工列表页面

        System.out.println("保存的员工信息："+employee);
        //保存员工
        employeeDao.save(employee);
        // redirect: 表示重定向到一个地址  /代表当前项目路径
        // forward: 表示转发到一个地址
        return "redirect:/emps";
    }

    //来到修改页面，查出当前员工，在页面回显
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model)
    {
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);

        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }

    //员工修改；需要提交员工id；
    @PutMapping("/emp")
    public String updateEmployee(Employee employee){
        System.out.println("修改的员工数据："+employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //员工删除
    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
