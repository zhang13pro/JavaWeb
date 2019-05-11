package vip.itellyou.dao.impl;

import java.util.List;

import org.junit.Test;

import junit.framework.Assert;
import vip.itellyou.pojo.User;
import vip.itellyou.pojo.UserQueryModel;

/**
 * �����ࣺ
 * 1> ������Ŀ����İ���һ��
 * 2> ���� = "Test"+Ŀ��������
 * @author Ma Wenhai
 *
 */
public class TestUserDaoImpl {
	@Test //ע��
	//���Է����� = "test"+Ŀ�귽����
	public void testInsert() throws Exception{
		//����Ŀ����Ķ���
		UserDaoImpl userDao = new UserDaoImpl();
		//����Ŀ������Ŀ�귽�����õ�ʵ�����еĽ��
		User user = new User();
		user.setName("����");
		user.setPwd("111111");
		user.setOnline(1);
		
		int actual = userDao.insert(user);
		//����Ԥ�ƵĽ��
		int expected=1;
		
		//ʹ�ö�����ķ����Ƚ�ʵ�����еĽ����Ԥ�ƵĽ��
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testFindAll() throws Exception{
		UserDaoImpl userDao = new UserDaoImpl();
		List list = userDao.findAll();
		int expected=7;
		Assert.assertEquals(expected, list.size());
	}
	@Test
	public void testFindByCondition() throws Exception{
		UserDaoImpl userDao = new UserDaoImpl();
		UserQueryModel qm = new UserQueryModel();
		qm.setName("Rose");
		qm.setOnline(1);
		List list = userDao.findByCondition(qm);
		
		int expected=1;
		Assert.assertEquals(expected, list.size());
	}
	
}






