package com.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.bean.song;
import com.study.dao.SongDao;

@Service
@Transactional
public class SongServiceImpl implements SongService {
	@Autowired
	SongDao songDao;

	public void add(song s) {
		songDao.add(s);
	}

}
