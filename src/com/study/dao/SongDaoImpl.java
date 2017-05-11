package com.study.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.study.bean.song;

@Repository
public class SongDaoImpl implements SongDao {

	@Resource
	SessionFactory factory;

	public void add(song s) {
		factory.getCurrentSession().persist(s);
	}

}
