<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="Utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
   <style>
		body
		{
			SCROLLBAR-ARROW-COLOR: #ffffff;  SCROLLBAR-BASE-COLOR: #dee3f7;
		}
    </style>
</head>
<frameset  frameborder=0 border="0" framespacing="0">

  <frameset cols="200,*" frameborder="0" border="0" framespacing="0">
		<frame src="${pageContext.request.contextPath}/admin/menu.jsp" name="leftFrame" noresize scrolling="no">
		<frame src="${pageContext.request.contextPath}/admin/welcom.jsp" name="mainFrame">
  </frameset>
</frameset>
</html>