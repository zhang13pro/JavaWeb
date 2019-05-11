package vip.itellyou.pojo;

import java.util.ArrayList;
import java.util.List;

import vip.itellyou.util.format.DateFormatter;

/**
 * ����ʵ����
 * 1>��===ʵ����
 * 2>�ֶ�====����
 * 3> ����ֶι�����ʵ�������|������Ϊ��������
 * 4> ��ͼ����
 * 5> ��̬����
 * @author Ma Wenhai
 *
 */
public class Subject {
	public static final int SINGLE=1;
	public static final int MULTI=2;
	
	private Long id;
	private String title;
	//ͶƱ���� �� 1 ��ѡ   2 ��ѡ
	private int number;
	private Long startTime;
	private Long endTime;
	
	//����ֶεĴ�����������
	private User user;
	//������ѡ��
	private List<Option> options;
	
	//��ͼ���ԣ��������ݣ�
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
		//����Ӧ����ͼ���Ը�ֵ		
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
