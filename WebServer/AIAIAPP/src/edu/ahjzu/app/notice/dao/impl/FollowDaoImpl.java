package edu.ahjzu.app.notice.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import TOOLS.indexHTML;

import edu.ahjzu.app.notice.dao.IUsrDao;
import edu.ahjzu.app.notice.daobasic.ConnBasic;
import edu.ahjzu.app.notice.pojo.Usr;

public class FollowDaoImpl {
	public boolean delete(int usrId, int followId) {
		Connection conn = ConnBasic.getconn();

		PreparedStatement pstmt = null;

		String strsql = "delete from followinfo where usrid =? and followid =?";
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(strsql);
			pstmt.setInt(1, usrId);
			pstmt.setInt(2, followId);
			boolean isOK = pstmt.execute();
			return isOK;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean add(int usrId, int followId) {
		if (isExist(usrId, followId)) {
			return false;
		}
		Connection conn = ConnBasic.getconn();
		PreparedStatement pstmt = null;
		String strsql = "insert into followinfo (usrid,followid) values(?,?)";
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(strsql);
			pstmt.setInt(1, usrId);
			pstmt.setInt(2, followId);
			boolean isOK = pstmt.execute();
			return isOK;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isExist(int usrid, int followid) {
		Connection conn = ConnBasic.getconn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlString = "select id from followinfo where usrid =? and followid =?";
		try {
			pstmt = conn.prepareStatement(sqlString);
			pstmt.setInt(1, usrid);
			pstmt.setInt(2, followid);
			rs = pstmt.executeQuery();
			if (rs != null)
				while (rs.next()) {
					return true;
				}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public List<Usr> getFollow(int usrId) {
		Connection conn = ConnBasic.getconn();
		PreparedStatement pstmt = null;
		List<Usr> resultList = new ArrayList<Usr>();
		ResultSet rs = null;
		IUsrDao usrDao = new UsrDaoImpl();
		String sqlString = "select followid from followinfo where usrid =?";
		try {
			pstmt = conn.prepareStatement(sqlString);
			pstmt.setInt(1, usrId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int followid = rs.getInt("followid");
				if (followid > 0) {
					Usr usr = usrDao.getUsr(followid);
					resultList.add(usr);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultList;
	}
}
