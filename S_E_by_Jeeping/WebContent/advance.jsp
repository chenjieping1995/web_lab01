<%@ page import="java.util.*" %>
<%@ page import="workspace.*" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>本搜索引擎相关</title>
	</head>
	<body>
		<H5>
			今天是：<%Date today=new Date();%>
			<%=today.getYear()+1900%>年
			<%=today.getMonth()+1%>月
    		<%=today.getDate()%>号
    		<%Day2Weekday d2w = new Day2Weekday();%>
    		星期<%=d2w.trans(today.getDay()) %>
		</H5>
		<div><hr style="FILTER: progid:DXImageTransform.Microsoft.Shadow(color:#987cb9,direction:145,strength:15)" width="100%" color=#987cb9 SIZE=1></div>
		<p>
			<b>本搜索引擎开发者：陈介平</b>
		</p>
		<p>
			<b>——现为中国科学技术大学计算机科学与技术学院大三本科生。</b>
		</p>
		<p>
			<b>完成时间：2016.12.17</b>
		</p>
		<p>特别鸣谢：冯彬、张建浩同学在jsp编程方面给予的帮助</p>
	</body>
</html>