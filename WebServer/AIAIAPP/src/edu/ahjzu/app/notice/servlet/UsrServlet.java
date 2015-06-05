package edu.ahjzu.app.notice.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import edu.ahjzu.app.notice.dao.IUsrDao;
import edu.ahjzu.app.notice.dao.impl.FollowDaoImpl;
import edu.ahjzu.app.notice.dao.impl.UsrDaoImpl;
import edu.ahjzu.app.notice.pojo.Usr;
import edu.ahjzu.app.notice.servlet.controler.LoginHandle;
import edu.ahjzu.app.notice.servlet.controler.RegeistUsrHandle;

public class UsrServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doAll(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doAll(req, resp);
	}

	private void doAll(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String oper = request.getParameter("Oper");
		System.out.println("UsrServlet-------->");
		System.out.println("Oper" + oper);
		//
		if (oper.equals("login")) {// login
			String name = request.getParameter("name");
			String pwd = request.getParameter("pwd");
			System.out.println("login  name: " + name);
			System.out.println("login  pwd: " + pwd);
			LoginHandle.login(name, pwd, response);
		} else if (oper.equals("loginname")) {// loginname
			LoginHandle.IdentifyName(request, response);
		} else if ("getfollow".equals(oper)) {// getfollow
			int usrId = Integer.valueOf(request.getParameter("id"));
			List<Usr> list = new FollowDaoImpl().getFollow(usrId);
			String result = createJson(list);
			PrintWriter writer = null;
			try {
				writer = response.getWriter();
				writer.write(result);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				writer.close();
			}

		} else if ("searchusrbyname".equals(oper)) {// searchusrbyname
			String name = request.getParameter("name");
			System.out.println("name: " + name);
			if (name != null && !"".equals(name)) {
				List<Usr> list = new UsrDaoImpl().getUsrByName(name);
				System.out.println("list size: " + list.size());
				String result = createJson(list);
				try {
					// result = new String(result.getBytes(), "utf8");
					System.out.println(result);
					response.getWriter().write(result);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					response.getWriter().write("{}");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else if ("searchusrbyid".equals(oper)) {// searchusrbyid
			int id = Integer.valueOf(request.getParameter("id"));
			Usr data = new UsrDaoImpl().getUsr(id);
			String result = "";
			if (data != null) {
				result = data.toJson();
				System.out.println("getusr:" + result);
			}
			try {
				response.getWriter().print(result);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (oper.equals("regeistdetail")) {// regeistdetail
			new RegeistUsrHandle().handle(request, response);
		} else if (oper.equals("followadd")) {// followadd
			int usrid = Integer.valueOf(request.getParameter("usrid"));
			int followid = Integer.valueOf(request.getParameter("followid"));
			PrintWriter writer = null;
			try {
				writer = response.getWriter();
				if (new UsrDaoImpl().addFollow(usrid, followid)) {
					writer.write("{SUCCESS}");
					System.out.println("followadd  {SUCCESS}");
				} else {
					writer.write("{FAILURE}");
					System.out.println("followadd  {FAILURE}");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (writer != null)
					writer.close();
			}
		} else if (oper.equals("followdelete")) {// followdelete
			int usrid = Integer.valueOf(request.getParameter("usrid"));
			int followid = Integer.valueOf(request.getParameter("followid"));
			PrintWriter writer = null;
			try {
				writer = response.getWriter();
				if (new UsrDaoImpl().deleteFollow(usrid, followid)) {
					writer.write("{SUCCESS}");
				} else {
					writer.write("{FAILURE}");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (writer != null)
					writer.close();
			}
		}
	}

	private String createJson(List<Usr> list) {
		JSONObject rootObj = new JSONObject();
		JSONArray array = new JSONArray();
		if (list != null)
			for (Usr usr : list) {
				if (usr != null) {
					JSONObject object = new JSONObject();
					object.put("id", usr.getId());
					object.put("name", usr.getName());
					object.put("imgurl", usr.getImgurl());
					array.add(object);
				}
			}
		rootObj.put("usrs", array);
		return rootObj.toJSONString();

	}
}
