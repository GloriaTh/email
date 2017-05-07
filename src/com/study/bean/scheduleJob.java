package com.study.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
/*
 * 定时任务
 */
@Entity
public class scheduleJob {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer jobId;
	@Column
	private String jobName;
	@Column
	private String jobGroup;
	@Column
	private String jobStatus;
	@Column
	private String cronExpression;
	@Column
	private String des;
	@Column
	private int isSend;

	@OneToOne
	@JoinColumn(name = "userId")
	private user userId;

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public int getIsSend() {
		return isSend;
	}

	public void setIsSend(int isSend) {
		this.isSend = isSend;
	}

	public user getUserId() {
		return userId;
	}

	public void setUserId(user userId) {
		this.userId = userId;
	}

}
