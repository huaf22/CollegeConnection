package TOOLS;

import javax.servlet.http.HttpServletRequest;

/**
 * ����IP��ַ
 * 
 * 
 * @author ���ʻ�
 * @since 2013-9-6 ����08:37:04
 */
public class IPHandle {
	/**
	 * 
	 * ����IP��ַ
	 * 
	 * @param request
	 * @return
	 */
	public static String getRemortIP(HttpServletRequest request) {

		if (request.getHeader("x-forwarded-for") == null) {

			return request.getRemoteAddr();

		}

		return request.getHeader("x-forwarded-for");

	}
}
