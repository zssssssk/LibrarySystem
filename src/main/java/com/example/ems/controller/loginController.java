package com.example.ems.controller;

import com.example.ems.bean.User;
import com.example.ems.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

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

//        if ( (!StringUtils.isEmpty(user.getUsername())) && "123456".equals(user.getPassword())){
//            //把登录成功的用户保存起来
//            session.setAttribute("loginUser",user);
//            //登录成功重定向到main.html；重定向防止表单重复提交
//            return "redirect:/main.html";
//        }else {
//            model.addAttribute("msg","账号密码错误");
//            return "login";
//        }


    }

    /**
     * 去main页面
     * @return
     */
    @GetMapping("/main.html")
    public String mainPage(HttpSession session,Model model){


            return "main";

    }


//    @GetMapping("/register")
//    public String register(){
//        return "Register example for Bootstrap.html";
//    }

//    @GetMapping("/user")
//    public String user(@RequestParam("username") String username,
//                       @RequestParam("password") String password,
//                       Model model){
//
//        //具体的业务
//        if(!StringUtils.isEmpty(username) && "123456".equals(password)){
//            return "Dashboard Template for Bootstrap.html";
//        }
//        else{
//            //登录失败
//            model.addAttribute("msg","用户名或者密码错误");
//            return "index";
//        }
//
//    }


}
