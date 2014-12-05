package TOOLS;

import javax.servlet.http.HttpServletRequest;

/**
 * 返回IP地址
 * 
 * 
 * @author 赵鲜华
 * @since 2013-9-6 下午08:37:04
 */
public class IPHandle {
	/**
	 * 
	 * 返回IP地址
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
