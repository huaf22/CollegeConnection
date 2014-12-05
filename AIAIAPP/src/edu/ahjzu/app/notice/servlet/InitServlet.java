package edu.ahjzu.app.notice.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ahjzu.app.notice.servlet.controler.LoginHandle;
import edu.ahjzu.app.notice.servlet.controler.chatRefreshOper;
import edu.ahjzu.app.notice.servlet.controler.getonlinepeopleOper;
import edu.ahjzu.app.notice.servlet.controler.RegeistUsrHandle;
import edu.ahjzu.app.notice.servlet.controler.jstlOper;
import edu.ahjzu.app.notice.servlet.controler.updateUsrOper;

/**
 * servlet总操作接口
 * 
 * 
 * @author 赵鲜华
 * @since 2013-9-6 下午08:38:59
 */
public class InitServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			doAll(request, response);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			doAll(request, response);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private void doAll(HttpServletRequest request,
			final HttpServletResponse response) throws IOException {
		System.out.println("ip:" + request.getRemoteAddr());
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String oper = request.getParameter("Oper");
		System.out.println("Oper：" + oper);
		if ("servertest".equals(oper)) {
			String dateStr = format.format(new Date());
			response.getWriter().write(dateStr);
		} else if (oper.equals("logindevice")) {// 注册用户设备
			LoginHandle.LoginDevice(request, response);
		} else if (oper.equals("chartreshresh")) {
			new chatRefreshOper().handle(request, response);
		} else if (oper.equals("updateusr")) {
			new updateUsrOper().handle(request, response);
		} else if (oper.equals("getonlinepeople")) {
			new getonlinepeopleOper().handle(request, response);
		} else if (oper.equals("chartadd")) {// chart插入
			// new insertusrOper().handle(request, response);
		} else if (oper.equals("jstl")) {
			new jstlOper().handle(request, response);
		}
		// else if (oper.equals("identifyserver")) {
		// LoginHandle.IdentifyServer(request, response);
		// }
		System.out.println("ok");
	}

}
