package cn.edu.hjnu.controller;

import cn.edu.hjnu.domain.User;
import cn.edu.hjnu.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@Controller
//关于账号密码
public class UserController {
    @Autowired
    private UserService userService;

    //用户登录
    @PostMapping(value = "/login")
    @ResponseBody
    public JSONObject login(User user, HttpServletRequest request) {
        JSONObject jsonObject = userService.login(user.getUsername(), user.getPassword());
        if (jsonObject.containsValue("登录成功!")){
            request.getSession().setAttribute("user",user.getUsername());
        }
        return jsonObject;
    }

    //退出登录
    @GetMapping(value = "/logout")
    @ResponseBody
    public JSONObject logout(HttpServletRequest request) {
        request.getSession().invalidate();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg","退出成功");
        jsonObject.put("code",200);
        return jsonObject;
    }

    //查询用户信息
    @GetMapping(value = "/selectUserInfo")
    @ResponseBody
    public User selectUserInfo(@RequestParam("username") String username) {
        return userService.userInfo(username);
    }

    //更新密码
    @PostMapping(value = "/updatePassword")
    @ResponseBody
    public JSONObject updatePassword(@RequestParam("username")String username,@RequestParam("oldPassword") String oldPassword,@RequestParam("newPassword") String newPassword) {
        return userService.updatePassword(username,oldPassword,newPassword);
    }
}
