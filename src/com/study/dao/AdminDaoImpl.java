package com.study.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.study.bean.admin;

@Repository
public class AdminDaoImpl implements AdminDao {
	@Resource
	SessionFactory factory;

	public admin findNameAPass(String adminName, String adminPassword) {
		return (admin) factory.getCurrentSession().createQuery(
		"from admin where adminName =? and adminPassword = ?")//
		.setParameter(0, adminName)//
		.setParameter(1, adminPassword)//
		.uniqueResult();
	}

}
