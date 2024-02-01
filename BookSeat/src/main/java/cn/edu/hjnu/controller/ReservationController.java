package cn.edu.hjnu.controller;

import cn.edu.hjnu.domain.ReservationInfo;
import cn.edu.hjnu.domain.reservation;
import cn.edu.hjnu.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    @GetMapping(value = "/PersonReservation")
    @ResponseBody
    public reservation PersonReservation(@RequestParam("studentID") int studentID){
        return reservationService.selectReservation(studentID);
    }
    @GetMapping(value = "/ReservationInfo")
    @ResponseBody
    public List<ReservationInfo> ReservationInfo(@RequestParam("aid") int aid,
                                                 @RequestParam("start_time") String start_time,
                                                 @RequestParam("end_time") String end_time){
        return reservationService.selectReservationInfo(start_time,end_time,aid);
    }
    @PostMapping(value = "/AddReservation")
    @ResponseBody
    public boolean AddReservation(reservation reservation){
        return reservationService.AddReservation(reservation);
    }
    @GetMapping(value = "/DeleteReservation")
    @ResponseBody
    public boolean DeleteReservation(int studentID){
        return reservationService.DeleteReservation(studentID);
    }
}
