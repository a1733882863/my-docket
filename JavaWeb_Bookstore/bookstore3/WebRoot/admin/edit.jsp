<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</HEAD>
<script type="text/javascript">
	//设置类别的默认值
	function setProductCategory(t) {
		var category = document.getElementById("category");//得到下拉列表

		var ops = category.options;//得到下拉列表中的所有option标签数组
		for ( var i = 0; i < ops.length; i++) {

			if (ops[i].value == t) {//判断哪一个option选项中的value值与t(当前书的类名称)相等
				ops[i].selected = true;//相等则把selected=selected加上
				return;
			}
		}

	};
</script>
<body >
	<form id="userAction_save_do" name="Form1"
		action="${pageContext.request.contextPath }/servlet/UpdateBookServlet"  enctype="multipart/form-data" method="post">
	
	<input type="hidden" name="id" value="${book.id }"/>
		<table cellSpacing="1" cellPadding="5" width="100%" align="center" border="0">
			<tr>
				<td  align="center"  colSpan="4" height="26"><strong><STRONG>编辑图书</STRONG> </strong></td>
			</tr>


			<tr>
				<td align="center" >图书名称：</td>
				<td ><input type="text" name="name"  value="${book.name }" /></td>
			</tr>
			<tr>
				<td align="center" >图书价格：</td>
				<td ><input type="text"	name="price" class="bg"   value="${book.price }"/></td>
			</tr>
			<tr>
				<td align="center" >图书数量：</td>
				<td ><input type="text"	name="pnum" class="bg"  value="${book.pnum }" /></td>
			</tr>
			<tr>
				<td align="center" >图书类别：</td>
				<td ><select name="category" id="category">
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


			
			<TR>
				<TD  align="center" >图书描述：</TD>
				<TD   colSpan="3"><textarea
						name="description" cols="30" rows="3" style="WIDTH: 35.5%"> ${book.description }</textarea>
				</TD>
			</TR>
			
<tr>
				<td align="center">图书封面：</td>
				<td  colSpan="3"><input	type="file" name="img_url" size="30" value="${book.img_url }" /></td>
			</tr>

			<tr>
				<td  style="WIDTH: 100%" align="center"
					 colSpan="4"><input type="submit"
					class="button_ok" value="确定"> <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
 			<INPUT
					class="button_ok" type="button" onclick="history.go(-1)" value="返回" />
					<span id="Label1"> </span></td>
			</tr>
		</table>
	</form>




</body>
</HTML>