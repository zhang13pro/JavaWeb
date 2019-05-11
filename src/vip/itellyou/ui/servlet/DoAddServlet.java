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
		//��ȡ������ύ������
		String title= request.getParameter("title");
		String number = request.getParameter("number");
		//ҳ���Ͽ��Զ����Ԫ��ͬname��
		String[] options = request.getParameterValues("options");
		
		//ʵ���������Ϊ��������
		Subject subject = new Subject();
		subject.setTitle(title);
		subject.setNumber(Integer.parseInt(number));
		for(String content:options){
			Option option = new Option();
			option.setContent(content);
			
			subject.getOptions().add(option);
		}
		
		try {
			//2 ����ҵ���߼����󣬵���ҵ�񷽷���������
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute(User.SESSION_NAME);
			SubjectService subjectService=new SubjectServiceImpl();
			subjectService.add(subject, user);
			
			//3 �ν�JSP���ض���/login��¼ҳ��
			response.sendRedirect(request.getContextPath()+"/list");
			
		}catch(RuleException re){
			//��Ϊ�û���������Υ��ҵ�����
			//�������ݻ��ԣ����Ƿ���ע���  reg.jsp
			//ͨ������󴫻ؽ��յ������ݣ�����ԭ��
//			request.setAttribute("user", user);
//			request.setAttribute("message", re.getMessage());
//			request.getRequestDispatcher("/WEB-INF/pages/reg.jsp")
//			    .forward(request, response);			
			
		}catch (Exception e) {	
			//����ȫ�ֵ��쳣���������
			throw new RuntimeException(e);
			
		}
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
