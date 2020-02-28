<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function addProduct(id) {
		location.href = "${pageContext.request.contextPath}/admin/products/add.jsp";
	}
	function delBook(id,name){
		if(confirm("是否确定删除《"+name+"》这本书?")){
			location.href="${pageContext.request.contextPath}/servlet/DeleteBookServlet?productid="+id;
		}
	}
	//全选和全不选的

	function checkAll(){
	
		var flag = document.getElementById("ckAll").checked;
			
		var ids = document.getElementsByName("ids")//name是elements
		
		for(var i=0;i < ids.length;i++){
			ids[i].checked =flag;
		}
	}
	function delAllBook(){
		//得到所有选中的复选框元素
		 var ids = document.getElementsByName("ids");
		var str ="";
		for(var i = 0;i<ids.length;i++){
			if(ids[i].checked){//如果被选中
				str+="ids="+ids[i].value+"&";
			}
		}
		str=str.substring(0,str.length-1);
		//alert(str);
		if(str!=""){location.href="${pageContext.request.contextPath}/servlet/DeleteAllBookServlet?"+str;}
		 
		var form2 = document.getElementById("form2");
		/* var ids = document.getElementsByName("ids")
		var form2 = document.forms[1];//和上面一样 */
		form.action="${pageContext.request.contextPath}/servlet/DeleteAllBookServlet";//若当前表单没有action值，这个就相当于赋值
		/* var i=ids.length;
		if(i>0){
		form2.submit();//相当点击submit按钮
		} */
	}
</script>
</HEAD>
<body>
		<table cellSpacing="1" cellPadding="0" width="100%" align="center"
			 border="0">
			<TBODY>
				<tr>
					<td  align="center" ><strong>图书列表</strong>
					</TD>
				</tr>
				<tr>
					<td class="ta_01" align="left">
						<button type="button" id="del" name="del" value="批量删除"
							class="button_add" onclick="delAllBook()">批量删除
						</button>
					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center">
					<form action="${pageContext.request.contextPath}/servlet/delAllBookServlet" method="post">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse;  WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; ">
								<td align="center" width="5%"><input type="checkbox"  id="ckAll" onclick="checkAll()"/>全选/全不选</td>
								<td align="center" width="18%">图书编号</td>
								<td align="center" width="18%">图书名称</td>
								<td align="center" width="9%">图书价格</td>
								<td align="center" width="9%">图书数量</td>
								<td width="8%" align="center">图书类别</td>
								<td width="8%" align="center">编辑</td>

								<td width="8%" align="center">删除</td>
							</tr>

			<c:forEach items="${pb.products }" var="b">							
								<tr  >
									<td align="center" width="5%"><input type="checkbox"  name="ids" value="${b.id }"/></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="23"> ${b.id }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="18%">${b.name }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="8%">${b.price }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="8%">${b.pnum }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"> ${b.category }</td>
									<td align="center" style="HEIGHT: 22px" width="7%"><a
										href="${pageContext.request.contextPath}/servlet/FindBookByIdServlet?id=${b.id }">
											<img
											src="${pageContext.request.contextPath}/admin/timg.png" width="16" height="16" border="0"
											border="0" style="CURSOR: hand"> </a>
									</td>

									<td align="center" style="HEIGHT: 22px" width="7%"><a
										href="javascript:delBook('${b.id }','${b.name }')">
										<font color="red">×</font>
									</a>
									</td>
								</tr>
					</c:forEach>	
						</table>
						</form>
					</td>
				</tr>
			</TBODY>
			
		</table>
<div class="pagination">
		<a href="${pageContext.request.contextPath  }/servlet/BookListServlet?currentPage=${pb.currentPage==1?1:pb.currentPage-1}&category=${pb.category}">上一页</a>
			第${pb.currentPage }页/共${pb.totalPage }页 
		<a href="${pageContext.request.contextPath  }/servlet/BookListServlet?currentPage=${pb.currentPage==pb.totalPage?pb.totalPage:pb.currentPage+1}&category=${pb.category}">下一页</a>
	</div>
</body>
</HTML>

