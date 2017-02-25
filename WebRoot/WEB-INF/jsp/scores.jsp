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
	      	<h3><a class="cou" href="service.do?service=queryCourseBySid&sid=<%=stu.getSid()%>">选课信息</a></h3>
	      </div>
	      <div id="result">
	      	<h3><a class="res">查询成绩</a></h3>
	      </div>
      </div>
      <div class="content">
      	<h3>您的课程成绩：</h3>
      	<div class="Info">
	      	<table class="tb" border="1">
		      	<tr>
		      	 	<td>课程编号</td>
		      	 	<td>课程名</td>
		      	 	<td>课程类型</td>
		      	 	<td>课程得分</td>
		      	 </tr>
		      	<%
		      		List scoList = (List)request.getAttribute("scoList");
		      		Iterator iter = scoList.iterator();
		      		Score sco;
		      		Course course;
		      		
		      		while(iter.hasNext()) {
		      			sco = (Score)iter.next();
		      			course = (Course)iter.next();
		      	 %>
	
		      	 <tr>
		      	 	<td><%=sco.getCid() %></td>      	 	
		      	 	<td><%=course.getName() %></td>
		      	 	<td><%=course.getType() %></td>
		      	 	<td><%=sco.getScore() %>
		      	 <tr>
	      	
	      	 <%}%>
	  		
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
		$('.textillate').textillate(
		{
			autoStart: true,			
			loop: true,
		});
	</script>
    </body>
</html>