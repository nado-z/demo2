package com.newer.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.newer.domain.User;
import com.newer.service.UserService;
import com.newer.util.MyConstants;

@Controller
@RequestMapping("login")
//������Ϊ��MyConstants.SESSSION_USER������ͬʱ�󶨵�session��
@SessionAttributes(MyConstants.SESSSION_USER)
public class LoginController {
	@Autowired
	private UserService userService;
  
	//controller����jspҳ������ķ�ʽ��
	
	//JSP  => controller
	//1.�βδ�ֵ����������ؼ�����ͬ)
	//2.����ֵ�����������������ؼ�����ͬ��
	//3.HttpServletRequest�ֶ�����ֵ
	//4.���ʵ�ַ�д�ֵ@PathVariable��restful����У�
	
	//controller => jsp
	 //1.Model ModelMap Map
	 //2.ModelAndView 
	 //....
	
	//����·����login/login
	@RequestMapping("login")
	public String login(String uname,String upwd,Model model ) {
		User user=this.userService.login(uname, upwd);
		if (user!=null) {
			model.addAttribute(MyConstants.SESSSION_USER, user);
			return "redirect:/main";  //�ض�����Ʋ�
		}else {
			model.addAttribute("msg", "�û��������벻��");
			return "forward:/index.jsp";
		}
	}
	
	/**
	 * ע��
	 * @return
	 */
	@RequestMapping("logout")
	public String  logout(HttpSession session,SessionStatus sessionStatus) {
		session.invalidate();
		sessionStatus.setComplete();
		return "redirect:/index.jsp";
	}
}
