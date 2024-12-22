package cn.edu.ecnu.stu.bookstore.controller;

import cn.edu.ecnu.stu.bookstore.component.Result;
import cn.edu.ecnu.stu.bookstore.pojo.User;
import cn.edu.ecnu.stu.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        userService.register(user);
        return Result.success();
    }

    @PostMapping("/unregister")
    public Result unregister(@RequestBody User user) {
        userService.unregister(user);
        return Result.success();
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        return Result.success(userService.login(user));
    }

    @PostMapping("/password")
    public Result changePassword(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        String oldPassword = map.get("oldPassword");
        String newPassword = map.get("newPassword");
        userService.changePassword(username, oldPassword, newPassword);
        return Result.success();
    }

    @PostMapping("/logout")
    public Result logout(@RequestBody Map<String, String> map) {
        userService.logout(map.get("username"));
        return Result.success();
    }
}

