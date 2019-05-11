package vip.itellyou.pojo;

import java.io.Serializable;

/**
 * �û�ʵ����
 * 1> һ���ֶ�=>����
 * 2> ��ÿ�������ṩgetter��setter������
 * 3> �������ع��췽��(һ��Ҫ����Ĭ�ϵĹ��췽��)
 * 
 * 4>��ͼֵ
 * 5> ��̬����
 * @author Ma Wenhai
 *
 */
public class User implements Serializable {
	public static final int ONLINE=2;
	public static final int NOT_ONLINE=1;
	public static final String SESSION_NAME="CurrentUser";
	//null����id��û��ֵ
	private Integer id;
	private String name;
	private String pwd;
	//1 �����ߣ�2 ����
	private int online;
	
	private String confirmPwd;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getOnline() {
		return online;
	}
	public void setOnline(int online) {
		this.online = online;
	}
	public String getConfirmPwd() {
		return confirmPwd;
	}
	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}
	
	
}
