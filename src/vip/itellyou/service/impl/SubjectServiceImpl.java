package vip.itellyou.service.impl;

import java.util.Date;

import vip.itellyou.dao.OptionDao;
import vip.itellyou.dao.SubjectDao;
import vip.itellyou.dao.impl.OptionDaoImpl;
import vip.itellyou.dao.impl.SubjectDaoImpl;
import vip.itellyou.pojo.Option;
import vip.itellyou.pojo.Subject;
import vip.itellyou.pojo.User;
import vip.itellyou.service.SubjectService;

public class SubjectServiceImpl implements SubjectService {
	private SubjectDao subjectDao;
	private OptionDao optionDao;
	public SubjectServiceImpl(){
		this.subjectDao = new SubjectDaoImpl();
		this.optionDao = new OptionDaoImpl();
	}
	
	@Override
	public void add(Subject subject,User user) throws Exception {
		//1 ��������
		//��������Ŀ�ʼͶƱʱ��ͽ���ͶƱʱ��
		Long now = new Date().getTime();
		subject.setStartTime(now);
		subject.setEndTime(now+1*24*60*60*1000);
		//���÷�����
		subject.setUser(user);
		
		subjectDao.insert(subject);
		subject.setId(subjectDao.findId());
		//2 �м���ѡ�����������ѡ��		
		int x=10/0;
		int i=1;
        for(Option option : subject.getOptions()){
        	//������ź������������id
        	option.setIdx(i++);
        	option.setSubjectId(subject.getId());
        	
        	optionDao.insert(option);
        }
	}

}
