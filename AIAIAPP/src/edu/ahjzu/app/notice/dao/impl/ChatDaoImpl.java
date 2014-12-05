package edu.ahjzu.app.notice.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import edu.ahjzu.app.notice.dao.IChatDao;
import edu.ahjzu.app.notice.daobasic.ConnBasic;
import edu.ahjzu.app.notice.init.initAll;
import edu.ahjzu.app.notice.pojo.Chat;

public class ChatDaoImpl implements IChatDao {

	/*
	 * 删除过期的记录 加入数据的时候将系统的实时时间设为记录的时间 删除3天前的记录
	 */
	public boolean delete() {
		Connection conn = ConnBasic.getconn();
		Timestamp deadtime = new Timestamp(System.currentTimeMillis()
				- initAll.chartdeletetime);

		PreparedStatement pstmt = null;

		String strsql = "delete from chart where time <=?";
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(strsql);

			pstmt.setTimestamp(1, deadtime);

			pstmt.execute();
			System.out.println(pstmt.toString());
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	/*
	 * //得到最新的 public ArrayList<chartData> getnewData(int ClientId){
	 * ArrayList<chartData> result=new ArrayList(); ResultSet rs=null;
	 * PreparedStatement pstmt=null;
	 * 
	 * String strsql="select id from chart order by id desc limit 1"; try {
	 * pstmt = (PreparedStatement) conn.prepareStatement(strsql);
	 * 
	 * rs = pstmt.executeQuery();
	 * 
	 * while(rs.next()){
	 * 
	 * int lastId=rs.getInt("id");
	 * 
	 * if((lastId-ClientId)>=10){ result=getTenNewData(); }else{
	 * result=getlaveData(ClientId); } } } catch (SQLException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); return null; } return
	 * result;
	 * 
	 * }
	 */
	public ArrayList<Chat> getById(int ClientId) {
		Connection conn = ConnBasic.getconn();
		ArrayList<Chat> result = new ArrayList<Chat>();
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		String strsql = "SELECT * FROM chart  where id > ClientId";
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(strsql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Chat data = new Chat();
				result.add(data);
				data.setid(rs.getInt("id") + "");
				data.setcontent(rs.getString("content"));
				data.setaddr_Latitude(rs.getDouble("latitude"));
				data.setaddr_Longitude(rs.getDouble("longitude"));
				data.setaddr(rs.getString("addr"));
				data.setpicpath(rs.getString("picpath"));
				data.setgoodop(rs.getInt("goodop"));
				data.setbadop(rs.getInt("badop"));
				data.setDeadline(rs.getTimestamp("time"));
			}
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/*
	 * //select id from chart order by id desc limit 1 //得到最新的10条数据 public
	 * ArrayList<chartData> getTenNewData() { ArrayList<chartData> result=new
	 * ArrayList(); ResultSet rs=null; PreparedStatement pstmt=null;
	 * 
	 * String strsql="SELECT * FROM chart ORDER BY id desc limit 10"; try {
	 * pstmt = (PreparedStatement) conn.prepareStatement(strsql);
	 * 
	 * rs = pstmt.executeQuery();
	 * 
	 * while(rs.next()){ chartData data=new chartData(); result.add(data);
	 * data.setid(rs.getInt("id")+""); data.setcontent(rs.getString("content"));
	 * data.setaddr_Latitude(rs.getDouble("latitude"));
	 * data.setaddr_Longitude(rs.getDouble("longitude"));
	 * data.setaddr(rs.getString("addr"));
	 * data.setpicpath(rs.getString("picpath"));
	 * data.setgoodop(rs.getInt("goodop")); data.setbadop(rs.getInt("badop"));
	 * data.setDeadline(rs.getTimestamp("time")); } //conn.close(); } catch
	 * (SQLException e) { System.out.println(e); return null; } return result; }
	 */
	// 插入数据
	public boolean insertData(Chat data) {
		Connection conn = ConnBasic.getconn();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		PreparedStatement pstmt = null;
		String strsql = "insert into chart (content,longitiude,latitude,addr,picpath,time,goodop,badop) values(?,?,?,?,?,?,?,?)";

		try {
			pstmt = (PreparedStatement) conn.prepareStatement(strsql);
			pstmt.setString(1, data.getcontent());
			pstmt.setDouble(2, data.getaddr_Longitude());
			pstmt.setDouble(3, data.getaddr_Latitude());
			pstmt.setString(4, data.getaddr());
			pstmt.setString(5, data.getpicpath());
			pstmt.setTimestamp(6, time);
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 0);

			pstmt.execute();

		} catch (SQLException e) {
			System.out.println(e);
			return false;
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

}
