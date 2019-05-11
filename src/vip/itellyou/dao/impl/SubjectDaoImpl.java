package vip.itellyou.dao.impl;

import java.sql.ResultSet;

import vip.itellyou.dao.SubjectDao;
import vip.itellyou.pojo.Subject;
import vip.itellyou.util.base.BaseDaoImpl;
import vip.itellyou.util.base.BaseQueryModel;

public class SubjectDaoImpl extends BaseDaoImpl implements SubjectDao {

	@Override
	public String getInsertSql(Object data) {
		Subject subject = (Subject)data;		
		return 
        "insert into t_subject(title,number,starttime,endTime,userId) values('"+subject.getTitle()+"',"+subject.getNumber()+","+subject.getStartTime()+","+subject.getEndTime()+","+subject.getUser().getId()+")";
	}

	@Override
	public String getFindAllSql() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFindConditionSql(BaseQueryModel queryModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object rsToObject(ResultSet rs) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
