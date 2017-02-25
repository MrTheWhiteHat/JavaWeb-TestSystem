<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" errorPage="error.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>考试管理系统</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <meta name="keywords" content="华中师范大学/华中师大/华师/华中大，考试管理系统/考试系统/考试">
    <meta name="description" content="华中师范大学考试管理系统，先进的管理方式，以及完备的管理功能，让您全方面的管理自己的学习或教学情况。">
    <script src="${pageContext.request.contextPath}/js/jquery.js" ></script>
    <script src="${pageContext.request.contextPath}/js/jquery.textillate.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.lettering.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.fittext.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.jrumble.1.3.min.js"></script>
    
    <link rel="icon" href="${pageContext.request.contextPath}/images/titleicon.png" type="image/x-icon"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/animate.css">

  </head>
  
  <body class="jquery-ripples">
	<div class="main"> 	
	  	
	  	<div class="header">
	  	</div>
	  	
	  	<!--善于利用多个class名作CSS 选择器，同类不同效果 -->
	  	<div class = "earth_box earth_center">
		  	<i class="earth earth_big earth_center"></i>
		  	<i class="earth earth_small earth_center"></i>
		</div>
	  	
	  	<div class="container">	  		  		
	  		<div class="content">
	  			<div class="wrap">
	  				<div class="slogan">
	  					<p class="welc">欢迎使用华中师范大学考试管理系统</p>
	  					<p class="wish">愿阳光每天撒在你心上  !</p>
	  				</div>
	  				<div class="chooseuser">
	  					<div class="cu_entry">
	  						 <!--此处使用的是Servlet 映射方式访问WEB-INF安全目录下的jsp文件的，${pageContext.request.contextPath}：为网站的根目录，login.jsp在名为login的
	  						Servlet对象中，直接省略Servlet名字访问.jsp就行了，不然报错！！！ 
	  						-->
	  						<a class="student" href="${pageContext.request.contextPath}/login.jsp">
	  							<span class="title">
	  								<i class="st">
	  								</i>
	  									我是学生
	  							</span>
	  						</a>
	  						<a class="teacher" href="${pageContext.request.contextPath}/login1.jsp">
	  							<span class="title">
	  								<i class="tr">
	  								</i>
	  									我是老师
	  							</span>
	  						</a>
	  					</div>
	  				</div>
	  			</div>
	  		</div>
	  		<div class="backimg">
	  			<div class="items">
		  			<div class="item item1" style="background-image:url(${pageContext.request.contextPath}/images/index1.jpg);"></div>
					<div class="item item2" style="background-image:url(${pageContext.request.contextPath}/images/index2.jpg);"></div>
					<div class="item item3" style="background-image:url(${pageContext.request.contextPath}/images/index3.jpg);"></div>
					<div class="item item4" style="background-image:url(${pageContext.request.contextPath}/images/index4.jpg);"></div>
					<div class="item item5" style="background-image:url(${pageContext.request.contextPath}/images/index5.jpg);"></div>
					<div class="item item6" style="background-image:url(${pageContext.request.contextPath}/images/index6.jpg);"></div>
					<div class="item item7" style="background-image:url(${pageContext.request.contextPath}/images/index7.jpg);"></div>
					<div class="item item8" style="background-image:url(${pageContext.request.contextPath}/images/index8.jpg);"></div>
					<div class="item item9" style="background-image:url(${pageContext.request.contextPath}/images/index9.jpg);"></div>
					<div class="item item10" style="background-image:url(${pageContext.request.contextPath}/images/index10.jpg);"></div>
	  			</div>
	  		</div>
	  			  		
	  	</div>
  	
	  	<div class="footer">
	  	</div>	  	
	</div>
	
	<script type="text/javascript">
		
		// 控制背景轮流播放
		
		var slideEle = slider($('.items'));
		
		function slider(elem) {
			var items = elem.children(), 
					max = items.length - 1,
					animating = false,
					currentElem,
					nextElem,
					pos = 0;
					
			sync();
			
			return {
				next: function () {
					move(1);
				},
				prev: function() {
					move(-1);
				},
				itemsNum: items && items.length
			};
			
			function move(dir) {
				if (animating) {
					return;
				}
				if (dir > 0 && pos ==max || dir < 0 && pos == 0) {
					if (dir > 0) {
						nextElem = elem.children('div').first().remove();
						nextElem.hide();
						elem.append(nextElem);
					} else {
						nextElem = elem.children('div').last().remove();
						nextElem.hide();
						elem.prepend(nextElem);
					}
					
					pos -= dir;
					sync();
				}
				
				animating = true;
				items = elem.children();
				currentElem = items[pos + dir];
				$(currentElem).fadeIn(400, function () {
					pos += dir;
					animating = false;
				});
			}
			
			function sync() {
				items = elem.children();
				for (var i = 0; i < items.length; ++i) {
					items[i].style.display = i == pos ? 'block' : ' ';
				}
			}					
		}
		
		if (slideEle.itemsNum && slideEle.itemsNum > 1) {
			setInterval(function () {
				slideEle.next();
			}, 2500);
		}
		
		// 使字体产生动画
		
		$('.welc').textillate(
		{
			autoStart:true,
			in: {
				effect: 'fadeInDownBig',
				delay: 100,				
				delayScale: 1.5,
				shuffle: true,
				
			},
			loop: true,
			out: { // 字符出动画时的效果设置
				effect: 'hinge', // 荷叶效果
				delayScale: 1.5,
				delay: 200, // 设置每个字效果延迟时间
				//下面四个效果只能取其一，不能同时选true
				sync: false, // 同步与否
				shuffle: true, // 随机与否
				reverse: false, // 字符反向动画与否
				sequence: false, // 按顺序与否
				callback: function() {} // 回调函数
			},
		});
		
		$('.wish').textillate({
		autoStart:true,
		in: {
			effect: 'flip',
			shuffle: true,
			delay: 100,
		},
		loop: true,
		out: {
			effect: 'fadeOutUpBig',
			delay: 200,
			shuffle: true,
		},
		});
		
		// 是html组件摇起来
		
		$('.student, .teacher, .slogan').jrumble();
		$('.student').hover(function() {
			$(this).trigger('startRumble');
		}, function() {
			$(this).trigger('stopRumble');
		});
		
		$('.teacher').hover(function() {
			$(this).trigger('startRumble');
		}, function() {
			$(this).trigger('stopRumble');
		});
		
		$('.slogan').hover(function() {
			$(this).trigger('startRumble');
		}, function() {
			$(this).trigger('stopRumble');
		});
		
		
  	</script>  	
  </body>
</html>
