package vip.itellyou.ui.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vip.itellyou.pojo.User;
import vip.itellyou.service.UserService;
import vip.itellyou.service.impl.UserServiceImpl;

public class AutoLoginFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		// ����Զ���¼
		// û�е�¼����Ҫ�Զ���¼,session�л�û���û�����
		HttpSession session = request.getSession();
		if (session.getAttribute(User.SESSION_NAME) == null) {

			// ��ȡcookie�е��û�id
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (User.SESSION_NAME.equals(cookie.getName())) {
						// ��cookie�ж�ȡ�û�id
						int id = Integer.parseInt(cookie.getValue());
						try {
							// ����id��ȡ�û�����
							UserService userService = new UserServiceImpl();
							User user = userService.getUser(id);

							// ģ���¼�ɹ������û�����д�뵽session��
							session.setAttribute(User.SESSION_NAME, user);

							break;
						} catch (Exception e) {
							throw new RuntimeException(e);
						}
					}
				}
				
				chain.doFilter(request, response);
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
