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
	 *处理登录表单中填写的用户名和密码
	 * 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1 获取提交的数据
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String remember = request.getParameter("remember");
		
		User user =new User();
		user.setName(name);
		user.setPwd(pwd);
		
		try {
			//2 调用业务逻辑方法
			UserService userService =new UserServiceImpl();
			user = userService.login(user);
			
			//登录成功了，在session中记录用户对象
			HttpSession session = request.getSession();
			session.setAttribute(User.SESSION_NAME, user);
			
			//检查是否勾选了记住我，将用户id记录到cookie
			if(remember!=null){
				Cookie cookie =new Cookie(User.SESSION_NAME,user.getId().toString());
				cookie.setMaxAge(10*24*60*60);
				response.addCookie(cookie);
			}
			
			//3 衔接JSP：
			//默认一个地址，有进入登录的地址则重定向会到原来的地址
			//数据包：  refere
			//request.getHeader("属性名")			
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
