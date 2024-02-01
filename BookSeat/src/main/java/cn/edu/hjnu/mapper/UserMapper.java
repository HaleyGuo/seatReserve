package cn.edu.hjnu.mapper;

import cn.edu.hjnu.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    //判断登陆
    public User login(int studentID);
    //查看个人信息
    public User userInfo(int studentID);
    //修改密码
    public User checkPassword(int studentID);
    public boolean updatePassword(@Param("studentID") int studentID,@Param("newPassword") String newPassword);
}