<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
  <!-- 引入JQuery -->
  <script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.5.5.5/jquery.min.js"></script>
  <!-- 引入EasyUI -->
  <script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.5.5.5/jquery.easyui.min.js"></script>
  <!-- 引入EasyUI的中文国际化js，让EasyUI支持中文 -->
  <script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.5.5.5/locale/easyui-lang-zh_CN.js"></script>
  <!-- 引入EasyUI的样式文件-->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-easyui-1.5.5.5/themes/default/easyui.css" type="text/css"/>
  <!-- 引入EasyUI的图标样式文件-->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-easyui-1.5.5.5/themes/icon.css" type="text/css"/>
</head>
<body>
	<form id="add">
		<table border="1">
			<tr>
				<th>时间</th>
				<td>
				<input class="easyui-datebox" name="date" data-options="required:true"></input>
				</td>
			</tr>
			<tr>
				<th>学校</th>
				<td><input id="school" name="school" data-options="required:true"/>
				</td>
			</tr>
			<tr>
				<th>预计人数</th>
				<td><input id="student_predict"  class="easyui-textbox" name="student_predict" data-options="required:true"/>
				</td>
			</tr>
			<tr>
				<th>现场人员</th>
				<td><input id="scene_person" name="scene_person" data-options="required:true"/>
				</td>
			</tr>
			<tr>
				<th>远程人员</th>
				<td><input id="back_person" name="back_person" data-options="required:true"/>
				</td>
			</tr>
			
		</table>
	</form>
	<a class="easyui-linkbutton" onclick="submitForm()" style="margin-left:30px;">提交</a>
<style>
		#add {
			padding: 8px;
		}
		
		#add table {
			width: 100%;
			border-collapse: collapse;
			border-color: #D4D4D4;
			border: 0px;
		}
		
		#add table tr {
			height: 30px;
			line-height: 30px;
		}
		
		#add table th {
			text-align: center;
			color: #777;
			width: 100px;
		}
		
		#add table td {
			padding: 4px 10px;
		}
		
		.isshow {
			position: relative;
			top: -6px;
			outline: none;
		}
		
		.show {
			position: relative;
			top: 1px;
		}
		
		.textbox {
			height: 20px;
			line-height: 20px;
			padding: 2px;
			outline: none;
		}
		
		.textbox-addon {
			position: absolute;
			top: 2px;
		}
		
		#submit {
			margin-left: 30px;
		}
		
		input[name=display] {
			position: relative;
			top: 4px;
		}
</style>
<script>
　　$.ajax({
　　　　url : "http://132.230.104.158:8090/getSchoolName",
　　　　dataType : "jsonp",
	　　complete : function(data){
			if(data.status==200){
				data=data.responseJSON;
				//var persons=eval ("(" + data["persons"] + ")");
				var persons=JSON.parse(data["persons"]);
				var schools=JSON.parse(data["schools"]);
				console.info(persons);
				console.info(schools);
				$('#scene_person').combobox({
                       data: persons,                        
                       textField: 'text',
                       valueField: 'text'}); 
				$('#back_person').combobox({
                       data: persons,                        
                       textField: 'text',
                       valueField: 'text'}); 
				$('#school').combobox({
                       data: schools,                        
                       textField: 'text',
                       valueField: 'text'});  
			}else{
				alert("获取下拉列表失败");
				console.info('----- ----------获取下拉列表失败------------------');
			}
	　　}
	});

  function submitForm(){
	  $('#add').form('submit', {
		    url:"http://132.230.104.158:8090/insertCalendarByForm",
		    success: function(){
		    	alert("添加成功");
		    	$('#add').form('clear');
			}
		});
  }
 
   
</script>
</body>
</html>