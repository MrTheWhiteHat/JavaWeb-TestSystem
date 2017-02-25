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
	 
	 // 保存cookie时的cookieName, 与CookieUtil.java中设置相同
	 
	 private final static String cookieDomainName = "cn.edu.ccnu.testsystem";
	 
	 public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) 
	 	throws IOException, ServletException {
		 HttpServletRequest request = (HttpServletRequest)req;
		 HttpServletResponse response = (HttpServletResponse)resp;
		 HttpSession session = request.getSession(true);
		 User user = (User)session.getAttribute("user");
		 
		 // 如果封装的user不为空，说明已经登陆，则继续执行用户的请求.下面的就不处理了
		 if(user != null) {
			 chain.doFilter(request, response);
			 return;
		 }
		 
		 // user为空，说明用户还没登陆，就尝试得到浏览器传送过来的Cookie
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
		 
		 // 如果cookieValue为空，也继续执行用户请求
		 if (cookieValue == null) {
			 chain.doFilter(request, response);
			 return;
		 }
		 
		 //cookieValue不为空执行下面的方法，调用CookieUtil.java中的readCookieAndLogon方法
		 
		 try {
			 CookieUtil.readCookieAndLogon(cookieValue, request, response,chain);
		 }	catch (Exception e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public void init(FilterConfig arg0) throws ServletException {
		 
	 }
 }