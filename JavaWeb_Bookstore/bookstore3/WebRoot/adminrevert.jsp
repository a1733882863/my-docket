<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%@ include file="head.jsp" %>
	<table border="0" width="750" align="center" cellpadding="1" cellspacing="1">
		<tr>
			<td colspan="2" align="center" height="32">
				<font>回复留言</font>
			</td>
		</tr>
		<tr>
			<td height="10"></td>
		</tr>
		<tr>
			<td align="right">
				标题
			</td>
			<td>
				&nbsp;${message.title }
			</td>
		</tr>
		<tr>
			<td align="right">内容</td>
			<td>
				&nbsp;${message.content }
			</td>
		</tr>
		<tr>
			<td >
				<b>用户:</b>
			</td>
			<td >
				&nbsp;${message.user.name }
			</td>
		</tr>
		<tr>
			<td>
				回复留言:
			</td>
			<td>
				<form action="${pageContext.request.contextPath}/servlet/SaveOrUpdateRevert">
				<table>
				<tr>
					<td>
						<input type="hidden" name="id" value="${message.id }">
						<textarea rows="5" cols="20" name="content">
							${message.revert.content }
						</textarea>
					</td>
					<td align="center" valign="middle">
						<input type="submit" value="回 复"/>
					</td>
					</tr>
				</table>		
				</form>
			</td>
		</tr>
	</table>
</body>
</html>