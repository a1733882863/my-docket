<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.Date,java.text.*" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="head.jsp" />	
<form id="orderForm" action="${pageContext.request.contextPath }/servlet/AddOrderServlet"  method="post">
<%
Date nowday=new Date();
SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
String time=format.format(nowday);
%>
<table cellspacing="0">
		<tr>
														<td>序号</td>
														<td>商品名称</td>
														<td>单价</td>
														<td>类别</td>
														<td>数量</td>
														<td>价格</td>

													</tr>
												
										<c:set var="count" value="0"></c:set>	
								<c:forEach items="${cart }" var="p" varStatus="vs">
												
													<tr>
														<td>${vs.count }</td>
														<td>${p.key.name }</td>
														<td>${p.key.price }</td>
														<td>${p.key.category }</td>
														<td><input name="text" type="text" value="${p.value }"
															style="width:20px" readonly="readonly" /></td>
														<td>${p.key.price*p.value }</td>
													<c:set var="count" value="${count+p.key.price*p.value }"></c:set>	
													</tr>
												
							</c:forEach>
							<tr> 
									
											<td>
												收货地址:<input name="receiverAddress" type="text" value="必填"/>
											</td>
											<td>
												电话:<input name="receiverPhone" type="text" value="必填"/>
												<input type="hidden" name="money" value="${count }"/>
											</td>
											<td><input type="hidden" name="ordertime1" value=<%=time %> /></td>
									
							</tr>
							<tr>
								<td>
								收货人:<input
														name="receiverName" type="text" value=""
														style="width:150px" />
								</td>
							</tr>
							<tr> 
											<td>
												<a href="${pageContext.request.contextPath}/index.jsp">返回首页</a>
											</td>
											<td>
												<input type="submit" value="确认订单"/>
											</td>
											<td style="color:#FF0000"> （总价：${count }元）</td>
										</tr>
										
							</table>
							</form>
</body>
</html>