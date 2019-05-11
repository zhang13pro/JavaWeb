package vip.itellyou.pojo;

import java.io.Serializable;

/**
 * 用户实体类
 * 1> 一个字段=>属性
 * 2> 给每个属性提供getter和setter访问器
 * 3> 考虑重载构造方法(一定要保留默认的构造方法)
 * 
 * 4>视图值
 * 5> 静态常量
 * @author Ma Wenhai
 *
 */
public class User implements Serializable {
	public static final int ONLINE=2;
	public static final int NOT_ONLINE=1;
	public static final String SESSION_NAME="CurrentUser";
	//null代表id中没有值
	private Integer id;
	private String name;
	private String pwd;
	//1 不在线，2 在线
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
