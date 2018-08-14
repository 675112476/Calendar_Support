<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="cn.edu.just.utils.Worker"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>2018校园秋促支撑日历</title>
<link rel="stylesheet" href="css/Calendar1.css">
</head>
<body>
	<div id="head">2018校园秋促支撑日历</div>
	<div id="calendar_s">
		<div id="calendar">
			<div class="title">
				<h1 class="green" id="calendar-title">Month</h1>
				<h2 class="green" id="calendar-year">Year</h2>
				<button id="pre" style="border: 0;"></button>
				<button id="next" style="border: 0;"></button>
			</div>

			<table id="dateZone">
				<thead class="lightgrey body-list">
					<tr>
						<td>星期日</td>
						<td>星期一</td>
						<td>星期二</td>
						<td>星期三</td>
						<td>星期四</td>
						<td>星期五</td>
						<td>星期六</td>
					</tr>
				</thead>
				<tbody id="days">
					<tr>
					</tr>
				</tbody>

			</table>
			<div
				style="float: left; margin-left: 40px; margin-top: 20px; color: #6ac13c; font-size: 24px;">支撑人员联系方式：</div>
			<div class="clear"></div>
			<table id="nums" style="margin-left: 40px; width:100%"></table>
		</div>

		<div id="download" style="margin-top:20px;">
			<a href="/Calendar/DownloadServlet?filename=1.jpg" >日历下载</a>
		</div>

		<div id="mouseOverDiv"></div>
		<div style=height:30px;></div>

	</div>

	<script src='/Calendar/jquery-easyui-1.5.5.5/jquery.min.js'></script>
	<script type="text/javascript">
		var month_olypic = [ 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];//闰年每个月份的天数
		var month_normal = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];
		var month_name = [ "January", "Febrary", "March", "April", "May",
				"June", "July", "Auguest", "September", "October", "November",
				"December" ];

		//获取以上各个部分的id
		var holder = document.getElementById("days");
		var holder1 = document.getElementById("nums");
		var prev = document.getElementById("prev");
		var next = document.getElementById("next");
		var ctitle = document.getElementById("calendar-title");
		var cyear = document.getElementById("calendar-year");
		var mouseOver = document.getElementById("mouseOverDiv");

		
		//获取当天的年月日
		//var my_date = new Date(2018,8,2);
		var my_date = new Date();
		var my_year = my_date.getFullYear();//获取年份
		var my_month = my_date.getMonth(); //获取月份，一月份的下标为0
		var my_day = my_date.getDate();//获取当前日期
			
		//根据年月获取当月第一天是周几
		function dayStart(month, year) {
			var tmpDate = new Date(year, month, 1);
			return (tmpDate.getDay());
		}

		//根据年份判断某月有多少天(11,2018),表示2018年12月
		function daysMonth(month, year) {
			var tmp1 = year % 4;
			var tmp2 = year % 100;
			var tmp3 = year % 400;

			if ((tmp1 == 0 && tmp2 != 0) || (tmp3 == 0)) {
				return (month_olypic[month]);//闰年
			} else {
				return (month_normal[month]);//非闰年
			}
		}

		//获得日历每一天的基本信息
		function getCalendar() {
			$.ajax({
				url : "http://132.230.104.158:8090/getCalendar",
				dataType : "jsonp",
				complete : function(data) {

					data = data.responseJSON;
					var result = JSON.stringify(data);
					console.info("result:" + result);
					//将返回的数据分装成实体类放到calendars数组中去
					var calendars = new Array();
					$.each(data, function(index, obj) {
						var calendar = new Object();
						calendar.date = obj['date'];
						calendar.school = obj['school'];
						calendar.sceneperson = obj['sceneperson'];
						calendar.backperson = obj['backperson'];
						calendars.push(calendar);
					});
					//后端实现将相同一天的现场支撑和学校加到一起
					//调用refreshDate()函数，日历才会出现
					refreshDate(calendars.length, calendars);
				}
			});
		}
		
		function getOldstudent(i,to_date){
			$.ajax({
				url : "http://132.230.104.158:8090/getOldstudentByDate",
				dataType : "jsonp",
				async : false,
				data : {
					"date" : to_date
				},
				complete : function(data) {
					data = data.responseJSON;
					data=data.oldstudent;
					//var result = JSON.stringify(data);
					//console.info("getOldstudent:"+to_date + data);
					
					if(data[0]==null){
						
					}else{
						var Oldstudent = document.getElementById("Oldstudent_"+i);
						var old_str="<div class='a'><hr style='height:2px;border:none;border-top:1px dashed #DDDDDD; width:100%' /><div class='details'>老生：</div><div class='details'>"
						+ "<table id='detail_table'><tr><td class='div_td'>"+data[0]+"</td><td class='div_td'>"+data[1]+"</td></tr><tr><td class='div_td'>"+data[2]+"</td><td class='div_td'>"+data[3]+"</td></tr></table>"
						+ "</div>"
						+ "</div>";
						console.info(old_str);
						Oldstudent.innerHTML=old_str;
					}
				}
			});
		}

		//获取联系人的电话
		function getPhoneNum() {
			$.ajax({
				url : "http://132.230.104.158:8090/getWorker",
				dataType : "jsonp",
				async : false,
				complete : function(data) {
					data = data.responseJSON;
					var result = JSON.stringify(data);
					//console.info(result);
					//将返回的数据分装成实体类放到workers数组中去
					var workers = new Array();
					var phone_detail = "<tr>";
					var count = 0;
					$.each(data, function(index, obj) {
						count++;
						phone_detail += "<td style='text-align: left;'>"
								+ obj['name'] + ":" + obj['phone'] + "</td>";
						if (count % 4 == 0) {
							phone_detail += "</tr><tr>";
						}
						//console.info(phone_detail);
					});
					holder1.innerHTML = phone_detail;
				}
			});
		}
		function getHsonLength(json){
            var jsonLength=0;
            for (var i in json) {
                jsonLength++;
            }
            return jsonLength;
        }
		var result;
		//得到详情
		function getDivDetails(i, date,pre_to_after) {
			
			//发送详情的ajax请求
			var result="<div>";
			$.ajax({
				url : "http://132.230.104.158:8090/"+pre_to_after,
				data : {
					"date" : date
				},
				async : false,
				dataType : "jsonp",
				complete : function(data) {
					data = data.responseJSON;
					//得到我们要的字符串
					data = data.result;
					console.info(data);
					var data1 = JSON.parse(data);
					console.info(getHsonLength(data1));
					//result = JSON.stringify(data);
					for(var t=0;t<getHsonLength(data1)-1 ;t++){
						obj=data1[t];
						console.info(obj);
						var school=obj["school"];
						var student_predict=obj["student_predict"];
						var scene_person=obj["scene_person"];
						var scene_phone=obj["scene_phone"];
						var blue_tooth=obj["bluetooth"];
						
						console.info(blue_tooth);
						var blues="";
						for(var h=0;h<getHsonLength(blue_tooth);h++){
							
							var blue=blue_tooth[h];
							var tooth_brand=blue["tooth_brand"];
							//console.info(blue["tooth_brand"]);
							var tooth_amount=blue["tooth_amount"];
							blues+="<div class='day_details'><a class='bold_details'>蓝牙品牌：</a>"+tooth_brand+"&nbsp;&nbsp;&nbsp; <a class='bold_details'>数量：</a>"+tooth_amount+"</div>";
						}
						if(school!=""){
							result+="<div class='day_details'><a class='bold_details'>学校：</a>"+school+"</div>";
							result+="<div class='day_details'><a class='bold_details'>预计学生：</a>"+student_predict+"&nbsp;&nbsp;&nbsp; <a class='bold_details'>现场：</a>"+scene_person+"</div>";
							result+=blues;
							result+="<hr style='height:2px;border:none;border-top:1px dashed #DDDDDD;' />"
						}
					}
					obj=data1[getHsonLength(data1)-1];
					var far_person=obj["far_person"];
					var far_phone=obj["far_phone"];
					var day_num=obj["day_num"];
					var xxb_num=obj["xxb_num"];
					var to_after_num=obj["1"];
					var to_after_xxb=obj["2"];
					result+="<div class='day_details'><a class='bold_details'>远程：</a>"+far_person+"</div>";
					result+="<div class='day_details'><a class='bold_details'>电话：</a>"+far_phone+"</div>";
					if(to_after_num!=""){
						result+="<div class='day_details'><a class='bold_details'>"+to_after_num+"：</a>"+day_num+"</div>";
						result+="<div class='day_details'><a class='bold_details'>"+to_after_xxb+"：</a>"+xxb_num+"</div>";
					}
					
						
					
					var tar_div = document.getElementById(i);
					tar_div.innerHTML = result;
					var carlendar_div = document.getElementById("tar_da"+i);
					
					//设置div的鼠标事件
					$(carlendar_div).mouseover(function(e) {
						$(tar_div).css('display','block');  
						//定位到鼠标的相对位置
						$(tar_div).css("top", e.pageY+20);
						$(tar_div).css("left", e.pageX-80);
					});
					$(carlendar_div).mouseout(function(e) {
						$(tar_div).css('display','none');  
					});
					
				}
			});
			getProperty();
		}
		
		//获取property并改变相应的颜色
		function getProperty(){
			$.ajax({
				url : "http://132.230.104.158:8090/getProperty",
				async : false,
				dataType : "jsonp",
				complete : function(data) {
					data = data.responseJSON;
					result = JSON.stringify(data);
					//console.info("--property:"+result);
					$.each(data,function(index,obj){
						var property=obj["property"];
						var date=obj["date"];
						var dates=date.split('-');
						//console.info("--property dates:"+dates[2]+" property:"+property);
						var carlendar_div = document.getElementById("style_"+dates[2]);
						if(property==1){
							carlendar_div.style.backgroundColor="#FF0000";
						}else if(property==2){
							carlendar_div.style.backgroundColor="#0000FF ";
						}
					});
				}
			});
		}

		
		//绘制出OnMouseOver的div
		function createDiv() {
			var mouseOverDiv = "";
			for (var i = 1; i < 32; i++) {
				mouseOverDiv += "<div class='b' id="+i+"></div>";
				mouseOver.innerHTML = mouseOverDiv;
			}
		}
		/*
		//给div赋值
		function paintDiv(i, date) {
			var tar_div = document.getElementById(i);
			//console.info(date);
			var div_result=getDivDetails(date);
			console.info(div_result);
			tar_div.innerHTML = "<a>" + div_result + "</a>";
		}
		 */

		//js实现str插入+class,不要忘了用innerhtml进行插入
		function refreshDate(length, calendars) { 
			var str = "<tr>";
			//计算当月的天数和每月第一天都是周几，day_month和day_year都从上面获得
			var totalDay = daysMonth(my_month, my_year);
			var firstDay = dayStart(my_month, my_year);
			//console.info(firstDay);
			//添加每个月的空白部分
			for (var i = 0; i < firstDay; i++) {
				str += "<td>" + "</td>";
			}

			//从一号开始添加知道totalDay，并为pre，next和当天添加样式
			var myclass;
			var pre_to_after;
			for (var i = 1; i <= totalDay; i++) {
				//三种情况年份小，年分相等月份小，年月相等，天数小
				//点击pre和next之后，my_month和my_year会发生变化，将其与现在的直接获取的再进行比较
				//i与my_day进行比较,pre和next变化时，my_day是不变的
				if ((my_year < my_date.getFullYear())
						|| (my_year == my_date.getFullYear() && my_month < my_date
								.getMonth())
						|| (my_year == my_date.getFullYear()
								&& my_month == my_date.getMonth() && i < my_day)) {
					myclass = " class='lightgrey'";
					pre_to_after="preDetails";
				} else if (my_year == my_date.getFullYear()
						&& my_month == my_date.getMonth() && i == my_day) {
					myclass = "class = 'green greenbox'";
					pre_to_after="toDetails";
				} else {
					myclass = "class = 'darkgrey'";
					pre_to_after="afterDetails";
				}

				//判断这一天是否添加了新生详细信息
				var bool_details=0;
				str += "<td id='div_"+i+"'"+myclass+">"
						+ "<div style='color: #FFFFFF;background: #6ac13c;'id='style_"+i+"'>"
						+ (my_month + 1) + "/" + i + "</div>";
				//日月小于10的加上0补全格式
				//因为第一个月份第一个下标为0，所以+1
				if(my_month<10){
					var mon="-0"+(my_month + 1);
				}else{
					var mon="-"+(my_month + 1);
				}
				if(i<10){
					var day="-0"+i;
				}else{
					var day="-"+i;
				}
				var to_date = my_year + mon + day;
				for (var t = 0; t < length; t++) {
					var target = calendars[t].date;
					
					if (target == to_date) {
						var cal_date = calendars[t].date;
						var cal_school = calendars[t].school;
						var cal_sceneperson = calendars[t].sceneperson
						var cal_backperson = calendars[t].backperson;
						str += "<div class='a' id='tar_da"+i+"'><div class='details'>新生：</div><div class='details'>"
								+ "<table ><tr><td class='div_td'>"+cal_school[0]+"</td><td class='div_td'>"+cal_school[1]+"</td></tr><tr><td class='div_td'>"+cal_school[2]+"</td><td class='div_td'>"+cal_school[3]+"</td></tr></table>"
								+ "</div>"
								+ "</div>" + "<div id='Oldstudent_"+i+"'></div></td>";
						getDivDetails(i, to_date,pre_to_after);
						bool_details=1;
					}
				}
				if (bool_details == 0) {
					str+="<div id='Oldstudent_"+i+"'></div>";
				}
				

				//实现换行
				if ((i + firstDay) % 7 == 0) {
					str += "</tr><tr>"
				}
				getOldstudent(i,to_date);
			}
			holder.innerHTML = str;
			ctitle.innerHTML = month_name[my_month];
			cyear.innerHTML = my_year;
			getOldstudent();
		}

		//**********
		//开始获取员工信息并绘制
		getPhoneNum();
		//开始获取日历信息并绘制
		getCalendar();

		createDiv();
		
		//实现onclick向前或向后移动
		pre.onclick = function(e) {
			e.preventDefault();
			my_month--;
			if (my_month < 0) {
				my_year--;
				my_month = 11; //即12月份
			}
			getCalendar();
			createDiv();
		}

		next.onclick = function(e) {
			e.preventDefault();
			my_month++;
			if (my_month > 11) {
				my_month = 0;
				my_year++;
			}
			getCalendar();
			createDiv();
		}
	</script>
</body>
</html>