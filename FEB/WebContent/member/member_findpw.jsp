<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/cmn/common.jsp" %>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FindPassword</title>
	<link rel="stylesheet" href="style.css" >
	<link rel="shortcut icon" type="image/x-icon" href="<c:url value='/favicon.ico'/>">
</head>
<body>
<section class="login-form">
	<div class="findPw">
		<h1>비밀번호찾기</h1>
		<h3>가입하신 이메일로 비밀번호를 보내드립니다.</h3>

		<form action="<%=cPath %>/member/membermail.do" id="findPw" method="get" >
			<div class="int-area">
				<input type="text" name="email" id="email" autocomplete="off" required />
				<label for="email">E-MAIL</label>
			</div>
					<div class="btn-area">
				<button  type="button" class="button" name="findPw"  onclick="doFindPw();" >비밀번호찾기</button>	
			</div>
	</form>
	</div>
</section>
			
<!-- javascript -->
    <script type="text/javascript">
     function doFindPw(){
		console.log("doFindPw");
		var frm = document.getElementById("findPw");
		
 		var email = document.getElementById("email").value;
		console.log("email:"+email);
		
	 	if(null === email || email.trim().length==0){
    		document.getElementById("email").focus();
    		alert("E-MAIL을 입력 하세요.");
    		return;
    	}
	
	 	if( false ==confirm("메일을  보내드릴까요?") ) return; 
	 	frm.action="/FEB/member/membermail.do";
	 	frm.submit();
		
    } 
    </script>
   </body>
</html>