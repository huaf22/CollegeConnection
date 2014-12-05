package edu.ahjzu.app.notice.dao;

import java.util.ArrayList;

import edu.ahjzu.app.notice.pojo.Chat;


public interface  IChatDao {
	//得到最新的数据
       public ArrayList<Chat> getById(int ClientId);
       //插入数据
       public boolean insertData(Chat data);
       //删除过期的记录
       /*
        * 加入数据的时候将系统的实时时间设为记录的时间
        * 删除3天前的记录
        * */
       public boolean delete();
       
}
