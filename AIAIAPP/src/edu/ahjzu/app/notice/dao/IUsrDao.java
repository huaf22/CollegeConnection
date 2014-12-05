package edu.ahjzu.app.notice.dao;

import java.util.List;

import edu.ahjzu.app.notice.pojo.Usr;

public interface IUsrDao {
	// �������û�
	public int insert(Usr usr);

	// ɾ���û�
	public boolean deleteusr(int id);

	// �õ������û�
	public List<Usr> getUsr();

	// �õ������û�
	public List<Usr> getOnlineUsr(int id);

	// �����û�״̬
	public boolean update(Usr data);

	// ��֤�û���¼
	public int login(String name, String pwd);

	//
	public boolean identifyName(String name);

	public int getUsrIdByName(String name);

	public Usr getUsr(int id);
	
}
