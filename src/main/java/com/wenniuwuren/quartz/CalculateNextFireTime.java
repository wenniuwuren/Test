package com.wenniuwuren.quartz;

import org.quartz.TriggerUtils;
import org.quartz.impl.triggers.CronTriggerImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 根据cron表达式计算最近几次的执行时间
 * 使用quartz的jar包中提供的TriggerUtils类来计算
 * Created by Yibin_Zhu on 2017/8/7.
 */
public class CalculateNextFireTime {


    public static void main(String[] args) throws ParseException, InterruptedException {
        // 获取最近几次的执行时间 start
        CronTriggerImpl cronTriggerImpl = new CronTriggerImpl();
        cronTriggerImpl.setCronExpression("0 0 15 5 * ?");//这里写要准备猜测的cron表达式
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        calendar.add(Calendar.YEAR, 2);//把统计的区间段设置为从现在到2年后的今天（主要是为了方法通用考虑，如那些1个月跑一次的任务，如果时间段设置的较短就不足20条)
        List<Date> dates = TriggerUtils.computeFireTimesBetween(cronTriggerImpl, null, now, calendar.getTime());//这个是重点，一行代码搞定~~
        System.out.println(dates.size());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(int i =0;i < dates.size();i ++){
            if(i >19){//这个是提示的日期个数
                break;
            }
            System.out.println(dateFormat.format(dates.get(i)));
        }
        // 获取最近几次的执行时间 end

        System.out.println("-----------------------------------------");
        // 获取最近n次的执行时间  这里n=1
        List<Date> dates1 = TriggerUtils.computeFireTimes(cronTriggerImpl, null, 1);
        for (Date d : dates1) {
            System.out.println(dateFormat.format(d));
        }
    }
}
