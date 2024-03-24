package cn.edu.hjnu.service.Impl;

import cn.edu.hjnu.domain.ReservationInfo;
import cn.edu.hjnu.domain.reservation;
import cn.edu.hjnu.mapper.ReservationMapper;
import cn.edu.hjnu.service.ReservationService;
import cn.edu.hjnu.task.Task;
import com.alibaba.fastjson.JSONObject;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationMapper reservationMapper;
    public reservation[] selectReservation(String username) {
        return reservationMapper.selectReservation(username);
    }

    public List<ReservationInfo> selectReservationInfo(String start_time, String end_time, int aid) {
        return reservationMapper.selectReservationInfo(start_time,end_time,aid);
    }

    public JSONObject AddReservation(reservation reservation) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code","200");

        //判断预约时间是否大于当前时间
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime reservation_time = LocalDateTime.parse(reservation.getStart_time(), timeFormatter);
        LocalDateTime now_time = LocalDateTime.now();
        int compare = reservation_time.compareTo(now_time);
        if (compare<0){
            jsonObject.put("msg","预约时间需大于当前!");
            return jsonObject;
        }

        //添加预约
        if (reservationMapper.AddReservation(reservation.getUsername(), reservation.getStart_time(), reservation.getEnd_time(), reservation.getSeat_id())){
            //预约成功后,添加定时任务
            Task task = new Task();
            LocalDateTime localDateTime = LocalDateTime.parse(reservation.getStart_time(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            //半个小时内需签到
            LocalDateTime start_plus = localDateTime.plusMinutes(30);
            ZonedDateTime zonedDateTime = start_plus.atZone(ZoneId.systemDefault());
            Date start_time = Date.from(zonedDateTime.toInstant());
            LocalDateTime start_pp = start_plus.plusSeconds(30);
            ZonedDateTime zonedDateTime2 = start_pp.atZone(ZoneId.systemDefault());
            Date end_time = Date.from(zonedDateTime2.toInstant());
            try {
                task.init(start_time,end_time,reservation);
            } catch (SchedulerException e) {
                throw new RuntimeException(e);
            }
            jsonObject.put("msg","预约成功");
            return jsonObject;
        }
        jsonObject.put("msg","预约失败");
        return jsonObject;
    }

    public JSONObject DeleteReservation(int reservation_id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code","200");
        //退约
        if (reservationMapper.DeleteReservation(reservation_id)){
            try {
                //退约后删除定时任务
                Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
                scheduler.deleteJob(new JobKey(String.valueOf(reservation_id)));
            } catch (SchedulerException e) {
                throw new RuntimeException(e);
            }
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
    public JSONObject SignIn(int reservation_id) {
        //判断是否在预约时间内签到
        reservation reservation = reservationMapper.CheckReservation(reservation_id);
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
        //签到
        if (reservationMapper.SignIn(reservation_id)){
            try {
                //签到后删除定时任务
                Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
                scheduler.deleteJob(new JobKey(String.valueOf(reservation_id)));
            } catch (SchedulerException e) {
                throw new RuntimeException(e);
            }
            jsonObject.put("msg","签到成功!");
            return jsonObject;
        }
        jsonObject.put("msg","签到失败!");
        return jsonObject;
    }

    @Override
    public boolean Late(int reservation_id) {
        return reservationMapper.Late(reservation_id);
    }

}
