package vip.itellyou.util.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 数据访问的辅助类：获取连接对象；释放资源
 * @author Ma Wenhai
 *
 */
public class DbHelper {
	//不要和陌生人说话
	//获取连接对象
	//1 定义c3p0的数据库连接池对象
	private static DataSource dataSource = 
			new ComboPooledDataSource();
	
	//保证同一次请求处理过程中DAO，Filter获取的是同一个连接对象
	//连接对象在线程中应该是唯一的
	//ThreadLocal线程槽：用于在线程中保存数据的集合
	private static ThreadLocal<Connection> cons
	   =new ThreadLocal<Connection>();
	
	public static Connection getConnection() 
			throws Exception{
		//1 从线程槽中读取连接对象A
		Connection con = cons.get();
		//2 判断：连接对象==null
		if(con==null){
		//     是，从数据库连接池中获取连接对象
			con = dataSource.getConnection();
		//        连接对象放入到线程槽
			cons.set(con);
		}
		return  con;
	}
	
	public static void close() throws Exception{
		//关闭数据库连接，同时在线程槽中删除连接对象
		Connection con = cons.get();
		if(con!=null){
			con.close();
			cons.remove();
		}
	}


	//释放资源
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
	
	//封装事务处理的方法
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






