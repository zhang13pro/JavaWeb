package vip.itellyou.service;

import vip.itellyou.pojo.User;

/**
 * �û�ҵ���߼��ӿ�
 * @author Ma Wenhai
 *
 */
public interface UserService {
	public void register(User user) throws Exception;
    
	//�����¼�õ��û���������
	//��ѯ
	public User login(User user) throws Exception;
	public User getUser(int id) throws Exception;
}





