package com.study.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/*
 * 用户
 */
@Entity
public class user {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@Column
	private String email;

	@OneToMany(mappedBy = "userId", fetch = FetchType.EAGER)
	private Set<song> music = new HashSet<song>();

	@OneToOne(mappedBy = "userId")
	private scheduleJob job;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Set<song> getMusic() {
		return music;
	}

	public void setMusic(Set<song> music) {
		this.music = music;
	}

	public scheduleJob getJob() {
		return job;
	}

	public void setJob(scheduleJob job) {
		this.job = job;
	}

}
