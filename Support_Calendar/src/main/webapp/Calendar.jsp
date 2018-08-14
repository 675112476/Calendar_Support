<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page import="cn.edu.just.demo.entity.Worker" %>
<%@ page import="cn.edu.just.demo.utils.DBUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>2018校园秋促支撑日历</title>
<link rel="stylesheet" href="Calendar_css.css">
</head>
<body>
	<div id="calendar_s">
		<div id="head">2018校园秋促支撑日历</div>

		<div id="calendar">
			<table id="dateZone">
				<thead>
					<tr>
						<td>星期一</td>
						<td>星期二</td>
						<td>星期三</td>
						<td>星期四</td>
						<td>星期五</td>
						<td>星期六</td>
						<td>星期日</td>
					</tr>
					<tr>
				</thead>
				<tbody id="dateTable">

				</tbody>
			</table>
			<div>
				<div id="worker_inf">
				支撑人员联系方式：
				</div>
				<br />
				<div id="worker_phone">
					<%
						ResultSet resultSet = null;
						List<Worker> worker_list = new ArrayList<Worker>();
						Connection connection = DBUtils.getConnection();
						PreparedStatement statement = null;
						String sql = "select * from worker;";
						try {
							statement = connection.prepareStatement(sql);
							resultSet = statement.executeQuery();
							while (resultSet.next()) {
								String name = resultSet.getString(2);
								String phone = resultSet.getString(3);
								Worker worker = new Worker(name, phone);
								worker_list.add(worker);
							}
							request.setAttribute("worker_list", worker_list);
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					%>

					<%
						int i = 1;
						for (Worker worker : worker_list) {
					%>
					
					<%=worker.getName()%>:<%=worker.getPhone()%>&nbsp
					<%
						if (i % 4 == 0) {
					%>
					<br />
					<%
						}
							i++;
						}
					%>
				</div>
			</div>
		</div>
		<div id="todayInfo">
			<div id="dateInfo">2018-07-11</div>
			<div id="weekInfo">星期三</div>
			<div id="allProvince">全省：</div>
			<div id="dateNum" class="dateNum">6300</div>
			<div id="allProvince">无锡：</div>
			<div id="dateNum" class="dateNum">1300</div>
		</div>
	</div>
	<script src="handlebars-v4.0.11.js"></script>
</body>
</html>