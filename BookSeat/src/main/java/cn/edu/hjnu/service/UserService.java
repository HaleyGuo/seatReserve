package cn.edu.hjnu.service;

import cn.edu.hjnu.domain.User;
import com.alibaba.fastjson.JSONObject;

public interface UserService {
    //判断登陆
    public JSONObject login(String username, String password);
    //查看个人信息
    public User userInfo(String username);
    //修改密码
    public JSONObject updatePassword(String username, String oldPassword,String newPassword);
    //迟到扣信誉分
    public boolean SubCredit(String username);
}
