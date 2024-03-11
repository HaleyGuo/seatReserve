import cn.edu.hjnu.task.Task;
import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.GregorianCalendar;

public class test{
    public static void main(String[] args) throws SchedulerException {
        //调度器
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        //触发器
        LocalDateTime localDateTime = LocalDateTime.of(2024, 3, 9, 15, 15, 0);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        Date date = Date.from(zonedDateTime.toInstant());

        LocalDateTime localDateTime2 = LocalDateTime.of(2024, 3, 9, 15, 15, 20);
        ZonedDateTime zonedDateTime2 = localDateTime2.atZone(ZoneId.systemDefault());
        Date date2 = Date.from(zonedDateTime2.toInstant());

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule())
                .startAt(date)
//                .startNow()
                .endAt(date2)
                .build();
        //工作详情对象
        JobDetail jobDetail = JobBuilder.newJob(job.class).build();
        //注册任务和触发器
        scheduler.scheduleJob(jobDetail, trigger);
        //开启任务
        scheduler.start();
//        scheduler.deleteJob(jobDetail.getKey());
    }
}
