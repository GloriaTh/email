package com.study.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.bean.user;
import com.study.bean.userEx;
import com.study.dao.UserDao;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao;

	public List<user> getAll() {
		return userDao.getAll();
	}

	public void save(user user) {
		userDao.save(user);
	}

	public void update(user user) {
		userDao.update(user);
	}

	public user find(int userId) {
		return userDao.find(userId);
	}

	public List<user> list() {
		return userDao.list();
	}

	public Map<String, Object> page(int thisPage, int rowperPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 当前页
		map.put("thispage", thisPage);

		List<user> list = userDao.page((thisPage - 1) * rowperPage, rowperPage);

		int countrow = userDao.getCount();
		map.put("countrow", countrow);

		List<userEx> maplist = new ArrayList<userEx>();

		for (user u : list) {
			userEx userEx = new userEx();
			userEx.setUserId(u.getUserId());
			userEx.setEmail(u.getEmail());
			userEx.setJob(u.getJob().getCronExpression());
			if (u.getJob().getIsSend() == 0) {
				userEx.setIsSend("是");
			} else {
				userEx.setIsSend("否");
			}

			maplist.add(userEx);
		}

		map.put("list", maplist);

		return map;
	}

}
