package com.zzp.fengmiweb.utils;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.zzp.fengmiweb.entity.User;

/*
 * 基于JDK实现邮件发送
 * 主要是实现激活码的发送
 * */
public class EmailUtils {
	public static void sendEmail(User user){
		//邮箱
//		String myAccount = "lx_teach@163.com";
		String myAccount = "zzptest@163.com";
		//授权码
//		String myPass = "java168";
		String myPass = "709394zzp";
		//邮箱服务器
		String SMTPHost = "smtp.163.com";
		//设置属性信息
		Properties prop = new Properties();
		//设置协议
		prop.setProperty("mail.transport.protocol", "smtp");
		//邮件服务器
		prop.setProperty("mail.smtp.host", SMTPHost);
		//认证
		prop.setProperty("mail.smtp.auth", "true");
		//1、创建会话
		Session session = Session.getDefaultInstance(prop);
		//设置是否需要调试
		session.setDebug(false);
		//2、创建发送信息
		MimeMessage message = createMsg(session,myAccount,user);
		//4发送信息操作
		try {
			
			Transport tran = session.getTransport();
			//连接
			tran.connect(myAccount, myPass);
			//发送消息 
			tran.sendMessage(message, message.getAllRecipients());
			//关闭
			tran.close();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	//生成邮件消息
	private static MimeMessage createMsg(Session session, String myAccount, User user) {
		//创建消息对象
		MimeMessage message = new MimeMessage(session);
		//设置
		try {
			//3.1发送方
			message.setFrom(new InternetAddress(myAccount, "锋迷网官方邮件", "utf-8"));
			//3.2设置接收方
			/*
			 * MimeMessage.RecipientType.TO 
			 * MimeMessage.RecipientType.CC 
			 * MimeMessage.RecipientType.BCC 
			 * */
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(user.getEmail(), user.getUsername(), "utf-8"));
			//3.3 设置主题
			message.setSubject("锋迷网激活码","utf-8");
			//获取本机的ip地址
			String ip = Inet4Address.getLocalHost().getHostAddress();
			String url = "http://"+ip+":8080/fengmiweb/active?email="+ Base64Utils.encode(user.getEmail())+"&code="+Base64Utils.encode(user.getActivatecode());
			//设置正文信息
			message.setContent(user.getUsername()+",欢迎你加入我们<br>为了更好体验我们的产品，请<a href='"+url+"'>点击激活 "+url+"</a>","text/html;charset=utf-8");
			//设置日期
			message.setSentDate(new Date());
			//保存
			message.saveChanges();
		} catch (UnsupportedEncodingException | MessagingException | UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
}
