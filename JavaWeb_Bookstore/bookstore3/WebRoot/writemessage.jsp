<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 <script type="text/javascript">
      function myCheck()
      {
        for(var i=0;i<document.form1.elements.length-1;i++)
        {
         if(document.form1.elements[i].value=="")
         {
           alert("当前表单不能有空项");
           document.form1.elements[i].focus();
           return false;
         }
        }
        return true;
        
      }
    </script>
</head>
<body>
<jsp:include page="head.jsp" />	
<c:choose>
	<c:when test="${empty user }">
	<jsp:include page="login.jsp" />
	</c:when>
<c:otherwise>
<form name="form1" action="${pageContext.request.contextPath}/servlet/SaveMessageServlet" method="post" onSubmit="return myCheck()">
	<table border="0" width="750" align="center" cellpadding="1" cellspacing="1">
		<tr>
			<td colspan="2" align="center" height="32">
				<font>我要留言</font>
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
				<input type="text" name="title" size="30"/>
			</td>
		</tr>
		<tr>
			<td align="right">内容</td>
			<td>
				<textarea rows="8" cols="50" name="content" ></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center" height="50">
				<input type="submit" value="确认"/> 
			</td>
		</tr>
	</table>
</form>
</c:otherwise>
</c:choose>
</body>
</html>