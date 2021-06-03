package com.example.ems.controller;

import com.example.ems.bean.User;
import com.example.ems.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class tableController {

    @Autowired
    UserService userService;

    @GetMapping("/basic_table.html")
    public String basic_table(){

        return "table/basic_table";
    }
    @GetMapping("/dynamic_table.html")
    public String dynamic_table(Model model){

        //从数据表中查出UserTable的数据进行展示
        List<User> list = userService.list();
        model.addAttribute("users",list);



        return "table/dynamic_table";
    }
    @GetMapping("/editable_table.html")
    public String editable_table(){

        return "table/editable_table";
    }
    @GetMapping("/pricing_table.html")
    public String pricing_table(){

        return "table/pricing_table";
    }
    @GetMapping("/responsive_table.html")
    public String responsive_table(){

        return "table/responsive_table";
    }
}
