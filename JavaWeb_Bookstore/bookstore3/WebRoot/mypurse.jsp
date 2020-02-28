<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
.divcss{ border:1px solid #F00; width:750px; } 
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="head.jsp" />
	<c:if test="${ empty purse }">	<a href="${pageContext.request.contextPath }/createmypurse.jsp">开通钱包功能</a>
</c:if>
<c:if test="${ not empty purse }">欢迎你:${user.name }
&nbsp;&nbsp;&nbsp;你的余额为:<font color="red">${purse.money }</font><br/>
 <a href="${pageContext.request.contextPath }/recharge.jsp">余额充值</a>
</c:if>
</body>
</html>