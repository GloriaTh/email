package com.study.dao;

import java.util.List;

import com.study.bean.user;

public interface UserDao {

	public List<user> getAll();

	public void save(user user);

	public void update(user user);

	public user find(int userId);

	public List<user> list();

	public List<user> page(int i, int rowperPage);

	public int getCount();

}
