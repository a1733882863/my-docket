<%@ page language="java" import="java.util.*,java.net.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'userLog.jsp' starting page</title>
	
  </head>
 
  <body>
 <%      String name1="";
		String password1="";
 		Cookie cookie=null;
		Cookie [] cookies =request.getCookies();
		for(int i =0;cookies!=null && i<cookies.length;i++){
			if("user".equals(cookies[i].getName())){
				cookie=cookies[i];
			}
		}
		if(cookie!=null){
		String value =cookie.getValue();
		String[] values=value.split("&");
		  name1=values[0];
		 password1=values[1]; 
		}
		
		
		 %>
  
  		

${msg } 
	<form action="${pageContext.request.contextPath }/servlet/LogServlet" method="post">
	<table>
	<tr><td>用户名或邮箱登陆：</td><td><input type="text" name="name" value="<%=name1 %>"/></td><td> ${error1 } </td></tr>
	<tr><td>密码：</td><td><input type="password" name="password" value="<%=password1 %>"/></td><td>${error2 }</td></tr>	
	<tr><td><input type="checkbox" name="autolog" />记住密码<br></td></tr>	
	<tr><td><a href="${pageContext.request.contextPath }/register.jsp">注册</a></td><td><input type="submit" value="登陆"/></td></tr>
		
	</table>
	</form>
  </body>
</html>
