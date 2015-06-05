package edu.ahjzu.app.notice.servlet.controler;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ahjzu.app.notice.dao.IChatDao;
import edu.ahjzu.app.notice.dao.impl.ChatDaoImpl;
import edu.ahjzu.app.notice.pojo.Chat;

public class chatRefreshOper {
	public void handle(HttpServletRequest request, HttpServletResponse response) {
		String addr_Longitude = request.getParameter("addr_Longitude");
		String addr_Latitude = request.getParameter("addr_Latitude");
		String addr = request.getParameter("addr");
		String content = request.getParameter("content");

		Chat data = new Chat();

		data.setaddr(addr);
		data.setaddr_Longitude(Double.parseDouble(addr_Longitude));
		data.setaddr_Latitude(Double.parseDouble(addr_Latitude));
		data.setcontent(content);
		data.setgoodop(0);
		data.setbadop(0);
		data.setpicpath("");
		data.setDeadline(new Timestamp(System.currentTimeMillis()));

		IChatDao chartoper = new ChatDaoImpl();
		chartoper.insertData(data);
	}
}
