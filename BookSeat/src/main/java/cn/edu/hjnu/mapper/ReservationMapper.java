package cn.edu.hjnu.mapper;

import cn.edu.hjnu.domain.ReservationInfo;
import cn.edu.hjnu.domain.reservation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReservationMapper {
    //查看预约信息
    public reservation selectReservation(int studentID);
    //查看座位预约情况
    public List<ReservationInfo> selectReservationInfo(@Param("start_time") String start_time, @Param("end_time") String end_time, @Param("aid") int aid);
    //预约座位
    public boolean AddReservation(@Param("studentID") int studentID,@Param("start_time") String start_time,@Param("end_time") String end_time,@Param("seat_id") int seat_id);
    //退选座位
    public boolean DeleteReservation(@Param("studentID") int studentID);
}
