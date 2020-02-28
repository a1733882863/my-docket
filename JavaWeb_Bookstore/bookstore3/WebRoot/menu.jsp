<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="${pageContext.request.contextPath}/servlet/PageServlet?category=数学">数学</a>
	<a href="${pageContext.request.contextPath}/servlet/PageServlet?category=英语">英语</a>
	<a href="${pageContext.request.contextPath}/servlet/PageServlet?category=政治">政治</a>
	<a href="${pageContext.request.contextPath}/servlet/PageServlet?category=医学">医学</a>
	<a href="${pageContext.request.contextPath}/servlet/PageServlet?category=计算机">计算机</a>
	<a href="${pageContext.request.contextPath}/servlet/PageServlet?category=建筑学">建筑学</a>
	<a href="${pageContext.request.contextPath}/servlet/PageServlet?category=心理学">心理学</a>
	<a href="${pageContext.request.contextPath}/servlet/PageServlet?category=经济学">经济学</a>
	<a href="${pageContext.request.contextPath}/servlet/PageServlet" >全部图书</a>
	<jsp:include page="search.jsp" />
</body>
</html>