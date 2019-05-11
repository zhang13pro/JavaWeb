package vip.itellyou.service;

import vip.itellyou.pojo.User;

/**
 * 用户业务逻辑接口
 * @author Ma Wenhai
 *
 */
public interface UserService {
	public void register(User user) throws Exception;
    
	//处理登录用的用户名和密码
	//查询
	public User login(User user) throws Exception;
	public User getUser(int id) throws Exception;
}





