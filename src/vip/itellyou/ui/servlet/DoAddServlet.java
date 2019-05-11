package vip.itellyou.ui.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vip.itellyou.pojo.Option;
import vip.itellyou.pojo.Subject;
import vip.itellyou.pojo.User;
import vip.itellyou.service.SubjectService;
import vip.itellyou.service.impl.SubjectServiceImpl;
import vip.itellyou.util.exception.RuleException;

public class DoAddServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取浏览器提交的数据
		String title= request.getParameter("title");
		String number = request.getParameter("number");
		//页面上可以多个表单元素同name，
		String[] options = request.getParameterValues("options");
		
		//实体类对象作为数据载体
		Subject subject = new Subject();
		subject.setTitle(title);
		subject.setNumber(Integer.parseInt(number));
		for(String content:options){
			Option option = new Option();
			option.setContent(content);
			
			subject.getOptions().add(option);
		}
		
		try {
			//2 创建业务逻辑对象，调用业务方法处理数据
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute(User.SESSION_NAME);
			SubjectService subjectService=new SubjectServiceImpl();
			subjectService.add(subject, user);
			
			//3 衔接JSP：重定向到/login登录页面
			response.sendRedirect(request.getContextPath()+"/list");
			
		}catch(RuleException re){
			//因为用户操作不当违反业务规则
			//进行数据回显：还是发回注册表单  reg.jsp
			//通过域对象传回接收到的数据，错误原因
//			request.setAttribute("user", user);
//			request.setAttribute("message", re.getMessage());
//			request.getRequestDispatcher("/WEB-INF/pages/reg.jsp")
//			    .forward(request, response);			
			
		}catch (Exception e) {	
			//交给全局的异常处理过滤器
			throw new RuntimeException(e);
			
		}
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
