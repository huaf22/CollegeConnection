package edu.ahjzu.app.notice.dao;

import java.util.List;

import edu.ahjzu.app.notice.pojo.Usr;

public interface IUsrDao {
	// 插入新用户
	public int insert(Usr usr);

	// 删除用户
	public boolean deleteusr(int id);

	// 得到所有用户
	public List<Usr> getUsr();

	// 得到在线用户
	public List<Usr> getOnlineUsr(int id);

	// 更新用户状态
	public boolean update(Usr data);

	// 验证用户登录
	public int login(String name, String pwd);

	//
	public boolean identifyName(String name);

	public int getUsrIdByName(String name);

	public Usr getUsr(int id);
	
}
