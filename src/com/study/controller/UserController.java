package com.study.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.study.bean.scheduleJob;
import com.study.bean.user;
import com.study.service.SchedulerService;
import com.study.service.UserService;
import com.study.utils.GeneralMethod;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private SchedulerService schedulerService;

	//添加定时任务
	@RequestMapping("/addTask")
	public void addTask(HttpServletRequest request,
			HttpServletResponse response, String hour, String min,
			String email, int isSend) {
		user user = new user();
		//设置电子邮件
		user.setEmail(email);
		userService.save(user);
		//定时任务
		scheduleJob job = new scheduleJob();
		//绑定用户
		job.setUserId(user);
		//时间
		job.setCronExpression("00 " + min + " " + hour + " * * ?");
		//是否发送
		job.setIsSend(0);
		//定时任务名
		job.setJobName("推荐");
		schedulerService.addTask(job);
		user.setJob(job);
		userService.update(user);
		GeneralMethod.BackMessage("success", response);
	}

	//修改定时任务
	@RequestMapping("/updateTask")
	public void updateTask(HttpServletRequest request,
			HttpServletResponse response, int userId, String hour, String min,
			String email, int isSend) {
		user user = userService.find(userId);
		user.setEmail(email);
		scheduleJob job = user.getJob();
		job.setCronExpression("00 " + min + " " + hour + " * * ?");
		int send = job.getIsSend();
		job.setIsSend(isSend);
		schedulerService.updateTask(job, isSend, send);
		user.setJob(job);
		userService.update(user);
		GeneralMethod.BackMessage("success", response);
	}
	
	//获取对应id定时任务跳转至修改页面
	@RequestMapping("/getTask")
	public ModelAndView getTask(HttpServletRequest request,
			HttpServletResponse response, int userId) {
		user user = userService.find(userId);
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", String.valueOf(userId));
		map.put("email", user.getEmail());
		String[] ss = user.getJob().getCronExpression().split(" ");
		map.put("hour", ss[2]);
		map.put("min", ss[1]);
		map.put("isSend", String.valueOf(user.getJob().getIsSend()));
		ModelAndView mv = new ModelAndView("/updateTask");
		mv.addObject("job", map);
		return mv;
	}

	//管理员修改用
	@RequestMapping("/getTaskM")
	public ModelAndView getTaskM(HttpServletRequest request,
			HttpServletResponse response, int userId) {
		user user = userService.find(userId);
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", String.valueOf(userId));
		map.put("email", user.getEmail());
		String[] ss = user.getJob().getCronExpression().split(" ");
		map.put("hour", ss[2]);
		map.put("min", ss[1]);
		map.put("isSend", String.valueOf(user.getJob().getIsSend()));
		ModelAndView mv = new ModelAndView("/updateTaskM");
		mv.addObject("job", map);
		return mv;
	}

	@RequestMapping("/test")
	public void test(HttpServletRequest request, HttpServletResponse response) {
		user user = new user();
		user.setEmail("626876282@qq.com");
		userService.save(user);
		scheduleJob job = new scheduleJob();
		job.setUserId(user);
		job.setCronExpression("0 54 20 * * ?");
		job.setIsSend(0);
		job.setJobName("推荐");
		schedulerService.addTask(job);
		user.setJob(job);
		userService.update(user);
	}

	//获取所有的定时任务
	@RequestMapping("/getAllTask")
	public void getAllTask(HttpServletRequest request,
			HttpServletResponse response, int thisPage) {
		Map<String, Object> map = userService.page(thisPage, 15);
		GeneralMethod.BackMessage(map, response);
	}
}
