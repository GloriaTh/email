package com.study.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.study.bean.user;

@Repository
public class UserDaoImpl implements UserDao {
	@Resource
	SessionFactory factory;

	@SuppressWarnings("unchecked")
	public List<user> getAll() {
		return factory.getCurrentSession().createQuery("from user").list();
	}

	public void save(user user) {
		factory.getCurrentSession().persist(user);
	}

	public void update(user user) {
		factory.getCurrentSession().merge(user);
	}

	public user find(int userId) {
		return (user) factory.getCurrentSession().get(user.class, userId);
	}

	@SuppressWarnings("unchecked")
	public List<user> list() {
		return factory.getCurrentSession().createQuery("from user").list();
	}

	public int getCount() {
		return factory.getCurrentSession().createQuery("from user").list()
				.size();
	}

	@SuppressWarnings("unchecked")
	public List<user> page(int thisPage, int rowperPage) {
		return factory.getCurrentSession().createQuery(// 分页查询
				"from user")//
				.setFirstResult(thisPage)// 开始值
				.setMaxResults(rowperPage)// +值
				.list();
	}
}
