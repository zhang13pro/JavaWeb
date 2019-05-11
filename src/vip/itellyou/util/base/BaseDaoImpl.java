package vip.itellyou.util.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vip.itellyou.util.dao.DbHelper;

/**
 * ���ݷ��ʵĸ��࣬ʵ�ָ��ӿ��е���ɾ�Ĳ�ķ���
 * 
 * @author Ma Wenhai
 *
 */
public abstract class BaseDaoImpl implements BaseDao {

	@Override
	public int insert(Object data) throws Exception {
		// JDBC
		// 1 �������ݿ����Ӷ���Connection
		Connection con = DbHelper.getConnection();
		// 2 ��дsql��䣬�����������PreparedStatement
		String sql = getInsertSql(data);
		PreparedStatement pst = con.prepareStatement(sql);

		// 3 ִ�����executeUpdate ,executeQuery
		int rows = pst.executeUpdate();

		// 4 ����ִ�еĽ����ResultSet�����
		// ������еļ�¼ת����ʵ�������
		// 5 �ͷ���Դ(�ر�����)
		DbHelper.closeAll(null, pst, null);
		return rows;
	}

	@Override
	public int update(Object data) throws Exception {
		// 1 �������ݿ����Ӷ���Connection
		return 0;
	}

	@Override
	public int delete(int id) throws Exception {
		// 1 �������ݿ����Ӷ���Connection
		return 0;
	}

	public List findAll() throws Exception {
		// 1 �������ݿ����Ӷ���Connection
		Connection con = DbHelper.getConnection();
		// 2 ��дsql��䣬�����������PreparedStatement
		String sql = getFindAllSql();
		PreparedStatement pst = con.prepareStatement(sql);

		// 3 ִ�����executeUpdate ,executeQuery
		ResultSet rs = pst.executeQuery();
		// 4 ����ִ�еĽ����ResultSet�����
		// ������еļ�¼ת����ʵ�������
		List list = new ArrayList();
		// ���ս�����е����ݽ���ѭ��
		while (rs.next()) {
			// ��һ����¼ת����java����
			Object data = rsToObject(rs);
			// ��������뵽������
			list.add(data);
		}

		// 5 �ͷ���Դ(�ر�����)
		DbHelper.closeAll(null, pst, rs);

		return list;
	}

	// ����id������һ������
	public Object findOne(int id) throws Exception {
		return null;
	}

	// �������������������������ļ�¼
	// �������������ݣ�һ��������ĳЩ���Ե�ֵ��ʵ����
	// UserQueryModel SubjectQueryModel

	public List findByCondition(BaseQueryModel queryModel) throws Exception {
		// 1 �������ݿ����Ӷ���Connection
		Connection con = DbHelper.getConnection();
		// 2 ��дsql��䣬�����������PreparedStatement
		String sql = getFindConditionSql(queryModel);
		PreparedStatement pst = con.prepareStatement(sql);

		// 3 ִ�����executeUpdate ,executeQuery
		ResultSet rs = pst.executeQuery();
		// 4 ����ִ�еĽ����ResultSet�����
		// ������еļ�¼ת����ʵ�������
		List list = new ArrayList();
		while (rs.next()) {
			// ��һ����¼ת����java����
			Object data = rsToObject(rs);
			// ��������뵽������
			list.add(data);
		}

		// 5 �ͷ���Դ(�ر�����)
		DbHelper.closeAll(null, pst, rs);

		return list;
	}

	public Long findId() throws Exception {
		// 1 �������ݿ����Ӷ���Connection
		Connection con = DbHelper.getConnection();
		// 2 ��дsql��䣬�����������PreparedStatement
		String sql = "SELECT LAST_INSERT_ID() AS maxid";
		PreparedStatement pst = con.prepareStatement(sql);

		// 3 ִ�����executeUpdate ,executeQuery
		ResultSet rs = pst.executeQuery();
		// 4 ����ִ�еĽ����ResultSet�����
		// ������еļ�¼ת����ʵ�������
		Long result = null;
		if (rs.next()) {
			result = rs.getLong("maxid");
		}

		// 5 �ͷ���Դ(�ر�����)
		DbHelper.closeAll(null, pst, rs);

		return result;
	}

	// ����sql���ĳ��󷽷�����������ʵ��
	public abstract String getInsertSql(Object data);

	public abstract String getFindAllSql();

	public abstract String getFindConditionSql(BaseQueryModel queryModel);

	// ��������е�һ����¼ת����һ��JAva����
	public abstract Object rsToObject(ResultSet rs) throws Exception;
}
