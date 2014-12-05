package edu.ahjzu.app.notice.servlet.controler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ahjzu.app.notice.dao.IUsrDao;
import edu.ahjzu.app.notice.dao.impl.UsrDaoImpl;
import edu.ahjzu.app.notice.pojo.Usr;

public class getonlinepeopleOper {
	public void handle(HttpServletRequest request, HttpServletResponse response) {
		int id = -1;
		PrintWriter writer = null;
		if (request.getParameter("id") != null) {
			id = Integer.valueOf(request.getParameter("id"));
			System.out.println("id:" + id);
			if (id > 0) {
				// request.getSession().setAttribute("id:", id);
				List<Usr> usrList = new UsrDaoImpl().getOnlineUsr(id);
				try {
					String result = Usr.toJson(usrList);
					System.out.println(result);
					writer = response.getWriter();
					writer.write(result);
					writer.flush();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (writer != null) {
						writer.close();
					}
				}
			}
		}

	}
}
