<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>西安庄勇物业管理有限公司后台管理</title>
		
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/index.css">
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.7.1.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/main.js"></script>
		<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
		<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<!-- <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css"> -->
		
		<script type="text/javascript">
		var i=1;
		var t;
		function change()  //
		{
			if(i==3)  
			{
				i=1;
			}
			i++;
			document.getElementById("divimg").src="images/"+i+".jpg";
			t=setTimeout("change()",2000);
		}
		function stopimg() //
		{
			clearTimeout(t);
		}
		function goon()  //
		{
			change();
		}
		</script>
		
<style type="text/css">

		#login{
			width:260px;
			height:200px;
			padding:20px;
			background-color: white;
			border-radius: 10px;
			margin:0 auto;
			margin-top:5%;
		}
		#warning,#warning1{
			display: none;
			color:red;
		}
		#login label{
			display:block;
			margin:5px;
		}
		#login input{
			padding:5px 10px;
			font-size:17px;
			border-radius:8px;
			outline:none;
			border: 1px solid #ccc;
		}
		.form-group{
			margin-bottom:15px;
		}
		#login a{
			text-decoration:none;
			color:#337ab7;
		}
		#submit{
			width:92%;
			display:block;
			margin-top:5px;
		}
		#submit:hover{
			border:1px solid #337ab7;
		}
