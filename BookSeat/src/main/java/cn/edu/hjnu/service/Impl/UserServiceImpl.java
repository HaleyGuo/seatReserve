package cn.edu.hjnu.service.Impl;

import cn.edu.hjnu.domain.User;
import cn.edu.hjnu.mapper.UserMapper;
import cn.edu.hjnu.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public JSONObject login(String username, String password) {
        User user = userMapper.login(username);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        if (user == null) {
            jsonObject.put("msg","账号或密码错误!");
            return jsonObject;
        }
        if (user.getPassword().equals(password)) {
            jsonObject.put("msg","登录成功!");
            return jsonObject;
        }
        jsonObject.put("msg","账号或密码错误!");
        return jsonObject;
    }

    public User userInfo(String username) {
        return userMapper.userInfo(username);
    }

    public JSONObject updatePassword(String username, String oldPassword, String newPassword) {
        User user = userMapper.checkPassword(username);
        JSONObject jsonObject = new JSONObject();
        if (user == null) {
            jsonObject.put("code", "404");
            jsonObject.put("msg", "用户不存在!");
            return jsonObject;
        }
        jsonObject.put("code","200");
        if (user.getPassword().equals(oldPassword)) {
            if (userMapper.updatePassword(username, newPassword)){
                jsonObject.put("msg", "更新成功!");
                return jsonObject;
            }
            jsonObject.put("msg", "更新失败!");
            return jsonObject;
        } else {
            jsonObject.put("msg", "密码错误!");
            return jsonObject;
        }
    }

    @Override
    public boolean SubCredit(String username) {
        return userMapper.SubCredit(username);
    }

}
