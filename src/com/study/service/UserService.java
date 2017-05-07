package com.study.service;

import java.util.List;
import java.util.Map;

import com.study.bean.user;

public interface UserService {

	public List<user> getAll();

	public void save(user user);

	public void update(user user);

	public user find(int userId);

	public List<user> list();

	public Map<String, Object> page(int thisPage, int i);


}
