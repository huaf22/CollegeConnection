package edu.ahjzu.app.notice.pojo;

/**
 * 网页数据
 * 
 * @author Administrator
 * 
 */
public class Page {
	public String href;
	public String text;

	/**
	 * 
	 * @param text
	 *            正文
	 * @param href
	 *            链接
	 */
	public Page(String text, String href) {
		this.href = href;
		this.text = text;
	}
}