package com.zq.controller;

import com.zq.annotation.LogPoint;
import com.zq.datasource.DynamicDataSourceContextHolder;
import com.zq.service.IUserService;
import com.zq.util.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("auth")
@Api(value = "用户认证Controller",tags = "tags")
public class AuthController {

    @Autowired
    private static IUserService userService;

    @Autowired
    private static IUserService userService2;

    static {
        System.out.println(userService);
        System.out.println(userService2);
    }
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
    @ApiOperation(value = "获取用户信息",notes = "notes")
    @ApiImplicitParams({@ApiImplicitParam(name = "modelMap", value = "回显参数", required = false)})
    public String getUserInfo(ModelMap modelMap){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails){
            User user = (User)principal;
            com.zq.entity.User user1 = userService.getByUsername(user.getUsername());
            if (user1.getLegalPerson().equals("srcb")){
                // TODO 做数据源的切换
                DynamicDataSourceContextHolder.setDataSourceType("haif");
                com.zq.entity.User user2 = userService.getByUsername(user.getUsername());
                DynamicDataSourceContextHolder.setDataSourceType("admin");
                com.zq.entity.User user3 = userService.getByUsername(user.getUsername());
            }
            modelMap.put("user",user);
        }
        System.out.println("返回数据。。。。");
        LogPoint logPoint = this.getClass().getAnnotation(LogPoint.class);

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
