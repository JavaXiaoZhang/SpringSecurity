/*
package com.zq.quatyz;

import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class QuartzUtil{

    public void simpleDemo(){
        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler;
        try {
            scheduler = stdSchedulerFactory.getScheduler();
            JobDetail job = JobBuilder.newJob(DemoJob.class).withIdentity("jd-s1","jdg-s1").withDescription("jdg-s1").build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("tg-s1", "tgg-s1").withDescription("tg")
                    .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(1).withRepeatCount(10)).startNow().build();
            scheduler.scheduleJob(job, trigger);
            //scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void cronDemo(){
        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler;
        try {
            scheduler = stdSchedulerFactory.getScheduler();
            JobDetail job = JobBuilder.newJob(DemoJob.class).withIdentity("jd-s1","jdg-s1").withDescription("jdg-s1").build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("tg-s1", "tgg-s1").withDescription("tg")
                    .withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ?")).startAt(new Date(System.currentTimeMillis()-1000)).build();
            scheduler.scheduleJob(job, trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        QuartzUtil quartzUtil = new QuartzUtil();
        quartzUtil.cronDemo();
//        quartzUtil.simpleDemo();

//        LocalDate date = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.of(7)));
//        int dayOfMonth = date.getDayOfMonth();
//        System.out.println(dayOfMonth);
    }
}


*/
