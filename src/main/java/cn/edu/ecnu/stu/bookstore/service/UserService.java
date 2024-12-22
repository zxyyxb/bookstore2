package cn.edu.ecnu.stu.bookstore.service;

import cn.edu.ecnu.stu.bookstore.pojo.User;

import java.util.Map;

public interface UserService {

    void register(User user);
    void unregister(User user);
    User selectUserByName(String username);
    Map<String, Object> login(User user);

    void logout(String username);

    void changePassword(String username, String oldPassword, String newPassword);


}
