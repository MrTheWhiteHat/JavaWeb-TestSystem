package cn.edu.ccnu.testsystem;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.ccnu.testsystem.dao.CourseDao;
import cn.edu.ccnu.testsystem.dao.ScoreDao;
import cn.edu.ccnu.testsystem.dao.StudentDao;
import cn.edu.ccnu.testsystem.dao.TeacherDao;
import cn.edu.ccnu.testsystem.entity.Course;
import cn.edu.ccnu.testsystem.entity.Student;
import cn.edu.ccnu.testsystem.entity.Teacher;

public class TestServlet extends HttpServlet {

	
	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String service = request.getParameter("service");
		
		if (service.equals("login")) {
			String inputId = request.getParameter("id");
			String password = request.getParameter("pwd");
			String role = request.getParameter("role");
			if (role.equals("1")) {
				int sid = Integer.parseInt(inputId);
				StudentDao stuDao = new StudentDao();
				Student stu = stuDao.searchStudentBySid(sid);
				
				String message;
				
				if (stu == null) {
					message = "用户不存在！";
					request.setAttribute("message", message);
					request.getRequestDispatcher("login.jsp").forward(request, response);
				} else if (password != null && !password.equals(stu.getPassword())) {
					message = "密码错误，请重新输入密码！";
					request.setAttribute("password", password);
					request.setAttribute("message", message);
					request.getRequestDispatcher("login.jsp").forward(request, response);
				} else {
					message = stu.getName();
					request.setAttribute("studentinfo", stu); //设置学生个人信息属性，方便登录成功后取出表现给用户
					request.setAttribute("message", message);
					request.getRequestDispatcher("main.jsp").forward(request, response);
				}
			} else if (role.equals("2")) {
				int tid = Integer.parseInt(inputId);
				
				TeacherDao teacherDao = new TeacherDao();
				Teacher teacher = teacherDao.searchTeacherByTid(tid);
				
				String message;
				if (teacher == null) {
					message = "用户不存在！";
					request.setAttribute("message", message);
					request.getRequestDispatcher("login1.jsp").forward(request, response);
				} else if (password != null && !password.equals(teacher.getPassword())) {
					message = "密码错误，请重新登陆！";
					request.setAttribute("message", message);
					request.getRequestDispatcher("login1.jsp").forward(request, response);
				} else {
					
					message = teacher.getName();
					request.setAttribute("teaInfo", teacher); 
					request.setAttribute("message", message);
					CourseDao courseDao = new CourseDao();
					List courseList = courseDao.searchCoursesByTid(tid);
					request.setAttribute("courses", courseList);
					request.getRequestDispatcher("main1.jsp").forward(request, response);
				}
			}
		}
		
		else if(service.equals("inputScore")) {
			String message;
			String inputCid = request.getParameter("cid");
			int cid = Integer.parseInt(inputCid);
			
			CourseDao courseDao = new CourseDao();
			Course course = courseDao.searchCourseByCid(cid);
			ScoreDao scoreDao = new ScoreDao();
			List scoreList = scoreDao.searchInputScoresByCid(cid);
			// 忘记写下面两个得到的结果就是NullPointerException.....
			TeacherDao teacherDao = new TeacherDao();
			Teacher teacher = teacherDao.searchTeacherByCid(cid);
			
			message = teacher.getName();
			request.setAttribute("message", message);			
			request.setAttribute("teaInfo", teacher);
			request.setAttribute("inputscores", scoreList);
			request.setAttribute("course", course);
			request.getRequestDispatcher("response1.jsp").forward(request, response);
		}
	    
