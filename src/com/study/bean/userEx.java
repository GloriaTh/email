package com.study.bean;

import java.util.HashSet;
/*
 * 用户-gson传送给前台
 */
public class userEx {
	private Integer userId;
	private String email;

	private HashSet<String> music = new HashSet<String>();

	private String isSend;

	private String job;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public HashSet<String> getMusic() {
		return music;
	}

	public void setMusic(HashSet<String> music) {
		this.music = music;
	}

	public String getIsSend() {
		return isSend;
	}

	public void setIsSend(String isSend) {
		this.isSend = isSend;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

}
