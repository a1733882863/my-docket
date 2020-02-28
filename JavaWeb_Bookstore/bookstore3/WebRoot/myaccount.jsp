<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="head.jsp" %>
<table>
<tr>
<td><a href="${pageContext.request.contextPath }/modified.jsp">修改用户信息</a></td><td><a href="${pageContext.request.contextPath }/servlet/FindOrderServlet">历史订单</a></td><td><a href="${pageContext.request.contextPath }/deleteuser.jsp">删除账户</a></td>
</tr>

</table>
</body>
</html>