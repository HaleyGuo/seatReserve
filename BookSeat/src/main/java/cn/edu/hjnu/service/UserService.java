package cn.edu.hjnu.service;

import cn.edu.hjnu.domain.User;

public interface UserService {
    //判断登陆
    public String login(int studentID, String password);
    //查看个人信息
    public User userInfo(int studentID);
    //修改密码
    public String updatePassword(int studentID, String oldPassword,String newPassword);
}
