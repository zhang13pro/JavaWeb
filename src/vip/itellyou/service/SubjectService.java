package vip.itellyou.service;

import vip.itellyou.pojo.Subject;
import vip.itellyou.pojo.User;

/**
 * 用户业务逻辑接口
 * @author Ma Wenhai
 *
 */
public interface SubjectService {
	//发起调查项目
	public void add(Subject subject,User user) throws Exception;
}
