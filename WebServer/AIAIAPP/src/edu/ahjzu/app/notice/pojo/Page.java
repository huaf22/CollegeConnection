package edu.ahjzu.app.notice.pojo;

/**
 * ��ҳ����
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
	 *            ����
	 * @param href
	 *            ����
	 */
	public Page(String text, String href) {
		this.href = href;
		this.text = text;
	}
}