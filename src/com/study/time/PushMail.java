package com.study.time;

import java.util.HashSet;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import com.study.bean.song;
import com.study.bean.user;
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
		//加载userService
		ApplicationContext cxt = new ClassPathXmlApplicationContext(
				"spring-common.xml");
		UserService userService = (UserService) cxt.getBean("userServiceImpl");
		MusicCrawler m = MusicCrawler.getInstance();
		MailUitls mail = new MailUitls();
		// 获取所有发送邮件的用户
		List<user> users = userService.getAll();
		int i = 1;
		while (users.size() != 0) {
			// 获取一批歌曲
			m.getMusic(i);
			HashSet<song> songs = m.getSongs();
			for (int x = 0; x < users.size(); x++) {
				// 判断该用户是否推送过该歌曲
				HashSet<String> usersong = users.get(x).getMusic();
				int size = users.get(x).getMusic().size();
				for (song s : songs) {
					//set 如果size没有变化说明已经推荐过该歌曲
					usersong.add(s.getSongUrl());
					if (usersong.size() != size) {
						user user = users.get(x);
						user.getMusic().add(s.getSongUrl());
						userService.update(user);
						//发送邮件
						mail.sendMail(users.get(x).getEmail(), s.getSongName(),
								s.getSongUrl(), users.get(x).getUserId());
						users.remove(users.get(x));
						break;
					}
				}
			}
			i++;
		}
		System.out.println("over");
	}
}
