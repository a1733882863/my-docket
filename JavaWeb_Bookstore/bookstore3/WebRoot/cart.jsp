<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	function changeNum(id,num,totalCount){
		num = parseInt(num);
		totalCount =parseInt(totalCount);
		
		if(num<1){
				num = 0;
		}
		
		if(num>totalCount){
			alert("购买数量不能大于库存数量！");
			num=totalCount;
		}
		location.href="${pageContext.request.contextPath}/servlet/BookNumberServlet?id="+id+"&num="+num;
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>		
<jsp:include page="head.jsp" />	
								<table cellspacing="1">
											<tr>
												<td>&nbsp;&nbsp;序号&nbsp;&nbsp;</td>
												<td>&nbsp;&nbsp;商品名称&nbsp;&nbsp;</td>
												<td>&nbsp;&nbsp;单价&nbsp;&nbsp;</td>
												<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数量&nbsp;&nbsp;</td>
												<td>&nbsp;&nbsp;库存&nbsp;&nbsp;</td>
												<td>&nbsp;&nbsp;价格&nbsp;&nbsp;</td>
												<td>&nbsp;&nbsp;删除&nbsp;&nbsp;</td>
											</tr>
										 <c:set var="sum" value="0"></c:set> 
										<c:forEach items="${cart }" var="entry" varStatus="vs">
												<tr>
													<td>${vs.count }</td>
													<td>${entry.key.name }</td>

													<td>${entry.key.price }</td>
													<td><input type="button" value='-'
														style="width:20px"
														onclick="changeNum('${entry.key.id}','${entry.value-1 }','${entry.key.pnum }')">

														<input name="text" type="text" value="${entry.value }"
														style="width:40px;text-align:center" /> <input
														type="button" value='+' style="width:20px"
														onclick="changeNum('${entry.key.id}','${entry.value+1 }','${entry.key.pnum }')">

													</td>
													<td>${entry.key.pnum }</td>
													<td >&nbsp;&nbsp;&nbsp;&nbsp;${entry.value*entry.key.price }</td>

													<td>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/servlet/BookNumberServlet?id=${entry.key.id}&num=0"
														style="color:#FF0000">X</a>
													</td>
												</tr>
											<c:set var="sum" value="${sum+entry.value*entry.key.price }">
											</c:set>
										</c:forEach>
										</table>
										<table>
										<tr> 
											<td>
												<a href="${pageContext.request.contextPath}/index.jsp">返回首页
											</td>
											<td>
												<a href="${pageContext.request.contextPath}/order.jsp">提交订单</a>
											</td>
											<td style="color:#FF0000"> （总价：${sum }元）</td>
										</tr>
										</table>
							
</body>
</html>