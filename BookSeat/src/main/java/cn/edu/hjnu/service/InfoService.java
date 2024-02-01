package cn.edu.hjnu.service;

import cn.edu.hjnu.domain.area;
import cn.edu.hjnu.domain.seats;

import java.util.List;

public interface InfoService {
    public List<area> getArea();
    public List<seats> getSeats(int aid);
}
