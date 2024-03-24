package cn.edu.hjnu.controller;

import cn.edu.hjnu.domain.area;
import cn.edu.hjnu.domain.seats;
import cn.edu.hjnu.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@CrossOrigin
@Controller
//test
//获取所有区域和区域内的座位信息
public class InfoController {
    @Autowired
    private InfoService infoService;
    @GetMapping(value = "/getArea")
    @ResponseBody
    public List<area> getArea() {
        return infoService.getArea();
    }
    @GetMapping(value = "/getSeats")
    @ResponseBody
    public List<seats> getSeats(int aid) {
        return infoService.getSeats(aid);
    }
}
