package edu.ahjzu.app.notice.servlet.controler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.ahjzu.app.notice.pojo.Page;
import edu.ahjzu.app.notice.servlet.NewsServlet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class IndexHandle {
	private static String mDateStr = "";
	private static String mPage = "";

	public static void createIndexPage(HttpServletRequest req,
			HttpServletResponse resp) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = format.format(new Date());
		if (mPage.equals("")) {
			getPage();
		}
		if ("".equals(mDateStr)) {
			mDateStr = dateStr;
		} else {
			if (!mDateStr.equals(dateStr)) {
				getPage();
			}
		}
		try {
			resp.getOutputStream().write(mPage.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void getPage() {
		List<Page> list = new ArrayList<Page>();

		String text = null;
		String title = null;
		String href = null;
		String url = "http://www.ahjzu.edu.cn";
		long start = System.currentTimeMillis();
		Document doc = null;
		try {
			doc = Jsoup.connect(url).timeout(10000).get();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Time is:"
					+ (System.currentTimeMillis() - start) + "ms");
		}
		Elements links = doc.select("a[href]");
		for (int i = 0; i < links.size(); i++) {
			text = (String) links.get(i).text();
			title = links.get(i).attr("title");
			href = links.get(i).attr("href");
			if (!title.isEmpty()) {
				if (href.indexOf("http") == (-1)) {
					// System.out.println(title);
					// System.out.println(url + href);
					Page page = new Page(title, url + href);
					list.add(page);
				}
			}
		}
		if (list != null && list.size() > 0) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("<!DOCTYPEHTML><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /> <meta name=\"viewport\" content=\"width=device-width; initial-scale=1.0; maximum-scale=1.0;\">  <title>index</title><link href=\"css/style.css\" rel=\"stylesheet\" type=\"text/css\"  /></head>  <body><div class=\"content\">");
			for (Page page : list) {
				buffer.append("<div class=\"blog\"><div class=\"blog-content\"><p><a href=\""
						+ page.href
						+ "\">"
						+ page.text
						+ "</a></p></div></div>");
			}
			buffer.append("</div></body></html>");
			System.out.println(buffer.toString());
			// resp.getWriter().write(buffer.toString());
			mPage = buffer.toString();
		}

	}
}
