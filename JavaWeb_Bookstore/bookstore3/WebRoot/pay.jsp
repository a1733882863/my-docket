<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="head.jsp" />	
<form action="${pageContext.request.contextPath }/servlet/CallBackServlet" method="get">
		订单号：<INPUT TYPE="text" readonly="readonly" NAME="orderid" value="${order }"> 
		支付金额：<INPUT	TYPE="text" readonly="readonly" NAME="money" value="${money }">元
		<div class="divBank">
			<div class="divText">选择付款方式</div>
			<div style="margin-left: 20px;">
						<input
						id="余额" type="radio" name="yue" value="yue" /> 
						余额付款
			</div>
			<div style="margin: 40px;">
				<INPUT TYPE="submit" value="确定支付">
			</div>
		</div>
	</form>
</body>
</html>