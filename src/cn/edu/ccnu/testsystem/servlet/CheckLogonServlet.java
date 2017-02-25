package cn.edu.ccnu.testsystem.servlet;

/**
 * 验证用户登陆信息的Servlet, 此程序调用了CookieUtil.java的saveCookie方法
 */

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import cn.edu.ccnu.testsystem.bean.User;
import cn.edu.ccnu.testsystem.dao.*;
import cn.edu.ccnu.testsystem.util.CookieUtil;

public class CheckLogonServlet extends HttpServlet {
	public void doGet (HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String username =request.getParameter("uesrname").trim();
		String password = CookieUtil.getMD5(request.getParameter("password"));
		String remeberMe = request.getParameter("remeberMe");
		HttpSession session = request.getSession(false);
		
		// 讲接收到的用户名传递到UserDao的checkUser方法中，检查用户
		// 返回一个User类型的对象
		StudentDao ud = DaoImpIFactory.getInstance();
		User user = ud.selectUserByUsername(username);
		if (user == null) {
			request.setAttribute("checkUserError", "<a href='register.jsp'> <font color=red> 用户名不存在，请先注册 </font></a>");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		
		if (!password.equals(user.getPassword())) {
			request.setAttribute("checkPasswordError", "<font color=red>密码输入错误，请重新输入</font>");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		
		// 保存Cookie, 这里调用了CookieUtil.java中的saveCookie方法， 将上面的user对象作为参数传递
		if ("on".equals(remeberMe)) {
			CookieUtil.saveCookie(user, response);
		}
		
		// 在Session中保存用户信息，并转向用户的个人信息页面
		session.setAttribute("user", user);
		request.getRequestDispatcher("User/userInfo.jsp").forward(request, response);
	}
}