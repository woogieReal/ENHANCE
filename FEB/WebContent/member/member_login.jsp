<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/cmn/common.jsp" %>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN</title>
	<link rel="stylesheet" href="style.css" >
	<link rel="shortcut icon" type="image/x-icon" href="<c:url value='/favicon.ico'/>">
</head>
<body>
	<section class="login-form">
	<div class="login">
		<h1>로그인</h1>
		<%-- <div>${flag}</div> --%>
		<form action="<%=cPath %>/member/member.login" id="login" method="post" >
			
			<input type="hidden" type="text" name="member_no" value="2"/>
			<input type="hidden" type="text" name="work_div" value="login"/>
			<div class="int-area">
				<input type="text" name="email" id="email" autocomplete="off" required />
				<label for="email">E-MAIL</label>
			</div>
			<div class="int-area">
				<input type="password" name="pw" id="pw" autocomplete="off" required />
				<label for="pw">PASSWORD</label>
			</div>
			<div class="btn-area">
				<button  type="submit" class="button" name="loginBtn" id="loginBtn" onclick="doLogin();" >로그인</button>	
		 <div class="caption">
			<a href="/FEB/member/member_findpw.jsp">비밀번호 찾기</a>
		</div>
		<div class="caption">
			<a href="/FEB/member/member_Join.jsp">계정 만들기</a>
		</div>
		
	</form>
	</div>
</section>
			
<!-- javascript -->
    <script type="text/javascript">
	
    function doLogin(){	
    	
    	console.log("login");
    	
    	var frm  = document.getElementById("login");
    	
    	var email = frm.email.value;
    	console.log("email:"+email);
    	var pw = frm.pw.value;
    	console.log("pw:"+pw);
   	 
    	//frm.submit();
    }		
    </script>
   </body>
</html>