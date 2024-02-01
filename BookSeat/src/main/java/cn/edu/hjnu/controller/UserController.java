package cn.edu.hjnu.controller;

import cn.edu.hjnu.domain.User;
import cn.edu.hjnu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    @ResponseBody
    public String login(User user) {
        return userService.login(user.getStudentID(), user.getPassword());
    }

    @GetMapping(value = "/selectUserInfo")
    @ResponseBody
    public User selectUserInfo(@RequestParam("studentID") int studentID) {
        return userService.userInfo(studentID);
    }

    @GetMapping(value = "/updatePassword")
    @ResponseBody
    public String updatePassword(@RequestParam("studentID")int studentID,@RequestParam("oldPassword") String oldPassword,@RequestParam("newPassword") String newPassword) {
        return userService.updatePassword(studentID,oldPassword,newPassword);
    }
}
