package vip.itellyou.dao.impl;

import java.sql.ResultSet;

import vip.itellyou.dao.OptionDao;
import vip.itellyou.pojo.Option;
import vip.itellyou.util.base.BaseDaoImpl;
import vip.itellyou.util.base.BaseQueryModel;

public class OptionDaoImpl extends BaseDaoImpl implements OptionDao {

	@Override
	public String getInsertSql(Object data) {
		Option option = (Option)data;
		return "insert into t_option(content,idx,subjectId) values"+
				"('"+option.getContent()+"',"+option.getIdx()+","+option.getSubjectId()+")";
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
