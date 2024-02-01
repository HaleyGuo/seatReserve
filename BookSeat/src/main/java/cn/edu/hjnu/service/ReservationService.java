package cn.edu.hjnu.service;

import cn.edu.hjnu.domain.ReservationInfo;
import cn.edu.hjnu.domain.reservation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReservationService {
    public reservation selectReservation(int studentID);
    public List<ReservationInfo> selectReservationInfo(String start_time, String end_time, int aid);
    public boolean AddReservation(reservation reservation);
    public boolean DeleteReservation(int studentID);
}
