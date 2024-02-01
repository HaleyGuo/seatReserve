/*
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.*;

class test {

    public static void runTaskAtSpecificDateTime(LocalDateTime targetDateTime, Runnable task) {
        // 创建ScheduledExecutorService实例
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        // 获取当前的日期和时间
        LocalDateTime now = LocalDateTime.now();

        // 计算现在和目标时间之间的时间差
        long delay = ChronoUnit.MILLIS.between(now, targetDateTime);

        // 如果目标时间在过去，可以选择直接执行任务或者抛出异常
        if (delay < 0) {
            System.out.println("The target time is in the past. Task not scheduled.");
            // 这里可以选择执行 executorService.execute(task); 代替直接运行任务
            executorService.shutdown();
            return;
        }

        // 安排任务在指定延迟后执行
        ScheduledFuture<?> future = executorService.schedule(task, delay, TimeUnit.MILLISECONDS);

        // 当需要停止时关闭ScheduledExecutorService
        // 通常在应用程序停止时调用 executorService.shutdown();
    }

    public static void main(String[] args) {
        String time = "2023-07-21 14:30:00";
        // 创建一个日期时间格式器，与给定的时间字符串格式匹配
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 解析字符串到LocalDateTime对象
        LocalDateTime dateTime = LocalDateTime.parse(time, formatter);

        // 提取年、月、日
        int year = dateTime.getYear();
        int month = dateTime.getMonthValue();
        int dayOfMonth = dateTime.getDayOfMonth();
        int hour = dateTime.getHour();
        int minute = dateTime.getMinute();
        int second = dateTime.getSecond();
        // 设置目标日期和时间为2023年07月21日14时30分0秒
        LocalDateTime targetDateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute, second);

        // 创建一个任务
        Runnable task = () -> {
            System.out.println("Task executed at " + LocalDateTime.now());
        };

        // 在指定日期时间执行任务
        runTaskAtSpecificDateTime(targetDateTime, task);
    }
}*/
