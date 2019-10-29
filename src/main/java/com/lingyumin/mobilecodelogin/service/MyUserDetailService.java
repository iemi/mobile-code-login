package com.lingyumin.mobilecodelogin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * [简要描述]:
 * [详细描述]:
 *
 * @author:
 * @date: 3:34 PM 2019/10/11
 * @since: JDK 1.8
 */
@Component
public class MyUserDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        /**
         * 这里实际情况应该是根据参数s查询数据库用户数据
         */
        return new User(s,"123", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }

}
