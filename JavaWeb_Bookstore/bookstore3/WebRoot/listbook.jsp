<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>bookStore列表</title>
</head>
<body class="main">
	
	<table cellspacing="0" class="booklist">
		<tr>
			<c:forEach items="${pb.products }" var="b">
				<td>

					<div >
						<p>
							<a href="${pageContext.request.contextPath }/servlet/BookInfoServlet?id=${b.id}"><img
								src="${pageContext.request.contextPath }/bookimg/${b.img_url}"
								width="154" height="216" border="0" /> </a>
						</p>
					</div>

					<div>
						<a
							href="${pageContext.request.contextPath }/servlet/BookInfoServlet?id=${b.id}">
							书名:${b.name}<br/>
							售价:${b.price }
						</a>
					</div>
				</td>
			</c:forEach>
		</tr>
	</table>
	<div>
		<a href="${pageContext.request.contextPath  }/servlet/PageServlet?currentPage=${pb.currentPage==1?1:pb.currentPage-1}&category=${pb.category}">&lt;&lt;上一页</a>
			第${pb.currentPage }页/共${pb.totalPage }页 
		<a href="${pageContext.request.contextPath  }/servlet/PageServlet?currentPage=${pb.currentPage==pb.totalPage?pb.totalPage:pb.currentPage+1}&category=${pb.category}">&lt;&lt;下一页</a>
	</div>
</body>
</html>