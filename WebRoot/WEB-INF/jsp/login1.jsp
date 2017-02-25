<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" errorPage="error.jsp" %>
<%@ page import="java.net.URLDecoder" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC  "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>Project登录</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
		<link rel="icon" href="${pageContext.request.contextPath}/images/titleicon.png" type="image/x-icon"/>
		<link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css"/>
		<script src="${pageContext.request.contextPath}/js/jquery.js" ></script>
		<script src="${pageContext.request.contextPath}/js/waterpipe.js"></script>
	</head>
	<body>
		<div class="container">
			<div class="header">
	  			<a class="header_logo" href="${pageContext.request.contextPath}/index.jsp">  Project系统  </a>            
	  			<div class="header_link">
		  			<a href="${pageContext.request.contextPath}/index.jsp" >  首  页 </a>
		  				&nbsp;|&nbsp; 
		  			<a href="http://en.project.com" > English </a>
		  				&nbsp;|&nbsp;
		  			<a href="http://app.project.com" target="_blank">  手机版 </a> <!--target="_blank":重新打开一个窗口-->
		  				&nbsp;|&nbsp; 
		  			<a href="http://et.project.com" target="_blank" > 企业版 </a>
		  			    &nbsp;|&nbsp;
		  		</div>
	  	    </div>
			<div class="content"> 
				<div id="loginform">
					 <ul class="ui-nav" id="loginMethod-tabs">
						<li class data-status="show_qr"> 登 录 框 </li>
						<!-- <li class="active" data-status="show_login"> 扫码登录 </li>  -->
				     </ul>
				     <form id="myform1" name = "loginForm" method="post" action="service.do?service=login">
				     	<div class="ui-form-item">
				        	<label id="label-user">
								<span class="ui-icon">
									<i class="iconauth"> </i>
								</span>
				        	</label>
				            <input id="user" type="text" name="id" accesskey="u"  placeholder="教 工 号 " datatype="*" nullmsg="请输入用户名！" errormsg="请输入用户名！" required/><br/> 
						</div>
						<div class="ui-form-item ui-form-item-2" id="passwd">
							
							<!--此处的type和name不能重名否则会造成登陆错误，因为getParameter("password")不到东西！！！-->
						    <input id="psw" type="password"  name="pwd" accesskey="p"  placeholder="密  码"  datatype="*"  nullmsg="请输入密码！" errormsg="请输入密码！" required/><br/>
						</div>
						
						<div id="content1" title = "为了确保您的信息安全，不建议在网吧等公共环境勾选此项">
						     <input id="cb" type="checkbox" name="rememberMe" checked="checked" />
						     <font color="#fff"> 下次自动登录</font> 
						   
						     <select style="display:none" class="select" name="role"> <!--style="display:none":设置了选择框成了隐藏  -->
					              <option value="2">老师</option>					          
          					</select>
						<%
				           String message = (String)request.getAttribute("message");				   
				           if(message!=null){
        				%>
			            <font color="red">
			            	<h3><%=message %></h3>			     
			            </font>
				         <%} %>
						<div id="content2">
						   <input id="login" type="submit" value="登  录" /> <!--value：要显示的文字-->&nbsp;
						   <input id="cancel" type="reset"  value="取  消" />						     
						</div>
						
						<div id="content3">
							<a href="localhost:8080" target="_blank"> 忘记密码？</a> |
							<a href="localhost:8080" target="_blank"> 注册新帐号</a> 
						</div>
					
				     </form>
				</div>
	  		</div>
	   	    <div class="footer">
		  	   <a href="www.bing.com" target="_blank"> 关于我们 </a> &nbsp;|&nbsp;
		  	   <a href="www.bing.com" target="_blank"> 客服中心 </a>&nbsp;|&nbsp;
		  	   <a href="www.bing.com" target="_blank"> 联系我们 </a> &nbsp;|&nbsp;
		  	   <a href="www.bing.com" target="_blank"> 帮助中心 </a> &nbsp;|&nbsp;
		  	   <span class="gray">&copy;2016 - 2020 Project Inc. All Rights Reserved.</span>
	        </div>
	        <div class="login-background" id="lg-bg" style="width:100%;height:100%;">
	    		
	    		<canvas>Hello World!</canvas>
	        </div>
	     </div>
	     <script type="text/javascript">
	     	var smokyBG = $('#lg-bg').waterpipe({
	     	//Default values
	     	gradientStart: '#fffff0',
	     	gradientEnd: '#222222',
	     	smokeOpacity: 0.1,
	     	numCircles: 0.666666,
	     	maxMaxRad: 'auto',
	     	minRadFactor: 0,
	     	iterations: 8,
	     	drawsPerFrame: 10,
	     	lineWidth: 2,
	     	speed: 10,
	     	bgColorInner: "#00ffff",
	     	bgColorOuter: "#666666"
	     	});
	     </script>
	</body>
</html>
