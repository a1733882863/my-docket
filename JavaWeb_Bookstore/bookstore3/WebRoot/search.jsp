<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		//var div = document.getElementById("context1");
		var div=document.getElementById("context1");
		//searchElement.onkeyup = function(){//给文件框注册按键弹起事件
		searchElement.onkeyup=function(){
		var name=this.value;
		if(name.trim()==""){
				div.style.display="none";
				return;
			} 
		var xhr=getXMLHttpRequest();
		//处理结果
		xhr.onreadystatechange=function(){
			//if(xhr.readyState==4&&xhr.status==200){
			if(xhr.readyState==4){
				if(xhr.status==200){
				var str = xhr.responseText;//得到服务器返回数据
				
				var ss=str.split(",");
				var childDivs="";
				for(var i=0;i<ss.length;i++){
					childDivs+="<div onclick='writeText(this)' onmouseout='changeBackground_out(this)' onmouseover='changeBackground_over(this)'>"+ss[i]+"</div>"
				}
				div.innerHTML=childDivs;
				div.style.display="block";
			}
		}
		}
		xhr.open("get","${pageContext.request.contextPath}/servlet/SearchAJAXServlet?name="+name+"&time="+new Date().getTime());
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
<div id="divsearch">
	<form action="${pageContext.request.contextPath}/servlet/FindBookBySearchServlet"
		method="post">
		<table  border="0" cellspacing="0">
			<tr>
				<td >
				 <input id="name" name="name" type="text"  class="inputtable"  /> 
					<input type="submit" value="搜索"
					border="0" style="margin-bottom:-4px">
				</td>
			</tr>
		</table>

	</form>
</div>
<div id="context1"
	style="display:block;white;width:128px;height:200px;position:absolute;left:8px;top:85px; color: Aqua">
</div>