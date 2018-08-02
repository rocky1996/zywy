<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
	<head>
	<meta charset="UTF-8">
	<title>Document</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/index.css">
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.7.1.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/main.js"></script>
		<script type="text/javascript">
		var i=1;
		var t;
		function change()  
		{
			if(i==3)  
			{
				i=1;
			}
			i++;
			document.getElementById("divimg").src="images/"+i+".jpg";
			t=setTimeout("change()",2000);
		}
		function stopimg() 
		{
			clearTimeout(t);
		}
		function goon()  
		{
			change();
		}
		</script>
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
								<span> | </span>
								<a href="${pageContext.request.contextPath }/servlet/UserServlet?operation=logout">安全退出</a>
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
									<input  type="submit" name="search" value="按钮"  style="width:58px; height:22px;" />
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
			  	<li><b><a href="next.jsp">公司介绍</a></b>
			  		<ul class="menu_count_secondul1">
			  			<li>
			  				<b><a href="javascript:;">公司简介</a></b>
			  				<span class="modalSpan" style="display: none;width: 300px;background: #20467a;position: absolute;left: 70px;top: 0px;">
			  					<span><a href="${pageContext.request.contextPath }/manager/ComIntro/ComIntroAdd.jsp">上传文件</a></span>
			  					<span><a href="${pageContext.request.contextPath }/servlet/ComIntroServlet?operation=showAll">后台显示</a></span>
			  				</span>
			  			</li>
			  			<li>
			  				<b><a href="javascript:;">组织架构</a></b>
			  				<span class="modalSpan" style="display: none;width: 300px;background: #20467a;position: absolute;left: 70px;top: 0px;">
			  					<span><a href="${pageContext.request.contextPath }/manager/OrgStu/OrgStuAdd.jsp">上传文件</a></span>
			  					<span><a href="${pageContext.request.contextPath }/servlet/OrgStuServlet?operation=showAll">后台显示</a></span>
			  				</span>
			  			</li>
			  			<li>
			  				<b><a href="javascript:;">企业文化</a></b>
			  				<span class="modalSpan" style="display: none;width: 300px;background: #20467a;position: absolute;left: 70px;top: 0px;">
			  					<span><a href="${pageContext.request.contextPath }/manager/ComCul/ComCulAdd.jsp">上传文件</a></span>
			  					<span><a href="${pageContext.request.contextPath }/servlet/ComCulServlet?operation=showAll">后台显示</a></span>
			  				</span>
			  			</li>


			  			<li>
			  				<b><a href="javascript:;">团队建设</a></b>
			  				<span class="modalSpan" style="display: none;width: 300px;background: #20467a;position: absolute;left: 70px;top: 0px;">
			  					<span><a href="${pageContext.request.contextPath }/manager/TeamCon/TeamConAdd.jsp">上传文件</a></span>
			  					<span><a href="${pageContext.request.contextPath }/servlet/TeamConServlet?operation=showAll">后台显示</a></span>
			  				</span>
			  			</li>


			  			<li>
			  				<b><a href="javascript:;">大事记</a></b>
			  				<span class="modalSpan" style="display: none;width: 300px;background: #20467a;position: absolute;left: 70px;top: 0px;">
			  					<span><a href="${pageContext.request.contextPath }/manager/BigThing/BigThingAdd.jsp">上传文件</a></span>
			  					<span><a href="${pageContext.request.contextPath }/servlet/BigThingServlet?operation=showAll">后台显示</a></span>
			  				</span>
			  			</li>
			  		</ul>
			  	</li>
			  	<li><img src="images/dh_fgx.gif" style="margin-left:20px;" /></li>
			  	<li><b><a href="next.php">新闻中心</a></b>
			  	    <ul class="menu_count_secondul2">
			  	    	<!--  
			  			<li><b><a href="next.php">公司新闻</a></b></li>
			  			<hr width="89px" noshade="noshade" size="1" color="#eee" style="margin-left:-39px;" />
			  			-->
			  			<li>
			  				<b><a href="javascript:;">公司新闻</a></b>
			  				<span class="modalSpan" style="display: none;width: 300px;background: #20467a;position: absolute;left: 70px;top: 0px;">
			  					<span><a href="${pageContext.request.contextPath }/manager/ComNew/ComNewAdd.jsp">上传文件</a></span>
			  					<span><a href="${pageContext.request.contextPath }/servlet/ComNewServlet?operation=showAll">后台显示</a></span>
			  				</span>
			  			</li>
			  			<li>
			  				<b><a href="javascript:;">地方动态</a></b>
			  				<span class="modalSpan" style="display: none;width: 300px;background: #20467a;position: absolute;left: 70px;top: 0px;">
			  					<span><a href="${pageContext.request.contextPath }/manager/LocalDyn/LocalDynAdd.jsp">上传文件</a></span>
			  					<span><a href="${pageContext.request.contextPath }/servlet/LocalDynServlet?operation=showAll">后台显示</a></span>
			  				</span>
			  			</li>
			  			<li>
			  				<b><a href="javascript:;">媒体新闻</a></b>
			  				<span class="modalSpan" style="display: none;width: 300px;background: #20467a;position: absolute;left: 70px;top: 0px;">
			  					<span><a href="${pageContext.request.contextPath }/manager/MedNew/MedNewAdd.jsp">上传文件</a></span>
			  					<span><a href="${pageContext.request.contextPath }/servlet/MedNewServlet?operation=showAll">后台显示</a></span>
			  				</span>
			  			</li>
			  			<!-- 
			  			<li><b><a href="next.php">地方动态</a></b></li>
			  			<hr width="89px" noshade="noshade" size="1" color="#eee" style="margin-left:-39px;" />
			  			<li><b><a href="next.php">媒体新闻</a></b></li>
			  			 -->
			  		</ul>
			  	</li>
			  	<li><img src="images/dh_fgx.gif" style="margin-left:20px;" /></li>
			  	<li><b><a href="next.php">产品服务</a></b>
			  		<ul class="menu_count_secondul2">
			  			<li>
			  				<b><a href="javascript:;">家政服务</a></b>
			  				<span class="modalSpan" style="display: none;width: 300px;background: #20467a;position: absolute;left: 70px;top: 0px;">
			  					<span><a href="${pageContext.request.contextPath }/manager/HouseKeep/HouseKeepAdd.jsp">上传文件</a></span>
			  					<span><a href="${pageContext.request.contextPath }/servlet/HouseKeepServlet?operation=showAll">后台显示</a></span>
			  				</span>
			  			</li>
			  			<li>
			  				<b><a href="javascript:;">汽车维修</a></b>
			  				<span class="modalSpan" style="display: none;width: 300px;background: #20467a;position: absolute;left: 70px;top: 0px;">
			  					<span><a href="${pageContext.request.contextPath }/manager/CarRep/CarRepAdd.jsp">上传文件</a></span>
			  					<span><a href="${pageContext.request.contextPath }/servlet/CarRepServlet?operation=showAll">后台显示</a></span>
			  				</span>
			  			</li>
			  			<li>
			  				<b><a href="javascript:;">便民维修</a></b>
			  				<span class="modalSpan" style="display: none;width: 300px;background: #20467a;position: absolute;left: 70px;top: 0px;">
			  					<span><a href="${pageContext.request.contextPath }/manager/ConRep/ConRepAdd.jsp">上传文件</a></span>
			  					<span><a href="${pageContext.request.contextPath }/servlet/ConRepServlet?operation=showAll">后台显示</a></span>
			  				</span>
			  			</li>
			  		</ul>
			  	</li>
			  	<li><img src="images/dh_fgx.gif" style="margin-left:20px;" /></li>
			  	<li><b><a href="next.php">社区生活</b>
			  		<ul class="menu_count_secondul4">
			  			<li>
			  				<b><a href="javascript:;">业主之家</a></b>
			  				<span class="modalSpan" style="display: none;width: 300px;background: #20467a;position: absolute;left: 70px;top: 0px;">
			  					<span><a href="${pageContext.request.contextPath }/manager/OwnHome/OwnHomeAdd.jsp">上传文件</a></span>
			  					<span><a href="${pageContext.request.contextPath }/servlet/OwnHomeServlet?operation=showAll">后台显示</a></span>
			  				</span>
			  			</li>
			  		</ul>
			  	</li>
			  	<li><img src="images/dh_fgx.gif" style="margin-left:20px;" /></li>
			  	<li><b><a href="next.php">加入庄勇</a></b>
			  		<ul class="menu_count_secondul5">
			  			<li>
			  				<b><a href="javascript:;">招聘信息</a></b>
			  				<span class="modalSpan" style="display: none;width: 300px;background: #20467a;position: absolute;left: 70px;top: 0px;">
			  					<span><a href="${pageContext.request.contextPath }/manager/JoinZy/JoinZyAdd.jsp">上传文件</a></span>
			  					<span><a href="${pageContext.request.contextPath }/servlet/JoinZyServlet?operation=showAll">后台显示</a></span>
			  				</span>
			  			</li>
			  		</ul>
			  	</li>
			  	<li><img src="images/dh_fgx.gif" style="margin-left:20px;" /></li>
			  	<li><b><a href="next.php">议投诉建</a></b>
			  		<ul class="menu_count_secondul6">
			  			<li>
			  				<b><a href="javascript:;">投诉电话</a></b>
			  				<span class="modalSpan" style="display: none;width: 300px;background: #20467a;position: absolute;left: 70px;top: 0px;">
			  					<span><a href="${pageContext.request.contextPath }/manager/ComCall/ComCallAdd.jsp">上传文件</a></span>
			  					<span><a href="${pageContext.request.contextPath }/servlet/ComCallServlet?operation=showAll">后台显示</a></span>
			  				</span>
			  			</li>
			  		</ul>
			  	</li>
			  </ul>
			</div>
		</div>
		<div style="width:100%;height:15px;background:#eee;"></div>
	</body>
</html>