package vip.itellyou.service.impl;

import java.util.List;

import vip.itellyou.dao.UserDao;
import vip.itellyou.dao.impl.UserDaoImpl;
import vip.itellyou.pojo.User;
import vip.itellyou.pojo.UserQueryModel;
import vip.itellyou.service.UserService;
import vip.itellyou.util.exception.RuleException;
import vip.itellyou.util.format.Md5Class;

/**
 * 用户数据业务逻辑类
 * @author Ma Wenhai
 *
 */
public class UserServiceImpl implements UserService {
	//业务逻辑依赖于数据访问
	private UserDao userDao;
	public UserServiceImpl(){
		userDao = new UserDaoImpl();
	}
	@Override
	public void register(User user) throws Exception {
		//因为用户操作不当违反业务规则，		
		if(user.getName()==null || user.getName().trim().length()==0){
			//抛出异常
			throw new RuleException("用户名不能为空");
		}
		//....
		if(!user.getPwd().equals(user.getConfirmPwd())){
			throw new RuleException("确认密码和密码不一致");
		}
		
		//用户名不能重复
		UserQueryModel qm = new UserQueryModel();
		qm.setName(user.getName());
		List list = userDao.findByCondition(qm);
		if(list.size()>0){
			throw new RuleException("用户名已经被注册");
		}
		
		user.setOnline(1);
		user.setPwd(Md5Class.stringToMd5(user.getPwd()));
		
		userDao.insert(user);
	}
	@Override
	public User login(User user) throws Exception {
		//实现一些业务逻辑
		//根据用户名和密码进行查询
		UserQueryModel qm = new UserQueryModel();
		qm.setName(user.getName());
		qm.setPwd(Md5Class.stringToMd5(user.getPwd()));
		List list = userDao.findByCondition(qm);
				
		if(list.size()==1){
			user = (User)list.get(0);//记录查询的结果
			
			//用户已经在线就不能再登录
			if(user.getOnline()==User.ONLINE){
				throw new RuleException("此用户已经登录");
			}
			
			//修改用户的在线状态
			user.setOnline(User.ONLINE);
			userDao.update(user);		
		}
		else{
			throw new RuleException("用户不存在或者密码错误");
		}
		return user;
	}
	@Override
	public User getUser(int id) throws Exception {
		return (User)userDao.findOne(id);
	}

}
