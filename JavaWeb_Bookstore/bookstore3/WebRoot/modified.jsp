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
<form action="${pageContext.request.contextPath }/servlet/ModifiedUser" method="post">
									<input type="hidden" name="id" value="${user.id }"/>
									<input type="hidden" name="state" value="${user.state }"/>
									<table width="100%" border="0" cellspacing="2" class="upline">
										<tr>
											<td style="text-align:right; width:20%">用户邮箱：</td>
											<td style="width:40%; padding-left:20px">${user.email }</td>
											<td>&nbsp;</td>


										</tr>
										<tr>
											<td style="text-align:right">用户名：</td>
											<td style="padding-left:20px">${user.name }</td>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td style="text-align:right">修改密码：</td>
											<td><input type="password" name="password"
												class="textinput" /></td>
											<td><font color="#999999">密码设置至少6位，请区分大小写</font></td>
										</tr>
										<tr>
											<td style="text-align:right">确认密码：</td>
											<td><input type="password" class="textinput" /></td>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td style="text-align:right">性别：</td>
											<td colspan="2">&nbsp;&nbsp;<input type="radio"
												name="gender" value="男" ${user.gender== "男"? "checked='checked' ":"" } />
												男 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
												type="radio" name="gender" value="女" ${user.gender==
												"女"? "checked='checked' ":"" } /> 女</td>
										</tr>
										<tr>
											<td style="text-align:right">联系方式：</td>
											<td colspan="2"><input name="telephone" type="text"
												value="${user.telephone }" class="textinput" /></td>
										</tr>

										<tr>
											<td style="text-align:right">&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
										</tr>
									</table>
									<table width="70%" border="0" cellspacing="0">
							<tr>
								<td style="padding-top:20px; text-align:center"><input 
								type="submit"	 name="submit" border="0" value="提交">
								</td>
							</tr>
						</table>

</form>
</body>
</html>