package edu.ahjzu.app.notice.pojo;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

public class News {
	public String id = "";
	public String title = "";
	public String content = "";
	public byte[] image = null;
	private String imgName = null;

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String url = "";
	public String time = "";

	public News() {
	}

	public News(String id, String title, String content, String imgName,
			String url, String time) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.imgName = imgName;
		this.url = url;
		this.time = time;
	}

	public static List<News> toObject(String json) {
		if (json != null && !"".equals(json)) {
			JSONObject object = JSONObject.parseObject(json);
			JSONArray array = object.getJSONArray("news");
			List<News> newList = new ArrayList<News>();
			for (int i = 0; i < array.size(); ++i) {
				JSONObject obj = array.getJSONObject(i);
				News news = new News(obj.getString("id"),
						obj.getString("title"), obj.getString("content"),
						obj.getString("imgname"), obj.getString("url"),
						obj.getString("time"));
				newList.add(news);
			}
			return newList;
		}
		return null;
	}

	public static String toJson(List<News> list) {
		JSONObject rootObject = new JSONObject();
		JSONArray array = new JSONArray();
		try {
			for (int i = 0; i < list.size(); ++i) {
				News ele = list.get(i);
				JSONObject obj = new JSONObject();
				obj.put("id", ele.getId());
				obj.put("content", ele.getContent());
				obj.put("url", ele.getUrl());
				obj.put("time", ele.getTime());
				obj.put("title", ele.getTitle());
				obj.put("imgname", ele.getImgName());
				array.add(obj);
			}
			rootObject.put("news", array);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		System.out.println(rootObject.toString());
		return rootObject.toString();
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;

	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
