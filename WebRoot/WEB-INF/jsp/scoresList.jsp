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
    <div class="container">
      <jsp:include  page="/WEB-INF/jsp/header1.jsp"/>  
      <%
      		Teacher teaInfo = (Teacher)request.getAttribute("teaInfo");
      %> 
      <div class="navigation">
      	<h2>导航栏</h2>
		  <div id="line1">
		  </div>
	      <div id="perInfo">
	      	<h3><a class="per" href="service.do?service=queryTeaInfo&tid=<%=teaInfo.getTid() %>">个人信息</a></h3>
	      </div>	      
	      <div id="couInfo">
	      	<h3><a class="cou" href="service.do?service=queryTeachCourses&tid=<%=teaInfo.getTid()%>">授课信息</a></h3>
	      </div>
	     
      </div>
      <div class="content">
        <h3> 课程成绩列表</h3>
        <%
        	Course course = (Course)request.getAttribute("course");
         %>
        <div class="Info">
	      <table class="tb" border="1">	    	
		    <tr>
		    <td>课程编号：</td>
		    <td><%=course.getCid() %></td>
		    <td>| 课程名：</td>
		    <td><%=course.getName() %></td>
		    <td>| 授课教师：</td>
		    <td><%=course.getTname() %></td>
		    <td>| 开课院系：</td>
		    <td><%=course.getMajor() %></td>
		    </tr>
		  </table> 
    	  <br>
	      <table class="tb" border="1">
		       <tr>
		         <td>学号</td>
		         <td>姓名</td>
		         <td>成绩</td>
		      </tr>
			    <%
			       List scoreList = (List)request.getAttribute("scores");
			       Iterator iter = scoreList.iterator();
				   Score score;
					
				  while(iter.hasNext()){ 
					score = (Score)iter.next();
			        
			     %>
	    
		      <tr>
		         <td><%=score.getSid()%></td>
		         <td><%=score.getSname() %></td>
		         <td><%=score.getScore() %></td>
		         
		      </tr>
	     	  <%} %>  
	      </table>
      	</div>
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