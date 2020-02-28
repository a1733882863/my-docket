<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form id="userAction_save_do" name="Form1"
		action="${pageContext.request.contextPath }/servlet/BookAddServlet" enctype="multipart/form-data" method="post">
		&nbsp;
		<table cellSpacing="1" cellPadding="5" width="100%" align="center"
			  border="0">
			<tr>
				<td  align="center" colSpan="4"
					height="26"><strong><STRONG>添加图书</STRONG> </strong>
				</td>
			</tr>


			<tr>
				<td align="center"  >图书名称：</td>
				<td class="ta_01" ><input type="text"
					name="name" />
				</td>
			</tr>
			<tr>
				<td align="center" >图书价格：</td>
				<td ><input type="text"
					name="price" 
					 />
				</td>
			</tr>
			<tr>
				<td align="center"  class="ta_01">图书数量：</td>
				<td ><input type="text"
					name="pnum" 
					class="bg" />
				</td>
				</tr>
				<tr>
				<td align="center" >图书类别：</td>
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
					</select>
				</td>
				</tr>

			
			<TR>
				<TD class="ta_01" align="center" >图书描述：</TD>
				<TD class="ta_01"  colSpan="3">
				<textarea
						name="description" cols="30" rows="3" 
						style="WIDTH: 35.5%"></textarea>
				</TD>
			</TR>
				<tr>
					<td align="center" >图书图片：</td>
					<td >
					<input type="file" name="img_url" size="30" value=""/>
				</td>
			</tr>

			<tr>
				<td class="ta_01" style="WIDTH: 100%" align="center"
					 colSpan="4">
					
					
						
					<input type="submit" align="left" value="确定">		
					<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT> <INPUT
					 type="button" onclick="history.go(-1)" value="返回" />
					<span id="Label1">
					
					</span>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>