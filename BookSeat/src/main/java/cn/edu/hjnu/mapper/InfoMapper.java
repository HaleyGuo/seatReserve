package cn.edu.hjnu.mapper;

import cn.edu.hjnu.domain.area;
import cn.edu.hjnu.domain.seats;

import java.util.List;

public interface InfoMapper {
    //查询所有区域
    public List<area> getArea();
    //查询区域的所有座位
    public List<seats> getSeats(int aid);
}
