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
	<table cellspacing="0" class="booklist">
		<tr>
			<c:forEach items="${favoriteitems }" var="b">
				<td>

					<div >
						<p>
							<a href="${pageContext.request.contextPath }/servlet/BookInfoServlet?id=${b.pid}"><img
								src="${pageContext.request.contextPath }/bookimg/${b.img_url}"
								width="154" height="216" border="0" /> </a>
						</p>
					</div>

					<div>
						<a
							href="${pageContext.request.contextPath }/servlet/BookInfoServlet?id=${b.pid}">
							书名:${b.name}<br/>
						</a>
					</div>
					<div>
					<font style=""><a href="${pageContext.request.contextPath }/servlet/UnFavoriteServlet?id=${b.id}">取消收藏</a></font>
					</div>
				</td>
			</c:forEach>
		</tr>
	</table>
</body>
</html>