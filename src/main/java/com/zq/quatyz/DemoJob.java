/*
package com.zq.quatyz;

import org.quartz.*;

public class DemoJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("任务执行中。。。");

        System.out.println("上次执行时间: " + context.getPreviousFireTime());// 上次执行时间
        System.out.println("本次执行时间  " + context.getFireTime());// 本次执行时间
        System.out.println("下一次执行时间: " + context.getNextFireTime());// 下一次执行时间
        System.out.println("循环次数: " + context.getJobRunTime());// 循环次数

        System.out.println("<- 结束执行.");
//        SimpleTrigger st = (SimpleTrigger)context.getTrigger();
//        System.out.println("当前次数: " + st.getTimesTriggered());
        new ThreadLocal<>().remove();
    }
}
*/
