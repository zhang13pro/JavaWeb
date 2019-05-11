package vip.itellyou.ui.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vip.itellyou.util.dao.DbHelper;

/**
 * �������ر����ݿ�����
 * @author Ma Wenhai
 *
 */
public class TransFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse)res;
		
		try {
			//1 ��servletִ��֮ǰ����������
			DbHelper.beginTransaction();
			
			//2 ����
			chain.doFilter(request, response);
			
			//3 servletִ��֮���ύ����
			DbHelper.commitTransaction();
			
			DbHelper.close();
		} catch (Exception e) {
			try {
				DbHelper.rollbackTransaction();
				DbHelper.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//����ȫ�ֵ��쳣���������
			throw new RuntimeException(e);
		}
		
		

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
