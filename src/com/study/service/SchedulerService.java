package com.study.service;

import java.util.List;

import com.study.bean.scheduleJob;

public interface SchedulerService {

	public void addTask(scheduleJob job);

	public void updateTask(scheduleJob job, int isSend, int send);

	public List<scheduleJob> getAll();

	public scheduleJob find(int scheduleJobId);
}