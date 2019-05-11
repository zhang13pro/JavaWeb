package vip.itellyou.ui.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vip.itellyou.pojo.User;
import vip.itellyou.service.UserService;
import vip.itellyou.service.impl.UserServiceImpl;
import vip.itellyou.util.exception.RuleException;

public class DoLoginServlet extends HttpServlet {

	/**
	 *�����¼������д���û���������
	 * 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1 ��ȡ�ύ������
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String remember = request.getParameter("remember");
		
		User user =new User();
		user.setName(name);
		user.setPwd(pwd);
		
		try {
			//2 ����ҵ���߼�����
			UserService userService =new UserServiceImpl();
			user = userService.login(user);
			
			//��¼�ɹ��ˣ���session�м�¼�û�����
			HttpSession session = request.getSession();
			session.setAttribute(User.SESSION_NAME, user);
			
			//����Ƿ�ѡ�˼�ס�ң����û�id��¼��cookie
			if(remember!=null){
				Cookie cookie =new Cookie(User.SESSION_NAME,user.getId().toString());
				cookie.setMaxAge(10*24*60*60);
				response.addCookie(cookie);
			}
			
			//3 �ν�JSP��
			//Ĭ��һ����ַ���н����¼�ĵ�ַ���ض���ᵽԭ���ĵ�ַ
			//���ݰ���  refere
			//request.getHeader("������")			
			response.sendRedirect(request.getContextPath()+"/list");
			
			
		}catch(RuleException re){
			request.setAttribute("user", user);
			request.setAttribute("message", re.getMessage());
			request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
