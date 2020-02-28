<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
.divcss{ border:1px solid #F00; width:750px; } 
</style>
<script type="text/javascript">
	function changePage(){
		var currPage = document.getElementById("currpage").value;
		window.self.location = "${pageContext.request.contextPath}/servlet/ViewMessageServlet?currPage="+currPage;
	}
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="head.jsp" />

<div align="left" >
<div align="left" class="divcss">
<c:forEach items="${pagebean.list }" var="m">

		<table border="0" width="750" align="center" cellpadding="0">
		<tr>
				<td colspan="2">
					用户: ${m.user.name }&nbsp;|&nbsp;
					时间: <fmt:formatDate  type="both" value="${m.createTime }"/>&nbsp;|&nbsp;
					联系方式: <a href="mailto:${m.user.email }">${m.user.email }</a>
				</td>
			</tr>
			<tr>
				<td height="22">
					<font>标题: ${m.title }</font>
				</td>
				<td align="right">
					<c:if test="${!empty admin }">
						<c:if test="${empty m.revert }">
							<a href="${pageContext.request.contextPath}/servlet/AdminRevertServlet?id=${m.id }">回复</a>
						</c:if>
							<a href="${pageContext.request.contextPath}/servlet/DeleteMessageServlet?id=${m.id }">刪除</a>
					</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div>${m.content }</div>
				</td>
			</tr>
			<c:if test="${!empty m.revert.content }">
				<tr>
					<td colspan="2">
						<div align="center">
							<table border="0" cellpadding="1" cellspacing="1" width="690">
								<tr>
									<td align="left" valign="middle">
										<img src="sucai/admin.jpg" width="13" height="18">
										<font>商家回复:</font>
									</td>
									<td align="center">
										<c:if test="${!empty admin }">
											<a href="${pageContext.request.contextPath}/servlet/AdminRevertServlet?id=${m.id }">修改</a>
										</c:if>
									</td>
								</tr>
								<tr>
									<td colspan="2" height="2"></td>
								</tr>
								<tr>
									<td colspan="2">${m.revert.content }</td>
								</tr>
								<tr>
									<td colspan="2" align="right">回复时间:
									 <fmt:formatDate  type="both" value="${m.createTime }"/>								
									</td>
								</tr>
							</table>
						</div>
					</td>
			</c:if>
		</table>
<br/>
</c:forEach>
</div>
</div>
	<table  border="0" width="750" align="left">
		<tr>
			<td align="center">
				总记录数: ${pagebean.totalRecords }
				当前${pagebean.currPage }/${pagebean.totalPage }页
				<a href="${pageContext.request.contextPath}/servlet/ViewMessageServlet?currPage=${pagebean.previousPage }">上一页</a>
				<a href="${pageContext.request.contextPath}/servlet/ViewMessageServlet?currPage=${pagebean.nextPage }">下一页</a>
				<select id="currpage" onchange="changePage()">
					<c:forEach begin="1" end = "${pagebean.totalPage }" varStatus="vs">
						<c:choose>
							<c:when test="${pagebean.currPage ne vs.count }">
								<option value="${vs.count }" >
									第${vs.count }页
								</option>
							</c:when>
							<c:otherwise>
								<option value="${vs.count }" selected="selected">
									第${vs.count }页
								</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					</select>
			</td>
			<td>
				<a href="${pageContext.request.contextPath}/writemessage.jsp">我要留言</a>
			</td>
		</tr>
	</table>
</body>
</html>