package cn.edu.hjnu.mapper;

import cn.edu.hjnu.domain.ReservationInfo;
import cn.edu.hjnu.domain.reservation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReservationMapper {
    //查看预约信息
    public reservation selectReservation(String username);
    //查看座位预约情况
    public List<ReservationInfo> selectReservationInfo(@Param("start_time") String start_time, @Param("end_time") String end_time, @Param("aid") int aid);
    //检查预约情况,只允许预约一个座位
    public reservation CheckReservation(@Param("reservation_id") int reservation_id);
    //预约座位
    public boolean AddReservation(@Param("username") String username, @Param("start_time") String start_time, @Param("end_time") String end_time, @Param("seat_id") int seat_id);
    //退选座位
    public boolean DeleteReservation(@Param("reservation_id") int reservation_id);
    //查询个人预约记录
    public List<reservation> SelectReservationLog(@Param("username") String username);
    //签到
    public boolean SignIn(@Param("reservation_id") int reservation_id);
    //迟到退约
    public boolean Late(@Param("reservation_id") int reservation_id);
}
