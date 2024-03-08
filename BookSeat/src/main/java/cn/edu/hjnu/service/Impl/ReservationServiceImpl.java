package cn.edu.hjnu.service.Impl;

import cn.edu.hjnu.domain.ReservationInfo;
import cn.edu.hjnu.domain.reservation;
import cn.edu.hjnu.mapper.ReservationMapper;
import cn.edu.hjnu.service.ReservationService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationMapper reservationMapper;
    public reservation selectReservation(String username) {
        return reservationMapper.selectReservation(username);
    }

    public List<ReservationInfo> selectReservationInfo(String start_time, String end_time, int aid) {
        return reservationMapper.selectReservationInfo(start_time,end_time,aid);
    }

    @Override
    public boolean CheckReservation(String username) {
        reservation reservation = reservationMapper.CheckReservation(username);
        return reservation != null;
    }

    public JSONObject AddReservation(reservation reservation) {
        boolean flag = CheckReservation(reservation.getUsername());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code","200");
        if (flag){
            jsonObject.put("msg","该用户已预约");
            return jsonObject;
        }
        if (reservationMapper.AddReservation(reservation.getUsername(), reservation.getStart_time(), reservation.getEnd_time(), reservation.getSeat_id())){
            jsonObject.put("msg","预约成功");
            return jsonObject;
        }
        jsonObject.put("msg","预约失败");
        return jsonObject;
    }

    public JSONObject DeleteReservation(String username) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code","200");
        if (reservationMapper.DeleteReservation(username)){
            jsonObject.put("msg","退约成功!");
            return jsonObject;
        }
        jsonObject.put("msg","退约失败!");
        return jsonObject;
    }

    @Override
    public List<reservation> SelectReservationLog(String username) {
        return reservationMapper.SelectReservationLog(username);
    }

    @Override
    public JSONObject SignIn(String username) {
        reservation reservation = reservationMapper.CheckReservation(username);
        String startTime = reservation.getStart_time();
        String endTime = reservation.getEnd_time();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = now.format(dateTimeFormatter);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code","200");
        if (formatDateTime.compareTo(startTime) < 0 || formatDateTime.compareTo(endTime) > 0){
            jsonObject.put("msg","未在预约时间段内");
            return jsonObject;
        }
        if (reservationMapper.SignIn(username)){
            jsonObject.put("msg","签到成功!");
            return jsonObject;
        }
        jsonObject.put("msg","签到失败!");
        return jsonObject;
    }
}
