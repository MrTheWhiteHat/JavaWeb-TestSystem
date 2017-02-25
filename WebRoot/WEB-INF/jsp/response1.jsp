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
		<script src="${pageContext.request.contextPath}/js/jquery.jrumble.1.3.min.js"></script>
	
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
	      	<h3><a class="per">个人信息</a></h3>
	      </div>	      
	      <div id="couInfo">
	      	<h3><a class="cou" href="service.do?service=queryTeachCourses&tid=<%=teaInfo.getTid()%>">授课信息</a></h3>
	      </div>
	     
      </div>
      <div class="content">
        <h3> 成绩录入：</h3>
        <%
       		 Course course = (Course)request.getAttribute("course");
    	 %>
        <div class="Info">
		  
		  <table class="tb" border="1">
		    <tr>
		    <td>课程编号：</td>
		    <td><%=course.getCid() %></td>
		    <td> 课程名：</td>
		    <td><%=course.getName() %></td>
		    <td> 授课教师：</td>
		    <td><%=course.getTname() %></td>
		    <td> 开课院系：</td>
		    <td><%=course.getMajor() %></td>
		    </tr>
		  </table>
		  <br>
		  
		  <form action="service.do?service=saveScore" method="post" name="inputForm">
		      <table class="tb" id="inputtb" border="1">
		       <tr>
		         <td>学号</td>
		         <td>姓名</td>
		         <td>成绩</td>
		      </tr>
		    <%
		        List scoreList = (List)request.getAttribute("inputscores");
		        Iterator iter = scoreList.iterator();
				Score score;
				
				while(iter.hasNext()){ 
					score = (Score)iter.next();
		        
		     %>
		    
		      <tr>
		         <td><%=score.getSid()%></td>
		         <td><%=score.getSname() %></td>
		         <td><input type="text" name="<%=score.getSid()%>" required/></td> <!--required: 要求此处是必填字段  -->
		         
		      </tr>
		      <%} %>		      
		      </table>
		      
		      <br>		     
			      <input type="hidden" name="cid" value="<%=course.getCid() %>"/>
			      <input type="submit" value="保存" class="save"/>		      		      
	     </form>  
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
		
		// 使文字产生动画效果
		
		$('.textillate').textillate(
		{
			autoStart: true,			
			loop: true,
		});
		
		// 使html组件摇摆
		
		$('.save').jrumble();
		$('.save').hover(function() {
			$(this).trigger('startRumble');
		}, function() {
			$(this).trigger('stopRumble');
		});
				
	</script>
    </body>
</html>