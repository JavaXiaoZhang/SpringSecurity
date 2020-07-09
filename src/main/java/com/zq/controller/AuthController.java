package com.zq.controller;

import com.zq.annotation.LogPoint;
import com.zq.service.IUserService;
import com.zq.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private IUserService userService;

    /*@Autowired
    private AuthenticationManager authenticationManager;*/

    //已在config中配置路径与view的映射
    /*@GetMapping("home")
    public String home(){
        return "home";
    }

    @GetMapping("login")
    public String login(){
        return "login";
    }*/

    /*@PostMapping("checkLogin")
    public String checkLogin(User user){
        UsernamePasswordAuthenticationToken
                upToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        System.out.println("sign in");
        return "home";
    }*/

    @LogPoint(log = "获取用户信息")
    @GetMapping("getUserInfo")
    public String getUserInfo(ModelMap modelMap){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails){
            User user = (User)principal;
            modelMap.put("user",user);
        }
        return "show";
    }

    @LogPoint
    @GetMapping("getList")
    @ResponseBody
    public String getList(ModelMap map){
        PageUtils.setPageParams(0,1);
        List<com.zq.entity.User> list = userService.getList();
        map.put("list",list);
        return "hello";
    }
}
