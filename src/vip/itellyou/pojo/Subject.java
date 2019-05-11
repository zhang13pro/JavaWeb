package vip.itellyou.pojo;

import java.util.ArrayList;
import java.util.List;

import vip.itellyou.util.format.DateFormatter;

/**
 * 主题实体类
 * 1>表===实体类
 * 2>字段====属性
 * 3> 外键字段关联的实体类对象|集合作为导航属性
 * 4> 视图属性
 * 5> 静态常量
 * @author Ma Wenhai
 *
 */
public class Subject {
	public static final int SINGLE=1;
	public static final int MULTI=2;
	
	private Long id;
	private String title;
	//投票类型 ： 1 单选   2 多选
	private int number;
	private Long startTime;
	private Long endTime;
	
	//外键字段的处理：导航属性
	private User user;
	//导航：选项
	private List<Option> options;
	
	//视图属性：日期数据，
	private String startTimeView;
	private String endTimeView;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
		//给对应的视图属性赋值		
		this.startTimeView = DateFormatter.toShortDate(this.startTime);
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
		this.endTimeView=DateFormatter.toShortDate(this.endTime);
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getStartTimeView() {
		return startTimeView;
	}
	public String getEndTimeView() {
		return endTimeView;
	}
	public List<Option> getOptions() {
		return options;
	}
	public void setOptions(List<Option> options) {
		this.options = options;
	}
	public Subject() {
		super();
		this.options=new ArrayList<Option>();
	}
	
	
	
}
