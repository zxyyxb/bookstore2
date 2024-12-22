package cn.edu.ecnu.stu.bookstore.service.impl;

import cn.edu.ecnu.stu.bookstore.component.AppException;
import cn.edu.ecnu.stu.bookstore.component.Constants;
import cn.edu.ecnu.stu.bookstore.pojo.LoginUser;
import cn.edu.ecnu.stu.bookstore.pojo.User;
import cn.edu.ecnu.stu.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.selectUserByName(username);
        if(user == null)
            throw new AppException(Constants.CLIENT_ERROR, Constants.PARAMETER_ERROR_MESSAGE);
        return new LoginUser(user);
    }
}
