package com.study.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.bean.admin;
import com.study.dao.AdminDao;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
	@Resource
	AdminDao dao;

	public admin findNameAPass(String adminName, String adminPassword) {
		return dao.findNameAPass(adminName,adminPassword);
	}
}
