<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>bookStore注册页面</title>
<%--导入css --%>
<link rel="stylesheet" href="css/main.css" type="text/css" />
<script type="text/javascript">
	function changeImage() {

		document.getElementById("img").src = "${pageContext.request.contextPath}/servletImagServlet?time="
				+ new Date().getTime();
	}
</script>
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
	window.onload=function(){
	
		var nameElement=document.getElementsByName("name")[0];
		var emailElement=document.getElementsByName("email")[0];
emailElement.onblur=function(){
		var email=this.value;
		var msg1=document.getElementById("msg1");
		 var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		 var isok=reg.test(email );
		if(!isok&&email!=""){
			msg1.innerHTML="<font color='red'>×邮箱格式不正确</font>"
		}else{
		var xhr=getXMLHttpRequest();
			xhr.onreadystatechange=function(){
				if(xhr.readyState==4)
					if(xhr.status==200){
						
						if(xhr.responseText=="true"){
							if(email!=""){
								msg1.innerHTML="<font color='red'>*邮箱已被使用</font>"
							}else{
								msg1.innerHTML="<font color='#999999'>请输入邮箱</font>"
							}
						}
							
						else {
							if(email!=""){
							msg1.innerHTML="<font color='lime'>✔</font>邮箱没被注册过"
							}else{
								msg1.innerHTML="<font color='#999999'>请输入邮箱</font>"
							}
							
						}
					}
			}
		 xhr.open("get","${pageContext.request.contextPath}/servlet/ckEmailServlet?email="+email); 
			//xhr.open("get","${pageContext.request.contextPath}/servlet/searchAJAXServlet?name="+email);
			xhr.send(null);
		
		}
}
nameElement.onblur=function(){
			var name=this.value;
			var msg=document.getElementById("msg2");
			//(document.form1.userpassword.value=="")||(document.form1.userpassword.value.length<6)
			if(document.form1.name.value==""){
				msg.innerHTML="<font color='#999999'>请输入用户名</font>"
			}
			else if(document.form1.name.value.length<6){
				msg.innerHTML="<font color='red'>×用户名太短</font>"
			}
			else if(document.form1.name.value.length>15){
				msg.innerHTML="<font color='red'>×用户名太长</font>"
			}
			else{
			
			//1注册一个request对象
			var xhr =getXMLHttpRequest();
			//4处理结果
			xhr.onreadystatechange=function(){
				if(xhr.readyState==4)
					if(xhr.status==200){
						
						if(xhr.responseText=="true"){
							if(name!=""){
								msg.innerHTML="<font color='red'>*用户名已存在</font>"
							}
							else{
								msg.innerHTML="<font color='#999999'>请输入用户名</font>"
							}
						}	
						else {
							if(name!=""){
								msg.innerHTML="<font color='lime'>✔</font>用户名可用"
							}else{
								msg.innerHTML="<font color='#999999'>请输入用户名</font>"
							}
						}
					}
			}
			//2创建链接
			xhr.open("get","${pageContext.request.contextPath}/servlet/ckNameServlet?name="+name);
			//3发送请求
			xhr.send(null);
		}
		}
	}
</script>
</head>


<body>
	<div id="divcontent">
		<form action="${pageContext.request.contextPath}/servletRegister"
			method="post" id="form1" name="form1">
			<table width="850px" border="0" cellspacing="0">
				<tr>
					<td style="padding:30px">
						<h1>新用户注册 <font color="red">${user_msg }</font></h1>
						
						<table width="70%" border="0" cellspacing="2" class="upline">
							<tr>
								<td style="text-align:right; width:20%">会员邮箱：</td>
								<td style="width:40%">
								<input type="text" class="textinput" value="${uf.email }"
									name="email" /></td>
								<td ><span id="msg1"><font color="#999999">${uf.msg.email }</font></span></td>
							</tr>
							<tr>
								<td style="text-align:right">用户名：</td>
								<td>
									<input type="text" class="textinput" name="name" value="${uf.name }"/>
								</td>
								<td><span id="msg2"><font color="#999999">${uf.msg.name }${error }</font></span></td>
							</tr>
							<tr>
								<td style="text-align:right">密码：</td>
								<td><input type="password" class="textinput"
									name="password" /></td>
								<td><font color="#999999">${uf.msg.password }</font></td>
							</tr>
							<tr>
								<td style="text-align:right">重复密码：</td>
								<td><input type="password" class="textinput"
									name="repassword" /></td>
								<td><font color="#999999">${uf.msg.repassword }</font></td>
							</tr>
							<tr>
								<td style="text-align:right">性别：</td>
								<td >&nbsp;&nbsp;<input type="radio"
									name="gender" value="男" checked="checked" /> 男
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"
									name="gender" value="女" /> 女</td>
									<td></td>
							</tr>
							<tr>
								<td style="text-align:right">联系电话：</td>
								<td ><input type="text" class="textinput"
									 name="telephone" value="${uf.telephone }"/></td>
							
									<td style="width: 174px; ">
										<span id="msg1"><font color="#999999">${uf.msg.telephone }</font></span>
									</td>
							</tr>

						</table>



						<h1>注册校验</h1>
						<table width="80%" border="0" cellspacing="2">
							<tr>
								<td style="text-align:right; width:20%">输入校验码：</td>
								<td style="width:50%"><input type="text" class="textinput" name="ckcode1"/>
								</td>
								<td><font color='red'>${uf.msg.ckcode1 }</font></td>
							</tr>
							<tr>
								<td style="text-align:right;width:20%;">&nbsp;</td>
								<td colspan="2" style="width:50%"><img
									src="${pageContext.request.contextPath}/servletImagServlet" width="180"
									height="30" class="textinput" style="height:30px;" id="img" />&nbsp;&nbsp;
									<a href="javascript:void(0);" onclick="changeImage()">看不清换一张</a>
								</td>
							</tr>
						</table>

						<table width="70%" border="0" cellspacing="0">
							<tr>
							<!-- type="image" src="images/signup.gif" -->
								<td style="padding-top:20px; text-align:center"><input 
								type="submit"	 name="submit" border="0" value="提交">
								</td>
							</tr>
						</table></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
