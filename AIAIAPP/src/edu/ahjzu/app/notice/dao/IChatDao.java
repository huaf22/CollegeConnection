package edu.ahjzu.app.notice.dao;

import java.util.ArrayList;

import edu.ahjzu.app.notice.pojo.Chat;


public interface  IChatDao {
	//�õ����µ�����
       public ArrayList<Chat> getById(int ClientId);
       //��������
       public boolean insertData(Chat data);
       //ɾ�����ڵļ�¼
       /*
        * �������ݵ�ʱ��ϵͳ��ʵʱʱ����Ϊ��¼��ʱ��
        * ɾ��3��ǰ�ļ�¼
        * */
       public boolean delete();
       
}
