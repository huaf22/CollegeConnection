package edu.ahjzu.app.notice.pojo;

import java.sql.Timestamp;

public class Chat {
  private String id="";//id
  private double addr_Longitude=0;//����
  private double addr_Latitude=0;//γ��
  private String addr="";//��ַ������Ϊ��
  private String content="";//��Ϣ����
  private String picpath="";//ͼpain��ַ������Ϊ��
  private int goodop=0;//�������ޣ���Ŀ
  private int badop=0;//������Ŀ
  private Timestamp  time;//���ɵ�ʱ��
  public void setaddr_Longitude(double addr_Longitude){
	  this.addr_Longitude=addr_Longitude;
  }
  public void setaddr_Latitude(double addr_Latitude){
	  this.addr_Latitude=addr_Latitude;
  }
  public void setaddr(String addr){
		  this.addr=addr;
  }
  public void setcontent(String content){
	  this.content=content;
  }
  public void setpicpath(String picpath){
	  this.picpath=picpath;
  }
  public double getaddr_Longitude(){
	  return addr_Longitude;
  }
  public double getaddr_Latitude(){
	  return addr_Latitude;
  }
  public int getgoodop(){
	  return goodop;
  }
  public int getbadop(){
	  return badop;
  }
  public String getaddr(){
	  return addr;
  }
  public String getcontent(){
	  return content;
  }
  public String getpicpath(){
	  return picpath;
  }
  public void setgoodop(int goodop){
	  this.goodop=goodop;
  }
  public void setbadop(int badop){
	  this.badop=badop;
  }
  public void setid(String id){
	  this.id=id;
  }
  public String getid(){
	  return id;
  }
  public void setDeadline(Timestamp  Deadline){
	  this.time=Deadline;
  }
  public Timestamp getDeadline(){
	return time;
	  
  }
}
/*
<id></id>   
<addr_Longitude></addr_Longitude>
<addr_Latitude></addr_Latitude>
<addr></addr>
<content></content>
<picpath></picpath>
<goodop></goodop>
<badop></badop>
*/
