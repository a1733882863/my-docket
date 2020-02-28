<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <style type="text/css">
  #apDiv1 {
	position:absolute;
	width:594px;
	height:50px;
	z-index:1;
}
  .LogAndReg {
	float: none;
	padding-left: 300px;
	background-color: #999;
}
  </style>
  </head>
  
<body>	

<div class="LogAndReg" id="logAndReg"><a href="${pageContext.request.contextPath }/index.jsp">返回首页</a>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/servlet/ListMyFavoriteServlet">我的收藏夹</a>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/cart.jsp">查看购物车</a>
&nbsp;&nbsp;&nbsp;&nbsp;<c:if test="${ empty user }">	<a href="${pageContext.request.contextPath }/login.jsp">登录</a>
		<a href="${pageContext.request.contextPath }/register.jsp">注册</a>
</c:if>
<c:if test="${ not empty user }">欢迎你:<a href="${pageContext.request.contextPath }/servlet/MyAccount">${user.name }</a><a href="${pageContext.request.contextPath }/servlet/LogOutServlet"> &nbsp注销</a></c:if>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/admin/index.jsp">管理员入口</a>
&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/servlet/ViewMessageServlet">查看留言板</a>
<c:if test="${ not empty user }">
&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/servlet/MyPurseServlet">我的钱包</a>
</c:if>
</div>
</body>
</html>
