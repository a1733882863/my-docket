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
<form action="${pageContext.request.contextPath }/servlet/CreatePurseServlet" method="post">
正在开通钱包 初始余额 :<input type="text" readonly="readonly" name="money" value = "0"/>
	<input type="submit" value = "确认开通"/>
</form>
</body>
</html>