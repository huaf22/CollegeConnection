package edu.ahjzu.app.notice.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import TOOLS.MD5;
import edu.ahjzu.app.notice.daobasic.ConnBasic;
import edu.ahjzu.app.notice.pojo.News;

public class NewsDaoImpl {
	private SimpleDateFormat format = new SimpleDateFormat("yyyyMMDDhhmmss");

	/**
	 * 删除数据
	 */
	public boolean deleteById(int id) {
		Connection conn = ConnBasic.getconn();
		PreparedStatement pstmt = null;

		String strsql = "delete from news where id =?";
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(strsql);
			pstmt.setInt(1, id);
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
	 * 提取数据库的最新的条数据
	 */
	public ArrayList<News> getNews(int id) {
		Connection conn = ConnBasic.getconn();
		ArrayList<News> result = new ArrayList<News>();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String strsql = "SELECT * FROM news ";// where id >?

		try {
			pstmt = (PreparedStatement) conn.prepareStatement(strsql);
			// pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				News data = new News();
				result.add(data);

				data.setId(rs.getInt("id") + "");
				data.setTitle(rs.getString("title"));
				data.setContent(rs.getString("content"));
				data.setTime(rs.getDate("time").toString());
				data.setUrl(rs.getString("url"));
				data.setImgName(rs.getString("imgname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
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

	/**
	 * 插入新闻数据
	 */
	public boolean insertData(String title, String content, String url,
			InputStream in, String imgName) {
		Connection conn = ConnBasic.getconn();
		PreparedStatement pstmt = null;
		String strsql = "insert into news (title,content,url,time,imgname,image)values(?,?,?,?,?,?);";
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(strsql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, url);
			Timestamp timestamp = new Timestamp(new Date().getTime());
			pstmt.setTimestamp(4, timestamp);
			int pointIndex = -1;
			if (in != null && imgName != null && !"".equals(imgName)
					&& (pointIndex = imgName.lastIndexOf(".")) != -1) {
				// 加上日期时间生成MD5值
				imgName = MD5.getMD5(imgName.subSequence(0, pointIndex)
						+ format.format(new Date()))
						+ imgName.substring(pointIndex);
				pstmt.setString(5, imgName);
				try {
					int len = in.available();
					System.out.println("in.av:" + len);
					pstmt.setBinaryStream(6, in, len);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				pstmt.setString(5, null);
				pstmt.setBinaryStream(6, null);
				System.err.println("未图片出错：" + imgName);
			}
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
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
	 * @param id
	 * @return
	 */
	public byte[] getImage(int id) {
		byte[] result = null;
		Connection conn = ConnBasic.getconn();
		PreparedStatement pstmt = null;
		String strsql = "SELECT image FROM news where id = ?";
		ResultSet rs = null;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(strsql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getBytes("image");
				System.out.println("读取图片：" + result.length);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
}
