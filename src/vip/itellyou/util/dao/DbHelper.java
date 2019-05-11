package vip.itellyou.util.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * ���ݷ��ʵĸ����ࣺ��ȡ���Ӷ����ͷ���Դ
 * @author Ma Wenhai
 *
 */
public class DbHelper {
	//��Ҫ��İ����˵��
	//��ȡ���Ӷ���
	//1 ����c3p0�����ݿ����ӳض���
	private static DataSource dataSource = 
			new ComboPooledDataSource();
	
	//��֤ͬһ�������������DAO��Filter��ȡ����ͬһ�����Ӷ���
	//���Ӷ������߳���Ӧ����Ψһ��
	//ThreadLocal�̲ۣ߳��������߳��б������ݵļ���
	private static ThreadLocal<Connection> cons
	   =new ThreadLocal<Connection>();
	
	public static Connection getConnection() 
			throws Exception{
		//1 ���̲߳��ж�ȡ���Ӷ���A
		Connection con = cons.get();
		//2 �жϣ����Ӷ���==null
		if(con==null){
		//     �ǣ������ݿ����ӳ��л�ȡ���Ӷ���
			con = dataSource.getConnection();
		//        ���Ӷ�����뵽�̲߳�
			cons.set(con);
		}
		return  con;
	}
	
	public static void close() throws Exception{
		//�ر����ݿ����ӣ�ͬʱ���̲߳���ɾ�����Ӷ���
		Connection con = cons.get();
		if(con!=null){
			con.close();
			cons.remove();
		}
	}


	//�ͷ���Դ
	public static void closeAll(Connection con,
			PreparedStatement pst,ResultSet rs) throws Exception{
		if(rs!=null){
			rs.close();
		}
		if(pst!=null){
			pst.close();
		}
		if(con!=null){
			con.close();
		}
	}
	
	//��װ������ķ���
	public static void beginTransaction() throws Exception{
		getConnection().setAutoCommit(false);
	}
	
	public static void commitTransaction() throws Exception{
		getConnection().commit();
	}
	
	public static void rollbackTransaction() throws Exception{
		getConnection().rollback();
	}
}






