package com.study.listener;

import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.study.bean.scheduleJob;
import com.study.service.SchedulerService;
import com.study.time.PushMail;

/*
 * 在加载完成后,初始化所有定时任务
 */
public class InstantiationTracingBeanPostProcessor implements
		ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	SchedulerService schedulerService;
	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	private static boolean flag = true;

	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println(flag);
		if (flag) {
			System.out.println("初始化开始");
			List<scheduleJob> list = schedulerService.getAll();
			for (scheduleJob job : list) {
				if (job.getIsSend() == 0) {
					Scheduler scheduler = schedulerFactoryBean.getScheduler();
					TriggerKey triggerKey = TriggerKey.triggerKey(job
							.getJobName(), job.getJobGroup());
					// 获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
					CronTrigger trigger = null;
					try {
						trigger = (CronTrigger) scheduler
								.getTrigger(triggerKey);
					} catch (SchedulerException e1) {
						e1.printStackTrace();
					}
					// 不存在，创建一个
					if (null == trigger) {
						JobDetail jobDetail = JobBuilder.newJob(PushMail.class)
								.withIdentity(job.getJobName(),
										job.getJobGroup()).build();
						jobDetail.getJobDataMap().put("scheduleJob", job);
						// 表达式调度构建器
						CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
								.cronSchedule(job.getCronExpression());
						// 按新的cronExpression表达式构建一个新的trigger
						trigger = TriggerBuilder.newTrigger().withIdentity(
								job.getJobName(), job.getJobGroup())
								.withSchedule(scheduleBuilder).build();
						try {
							scheduler.scheduleJob(jobDetail, trigger);
						} catch (SchedulerException e) {
							e.printStackTrace();
						}
					} else {
						// Trigger已存在，那么更新相应的定时设置
						// 表达式调度构建器
						CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
								.cronSchedule(job.getCronExpression());
						// 按新的cronExpression表达式重新构建trigger
						trigger = trigger.getTriggerBuilder().withIdentity(
								triggerKey).withSchedule(scheduleBuilder)
								.build();
						// 按新的trigger重新设置job执行
						try {
							scheduler.rescheduleJob(triggerKey, trigger);
						} catch (SchedulerException e) {
							e.printStackTrace();
						}
					}
				}
			}
			flag = false;
			System.out.println("初始化结束");
		}
	}
}