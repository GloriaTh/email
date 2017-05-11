package com.study.time;

import java.util.HashSet;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import com.study.bean.scheduleJob;
import com.study.bean.song;
import com.study.bean.user;
import com.study.service.SongService;
import com.study.service.UserService;
import com.study.utils.MailUitls;

/*
 * 推送,定时任务运行的方法
 */
@Controller
public class PushMail implements Job {
	@SuppressWarnings("static-access")
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("start");
		scheduleJob scheduleJob = (scheduleJob) context.getMergedJobDataMap()
				.get("scheduleJob");
		// 加载userService
		ApplicationContext cxt = new ClassPathXmlApplicationContext(
				"spring-common.xml");
		UserService userService = (UserService) cxt.getBean("userServiceImpl");
		SongService songService = (SongService) cxt.getBean("songServiceImpl");
		MusicCrawler m = MusicCrawler.getInstance();
		MailUitls mail = new MailUitls();
		// 获取所有发送邮件的用户
		user user = scheduleJob.getUserId();
		int i = 1;
		// 获取一批歌曲
		m.getMusic(i);
		HashSet<song> songs = m.getSongs();
		// 判断该用户是否推送过该歌曲
		for (song s : songs) {
			if (user.getMusic().contains(s) == false) {
				s.setUserId(user);
				songService.add(s);
				user.getMusic().add(s);
				userService.update(user);
				System.out.println("修改结束");
				// 发送邮件
				mail.sendMail(user.getEmail(), s.getSongName(), s.getSongUrl(),
						user.getUserId());
				break;
			}
		}
		System.out.println("over");
	}
}
