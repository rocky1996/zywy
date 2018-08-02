<%@page contentType="text/html; charset=UTF-8" %>
<html>
	<META content="text/html; charset=UTF-8" http-equiv="Content-Type">
	<head>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/index.css">
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.7.1.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/main.js"></script>
		<style>
		#qiehuan_menu{
			margin-left: -1px;
			width: 392px;
			color:white;
			background: #20467A;
			border-top-left-radius: 10px;
			border-top-right-radius: 10px;
		}
		#qiehuan_menu li{
			margin-left: 20px;
		}
			.main_count_news_count{
				width: 390px;
				border-left: 1px solid #000;
				border-right: 1px solid #000;
				border-bottom: 1px solid #000;
			}
		#erweima{
			position:relative;
		}
		#erweima-img{
			weight:100px;
			height:100px;
			position:absolute;
			top:-100px;
			left:-8px;
			display:none;
			z-index:10000009;
		}
		</style>
		<script type="text/javascript">
		var i=1;
		var t;
		function change()  //切换图片方法
		{
			if(i==3)  //在这处可以改动图片循环几张
			{
				i=1;
			}
			i++;
			document.getElementById("divimg").src="images/"+i+".jpg";
			t=setTimeout("change()",2000);
		}
		function stopimg() //停止方法
		{
			clearTimeout(t);
		}
		function goon()  //再次运行
		{
			change();
		}
		
		</script>
	</head>
	<body onload="change()">
	<!--头部-->
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
						<!-- 
						<tr>
							<td style="width:336px;">
								<form style="float:right;">
									<input  type="text" name="txt" value="" style="width:127px;height:22px;" />
									<input  type="submit" name="search" value="搜索"  style="width:58px; height:22px;" />
								</form>
							</td>
						</tr>
						 -->
					</table>
				</div>
			</div>
			</div>
		</div>
	<!--目录-->
		<div class="menu"> 
			<div class="menu_count">
			  <ul class="menu_count_ul">
			  	<li><b><a href="#">首页</a></b></li>
			  	<li><img src="images/dh_fgx.gif" style="margin-left:20px;" /></li>
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
			  	<li><b><a href="next.php">新闻中心</a></b>
			  	    <ul class="menu_count_secondul2">
			  			<li><b><a href="${pageContext.request.contextPath }/servlet/ComNewServlet?operation=showMaxId">公司新闻</a></b></li>
			  			<hr width="89px" noshade="noshade" size="1" color="#eee" style="margin-left:-39px;" />
			  			<li><b><a href="${pageContext.request.contextPath }/servlet/LocalDynServlet?operation=showMaxId">地方动态</a></b></li>
			  			<hr width="89px" noshade="noshade" size="1" color="#eee" style="margin-left:-39px;" />
			  			<li><b><a href="${pageContext.request.contextPath }/servlet/MedNewServlet?operation=showMaxId">媒体新闻</a></b></li>
			  		</ul>
			  	</li>
			  	<li><img src="images/dh_fgx.gif" style="margin-left:20px;" /></li>
			  	<li><b><a href="next.php">产品服务</a></b>
			  		<ul class="menu_count_secondul2">
			  		<li><b><a href="${pageContext.request.contextPath }/servlet/HouseKeepServlet?operation=showMaxId">家政服务</a></b></li>
			  			<hr width="89px" noshade="noshade" size="1" color="#eee" style="margin-left:-39px;" />
			  			<li><b><a href="${pageContext.request.contextPath }/servlet/CarRepServlet?operation=showMaxId">汽车维修</a></b></li>
			  			<hr width="89px" noshade="noshade" size="1" color="#eee" style="margin-left:-39px;" />
			  			<li><b><a href="${pageContext.request.contextPath }/servlet/ConRepServlet?operation=showMaxId">便民维修</a></b></li>
			  		</ul>
			  	</li>
			  	<li><img src="images/dh_fgx.gif" style="margin-left:20px;" /></li>
			  	<li><b><a href="next.php">社区生活</a></b>
			  		<ul class="menu_count_secondul4">
			  			<li><b><a href="${pageContext.request.contextPath }/servlet/OwnHomeServlet?operation=showMaxId">业主之家</a></b></li>
			  		</ul>
			  	</li>
			  	<li><img src="images/dh_fgx.gif" style="margin-left:20px;" /></li>
			  	<li><b><a href="next.php">加入庄勇</a></b>
			  		<ul class="menu_count_secondul5">
			  			<li><b><a href="${pageContext.request.contextPath }/servlet/JoinZyServlet?operation=showMaxId">招聘信息</a></b></li>
			  		</ul>
			  	</li>
			  	<li><img src="images/dh_fgx.gif" style="margin-left:20px;" /></li>
			  	<li><b><a href="next.php">投诉建议</a></b>
			  		<ul class="menu_count_secondul6">
			  			<li><b><a href="${pageContext.request.contextPath }/servlet/ComCallServlet?operation=showMaxId">投诉电话</a></b></li>
			  		</ul>
			  	</li>
			  </ul>
			</div>
		</div>
		<div style="width:100%;height:15px;background:#eee;"></div>
	<!--中间图片部分以及选项卡切换部分-->
		<div class="main" style="background:#eee;">
			<div class="main_count">
				<div class="main_count_img">
					<img src="${pageContext.request.contextPath }/images/1.jpg" width="520px" height="244px" 
					  id="divimg" onmouseover="stopimg()" onmouseout="goon()">
				</div>
				<div class="main_count_news">
					<div class="main_count_news_menu" id="qiehuan_menu">
    					<ul>
      						<li><button id="newsBtn">公司新闻</button></li>
      						<li><button id="actionBtn">地方动态</button></li>
      						<li><button id="mediaBtn">媒体新闻</button></li>
    					</ul>
  					</div>
  					<div id="qiehuan">
      					<div class="main_count_news_count">
		      				<marquee id="a" align="left" behavior="scroll" direction="up" height="300" width="200" hspace="50" vspace="20" loop="-1" scrollamount="6"  onMouseOut="this.start()" onMouseOver="this.stop()">
		      					<ul id="newsCon">
		      					</ul>
		      				</marquee>
      					</div>
					    <div class="main_count_news_count" style="display:none;">
					       <marquee id="body" align="left" behavior="scroll" direction="up" height="300" width="200" hspace="50" vspace="20" loop="-1" scrollamount="6" onMouseOut="this.start()" onMouseOver="this.stop()">
		      					<ul id="actionCon">
		      					</ul>
							</marquee>
					    </div>
				        <div class="main_count_news_count" style="display:none;">
					       <marquee id="c" align="left" behavior="scroll" direction="up" height="300" width="200" hspace="50" vspace="20" loop="-1" scrollamount="10" onMouseOut="this.start()" onMouseOver="this.stop()">
								<ul id="mediaCon">
								</ul>
							</marquee> 
				        </div>
				    </div>
				    <span style="margin-left:320px; position:relative; top:-15px;">
				        	<a href="#" style="font-size:9pt;">更多+</a>
				    </span>
				</div>
			</div>
		</div>
		<div style="width:100%;height:15px;background:#eee;"></div>
	<!--下部三块通知公告部分-->
		<div class="notice">
			<div class="notice_count">
				<div class="notice_count_inform">
				   <div class="notice_count_menu">
				   		<b style="margin-left:20px; font-size:12pt; line-height:30px;">通知公告</b>
				   	</div>
					<ul style="border:1px solid black;">
						
					</ul>
				</div>
				<div class="notice_count_serverce">
					<div class="notice_count_menu">
						<b style="margin-left:20px; font-size:12pt; line-height:30px;">便民服务</b>
					</div>
					<ul style="border:1px solid black;">
												
					</ul>
				</div>
				<div class="notice_count_other">
					<div class="notice_count_menu">
						<b style="margin-left:20px; font-size:12pt; line-height:30px;">其他</b>
					</div>
					<ul style="border:1px solid black;">
						
					</ul>
				</div>
			</div>
		</div>
		<div class="bottom">
			<div class="bottom_count">
				<table>
					<tr>
						<td width="689px" height="52px">
							<span>版权所有：西安庄勇物业管理有限公司</span>
							<span>电话：18591987101</span>
							<span>西安市雁塔区兴善东街8号蓝溪都市花园</span>
						</td>
						<td id="erweima" width="145px" height="52px">
							<img id="erweima-img" alt="二维码" src="images/erweima.png"/>
							<input id='erweima-but' type="button" name="code" value="扫描二维码" />
						</td>
						<td width="126px" height="52px">
							<select>
								<option>--友情链接--</option>
								<option></option>
								<option></option>
								<option></option>
								<option></option>
								<option></option>
								<option></option>
							</select>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<script type="text/javascript">
			window.onload=function(){
				$.ajax({
					url:'servlet/ComNewServlet?operation=showDetailAll',
					type:'post',
					success:function(oData){
						var htmlTemp = '';
						var data = JSON.parse(oData);
						for(var i=0;i<data.length;i++){
							htmlTemp += '<li><a href="servlet/ComNewServlet?operation=showDetailOne&comid='+ data[i].comid +'">'+ data[i].title +'</a></li>'
						}
						
						var oCont = document.getElementById('newsCon');
						oCont.innerHTML = htmlTemp;
						htmlTemp = '';
					}
				});
				
				var oEb = document.getElementById("erweima-but");
				var oEi = document.getElementById("erweima-img");
				oEb.onclick = function(){ 
					oEi.style.display=='block' ? oEi.style.display='none' : oEi.style.display='block';
				}
				oEi.onclick = function(){
					oEi.style.display='none';
				}
				
				var oNewsBtn=document.getElementById('newsBtn');
				oNewsBtn.onclick=function(){
					$.ajax({
						url:'servlet/ComNewServlet?operation=showDetailAll',
						type:'post',
						success:function(oData){
							var htmlTemp = '';
							var data = JSON.parse(oData);
							for(var i=0;i<data.length;i++){
								htmlTemp += '<li><a href="servlet/ComNewServlet?operation=showDetailOne&comid='+ data[i].comid +'">'+ data[i].title +'</a></li>'
							}
							
							var oCont = document.getElementById('newsCon');
							oCont.innerHTML = htmlTemp;
							htmlTemp = '';
						}
					});
				}
				
				/* 第二个那妞 */
				var oActionBtn=document.getElementById('actionBtn');
				oActionBtn.onclick=function(){
					$.ajax({
						url:'servlet/LocalDynServlet?operation=showDetailAll',
						type:'post',
						success:function(oData){
							var htmlTemp = '';
							var data = JSON.parse(oData);
							for(var i=0;i<data.length;i++){
								htmlTemp += '<li><a href="servlet/LocalDynServlet?operation=showDetailOne&localid='+ data[i].localid +'">'+ data[i].title +'</a></li>'
							}
							
							var oCont = document.getElementById('actionCon');
							oCont.innerHTML = htmlTemp;
							htmlTemp = '';
						}
					});
				}
				
				/* 第三个oMediaBtn */
				var oMediaBtn=document.getElementById('mediaBtn');
				oMediaBtn.onclick=function(){
					$.ajax({
						url:'servlet/MedNewServlet?operation=showDetailAll',
						type:'post',
						success:function(oData){
							var htmlTemp = '';
							var data = JSON.parse(oData);
							for(var i=0;i<data.length;i++){
								htmlTemp += '<li><a href="servlet/MedNewServlet?operation=showDetailOne&medid='+ data[i].medid +'">'+ data[i].title +'</a></li>'
							}
							
							var oCont = document.getElementById('mediaCon');
							oCont.innerHTML = htmlTemp;
							htmlTemp = '';
						}
					});
				}
			}
		</script>
	</body>
</html>