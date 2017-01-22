<%@ page import= "workspace.*"%>
<%@ page import="java.util.*" %>
<%@ page language="java" pageEncoding="utf-8"%>
<jsp:directive.page import="workspace.*"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/bootstrap/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
  <head>
    <title>Web信息处理及应用课程实践</title>
  </head>
  <body>
  <div class="container">
    	<div class="row">
    	<div class="col-md-8">
  		<H4>
			今天是：<%Date today=new Date();%>
			<%=today.getYear()+1900%>年
			<%=today.getMonth()+1%>月
    		<%=today.getDate()%>号
    		<%Day2Weekday d2w = new Day2Weekday();%>
    		星期<%=d2w.trans(today.getDay()) %>
		</H4>
  	 <div>
   		<table width=600>
<tr>
  <td valign="middle">
   	<a href="index.html"><img vspace="0"hspace="0"border="0"src="img/search1_1.jpg"/></a>
  </td>
 <td height="80px">
  <table height="80px">
   	<tr>
   		<td valign="bottom"><font size="4"face="Verdana"><b>Search Engine for Sina News</b></font></td></tr>
  	<tr>
    	<td>
		    <form action="searcher.jsp"method="GET">
		   		<input type="text"name="keyword"size=36 maxlength="100">&nbsp;&nbsp;<input type="submit"value="搜索"/>
		    	<font size="2"face="宋体">
		    		<a href="advance.jsp">关于我们</a>
   				</font>
		    </form>
   		</td>
   	</tr>
  </table>
 </td>
</tr>
</table>
</div>
    <div><hr></div>
    <div class="inf">
	<%
		String keyword = request.getParameter("keyword");
		keyword = new String(keyword.getBytes("ISO8859-1"),"UTF-8");
		QuerySearcher mySearcher = new QuerySearcher();
		int pageSize = 10;
		int currentPage;
		if (request.getParameter("page") == null){
			currentPage = 1;
		}
		else currentPage = Integer.valueOf(request.getParameter("page"));
		int maxResult = 100;
	 	/* SpellCheck checker = new SpellCheck();
	 	String correct = checker.search(keyword); */
	 	Result results = mySearcher.search(keyword, maxResult);
	 	for (int i = (currentPage - 1) * pageSize; i < currentPage * pageSize; i++) {
	 		%>
	 		<a href="<% out.println(results.urls.get(i));%>" target="_blank"><% out.println(results.titles.get(i));%></a><br/>
	 		<span style="font-family:verdana; color:grey; font-size:12px">新闻日期:<%out.println(results.publishTimes.get(i));%></span><br/>
	 		<%
	 		out.println(results.contents.get(i));
	 		out.write("<br/>");
	 		out.write("<br/>");
	 	}
	 	/*for (int i=0; i<results.totalDocs; i++){
	 		%>
	 		<a href="<% out.println(results.urls.get(i));%>" target="_blank"><% out.println(results.titles.get(i));%></a><br/>
	 		<span style="font-family:verdana; color:grey; font-size:12px">新闻日期:<%out.println(results.publishTimes.get(i));%></span><br/>
	 		<%
	 		out.println(results.contents.get(i));
	 		out.write("<br/>");
	 	}*/
	%>
	<%
		// out.print("这里将会存放搜索结果信息与链接");
	%>
     </div>
     <br/>
     <br/>
     <div id="wrapper" style="text-align: center">
	     <div id="page" style="display: inline-block;">
	     	
	     	<%
	     	final int SHOWING_BESIDES_NUM = 1;
	     	final int SHOWING_BEGINNING_NUM = 1;
	     	final String HOST_NAME = "http://localhost:8080/S_E_by_Jeeping/searcher.jsp";
	     	
	     	if (currentPage > 2) {%>
	     	<%
	     	}
	     	if (currentPage > 1) {%>
	     		<a href="<% out.println(HOST_NAME + "?keyword=" + keyword + "&page=1"); %>"> &lt;&lt; </a>
	     		<a href="<% out.println(HOST_NAME + "?keyword=" + keyword + "&page=" + (currentPage - 1)); %>"> &lt;-- </a>
	     	<%
	     	}
	     	out.println(" " + currentPage + " ");
	     	if (currentPage < (maxResult + pageSize - 1) / pageSize) {%>
	     		<a href="<% out.println(HOST_NAME + "?keyword=" + keyword + "&page=" + (currentPage + 1)); %>"> --&gt; </a>
	     		<a href="<% out.println(HOST_NAME + "?keyword=" + keyword + "&page=" + ((maxResult + pageSize - 1) / pageSize)); %>"> &gt;&gt; </a>
	     	<%
	     	}
	     	%>
	     	
	     	
	      </div>
      </div>
      <br/>
      <br/>
      <%--显示结果 --%>
      		</div>
     	</div>
  	</body>	
</html>
