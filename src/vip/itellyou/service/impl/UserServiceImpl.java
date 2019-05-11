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
 * �û�����ҵ���߼���
 * @author Ma Wenhai
 *
 */
public class UserServiceImpl implements UserService {
	//ҵ���߼����������ݷ���
	private UserDao userDao;
	public UserServiceImpl(){
		userDao = new UserDaoImpl();
	}
	@Override
	public void register(User user) throws Exception {
		//��Ϊ�û���������Υ��ҵ�����		
		if(user.getName()==null || user.getName().trim().length()==0){
			//�׳��쳣
			throw new RuleException("�û�������Ϊ��");
		}
		//....
		if(!user.getPwd().equals(user.getConfirmPwd())){
			throw new RuleException("ȷ����������벻һ��");
		}
		
		//�û��������ظ�
		UserQueryModel qm = new UserQueryModel();
		qm.setName(user.getName());
		List list = userDao.findByCondition(qm);
		if(list.size()>0){
			throw new RuleException("�û����Ѿ���ע��");
		}
		
		user.setOnline(1);
		user.setPwd(Md5Class.stringToMd5(user.getPwd()));
		
		userDao.insert(user);
	}
	@Override
	public User login(User user) throws Exception {
		//ʵ��һЩҵ���߼�
		//�����û�����������в�ѯ
		UserQueryModel qm = new UserQueryModel();
		qm.setName(user.getName());
		qm.setPwd(Md5Class.stringToMd5(user.getPwd()));
		List list = userDao.findByCondition(qm);
				
		if(list.size()==1){
			user = (User)list.get(0);//��¼��ѯ�Ľ��
			
			//�û��Ѿ����߾Ͳ����ٵ�¼
			if(user.getOnline()==User.ONLINE){
				throw new RuleException("���û��Ѿ���¼");
			}
			
			//�޸��û�������״̬
			user.setOnline(User.ONLINE);
			userDao.update(user);		
		}
		else{
			throw new RuleException("�û������ڻ����������");
		}
		return user;
	}
	@Override
	public User getUser(int id) throws Exception {
		return (User)userDao.findOne(id);
	}

}
