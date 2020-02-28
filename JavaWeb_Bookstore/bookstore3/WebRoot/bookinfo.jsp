<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
var interval;

function startSecond() {

	interval = window.setInterval("changeSecond()", 1000);

};

function changeSecond() {
	var second = document.getElementById("second");

	var svalue = second.innerHTML;

	svalue = svalue - 1;

	if (svalue == 0) {
		window.clearInterval(interval);
		location.href = "index.jsp";
		return;
	}

	second.innerHTML = svalue;
}

//获取XMLHttpRequest对象
function getXMLHttpRequest() {
	var xmlhttp;
	if (window.XMLHttpRequest) {// code for all new browsers
		xmlhttp = new XMLHttpRequest();
	} else if (window.ActiveXObject) {// code for IE5 and IE6
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}

	return xmlhttp;

}
</script>
<script type="text/javascript">
	window.onload = function(){
		//得到搜索框对象
		var searchElement = document.getElementById("name");
		//得到DIV元素
		var div = document.getElementById("name");
		//var div=document.getElementById("context1");
		//searchElement.onkeyup = function(){//给文件框注册按键弹起事件
		searchElement.onclick=function(){
		var xhr=getXMLHttpRequest();
		//处理结果
		xhr.onreadystatechange=function(){
			//if(xhr.readyState==4&&xhr.status==200){
			if(xhr.readyState==4){
				if(xhr.status==200){
				var str = xhr.responseText;//得到服务器返回数据
				var childDivs="";
				if(str=="yes"){
				childDivs+="<font>"+"你已收藏该商品,无需再次收藏"+"</font>"
				}else if( str=="no" ){
					childDivs+="<font color='red'>"+"已收藏"+"</font>"
				}
					
			
				div.innerHTML=childDivs;
			}
		}
		}
		xhr.open("get","${pageContext.request.contextPath}/servlet/FavoriteServlet?id="+${b.id}
		+"&time="+new Date().getTime());
		xhr.send(null);
	}
}
 function changeBackground_over(div){
 	div.style.backgroundColor = "gray";
 }
 function changeBackground_out(div){
		div.style.backgroundColor = "";
	}
	//填充文本到搜索框
	function writeText(div){
		//得到搜索框
		var searchElement = document.getElementById("name");
		searchElement.value = div.innerHTML;//把div中的文本添加到搜索框中
		div.parentNode.style.display="none";//把context1的div隐藏
	}
</script>
<body>
<%@ include file="head.jsp" %>
	<table cellspacing="0">
		<tr>
			<td><img
				src="${pageContext.request.contextPath }/bookimg/${b.img_url}"
				width="154" height="216" border="0" /></td>
			<td>
				<table width="100%%" border="0" cellspacing="0">
					<tr>
						<td><font>书名：${b.name }</font></td>
					</tr>
					<tr>
						<td>售价：<font color="#FF0000">￥:${b.price }元</font><br />
						</td>
					</tr>
					<tr>
						<td>类别：${b.category }</td>
					</tr>
					<tr>
						<td><p>
								<b>内容简介：</b>
							</p>${b.description }</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td><font color="red"><a
					href="${pageContext.request.contextPath }/servlet/AddCartServlet?id=${b.id}"><h3>购买</h3></a></font>
			</td>
			<td><a
					href="${pageContext.request.contextPath }/servlet/PageServlet?currentPage=1"><h3>返回</h3></a></td>
					<td><div id="name" name="name"><input  type="button"  value="收藏" /> </div>
			</td>
		</tr>
	</table>
</body>
</html>