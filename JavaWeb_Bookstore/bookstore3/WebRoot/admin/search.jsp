<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table  cellPadding="0" width="100%" align="center"
			 border="0">
				<tr>
					<td  align="center" ><strong>查
							询 条 件</strong>
					</td>
				</tr>
				<tr>
					<td>
					<form id="Form1" name="Form1" action="${pageContext.request.contextPath}/servlet/SearchBookServlet" method="post">
						<table cellpadding="5" cellspacing="1" border="0" width="100%">
							<tr>
								<td height="22" align="center" >
									图书编号</td>
								<td ><input type="text"
									name="id" size="15" value="" id="Form1_userName" class="bg" />
								</td>
								</tr>
								<tr>
								<td height="22" align="center">
									类别：</td>
								<td ><select name="category"
									id="category">
										<option value="" selected="selected">--图书类别--</option>
						<option value="数学">数学</option>
						<option value="英语">英语</option>
						<option value="政治">政治</option>
						<option value="医学">医学</option>
						<option value="计算机">计算机</option>
						<option value="建筑学">建筑学</option>
						<option value="建筑学">心理学</option>
						<option value="经济学">经济学</option>
								</select></td>
							</tr>

							<tr>
								<td height="22" align="center" >
									图书名称：</td>
								<td ><input type="text"
									name="name" size="15" value="" id="Form1_userName" class="bg" />
								</td>
								</tr>
								<tr>
								<td height="22" align="center" >
									价格区间(元)：</td>
								<td><input type="text"
									name="minprice" size="10" value="" />- <input type="text"
									name="maxprice" size="10" value="" /></td>
							</tr>

							<tr>
								<td align="center" >
									<input type="submit" id="search" name="search"
										value="查找" >
										</input>
								</td>
							</tr>
						</table>
				</form>
				</td>
				</tr>
				</table>
</body>
</html>