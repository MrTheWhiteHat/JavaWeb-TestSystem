<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" errorPage="error.jsp" %>
<%@ page import="cn.edu.ccnu.testsystem.entity.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD//XHTML 1.0 Transition//EN"
"http://www.w3.org/TR/xhtml/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>欢迎来到考试管理系统</title>
		<meta http-equiv="Content-Type" content="text/html charset=gb2312"/>
		<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.textillate.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.lettering.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.fittext.js"></script>
	
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/animate.css">
		<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css"/>
		<link rel="icon" href="${pageContext.request.contextPath}/images/titleicon.png" type="image/x-icon"/>
  </head>
  <body>
  	 <%
      		Student stu = (Student)request.getAttribute("studentinfo");
     %>
    <div class="container">
      <jsp:include  page="/WEB-INF/jsp/header.jsp"/>   
      <div class="navigation">
      	<h2>导航栏</h2>
		  <div id="line1">
		  </div>
	      <div id="perInfo">
	      	<h3><a class="per" href="service.do?service=queryStuInfo&sid=<%=stu.getSid() %>">个人信息</a></h3>
	      </div>
	      <div id="couInfo">
	      	<h3><a class="cou">选课信息</a></h3>
	      </div>
	      <div id="result">
	      	<h3><a class="res" href="service.do?service=queryScoreBySid&sid=<%=stu.getSid() %>">查询成绩</a></h3>
	      </div>
      </div>
      <div class="content">
      	<h3>您的选课信息：</h3>
      	<div class="Info">
	      	<table class="tb" border="1">
		      	<tr>
		      	 	<td>课程编号</td>
		      	 	<td>任课教师</td>
		      	 	<td>教 工 号</td>
		      	 	<td>职     称</td>
		      	 	<td>课程名</td>
		      	 	<td>课程类型</td>
		      	 	<td>所属专业</td>
		      	 </tr>
		      	<%
		      		List coursesList = (List)request.getAttribute("coursesinfo");
		      		Iterator iter = coursesList.iterator();
		      		Course course;
		      		Teacher tea;
		      		
		      		while(iter.hasNext()) {
		      			course = (Course)iter.next();
		      			tea = (Teacher)iter.next();
		      	 %>
		      	 <tr>
		      	 	<td><%=course.getCid() %></td>
		      	 	<td><%=tea.getName() %></td>
		      	 	<td><%=course.getTid() %></td>
		      	 	<td><%=tea.getTitle() %></td>
		      	 	<td><%=course.getName() %></td>
		      	 	<td><%=course.getType() %></td>
		      	 	<td><%=course.getMajor() %></td>
		      	 <tr>
	      	 <%} %>
	  		</table>
      </div>
      
	  <jsp:include  page="/WEB-INF/jsp/footer.jsp"/>
  	</div>
  	<%
        String name = request.getParameter("name");
        String password = request.getParameter("pwd");
        //session.setAttribute("name",name);
        pageContext.setAttribute("name",name,PageContext.SESSION_SCOPE);
        session.setAttribute("password",password);
        application.setAttribute("address","华中师范大学信息管理系");
        
    %>
	<script>
		
		// 文字产生动画效果
		
		$('.textillate').textillate(
		{
			autoStart: true,			
			loop: true,
		});

	</script>
    </body>
</html>