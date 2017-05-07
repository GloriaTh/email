package com.study.bean;

import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
/*
 * 用户
 */
@Entity
public class user {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	@Column
	private String email;

	@Column
	private HashSet<String> music = new HashSet<String>();

	@OneToOne(mappedBy = "userId")
	private scheduleJob job;

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

	public scheduleJob getJob() {
		return job;
	}

	public void setJob(scheduleJob job) {
		this.job = job;
	}

}
