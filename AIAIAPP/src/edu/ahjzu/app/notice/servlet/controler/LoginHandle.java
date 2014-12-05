package edu.ahjzu.app.notice.servlet.controler;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ahjzu.app.notice.dao.IUsrDao;
import edu.ahjzu.app.notice.dao.impl.UsrDaoImpl;
import edu.ahjzu.app.notice.pojo.Usr;

public class LoginHandle {
	public static void LoginDevice(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int id = Integer.valueOf(request.getParameter("id"));
			String userid = request.getParameter("userid");
			String channelid = request.getParameter("channelid");
			//
			System.out.println("登录信息");
			System.out.println("id:" + id);
			System.out.println("userid:" + userid);
			System.out.println("channelid:" + channelid);
			if (id != -1) {
				boolean isok = new UsrDaoImpl().updateLoginState(id, userid,
						channelid);
				System.out.println("ip:" + request.getRemoteAddr());
				System.out.println("登录用户id:" + id);
				if (isok) {
					response.getWriter().print("sucess>>" + id);
				} else {
					response.getWriter().print("failure");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void IdentifyName(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter writer = null;
		try {
			String result = "";
			String name = request.getParameter("name");
			System.out.println("loginNameOper验证用户名：" + name);
			if (!"".equals(name)) {
				writer = response.getWriter();
				boolean isHave = new UsrDaoImpl().identifyName(name);
				if (isHave) {
					result = "false";
				} else {
					result = "true";
				}
				writer.write(result);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	// public static void IdentifyServer(HttpServletRequest request,
	// HttpServletResponse response) {
	// PrintWriter writer = null;
	// try {
	// writer = response.getWriter();
	// writer.write("服务器验证成功！");
	// } catch (IOException e) {
	// e.printStackTrace();
	// } finally {
	// if (writer != null) {
	// writer.flush();
	// writer.close();
	// }
	// }
	// }
	public static void login(String name, String pwd,
			HttpServletResponse response) {
		int id = new UsrDaoImpl().login(name, pwd);
		String result = "";
		if (id > 0) {
			Usr usr = new UsrDaoImpl().getUsr(id);
			if (usr != null) {
				result = usr.toJson();
			}
		}
		System.out.println("getusr:" + result);
		try {
			response.getWriter().print(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
