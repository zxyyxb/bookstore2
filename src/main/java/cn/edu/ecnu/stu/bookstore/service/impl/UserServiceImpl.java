package cn.edu.ecnu.stu.bookstore.service.impl;

import cn.edu.ecnu.stu.bookstore.component.AppException;
import cn.edu.ecnu.stu.bookstore.component.Constants;
import cn.edu.ecnu.stu.bookstore.mapper.UserMapper;
import cn.edu.ecnu.stu.bookstore.pojo.LoginUser;
import cn.edu.ecnu.stu.bookstore.pojo.User;
import cn.edu.ecnu.stu.bookstore.service.UserService;
import cn.edu.ecnu.stu.bookstore.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisTemplate redisTemplate;

    public void register(User user) {
        if(!StringUtils.hasLength(user.getUsername()))
            throw new AppException(Constants.CLIENT_ERROR, Constants.PARAMETER_ERROR_MESSAGE);
        if(userMapper.checkUserByUsername(user.getUsername()) != 0)
            throw new AppException(Constants.CLIENT_ERROR, Constants.REGISTER_ERROR_MESSAGE);
        String encoded = passwordEncoder.encode(user.getPassword());
        user.setPassword(encoded);
        userMapper.insert(user);
    }

    public void unregister(User user) {
        User user1 = userMapper.selectOneByName(user.getUsername());
        if(user1 == null || !passwordEncoder.matches(user.getPassword(), user1.getPassword())) {
            throw new AppException(Constants.CLIENT_ERROR, Constants.PASSWORD_ERROR);
        }
        userMapper.deleteByName(user.getUsername());
    }

    public User selectUserByName(String username) {
        return userMapper.selectOneByName(username);
    }

    public Map<String, Object> login(User user) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(token);
        LoginUser loginUser = (LoginUser)authenticate.getPrincipal();
        User user1 = loginUser.getUser();
        redisTemplate.opsForValue().set("userId:" + user1.getId(), "1", 1, TimeUnit.DAYS);
        Map<String, Object> map = new HashMap<>();
        map.put("token", JwtUtil.getToken(user1));
        map.put("user", user1);
        return map;
    }

    public void logout(String username) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!user.getUsername().equals(username)) {
            throw new AppException(Constants.CLIENT_ERROR, Constants.PARAMETER_ERROR_MESSAGE);
        }
        redisTemplate.delete("userId:" + user.getId());
    }

    public void changePassword(String username, String oldPassword, String newPassword) {
        User user = userMapper.selectOneByName(username);
        if(user == null || !passwordEncoder.matches(oldPassword, user.getPassword())){
            throw new AppException(Constants.CLIENT_ERROR, Constants.PASSWORD_ERROR);
        }
        newPassword = passwordEncoder.encode(newPassword);
        userMapper.updatePassword(username, newPassword);
        redisTemplate.delete("userId:" + user.getId());
    }
}
