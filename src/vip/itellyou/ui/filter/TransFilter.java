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
 * 事务处理，关闭数据库连接
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
			//1 在servlet执行之前，开启事务
			DbHelper.beginTransaction();
			
			//2 放行
			chain.doFilter(request, response);
			
			//3 servlet执行之后，提交事务
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
			//交给全局的异常处理过滤器
			throw new RuntimeException(e);
		}
		
		

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
