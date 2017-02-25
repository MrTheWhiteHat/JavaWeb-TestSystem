package cn.edu.ccnu.testsystem.util;

/**
 * ������
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import cn.edu.ccnu.testsystem.bean.User;
import cn.edu.ccnu.testsystem;
import cn.edu.ccnu.testsystem.dao;
import cn.edu.ccnu.testsystem.entity;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class CookieUtil {
	// ����cookieʱ��cookieName
	private final static String cookieDomainName = "cn.edu.ccnu.testsystem";
	// ����cookieʱ����վ�Զ���
	private final static String webKey = "testsystem";
	// ����cookie��Ч������������
	private final static long cookieMaxAge = 60 * 60 * 24 * 7 * 2;
	
	// ����Cookie���ͻ���---------------------------------------------------------------------------------------------------
	// ��TestServlet.java�б�����
	// ���ݽ�����user�����з�װ���ڵ�½ʱ��д���û���������
	public static void saveCookie (User user, HttpServletResponse response) {
		// cookie����Ч��
		long validTime = System.currentTimeMillis() + (cookieMaxAge * 1000);
		// MD5�����û���ϸ��Ϣ
		String cookieValueWithMd5 = getMD5(user.getUserName() + ":" + user.getPassword() + ":" + validTime + ":" + webKey);
		// ��Ҫ�������������Cookieֵ
		String cookieValue = user.getUserName() + ":" + validTime + ":" + cookieValueWithMd5;
		// ��һ�ζ�Cookie��ֵ����Base64����
		String cookieValueBase64 = new String(Base64.encode(cookieValue.getBytes()));
		// ��ʼ����Cookie
		Cookie cookie = new Cookie(cookieDomainName, cookieValueBase64);
		
		// �����꣨���ֵӦ�ô��ڻ����validTime��
		cookie.setMaxAge(60 * 60 * 24 * 365 * 2);
		// cookie��Ч·������վ�ĸ�Ŀ¼
		cookie.setPath("/");
		// ��ͻ���д��
		response.addCookie(cookie);
	}
	
	// ��ȡCookie���Զ���ɵ�½����-------------------------------------------------------------------------------------------
	// ��Filter�����е��ø÷�������AutoLogonFilter.java
	public static void readCookieAndLogon (String cookieValue,HttpServletRequest request, HttpServletResponse response, FilterChain chain) 
			throws IOException, ServletException,UnsupportedEncodingException {
		// ����cookieNameȡcookieValue
		Cookie cookies[] = request.getCookies();
		cookieValue = null;
		
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookieDomainName.equals(cookies[i].getName())) {
					cookieValue = cookies[i].getValue();
					break;
				}
			}
		}
		
		// ���cookieValueΪ�գ�����
		if (cookieValue == null ) {
			return;
		}
		
		// ���cookieValue��Ϊ�գ���ִ������Ĵ���
		// �ȶԵõ���CookieValue����Base64����
		String cookieValueAfterDecode = new String(Base64.decode(cookieValue), "utf-8");
		
		// �Խ�����ֵ���зֲ𣬵õ�һ�����飬������鳤�Ȳ�Ϊ3�����ǷǷ���½
		String cookieValues[] = cookieValueAfterDecode.split(":");
		if (cookieValues.length != 3) {
			response.setContentType ("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("�������÷�������¼��ʽ���뱾վ...");
			out.close();
			return;
		}
		
		// �ж��Ƿ�����Ч���ڣ����ھ�ɾ��Cookie
		long validTimeInCookie = new Long(cookieValues[1]);
		if (validTimeInCookie < System.currentTimeMillis()) {
			// ɾ��Cookie
			clearCookie(response);
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<a href='login.jsp'>����¼�쳣�������µ�½......</a>");
			out.close();
			return;
			
		}
		
		// ȡ��cookie�е��û������������ݿ��м������û���
		String username = cookieValues[0];
		// �����û��������ݿ��м���û��Ƿ����
		UserDao ud = DaoImapIFactory.getInstance();
		
		User user = ud.selectUserByUsername(username);
		
		// ���user���ز�Ϊ�գ���ȡ�����룬ʹ���û��� + ���� + ��Чʱ�� + webSiteKey����MD5����
		if (user != null) {
			String md5ValueInCookie = cookieValues[2];
			String md5ValueFromUser = getMD5(user.getUserName() + ":" + user.getPassword() + ":" + validTimeInCookie + ":" + webKey);
			// �������Cookie�е�MD5����Ƚϣ������ͬ��д��Session,�Զ���½�ɹ����������û�����
			if (md5ValueFromUser.equals(md5ValueInCookie)) {
				HttpSession session = request.getSession(true);
				session.setAttribute("user", user);
				chain.doFilter(request, response);
			}
		} else {
			// ����Ϊ��ִ��
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("cookie��֤����! �Զ���½ʧ��! ");
			out.close();
			return;
		}
	}
	
	// �û�ע��ʱ�����Cookie, ����Ҫʱ����ʱ����-----------------------------------------------------------------------------------------
	public static void clearCookie(HttpServletResponse response) {
		Cookie cookie = new Cookie(cookieDomainName, null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	// ��ȡCookie����ַ�����MD5����ַ���------------------------------------------------------------------------------------------------
	public static String getMD5(String value) {
		String result = null;
		try {
			byte[] valueByte = value.getBytes();
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(valueByte);
			result = toHex(md.digest());
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// �����ݽ������ֽ�����ת����ʮ�����Ƶ��ַ�����ʽ������
	private static String toHex(byte[] buffer) {
		StringBuffer sb = new StringBuffer(buffer.length * 2);
		for (int i = 0; i < buffer.length; i++) {
			sb.append(Character.forDigit(buffer[i] & 0xf0 >> 4, 16));
			sb.append(Character.forDigit(buffer[i] & 0x0f, 16));
		}
		return sb.toString();
	}	
}