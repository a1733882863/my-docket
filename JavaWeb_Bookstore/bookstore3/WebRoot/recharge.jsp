<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="Utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="head.jsp" />	
<form action="${pageContext.request.contextPath }/servlet/RechargeServlet" method="post">
你想要多少钱都行 :<input type="text"  name="money" value = "0"/>
	<input type="submit" value = "确认充值"/>
</form>
</body>
</html>