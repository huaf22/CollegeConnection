package edu.ahjzu.app.notice.daobasic;

import java.sql.Connection;
import java.sql.SQLException;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

/**
 * �������ݿ����ӳ�
 * 
 * @author zhaoxianhua
 * 
 */
public class ConnBasic {
	private static BoneCP connectionPool = null;

	/*
	 * ��ʼ�����ݿ����ӳ�
	 */
	static {
		// �������ݿ�����
		UserPsw userPsw = new UserPsw();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		BoneCPConfig config = new BoneCPConfig();//����
		config.setJdbcUrl(userPsw.getDbname());//���ݿ�����
		config.setUsername(userPsw.getName());//�û���
		config.setPassword(userPsw.getPassword());//����
		config.setMinConnectionsPerPartition(5);//����������Ŀ
		config.setMaxConnectionsPerPartition(30);//�����������
		config.setPartitionCount(1);
		try {
			connectionPool = new BoneCP(config);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * �õ����ݿ�����
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
	 * e) { System.out.println(e); } conn = con; System.out.println("���ݿⴴ�����ӣ�" +
	 * con.toString()); }
	 */
	public static void main(String[] args) {
		// ConnBasic basic=new ConnBasic();
		Connection conn = ConnBasic.getconn();

	}
}
