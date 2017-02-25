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
		<script src="${pageContext.request.contextPath}/js/jquery.ripples-min.js"></script>
		
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/animate.css">
		<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css"/>
		<link rel="icon" href="${pageContext.request.contextPath}/images/titleicon.png" type="image/x-icon"/>
  </head>
  <body>
    <div class="container">
      <jsp:include  page="/WEB-INF/jsp/header.jsp"/>  
      <%
      		Student stu = (Student)request.getAttribute("studentinfo");
      %> 
      <div class="navigation">
      	<h2>导航栏</h2>
		  <div id="line1">
		  </div>
	      <div id="perInfo">
	      	<h3><a class="per">个人信息</a></h3>
	      </div>
	      <div id="couInfo">
	      	<h3><a class="cou" href="service.do?service=queryCourseBySid&sid=<%=stu.getSid()%>">选课信息</a></h3>
	      </div>
	      <div id="result">
	      	<h3><a class="res" href="service.do?service=queryScoreBySid&sid=<%=stu.getSid() %>">查询成绩</a></h3>
	      </div>
      </div>
      <div class="content">
      	<h3>您的个人信息：</h3>
        <div class="Info">	       	
     		<p>学号：<%=stu.getSid() %></p>
     		<p>姓名：<%=stu.getName() %></p>
     		<p>年龄：<%=stu.getAge() %></p>
     		<p>专业：<%=stu.getMajor() %></p>
     		<p>年级：<%=stu.getGrade() %></p>
     		<p>性别：<%=stu.getGender() %></p>
        </div>
      </div>
      <div class="error" style="display: none; position: fixed; background: #000; color: #f00; padding: 5px; max-width: 50%">
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
		
		// 产生文字效果
		
		$('.textillate').textillate(
		{
			autoStart: true,			
			loop: true,
		});
		
		// 使背景产生水波效果
		
		$(document).ready(function() {
		try {
			$('.navigation').ripples({
				resolution: 512,
				dropRadius: 30,
				perturbance: 0.04,
			
			});
			
			$('.content').ripples({
				resolution: 512,
				dropRadius: 30,
				perturbance: 0.04,
				interactive: true
			});
			
			$('.header').ripples({
				resolution: 512,
				dropRadius: 30,
				perturbance: 0.04,
				interactive: true
			});
			
		} catch (e) {
			$('.error').show().text(e);
		}
		});
		
	</script>
    </body>
</html>