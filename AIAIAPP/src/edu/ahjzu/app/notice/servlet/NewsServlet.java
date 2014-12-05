package edu.ahjzu.app.notice.servlet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import sun.awt.SunToolkit.InfiniteLoop;

import edu.ahjzu.app.notice.dao.impl.NewsDaoImpl;
import edu.ahjzu.app.notice.pojo.News;

public class NewsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	private void doAll(HttpServletRequest request,
			final HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String oper = request.getParameter("Oper");
		System.out.println("Oper:" + oper);
		if (oper != null && !"".equals(oper)) {
			if (oper.equals("getnews")) {// get新闻
				// String idStr = request.getParameter("id");
				// if (idStr != null) {
				// int id = Integer.parseInt(idStr);
				// if (id > 0) {
				// System.out.println("id:" + id);
				ArrayList<News> list = new NewsDaoImpl().getNews(0);
				String result = News.toJson(list);
				System.out.println(result);
				PrintWriter writer = null;
				try {
					writer = response.getWriter();
					writer.write(result);
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (writer != null) {
						writer.close();
					}
					// }
					// }
				}
			} else if (oper.equals("getnewsimage")) {// 得到图片
				int id = Integer.parseInt(request.getParameter("id"));
				System.out.println("id:" + id);
				NewsDaoImpl newsDB = new NewsDaoImpl();
				byte[] result = newsDB.getImage(id);
				try {
					response.getOutputStream().write(result, 0, result.length);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if ("addnews".equals(oper)) {
				addNews(request, response);
			} else if ("deletenews".equals(oper)) {
				int id = Integer.parseInt(request.getParameter("id"));
				NewsDaoImpl newsDB = new NewsDaoImpl();
				newsDB.deleteById(id);
			}
		}
	}

	private void addNews(HttpServletRequest request,
			final HttpServletResponse response) {
		// 添加新闻
		News newsData = new News();
		InputStream stream = null;
		// 获取上传的图片
		ServletFileUpload upload = new ServletFileUpload();
		FileItemIterator iter = null;
		int fileLen = 0;
		int tempFileLen = 0;
		try {
			iter = upload.getItemIterator(request);
			while (iter.hasNext()) {
				FileItemStream item = iter.next();
				String name = item.getFieldName();
				stream = item.openStream();
				System.out.println("" + name);
				if (item.isFormField()) {// 如果是文本
					if (name != null && !"".equals(name)) {
						String value = Streams.asString(stream, "utf-8");
						System.out.println("	>>文本  " + value);
						if (name.equals("title"))
							newsData.setTitle(value);
						else if (name.equals("content"))
							newsData.setContent(value);
						else if (name.equals("url"))
							newsData.setUrl(value);
						else if (name.equals("imgname"))
							newsData.setImgName(value);
						else if (name.equals("filelen")) {
							tempFileLen = Integer.valueOf(value);
							System.out.println("file len:" + value);
						}
					}
				} else {
					System.out.println("	>>文件");
					byte[] buff = new byte[512];
					ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
					int len = 0;
					while ((len = stream.read(buff)) != -1) {
						fileLen += len;
						arrayOutputStream.write(buff, 0, len);
					}
					byte[] fileData = arrayOutputStream.toByteArray();
					System.out.println("length:" + fileData.length);
					newsData.setImage(fileData);
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (fileLen != 0) {
			System.err.println("len:" + fileLen + "/" + tempFileLen);
		}
		NewsDaoImpl newsDB = new NewsDaoImpl();
		ByteArrayInputStream arrayInputStream = null;
		if (newsData.getImage() != null) {
			arrayInputStream = new ByteArrayInputStream(newsData.getImage());
			System.out.println("arrayInputStream.len:"
					+ arrayInputStream.available());
		} else {
			System.out.println("newsData.getImage() == null");
		}
		boolean result = newsDB.insertData(newsData.getTitle(),
				newsData.getContent(), newsData.getUrl(), arrayInputStream,
				newsData.getImgName());
		try {
			PrintWriter writer = response.getWriter();
			writer.write(String.valueOf(result));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