		else if(service.equals("saveScore")) {
			Enumeration pNames = request.getParameterNames();
			@SuppressWarnings("rawtypes")
			Map hm = new HashMap();
			while (pNames.hasMoreElements()) {
				String name = (String) pNames.nextElement();
				float score = 0;
				if (name.startsWith("2")) {
					String scoreStr = request.getParameter(name);
					int sid = Integer.parseInt(name);
					if (scoreStr != null)
						score = Float.parseFloat(scoreStr);
					hm.put(sid, score);
				}			
			}
			String cidStr = request.getParameter("cid");
			int cid = Integer.parseInt(cidStr);
			ScoreDao scoreDao = new ScoreDao();
			scoreDao.saveScores(hm, cid);
			
			CourseDao cDao = new CourseDao();
			Course cou = cDao.searchCourseByCid(cid);
			
			//String inputTid = request.getParameter("tid");
			//int tid = Integer.parseInt(inputTid);
			TeacherDao teacherDao = new TeacherDao();
			Teacher teacher = teacherDao.searchTeacherByCid(cid);
			
			String message;
			message = teacher.getName();
			request.setAttribute("teaInfo", teacher); 
			request.setAttribute("message", message);
			
			List scoreList = scoreDao.searchScoresByCid(cid);
			request.setAttribute("scores", scoreList);
			request.setAttribute("course", cou);
			request.getRequestDispatcher("scoresList.jsp").forward(request, response);
		}
		
		else if(service.equals("queryCourseBySid")) {
			String inputSid = request.getParameter("sid");
			int sid = Integer.parseInt(inputSid);
			StudentDao stuDao = new StudentDao();
			Student stu = stuDao.searchStudentBySid(sid);
			ScoreDao scoDao = new ScoreDao();
			List coursesList = scoDao.queryCourseBySid(sid);
			
			String message;
			message = stu.getName();
			request.setAttribute("studentinfo", stu);
			request.setAttribute("coursesinfo", coursesList); 
			request.setAttribute("message", message);
			request.getRequestDispatcher("stdcos.jsp").forward(request, response);
					
		}
		
		else if(service.equals("queryScoreBySid")) {
			String inputSid = request.getParameter("sid");
			int sid = Integer.parseInt(inputSid);
			StudentDao stuDao = new StudentDao();
			Student stu = stuDao.searchStudentBySid(sid);
			ScoreDao scoDao = new ScoreDao();
			List scoList = scoDao.queryScoreBySid(sid);
			
			String message;
			message = stu.getName();
			request.setAttribute("studentinfo", stu);
			request.setAttribute("scoList", scoList);
			request.setAttribute("message", message);
			request.getRequestDispatcher("scores.jsp").forward(request, response);
			
		}
		
		else if(service.equals("queryStuInfo")) {
			String inputSid = request.getParameter("sid");
			int sid = Integer.parseInt(inputSid);
			StudentDao stuDao = new StudentDao();
			Student stu = stuDao.searchStudentBySid(sid);
			ScoreDao scoDao = new ScoreDao();
			List scoList = scoDao.queryScoreBySid(sid);
			ScoreDao scoDao1 = new ScoreDao();
			List coursesList = scoDao1.queryCourseBySid(sid);
			
			String message;
			message = stu.getName();
			request.setAttribute("studentinfo", stu);
			request.setAttribute("scoList", scoList);
			request.setAttribute("message", message);
			request.setAttribute("coursesinfo", coursesList);
			request.getRequestDispatcher("main.jsp").forward(request, response);
			
		}
		
		else if(service.equals("queryTeachCourses")) {
			String inputTid = request.getParameter("tid");
			int tid = Integer.parseInt(inputTid);
			TeacherDao teacherDao = new TeacherDao();
			Teacher teacher = teacherDao.searchTeacherByTid(tid);
			
			String message;
			message = teacher.getName();
			request.setAttribute("teaInfo", teacher); 
			request.setAttribute("message", message);
			CourseDao courseDao = new CourseDao();
			List courseList = courseDao.searchCoursesByTid(tid);
			request.setAttribute("courses", courseList);
			request.getRequestDispatcher("response.jsp").forward(request, response);
			
		}
		
		else if(service.equals("queryTeaInfo")) {
			String inputId = request.getParameter("tid");
			int tid = Integer.parseInt(inputId);
			
			TeacherDao teacherDao = new TeacherDao();
			Teacher teacher = teacherDao.searchTeacherByTid(tid);
			
			String message;				
			message = teacher.getName();
			request.setAttribute("teaInfo", teacher); 
			request.setAttribute("message", message);		
			request.getRequestDispatcher("main1.jsp").forward(request, response);
		}
	}
}
