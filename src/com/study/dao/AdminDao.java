package com.study.dao;

import com.study.bean.admin;

public interface AdminDao {

	public admin findNameAPass(String adminName, String adminPassword);
}
