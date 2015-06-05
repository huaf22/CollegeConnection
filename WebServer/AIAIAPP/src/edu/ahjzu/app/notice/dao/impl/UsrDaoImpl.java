package edu.ahjzu.app.notice.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.ahjzu.app.notice.dao.IUsrDao;
import edu.ahjzu.app.notice.daobasic.ConnBasic;
import edu.ahjzu.app.notice.pojo.Usr;

/**
 * 
 * @author 赵鲜华
 * @since 2013-9-6 下午08:23:47
 */
public class UsrDaoImpl implements IUsrDao {
	/**
	 * 查找用户名是否已经存在
	 * 
	 * @param name
	 * @return
	 * @see edu.ahjzu.app.notice.dao.IUsrDao#selectByName(java.lang.String)
	 */
	public boolean identifyName(String name) {
		Connection conn = ConnBasic.getconn();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String strsql = "SELECT id FROM usr where name =?";
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(strsql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	public byte[] getImage(int id) {
		byte[] result = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Connection connection = ConnBasic.getconn();
		String sql = "select image from usr where id = ?";
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				result = resultSet.getBytes("image");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				connection.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public List<Usr> getUsrByName(String name) {
		List<Usr> list = new ArrayList<Usr>();
		Connection conn = ConnBasic.getconn();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String strsql = "select id,name,imgurl from usr where name like ?";
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(strsql);
			pstmt.setString(1, name + "%");// 模糊查询
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Usr usr = new Usr();
				usr.setId(rs.getInt("id"));
				usr.setName(rs.getString("name"));
				String imgurl = rs.getString("imgurl");
				if (!"".equals(imgurl)) {
					usr.setImgurl("icon/" + imgurl);// 添加图片具体路径
				}
				list.add(usr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 根据用户id删除用户
	 * 
	 * @param id
	 * @return
	 * @see edu.ahjzu.app.notice.dao.IUsrDao#deleteusr(int)
	 */
	public boolean deleteusr(int id) {
		Connection conn = ConnBasic.getconn();
		PreparedStatement pstmt = null;
		String strsql = "delete from usr where id = ?";
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(strsql);
			pstmt.setInt(1, id);
			pstmt.execute();
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
		return true;
	}

	/**
	 * 
	 * 描述
	 * 
	 * @param strsql
	 * @return
	 */
	private List<Usr> selectUsr(String strsql) {
		Connection conn = ConnBasic.getconn();
		List<Usr> result = new ArrayList<Usr>();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(strsql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(">>");
				Usr data = new Usr();
				result.add(data);
				data.setId(rs.getInt("id"));
				data.setName(rs.getString("name"));
				data.setSex(rs.getString("sex"));
				data.setAge(rs.getInt("age"));
				data.setMode(rs.getInt("mode"));
				data.setStatus(rs.getString("status"));
				data.setPlace(rs.getString("place"));
				data.setLatitude(rs.getDouble("latitude"));
				data.setLongitiude(rs.getDouble("longitiude"));
				data.setAddr(rs.getString("addr"));
				data.setUserId(rs.getString("userid"));
				data.setChannelId(rs.getString("channelid"));
				String imgurl = rs.getString("imgurl");
				if (!"".equals(imgurl)) {
					data.setImgurl("icon/" + imgurl);// 添加图片具体路径
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
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

	/**
	 * 根据用户id返回用户名
	 * 
	 * @param name
	 * @return
	 * @see edu.ahjzu.app.notice.dao.IUsrDao#getUsrIdByName(java.lang.String)
	 */
	public int getUsrIdByName(String name) {
		Connection conn = ConnBasic.getconn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String strsql = "SELECT id FROM usr where name = ?";
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(strsql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				return rs.getInt("id");
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;

	}

	/**
	 * 返回所有在线用户
	 * 
	 * @return
	 * @see edu.ahjzu.app.notice.dao.IUsrDao#getOnlineUsr()
	 */
	public List<Usr> getOnlineUsr(int id) {
		String strsql = "SELECT * FROM usr where mode = 1 AND id <> " + id;
		System.out.println("sql>>" + strsql);
		return selectUsr(strsql);
	}

	/**
	 * 返回所有用户
	 * 
	 * @return
	 * @see edu.ahjzu.app.notice.dao.IUsrDao#getUsr()
	 */
	public List<Usr> getUsr() {
		String strsql = "SELECT * FROM usr";
		return selectUsr(strsql);
	}

	/**
	 * 插入用户
	 * 
	 * @param data
	 * @return
	 * @see edu.ahjzu.app.notice.dao.IUsrDao#insert(edu.ahjzu.app.notice.pojo.Usr)
	 */
	public int insert(Usr data) {
		int id = -1;
		Connection conn = ConnBasic.getconn();
		PreparedStatement pstmt = null;
		String strsql = "insert into usr ("
				+ "name,sex,age,truename,college,specialty,qq,imgname,image,mode,pwd) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(strsql,
					Statement.RETURN_GENERATED_KEYS);
			System.err.println("insert name:  " + data.getName());
			pstmt.setString(1, data.getName());
			pstmt.setString(2, data.getSex());
			pstmt.setInt(3, data.getAge());
			pstmt.setString(4, data.getTruename());
			pstmt.setString(5, data.getCollege());
			pstmt.setString(6, data.getSpecialty());
			pstmt.setInt(7, data.getQq());
			if (data.getImgname() != null && !"".equals(data.getImgname())
					&& data.getImage() != null) {
				pstmt.setString(8, data.getImgname());
				pstmt.setBytes(9, data.getImage());
			} else {
				pstmt.setString(8, "");
				pstmt.setBytes(9, null);
			}
			pstmt.setInt(10, 0);
			pstmt.setString(11, data.getPwd());
			pstmt.executeUpdate();// 执行
			ResultSet rs = pstmt.getGeneratedKeys(); // 获取结果
			if (rs.next()) {
				id = rs.getInt(1);// 取得ID
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
		return id;
	}

	/**
	 * 
	 * 根据用户id返回用户数据
	 * 
	 * @param id
	 * @return
	 */
	public Usr getUsrById(int id) {
		Connection conn = ConnBasic.getconn();
		Usr result = new Usr();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String strsql = "select name,imgname from usr where id=?";
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(strsql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result.setName(rs.getString("name"));
				result.setImgname(rs.getString("imgname"));
			}
		} catch (SQLException e) {
			System.out.println(e);
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

	/**
	 * 更新用户数据
	 * 
	 * @param data
	 * @return
	 * @see edu.ahjzu.app.notice.dao.IUsrDao#update(edu.ahjzu.app.notice.pojo.Usr)
	 */
	public boolean update(Usr data) {
		Connection conn = ConnBasic.getconn();
		PreparedStatement pstmt = null;
		String strsql = "update usr set (mode= ?,status= ?,place= ?,latitude= ?, longitiude= ?,addr= ?,channelid = ?,userid = ? )where id= ?";

		try {
			pstmt = (PreparedStatement) conn.prepareStatement(strsql);

			pstmt.setInt(1, data.getMode());
			pstmt.setString(2, data.getStatus());
			pstmt.setString(3, data.getPlace());
			pstmt.setDouble(4, data.getLatitude());
			pstmt.setDouble(5, data.getLongitiude());
			pstmt.setString(6, data.getAddr());
			pstmt.setString(7, data.getChannelId());
			pstmt.setString(8, data.getUserId());
			pstmt.setInt(9, data.getId());
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

	/**
	 * 验证用户登陆
	 */
	public int login(String name, String pwd) {
		Connection conn = ConnBasic.getconn();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String strsql = "SELECT id,pwd FROM usr where name = ?";
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(strsql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getString("pwd").equals(pwd)) {
					return rs.getInt("id");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	public Usr getUsr(int id) {
		Usr data = null;
		Connection conn = ConnBasic.getconn();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sqlString = "select * from usr where id = ?";
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sqlString);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				data = new Usr();
				data.setId(rs.getInt("id"));
				data.setName(rs.getString("name"));
				data.setImgname(rs.getString("imgname"));
				data.setSex(rs.getString("sex"));
				data.setAge(rs.getInt("age"));
				data.setMode(rs.getInt("mode"));
				data.setStatus(rs.getString("status"));
				data.setPlace(rs.getString("place"));
				data.setLatitude(rs.getDouble("latitude"));
				data.setLongitiude(rs.getDouble("longitiude"));
				data.setAddr(rs.getString("addr"));
				data.setUserId(rs.getString("userid"));
				String imgurl = rs.getString("imgurl");
				if (!"".equals(imgurl)) {
					data.setImgurl("icon/" + imgurl);// 添加图片具体路径
				}
				data.setChannelId(rs.getString("channelid"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	public boolean updateLoginState(int id, String userid, String channelid) {
		Connection conn = ConnBasic.getconn();
		// 如果密码正确就设置usr的mode属性为1
		String sqlString = "update  usr set mode = 1 ,userid =? ,channelid =? where id =?";
		try {
			PreparedStatement pstmt = (PreparedStatement) conn
					.prepareStatement(sqlString);
			pstmt.setString(1, userid);
			pstmt.setString(2, channelid);
			pstmt.setInt(3, id);
			pstmt.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean identifyFollow(int usrid, int followid) {
		Connection conn = ConnBasic.getconn();
		String sqlString = "select id from followinfo where usrid =? and followid =?";
		try {
			PreparedStatement pstmt = (PreparedStatement) conn
					.prepareStatement(sqlString);
			pstmt.setInt(1, usrid);
			pstmt.setInt(2, followid);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean addFollow(int usrid, int followid) {
		Connection conn = ConnBasic.getconn();
		// 如果已经关注
		if (identifyFollow(usrid, followid)) {
			return false;
		}
		String sqlString = "insert  into followinfo (usrid,followid) values (?,?)";
		try {
			PreparedStatement pstmt = (PreparedStatement) conn
					.prepareStatement(sqlString,
							Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, usrid);
			pstmt.setInt(2, followid);
			pstmt.executeUpdate();// 执行
			ResultSet rs = pstmt.getGeneratedKeys(); // 获取结果
			if (rs.next()) {
				int id = rs.getInt(1);// 取得ID
				if (id > 0) {
					System.out.println("addFollow  id: " + id);
					return true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteFollow(int usrid, int followid) {
		Connection conn = ConnBasic.getconn();
		String sqlString = "delete from followinfo where usrid =? and followid =?";

		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sqlString);
			pstmt.setInt(1, usrid);
			pstmt.setInt(2, followid);
			pstmt.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
