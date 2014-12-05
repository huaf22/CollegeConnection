package edu.ahjzu.app.notice.pojo;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

public class Usr {

	private int id;
	private String pwd;
	private String truename;
	private String name;
	private String imgname;
	private String sex;// 男/女
	private int age;
	private int mode;// 1在线/0不在线
	private String status;
	private String place;
	private double latitude;
	private double longitiude;
	private String addr;
	private String college;
	private String specialty;
	private int qq;
	private String ChannelId;
	private String UserId;
	private byte[] image;
	private String imgurl;

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgname() {
		return imgname;
	}

	public void setImgname(String imgname) {
		this.imgname = imgname;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitiude() {
		return longitiude;
	}

	public void setLongitiude(double longitiude) {
		this.longitiude = longitiude;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getChannelId() {
		return ChannelId;
	}

	public void setChannelId(String channelId) {
		ChannelId = channelId;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getTruename() {
		return truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public int getQq() {
		return qq;
	}

	public void setQq(int qq) {
		this.qq = qq;
	}

	@Override
	public String toString() {
		return "id:" + getId() + "\n name:" + getName() + "\n imgname"
				+ getImgname();
	}

	public static String toJson(List<Usr> list) {
		JSONObject rootObject = new JSONObject();
		JSONArray array = new JSONArray();
		try {
			if (list != null) {
				System.out.println("usrlist:  " + list.size());
				for (Usr usr : list) {
					JSONObject object = new JSONObject();
					object.put("id", usr.getId());
					object.put("name", usr.getName());
					object.put("truename", usr.getTruename());
					object.put("imgname", usr.getImgname());
					object.put("sex", usr.getSex());
					object.put("age", usr.getAge());
					object.put("mode", usr.getMode());
					object.put("status", usr.getStatus());
					object.put("place", usr.getPlace());
					object.put("latitude", usr.getLatitude());
					object.put("longitiude", usr.getLongitiude());
					object.put("addr", usr.getAddr());
					object.put("channelId", usr.getChannelId());
					object.put("userId", usr.getUserId());
					object.put("college", usr.getCollege());
					object.put("specialty", usr.getSpecialty());
					object.put("qq", usr.getQq());
					object.put("imgurl", usr.getImgurl());
					array.add(object);
				}
			}
			rootObject.put("usrs", array);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		String result = rootObject.toString();
		System.out.println("usr.toJson" + result);
		return result;

	}

	public String toJson() {
		JSONObject object = new JSONObject();
		try {
			
			object.put("id", getId());
			object.put("name", getName());
			object.put("truename", getTruename());
			object.put("imgname", getImgname());
			object.put("sex", getSex());
			object.put("age", getAge());
			object.put("mode", getMode());
			object.put("status", getStatus());
			object.put("place", getPlace());
			object.put("latitude", getLatitude());
			object.put("longitiude", getLongitiude());
			object.put("addr", getAddr());
			object.put("channelId", getChannelId());
			object.put("userId", getUserId());
			object.put("college", getCollege());
			object.put("specialty", getSpecialty());
			object.put("qq", getQq());
			object.put("imgurl", getImgurl());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return object.toString();
	}

	public static Usr fromJson(String jsonString) {
		Usr usr = null;
		try {
			usr = new Usr();
			JSONObject object = JSONObject.parseObject(jsonString);
			usr.setId(object.getIntValue("id"));
			usr.setName(object.getString("name"));
			usr.setTruename(object.getString("truename"));
			usr.setImgname(object.getString("imgname"));
			usr.setSex(object.getString("sex"));
			usr.setAge(object.getIntValue("age"));
			usr.setMode(object.getIntValue("mode"));
			usr.setStatus(object.getString("status"));
			usr.setPlace(object.getString("place"));
			usr.setLatitude(object.getDouble("latitude"));
			usr.setLongitiude(object.getDouble("longitiude"));
			usr.setAddr(object.getString("addr"));
			usr.setChannelId(object.getString("channelId"));
			usr.setUserId(object.getString("userId"));
			usr.setCollege(object.getString("college"));
			usr.setQq(object.getIntValue("qq"));
			usr.setSpecialty(object.getString("specialty"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return usr;

	}
}
