package com.study.utils;

// 文件名 SendEmail.java
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 邮件发送工具类
 * 
 * @author Zhouzhou
 * @date 2016-1-1
 * @time 上午08:52:47
 */
public class MailUitls {
	public void sendMail(String t, String songName, String songUrl, Integer id) {
		String host = "smtp.163.com"; // 发件人使用发邮件的电子信箱服务器
		String from = "13207881163@163.com"; // 发邮件的出发地（发件人的信箱）
		String to = t; // 发邮件的目的地（收件人信箱）

		Properties props = System.getProperties();

		props.put("mail.smtp.host", host);

		props.put("mail.smtp.auth", "true"); // 这样才能通过验证

		MyAuthenticator myauth = new MyAuthenticator("13207881163", "qq564123");
		Session session = Session.getDefaultInstance(props, myauth);

		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));
			message.setSubject("音乐推荐");

			message.setContent(
					"<h1>这是今天为您推荐的歌曲</h1><h3><a target='_BLANK' href='"
							+ songUrl + "'>" + songName
							+ "</a></h3><h3><a target='_BLANK' href='http://98.142.133.78:8080/user/getTask?userId="
							+ id + "'>您可以点此链接修改发送时间和发送邮件地址</a></h3>",
					"text/html;charset=UTF-8");

			message.saveChanges();

			Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
}