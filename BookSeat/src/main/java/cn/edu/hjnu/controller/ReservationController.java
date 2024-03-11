package cn.edu.hjnu.controller;

import cn.edu.hjnu.domain.ReservationInfo;
import cn.edu.hjnu.domain.reservation;
import cn.edu.hjnu.service.ReservationService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
//关于预约
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    //查询个人预约信息
    @GetMapping(value = "/PersonReservation")
    @ResponseBody
    public reservation PersonReservation(@RequestParam("username") String username){
        return reservationService.selectReservation(username);
    }
    //查询区域内的预约情况
    @GetMapping(value = "/ReservationInfo")
    @ResponseBody
    public List<ReservationInfo> ReservationInfo(@RequestParam("aid") int aid,
                                                 @RequestParam("start_time") String start_time,
                                                 @RequestParam("end_time") String end_time){
        return reservationService.selectReservationInfo(start_time,end_time,aid);
    }
    //预约座位
    @GetMapping(value = "/AddReservation")
    @ResponseBody
    public JSONObject AddReservation(reservation reservation){
        return reservationService.AddReservation(reservation);
    }
    //退约座位
    @GetMapping(value = "/DeleteReservation")
    @ResponseBody
    public JSONObject DeleteReservation(int reservation_id){
        return reservationService.DeleteReservation(reservation_id);
    }
    //预约记录
    @GetMapping(value = "/ReservationLog")
    @ResponseBody
    public List<reservation> SelectReservationLog(String username){
        return reservationService.SelectReservationLog(username);
    }
    //签到
    @GetMapping(value = "/SignIn")
    @ResponseBody
    public JSONObject SignIn(int reservation_id){
        return reservationService.SignIn(reservation_id);
    }
}
