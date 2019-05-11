package vip.itellyou.util.filter;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MyRequest extends HttpServletRequestWrapper {
    private String encode;
   
    public void setEncode(String encode){
    	this.encode = encode;
    }
    
	public MyRequest(HttpServletRequest request) {
		super(request);
	}
	
	public String getParameter(String name){
		String result = null;
		try {
			//���ø����getParamter��ISO-8859-1�����ȡ������ύ������
			//Ȼ����ISO-8859-1���뽫�ַ���ת�����ֽ�����
			byte[] bs  = super.getParameter(name).getBytes("ISO-8859-1");
			//ʹ���ֽ��������¹������ַ��������ַ����ı�����ָ���ı���
			result =  new String(bs,this.encode);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	

}
