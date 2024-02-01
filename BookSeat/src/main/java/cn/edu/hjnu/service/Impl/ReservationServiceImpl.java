package cn.edu.hjnu.service.Impl;

import cn.edu.hjnu.domain.ReservationInfo;
import cn.edu.hjnu.domain.reservation;
import cn.edu.hjnu.mapper.ReservationMapper;
import cn.edu.hjnu.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationMapper reservationMapper;
    public reservation selectReservation(int studentID) {
        return reservationMapper.selectReservation(studentID);
    }

    public List<ReservationInfo> selectReservationInfo(String start_time, String end_time, int aid) {
        return reservationMapper.selectReservationInfo(start_time,end_time,aid);
    }

    public boolean AddReservation(reservation reservation) {
        return reservationMapper.AddReservation(reservation.getStudentID(), reservation.getStart_time(), reservation.getEnd_time(), reservation.getSeat_id());
    }

    public boolean DeleteReservation(int studentID) {
        return reservationMapper.DeleteReservation(studentID);
    }
}
