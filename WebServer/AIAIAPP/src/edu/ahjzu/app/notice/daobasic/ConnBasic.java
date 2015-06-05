package edu.ahjzu.app.notice.daobasic;

import java.sql.Connection;
import java.sql.SQLException;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

/**
 * 配置数据库连接池
 * 
 * @author zhaoxianhua
 * 
 */
public class ConnBasic {
	private static BoneCP connectionPool = null;

	/*
	 * 初始化数据库连接池
	 */
	static {
		// 配置数据库连接
		UserPsw userPsw = new UserPsw();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		BoneCPConfig config = new BoneCPConfig();//配置
		config.setJdbcUrl(userPsw.getDbname());//数据库名称
		config.setUsername(userPsw.getName());//用户名
		config.setPassword(userPsw.getPassword());//密码
		config.setMinConnectionsPerPartition(5);//最少连接数目
		config.setMaxConnectionsPerPartition(30);//最大连接数据
		config.setPartitionCount(1);
		try {
			connectionPool = new BoneCP(config);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 得到数据库连接
	 * 
	 * @return
	 */
	public static Connection getconn() {
		Connection connection = null;
		try {
			connection = connectionPool.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	// Connection connection = null;
	/*
	 * static { Connection con = null; try {
	 * Class.forName("com.mysql.jdbc.Driver"); } catch (ClassNotFoundException
	 * e) { System.out.println(e); } try { con =
	 * DriverManager.getConnection(DBname, username, PW); } catch (SQLException
	 * e) { System.out.println(e); } conn = con; System.out.println("数据库创建连接：" +
	 * con.toString()); }
	 */
	public static void main(String[] args) {
		// ConnBasic basic=new ConnBasic();
		Connection conn = ConnBasic.getconn();

	}
}
