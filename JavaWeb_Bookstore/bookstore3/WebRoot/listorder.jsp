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
<table width="100%" border="0" cellspacing="0" >
									<tr>
										<td  >订单号</td>
										<td  >收货人</td>
										<td  >订单时间</td>
										<td  >状态</td>
										<td  >操作</td>
									</tr>

						<c:forEach items="${orders }" var="order" varStatus="vs">
								
									<tr>
										<td class="tableopentd02">${vs.count }</td>

										<td class="tableopentd02">${order.receiverName }</td>
										<td class="tableopentd02">${order.ordertime }</td>
										<td class="tableopentd02">${order.paystate==0? "未支付":"已支付"}</td>
										<td class="tableopentd03"><a href="${pageContext.request.contextPath }/servlet/FindOrderItems?orderid=${order.id}">查看</a>&nbsp;&nbsp;
											<a href="${pageContext.request.contextPath }/servlet/DeleteOrderServlet?orderid=${order.id}">刪除</a>
										</td>
									</tr>
						</c:forEach>		
								</table>
</body>
</html>