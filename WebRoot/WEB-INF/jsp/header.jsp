<%@ page language="java" import="java.util.*, java.io.*" pageEncoding="UTF-8"%>
 
<div class="header">
	<a class="header_logo" href="/MyProject/index.jsp">  Project系统  </a>            
	<div class="header_link">
		 <span class="welcuser"></span>
		 
		 <a class="exit" href="${pageContext.request.contextPath}/login.jsp"> 退 出  </a>	
		 <a href="${pageContext.request.contextPath}/index.jsp" >  首  页 </a>
		  				&nbsp;|&nbsp; 
		 <a href="http://en.project.com" > English </a>
		  				&nbsp;|&nbsp;
		 <a href="http://app.project.com" target="_blank">  App下载 </a> <!--target="_blank":重新打开一个窗口-->
		  				&nbsp;|&nbsp; 
		 <a href="http://et.project.com" target="_blank" > 论坛 </a>
		  			    &nbsp;&nbsp;
	</div>  
</div>
<div class="line">
</div>
<div class="textillate">
    <ul class="texts">
    	<li data-in-effect="flash" data-in-shuffle="false" data-out-effect="fadeOut" data-out-shuffle="true">欢迎<%=request.getAttribute("message")%>同学使用华中师范大学考试管理系统</li>
    	<li data-in-effect="flip" data-in-shuffle="true" data-out-effect="hinge" data-out-shuffle="true">愿你期末考试科科高分飘过</li>
    </ul>
</div>			
		  			