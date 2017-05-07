package com.study.dao;

import java.util.List;

import com.study.bean.scheduleJob;

public interface SchedulerDao {

	public void addTask(scheduleJob job);

	public void updateTask(scheduleJob job);

	public List<scheduleJob> getAll();

	public scheduleJob find(int scheduleJobId);
}
