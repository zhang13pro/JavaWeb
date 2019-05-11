package vip.itellyou.ui.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import vip.itellyou.util.filter.MyRequest;

public class EncodeFilter implements Filter {
    //��¼Ŀ����������
	private String encode;
	
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)req;
		//��servletִ��֮ǰ������request��response��CharacterEncoding
		req.setCharacterEncoding(encode);
		res.setCharacterEncoding(encode);
		
		//�����GET��ʽ�ύ�ģ�����req�����Ϊ�Լ��������������������
		if("GET".equals(request.getMethod().toUpperCase())){
			MyRequest mr  = new MyRequest(request);
			mr.setEncode(this.encode);
			req=mr;
		}
		
		chain.doFilter(req,res);
		

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		//FilterConfig����web.xml��������
		//�������еĳ�ʼ������
		encode = config.getInitParameter("enc");

	}

}
