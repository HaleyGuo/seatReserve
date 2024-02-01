package cn.edu.hjnu.service.Impl;

import cn.edu.hjnu.domain.area;
import cn.edu.hjnu.domain.seats;
import cn.edu.hjnu.mapper.InfoMapper;
import cn.edu.hjnu.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoServiceImpl implements InfoService {
    @Autowired
    private InfoMapper infoMapper;
    public List<area> getArea() {
        return infoMapper.getArea();
    }

    public List<seats> getSeats(int aid) {
        return infoMapper.getSeats(aid);
    }

}
