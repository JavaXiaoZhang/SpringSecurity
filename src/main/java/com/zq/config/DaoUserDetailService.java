package com.zq.config;

import com.zq.entity.User;
import com.zq.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZQ
 * @Date 2020/6/14
 */
@Component
public class DaoUserDetailService implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);
        if (user==null){
            throw new UsernameNotFoundException("用户不存在！");
        }
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        String roles = user.getRoles();
        if (!StringUtils.isEmpty(roles)){
            String[] strings = roles.split(",");
            for (String string : strings) {
                authorityList.add(new SimpleGrantedAuthority(string));
            }
        }
        //System.out.println(passwordEncoder.encode("123"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorityList);
    }
}
