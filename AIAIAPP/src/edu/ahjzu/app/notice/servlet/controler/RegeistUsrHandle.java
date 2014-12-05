package edu.ahjzu.app.notice.servlet.controler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import edu.ahjzu.app.notice.dao.IUsrDao;
import edu.ahjzu.app.notice.dao.impl.UsrDaoImpl;
import edu.ahjzu.app.notice.pojo.Usr;

public class RegeistUsrHandle {
	private Usr usr = new Usr();

	public void handle(HttpServletRequest request, HttpServletResponse response) {
		ServletFileUpload upload = new ServletFileUpload();
		FileItemIterator iter;
		try {
			iter = upload.getItemIterator(request);
			while (iter.hasNext()) {
				FileItemStream item = iter.next();
				String name = item.getFieldName();
				InputStream stream = item.openStream();
				System.out.println(name + " >>");
				if (item.isFormField()) {
					// 如果是文本
					System.out.println("  >>TEXT");
					txtHandle(name, Streams.asString(stream, "utf-8"));
				} else {
					System.out.println("  >>FILE");
					fileHandle(stream);
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int id = new UsrDaoImpl().insert(usr);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			if (id > 0) {
				writer.write("{SUCCESS}");
			} else {
				writer.write("{FAILURE}");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}
		System.out.println("----->over: " + id);
	}

	private void fileHandle(InputStream stream) {
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
		byte[] buff = new byte[512];
		try {
			int len = 0;
			while ((len = stream.read(buff)) != -1) {
				arrayOutputStream.write(buff, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		usr.setImage(arrayOutputStream.toByteArray());
	}

	private void txtHandle(String key, String value) {
		System.out.println(key + "--->" + value);
		if (key.equals("name")) {
			usr.setName(value);
		} else if (key.equals("pwd")) {
			usr.setPwd(value);
		} else if (key.equals("truename")) {
			usr.setTruename(value);
		} else if (key.equals("sex")) {
			usr.setSex(value);
		} else if (key.equals("age")) {
			if (value.equals("")) {
				usr.setAge(0);
			} else {
				usr.setAge(Integer.parseInt(value));
			}
		} else if (key.equals("mode")) {
			usr.setMode(1);
		} else if (key.equals("status")) {
			usr.setStatus(value);
		} else if (key.equals("place")) {
			usr.setStatus(value);
		} else if (key.equals("latitude")) {
			usr.setLatitude(Double.parseDouble(value));
		} else if (key.equals("longitiude")) {
			usr.setLongitiude(Double.parseDouble(value));
		} else if (key.equals("addr")) {
			usr.setAddr(value);
		} else if (key.equals("userid")) {
			usr.setUserId(value);
		} else if (key.equals("channelid")) {
			usr.setChannelId(value);
		} else if (key.equals("college")) {
			usr.setCollege(value);
		} else if (key.equals("specialty")) {
			usr.setSpecialty(value);
		} else if (key.equals("qq")) {
			if ("".equals(value)) {
				usr.setQq(0);
			} else {
				usr.setQq(Integer.valueOf(value));
			}
		} else if ("imgname".equals(key)) {
			usr.setImgname(value);
		}
	}
}
