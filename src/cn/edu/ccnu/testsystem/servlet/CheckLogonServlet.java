package cn.edu.ccnu.testsystem.servlet;

/**
 * ��֤�û���½��Ϣ��Servlet, �˳��������CookieUtil.java��saveCookie����
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
		
		// �����յ����û������ݵ�UserDao��checkUser�����У�����û�
		// ����һ��User���͵Ķ���
		StudentDao ud = DaoImpIFactory.getInstance();
		User user = ud.selectUserByUsername(username);
		if (user == null) {
			request.setAttribute("checkUserError", "<a href='register.jsp'> <font color=red> �û��������ڣ�����ע�� </font></a>");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		
		if (!password.equals(user.getPassword())) {
			request.setAttribute("checkPasswordError", "<font color=red>���������������������</font>");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		
		// ����Cookie, ���������CookieUtil.java�е�saveCookie������ �������user������Ϊ��������
		if ("on".equals(remeberMe)) {
			CookieUtil.saveCookie(user, response);
		}
		
		// ��Session�б����û���Ϣ����ת���û��ĸ�����Ϣҳ��
		session.setAttribute("user", user);
		request.getRequestDispatcher("User/userInfo.jsp").forward(request, response);
	}
}