<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="head.jsp" />	
	<form action="http://localhost:8080/bookstore3/servlet/PayServlet">
		<table cellspacing="0">
			<tr>
				<td>
					<table >
						<tr>
							<td>
								<p>订单编号:${order1.id }</p>
							</td>
						</tr>
						<tr>
							<td>
								<table cellspacing="1">
									<tr>
										<td>序号&nbsp;&nbsp;&nbsp;</td>
										<td>商品名称&nbsp;&nbsp;&nbsp;</td>
										<td>价格&nbsp;&nbsp;&nbsp;</td>
										<td>数量&nbsp;&nbsp;&nbsp;</td>
										<td>小计</td>

									</tr>


									<c:forEach items="${order1.orderitem }" var="entry"
										varStatus="vs">

										<tr>
											<td>&nbsp;&nbsp;&nbsp;${vs.count }</td>

											<td>&nbsp;&nbsp;&nbsp;&nbsp;${entry.product.name }</td>
											<td>${entry.product.price }</td>
											<td>&nbsp;&nbsp;&nbsp;${entry.buynum }</td>
											<td>&nbsp;&nbsp;${entry.buynum * entry.product.price }</td>

										</tr>

										<c:set var="sum"
											value="${sum+entry.buynum * entry.product.price }">
										</c:set>
									</c:forEach>

								</table>
								<table cellspacing="1">
									<tr>
										<td style="text-align:right; padding-right:40px;"><font
											style="color:#FF0000">合计：&nbsp;&nbsp;${sum}</font></td>
									</tr>
								</table>

								<p>
									收货地址：${order1.receiverAddress }&nbsp;&nbsp;&nbsp;&nbsp;<br />
									收货人：&nbsp;&nbsp;&nbsp;&nbsp;${order1.receiverName }&nbsp;&nbsp;&nbsp;&nbsp;<br />
									联系方式：${order1.receiverPhone }&nbsp;&nbsp;&nbsp;&nbsp;

								</p>
								<p style="text-align:right">
									<font style="color:#FF0000"> </font>
								</p>
							</td>
						</tr>
					</table>
				
			</tr>
			<tr>
				<td><input type="hidden" name="orderid123"
					value="${order1.id }" /></td>
			</tr>
			<tr>
				<td><input type="hidden" name="sum123" value="${sum }" /></td>
			</tr>
		</table>
		<input type="submit" value="点击付款">
		<%-- ${order1.paystate eq 0 ?"<a href='http://localhost:8080/bookstore3/servlet/PayServlet'> 点击去付款</a>":" 你已经付款了" } --%>
	</form>

</body>
</html>