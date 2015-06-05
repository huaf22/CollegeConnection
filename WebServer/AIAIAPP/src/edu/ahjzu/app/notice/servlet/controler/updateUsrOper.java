package edu.ahjzu.app.notice.servlet.controler;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ahjzu.app.notice.dao.IUsrDao;
import edu.ahjzu.app.notice.dao.impl.UsrDaoImpl;
import edu.ahjzu.app.notice.pojo.Usr;

public class updateUsrOper {
	public void handle(HttpServletRequest request, HttpServletResponse response) {
		OutputStream os = null;
		try {
			os = response.getOutputStream();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		}
		String mode = request.getParameter("mode");
		String status = request.getParameter("status");
		String place = request.getParameter("place");
		String latitude = request.getParameter("latitude");
		String longitiude = request.getParameter("longitiude");
		String addr = request.getParameter("addr");
		String userid = request.getParameter("usrid");
		String channelId = request.getParameter("channelid");
		String id = request.getParameter("id");

		Usr usr = new Usr();

		usr.setMode(Integer.parseInt(mode));
		usr.setStatus(status);
		usr.setPlace(place);
		usr.setLatitude(Double.parseDouble(latitude));
		usr.setLongitiude(Double.parseDouble(longitiude));
		usr.setAddr(addr);
		usr.setUserId(userid);
		usr.setChannelId(channelId);
		usr.setId(Integer.parseInt(id));

		IUsrDao usrOp = new UsrDaoImpl();

		boolean success = usrOp.update(usr);
		if (success) {
			try {
				os.write("true".getBytes());
				os.flush();
				os.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		} else {
			try {
				os.write("false".getBytes());
				os.flush();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
