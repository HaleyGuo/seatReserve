package cn.edu.hjnu.service;

import cn.edu.hjnu.domain.ReservationInfo;
import cn.edu.hjnu.domain.reservation;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReservationService {
    public reservation selectReservation(String username);
    public List<ReservationInfo> selectReservationInfo(String start_time, String end_time, int aid);
    public boolean CheckReservation(String username);
    public JSONObject AddReservation(reservation reservation);
    public JSONObject DeleteReservation(String username);
    public List<reservation> SelectReservationLog(String username);
    public JSONObject SignIn(String username);
}