</style>
		
	</head>
	<body onload="change()">
		<div class="head">
			<div class="head_count">
			<div class="head_count_logo">
			    <img src="${pageContext.request.contextPath }/images/logo.png" width="624px" height="88px">
			</div>
			<div class="head_count_right">
				<div class="head_count_right_top">
					<table>
						<tr>
							<td>
								<a href="">设为首页</a>
								<span> |</span>
								<a href="">加入收藏</a>
								<span> | </span>
								<a href="">网站地图</a>
							</td>
						</tr>
					</table>
				</div>
				<div class="head_count_right_bottom">
					<table>
						<tr>
							<td style="width:336px;">
								<form style="float:right;">
									<input  type="text" name="txt" value="" style="width:127px;height:22px;" />
									<input  type="submit" name="search" value="搜索"  style="width:58px; height:22px;" />
								</form>
							</td>
						</tr>
					</table>
				</div>
			</div>
			</div>
		</div>
		<div class="menu"> 
			<div class="menu_count">
			  <ul class="menu_count_ul">
			  	<li><b><a href="#">首页</a></b></li>
			  	<li><img src="${pageContext.request.contextPath }/images/dh_fgx.gif" style="margin-left:20px;" /></li>
			  	<li><b><a href="#">公司介绍</a></b>
			  		<ul class="menu_count_secondul1">
			  			<li><b><a href="${pageContext.request.contextPath }/servlet/ComIntroServlet?operation=showMaxId">公司简介</a></b></li>
						<hr width="89px" noshade="noshade" size="1" color="#eee" style="margin-left:-39px;" />
						<li><b><a href="${pageContext.request.contextPath }/servlet/OrgStuServlet?operation=showMaxId">组织架构</a></b></li>
			  			<hr width="89px" noshade="noshade" size="1" color="#eee" style="margin-left:-39px;" />
			  			<li><b><a href="${pageContext.request.contextPath }/servlet/ComCulServlet?operation=showMaxId">企业文化</a></b></li>
			  			<hr width="89px" noshade="noshade" size="1" color="#eee" style="margin-left:-39px;" />
			  			<li><b><a href="${pageContext.request.contextPath }/servlet/TeamConServlet?operation=showMaxId">团队建设</a></b></li>
			  			<hr width="89px" noshade="noshade" size="1" color="#eee" style="margin-left:-39px;" />
			  			<li style="margin-top:-5px;"><b><a href="${pageContext.request.contextPath }/servlet/BigThingServlet?operation=showMaxId">大事记</a></b></li>
			  		</ul>
			  	</li>
			  	<li><img src="images/dh_fgx.gif" style="margin-left:20px;" /></li>
			  	<li><b><a href="#">新闻中心</a></b>
			  	    <ul class="menu_count_secondul2">
			  			<li><b><a href="${pageContext.request.contextPath }/servlet/ComNewServlet?operation=showMaxId">公司新闻</a></b></li>
			  			<hr width="89px" noshade="noshade" size="1" color="#eee" style="margin-left:-39px;" />
			  			<li><b><a href="${pageContext.request.contextPath }/servlet/LocalDynServlet?operation=showMaxId">地方动态</a></b></li>
			  			<hr width="89px" noshade="noshade" size="1" color="#eee" style="margin-left:-39px;" />
			  			<li><b><a href="${pageContext.request.contextPath }/servlet/MedNewServlet?operation=showMaxId">媒体新闻</a></b></li>
			  		</ul>
			  	</li>
			  	<li><img src="images/dh_fgx.gif" style="margin-left:20px;" /></li>
			  	<li><b><a href="#">产品服务</a></b>
			  		<ul class="menu_count_secondul2">
			  			<li><b><a href="${pageContext.request.contextPath }/servlet/HouseKeepServlet?operation=showMaxId">家政服务</a></b></li>
			  			<hr width="89px" noshade="noshade" size="1" color="#eee" style="margin-left:-39px;" />
			  			<li><b><a href="${pageContext.request.contextPath }/servlet/CarRepServlet?operation=showMaxId">汽车维修</a></b></li>
			  			<hr width="89px" noshade="noshade" size="1" color="#eee" style="margin-left:-39px;" />
			  			<li><b><a href="${pageContext.request.contextPath }/servlet/ConRepServlet?operation=showMaxId">便民维修</a></b></li>
			  		</ul>
			  	</li>
			  	<li><img src="images/dh_fgx.gif" style="margin-left:20px;" /></li>
			  	<li><b><a href="#">社区生活</a></b>
			  		<ul class="menu_count_secondul4">
			  			<li><b><a href="${pageContext.request.contextPath }/servlet/OwnHomeServlet?operation=showMaxId">业主之家</a></b></li>
			  		</ul>
			  	</li>
			  	<li><img src="images/dh_fgx.gif" style="margin-left:20px;" /></li>
			  	<li><b><a href="#">加入庄勇</a></b>
			  		<ul class="menu_count_secondul5">
			  			<li><b><a href="${pageContext.request.contextPath }/servlet/JoinZyServlet?operation=showMaxId">招聘信息</a></b></li>
			  		</ul>
			  	</li>
			  	<li><img src="images/dh_fgx.gif" style="margin-left:20px;" /></li>
			  	<li><b><a href="#">议投诉建</a></b>
			  		<ul class="menu_count_secondul6">
			  			<li><b><a href="${pageContext.request.contextPath }/servlet/ComCallServlet?operation=showMaxId">投诉电话</a></b></li>
			  		</ul>
			  	</li>
			  </ul>
			</div>
		</div>
		<!-- <div style="width:100%;height:15px;background:#eee;"></div> -->
		<!-- 
		<div style="width:80%;height:auto;background:#eee;margin:20px auto;">
			<form action="${pageContext.request.contextPath}/servlet/UserServlet?operation=login" method="post">
				用户名：<input type="text" name="username"><br/>
				密码：<input type="password" name="password"><br/>
				提交：<input type="submit" value="submit"/>
			</form>
		<div>
		 --> 
		<div class="container" id="login" >
			<form action="${pageContext.request.contextPath}/servlet/UserServlet?operation=login" method="post">
				 <div class="panel-body">
				 	<div class="form-group">
				 		<label>用户名</label>
				 		<input type="text" class="form-control" name="username"/>
				 		<div id="warning">用户名不能为空</div>
				 		<div id="warning1">用户名格式错误</div>
				 		<label>密码</label>
				 		<input type="password" class="form-control" name="password"/>
				 	</div>
				 	<a href="#" class="text-info">忘记密码</a>
				 	<span>|</span>
				 	<a href="#" class="text-info" target="_blank">注册</a>
				 	<input type="submit" value="登录" id="submit">
				 	<!-- <input type="button" value="登录"  class=" btn btn-group-justified btn-primary" id="submit"/>-->
				 </div>
			</form>
		</div>
		
	${u }
	</body>
</html>