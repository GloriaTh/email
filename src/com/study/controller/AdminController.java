package com.study.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.study.bean.admin;
import com.study.service.AdminService;
import com.study.utils.GeneralMethod;
import com.study.utils.RandomValidateCode;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	//登陆
	@RequestMapping("/login")
	public void login(HttpServletRequest request, HttpServletResponse response,
			String adminName, String adminPassword, String rand) {
		response.setContentType("text/html;charset=utf-8");

		//获取验证码
		String sessionid = request.getSession().getId();
		String arandom = (String) request.getSession().getAttribute(
				sessionid + "imagecode");

		//验证
		if (arandom.equals(rand) == false) {
			GeneralMethod.BackMessage("验证码错误!", response);
			return;
		}

		//查询是否有对应账户
		admin admin = adminService.findNameAPass(adminName, adminPassword);

		if (admin == null) {
			GeneralMethod.BackMessage("用户名或密码错误!", response);
			return;
		} else {
			//保存至session
			request.getSession().setAttribute("admin", admin);
		}
	}

	@RequestMapping("/loginOut")
	public ModelAndView loginOut(HttpServletRequest request,
			HttpServletResponse response, String username, String password,
			Integer type, String rand) {
		request.getSession().invalidate();
		return new ModelAndView("index.jsp");
	}

	@RequestMapping("/rand")
	public void rand(HttpServletRequest request, HttpServletResponse response,
			String rand) {
		response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
		response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Set-Cookie", "name=value; HttpOnly");// 设置HttpOnly属性,防止Xss攻击
		response.setDateHeader("Expire", 0);
		RandomValidateCode randomValidateCode = new RandomValidateCode();
		try {
			randomValidateCode.getRandcode(request, response, "imagecode");// 输出图片方法
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
