 package cn.edu.ccnu.testsystem.filter;

 /**
 *
 * 
 * */
 
 import java.io.IOException;
 import javax.servlet.Filter;
 import javax.servlet.FilterChain;
 import javax.servlet.FilterConfig;
 import javax.servlet.ServletException;
 import javax.servlet.ServletRequest;
 import javax.servlet.ServletResponse;
 import javax.servlet.http.Cookie;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import javax.servlet.http.HttpSession;
 import cn.edu.ccnu.testsystem.bean.User;
 import cn.edu.ccnu.testsystem.util.CookieUtil;
 
 public class AutoLogonFilter implements Filter {
	 public void destroy() {
		 
	 }
	 
	 // ����cookieʱ��cookieName, ��CookieUtil.java��������ͬ
	 
	 private final static String cookieDomainName = "cn.edu.ccnu.testsystem";
	 
	 public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) 
	 	throws IOException, ServletException {
		 HttpServletRequest request = (HttpServletRequest)req;
		 HttpServletResponse response = (HttpServletResponse)resp;
		 HttpSession session = request.getSession(true);
		 User user = (User)session.getAttribute("user");
		 
		 // �����װ��user��Ϊ�գ�˵���Ѿ���½�������ִ���û�������.����ľͲ�������
		 if(user != null) {
			 chain.doFilter(request, response);
			 return;
		 }
		 
		 // userΪ�գ�˵���û���û��½���ͳ��Եõ���������͹�����Cookie
		 Cookie cookies[] = request.getCookies();
		 String cookieValue = null;
		 if (cookies != null) {
			 for (int i = 0; i < cookies.length; i++) {
				 if (cookieDomainName.equals(cookies[i].getName())) {
					 cookieValue = cookies[i].getValue();
					 break;
				 }
			 }
		 }
		 
		 // ���cookieValueΪ�գ�Ҳ����ִ���û�����
		 if (cookieValue == null) {
			 chain.doFilter(request, response);
			 return;
		 }
		 
		 //cookieValue��Ϊ��ִ������ķ���������CookieUtil.java�е�readCookieAndLogon����
		 
		 try {
			 CookieUtil.readCookieAndLogon(cookieValue, request, response,chain);
		 }	catch (Exception e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public void init(FilterConfig arg0) throws ServletException {
		 
	 }
 }