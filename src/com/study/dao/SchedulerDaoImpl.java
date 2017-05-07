package com.study.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.study.bean.scheduleJob;

@Repository
public class SchedulerDaoImpl implements SchedulerDao {

	@Resource
	SessionFactory factory;

	public void addTask(scheduleJob job) {
		factory.getCurrentSession().persist(job);
	}

	public void updateTask(scheduleJob job) {
		factory.getCurrentSession().merge(job);
	}

	@SuppressWarnings("unchecked")
	public List<scheduleJob> getAll() {
		return factory.getCurrentSession().createQuery("from scheduleJob")
				.list();
	}

	public scheduleJob find(int scheduleJobId) {
		return (scheduleJob) factory.getCurrentSession().get(scheduleJob.class,
				scheduleJobId);
	}
}
