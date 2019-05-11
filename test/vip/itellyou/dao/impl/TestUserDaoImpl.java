package vip.itellyou.dao.impl;

import java.util.List;

import org.junit.Test;

import junit.framework.Assert;
import vip.itellyou.pojo.User;
import vip.itellyou.pojo.UserQueryModel;

/**
 * 测试类：
 * 1> 包名和目标类的包名一致
 * 2> 类名 = "Test"+目标类类名
 * @author Ma Wenhai
 *
 */
public class TestUserDaoImpl {
	@Test //注解
	//测试方法名 = "test"+目标方法名
	public void testInsert() throws Exception{
		//创建目标类的对象
		UserDaoImpl userDao = new UserDaoImpl();
		//调用目标对象的目标方法，得到实际运行的结果
		User user = new User();
		user.setName("张三");
		user.setPwd("111111");
		user.setOnline(1);
		
		int actual = userDao.insert(user);
		//定义预计的结果
		int expected=1;
		
		//使用断言类的方法比较实际运行的结果和预计的结果
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






