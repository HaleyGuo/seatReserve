package cn.edu.hjnu.task;

import cn.edu.hjnu.domain.reservation;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class Task{
    public void init(Date start_time, Date end_time, reservation reservation) throws SchedulerException {
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule())
                .startAt(start_time)
                .endAt(end_time)
                .build();
        //工作详情对象
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("reservation_id",reservation.getReservation_id());
        jobDataMap.put("username",reservation.getUsername());
        JobDetail jobDetail = JobBuilder
                .newJob(CancelReservation.class)
                .withIdentity(String.valueOf(reservation.getReservation_id()))
                .usingJobData(jobDataMap)
                .build();
        //注册任务和触发器
        scheduler.scheduleJob(jobDetail, trigger);
        //开启任务
        if (!scheduler.isStarted()){
            scheduler.start();
        }
    }
}
