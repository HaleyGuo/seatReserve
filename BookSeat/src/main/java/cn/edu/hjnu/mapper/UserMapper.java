package cn.edu.hjnu.mapper;

import cn.edu.hjnu.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    //判断登陆
    public User login(String username);
    //查看个人信息
    public User userInfo(String username);
    //修改密码
    public User checkPassword(String username);
    public boolean updatePassword(@Param("username") String username,@Param("newPassword") String newPassword);
    //迟到扣信誉分
    public boolean SubCredit(String username);
}