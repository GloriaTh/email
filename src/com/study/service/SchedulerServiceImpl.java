package com.study.service;

import java.util.List;

import javax.annotation.Resource;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.study.bean.scheduleJob;
import com.study.dao.SchedulerDao;
import com.study.time.PushMail;

@Service("schedulerService")
public class SchedulerServiceImpl implements SchedulerService {

	@Resource
	SchedulerDao schedulerdao;
	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	public void addTask(scheduleJob job) {
		schedulerdao.addTask(job);
		if (job.getIsSend() == 0) {
			add(job);
		}
	}

	public void updateTask(scheduleJob job, int isSend, int send) {
		schedulerdao.updateTask(job);

		if (send == 0) {
			if (isSend == 0) {
				update(job);
			} else {
				delete(job);
			}
		} else {
			if (isSend == 0) {
				add(job);
			} else {
			}
		}

	}

	public List<scheduleJob> getAll() {
		return schedulerdao.getAll();
	}

	public scheduleJob find(int scheduleJobId) {
		return schedulerdao.find(scheduleJobId);
	}

	public void add(scheduleJob job) {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job
				.getJobGroup());
		// 获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
		CronTrigger trigger = null;
		try {
			trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		} catch (SchedulerException e1) {
			e1.printStackTrace();
		}
		// 不存在，创建一个
		if (null == trigger) {
			JobDetail jobDetail = JobBuilder.newJob(PushMail.class)
					.withIdentity(job.getJobName(), job.getJobGroup()).build();
			jobDetail.getJobDataMap().put("scheduleJob", job);
			// 表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
					.cronSchedule(job.getCronExpression());
			// 按新的cronExpression表达式构建一个新的trigger
			trigger = TriggerBuilder.newTrigger().withIdentity(
					job.getJobName(), job.getJobGroup()).withSchedule(
					scheduleBuilder).build();
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
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
					.withSchedule(scheduleBuilder).build();
			// 按新的trigger重新设置job执行
			try {
				scheduler.rescheduleJob(triggerKey, trigger);
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		}
	}

	public void update(scheduleJob job) {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job
				.getJobGroup());
		// 获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
		CronTrigger trigger = null;
		try {
			trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		// 表达式调度构建器
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
				.cronSchedule(job.getCronExpression());
		// 按新的cronExpression表达式重新构建trigger
		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
				.withSchedule(scheduleBuilder).build();
		// 按新的trigger重新设置job执行
		try {
			scheduler.rescheduleJob(triggerKey, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	public void delete(scheduleJob job) {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
		try {
			scheduler.deleteJob(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

}