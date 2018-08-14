<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>后台左侧导航</title>
<link rel="stylesheet" href="../css/left.css">
<link href="../css/bootstrap.min.css" rel="stylesheet">
<script src='/Calendar/jquery-easyui-1.5.5.5/jquery.min.js'></script>
<script src="../css/bootstrap.min.js"></script>

<style type="text/css">
.menu {
	display: none;
	margin-left: 10px;
}
</style>

</head>
<body>
	<div class="main-left-col">
		<img src="/Calendar/images/dianxin.png" style="width: 70%;height:auto;margin: 19px;">
		

		<ul class="side-nav">

			<li><a href="/Calendar/Calendar.jsp" target="right"><i class="icon-home"></i> 主页</a>
			</li>
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="collapse" data-target="#website-dropdown" href="#"><i
					class="icon-sitemap"></i> 日历信息管理 <b class="caret"></b></a>
				<ul id="website-dropdown" class="collapse">
					<li><a href="/Calendar/manager/altercalendar.html" target="right">修改新生信息</a></li>
					<li><a href="/Calendar/manager/insertcalendar.jsp" target="right">添加新生信息</a></li>
					<li><a href="/Calendar/manager/oldstudent.html" target="right">修改老生信息</a></li>
				</ul></li>
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="collapse" data-target="#blues-dropdown" href="#"><i
					class="icon-signal"></i> 蓝牙管理<b class="caret"></b></a>
				<ul id="blues-dropdown" class="collapse">
					<li><a href="bluetooth.html" target="right">蓝牙设备管理</a></li>
				</ul></li>
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="collapse" data-target="#store-dropdown" href="#"><i
					class="icon-shopping-cart"></i> 员工管理 <b class="caret"></b></a>
				<ul id="store-dropdown" class="collapse">
					<li><a href="worker.html" target="right">值班员工管理</a></li>
				</ul></li>
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="collapse" data-target="#reports-dropdown" href="#"><i
					class="icon-signal"></i> 学校管理<b class="caret"></b></a>
				<ul id="reports-dropdown" class="collapse">
					<li><a href="school.html" target="right">支撑学校管理</a></li>
				</ul></li>
			<li><a href="#"><i class="icon-bullhorn"></i> Alerts <span
					class="badge badge-warning">0</span></a></li>
		</ul>

	</div>
	
</body>
</html>