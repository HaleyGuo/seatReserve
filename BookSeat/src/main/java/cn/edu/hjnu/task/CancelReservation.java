package cn.edu.hjnu.task;

import cn.edu.hjnu.service.Impl.ReservationServiceImpl;
import cn.edu.hjnu.service.Impl.UserServiceImpl;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class CancelReservation implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext){
        boolean late = new ReservationServiceImpl().Late(Integer.parseInt(jobExecutionContext.getMergedJobDataMap().getString("reservation_id")));
        if (late){
            new UserServiceImpl().SubCredit(jobExecutionContext.getMergedJobDataMap().getString("username"));
        }
    }
}
