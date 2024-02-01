package cn.edu.hjnu.service.Impl;

import cn.edu.hjnu.domain.User;
import cn.edu.hjnu.mapper.UserMapper;
import cn.edu.hjnu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public String login(int studentID, String password) {
        User user = userMapper.login(studentID);
        if (user == null) {
            return "用户登陆失败，请检查用户名和密码是否有误!";
        }
        if (user.getPassword().equals(password)) {
            return "登陆成功!";
        }
        return "用户登陆失败，请检查用户名和密码是否有误!";
    }

    public User userInfo(int studentID) {
        return userMapper.userInfo(studentID);
    }

    public String updatePassword(int studentID, String oldPassword, String newPassword) {
        User user = userMapper.checkPassword(studentID);
        if (user.getPassword().equals(oldPassword)) {
            if (userMapper.updatePassword(studentID, newPassword)){
                return "更新成功!";
            }
            return "更新失败!";
        } else {
            return "密码错误!";
        }
    }

}
