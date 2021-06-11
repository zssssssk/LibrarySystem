package com.example.ems.controller;

import com.example.ems.bean.User;
import com.example.ems.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class loginController {

    @Autowired
    UserService userService;

    @GetMapping(value = {"/login","/"})
    public String login(){
        return "login";
    }


    @PostMapping("/login")
    public String main(HttpSession session,Model model,@RequestParam("username") String username,@RequestParam("password") String password){

        List<User> lists = userService.list();
        for (User user:lists){
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                //把登录成功的用户保存起来
                session.setAttribute("loginUser",user);
                //登录成功重定向到main.html；重定向防止表单重复提交
                return "redirect:/main.html";
            }
        }//把登录成功的用户保存起来
        model.addAttribute("msg","账号密码错误");
        return "login";
    }

    /**
     * 去main页面
     * @return
     */
    @GetMapping("/main.html")
    public String mainPage(HttpSession session,Model model){


            return "main";

    }

    @GetMapping("/registration")
    public String registrationUser(){
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(User user,String passwordAgain,Model model){

        if (!user.getPassword().equals(passwordAgain)){
            model.addAttribute("msg","请确认密码");
            return "registration";
        }

        userService.save(user);
        return "redirect:/Administrator";

    }




}
