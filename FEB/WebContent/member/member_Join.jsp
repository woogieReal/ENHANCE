<%@page import="com.sist.feb.member.dao.MemberRegisterDao"%>
<%@page import="com.sist.feb.member.service.MemberRegisterService"%>
<%@page import="com.sist.feb.member.dao.MemberDao"%>
<%@page import="com.sist.feb.member.domain.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/cmn/common.jsp" %>
<%
	LOG.debug("cPath: " + cPath);
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>JOIN</title>
	<link rel="stylesheet" href="style.css">
	<link rel="shortcut icon" type="image/x-icon" href="<c:url value='/favicon.ico'/>">
	<style>
		select { 
			width: 400px; 
			height: 31px; 
			font-size: 15px; 
			color:#999;
			border:2px solid #ddd;
		}
	</style>
</head>
<body>
	<section class="login-form">
	<div class="register">
		<h1>SIGN IN</h1>
		<form action="<%=cPath %>/member/member.register" id="register" name="register" method="get">
			 <input type="hidden" name="work_div" value=""/>
			
			<div class="int-area">
				<input type="text" name="email" id="email" maxlength="30" autocomplete="off" required />
				<label for="email">E-MAIL</label>
			</div>
			<div class="int-area">
				<input type="text" name="name" id="name" maxlength="20" autocomplete="off" required />
				<label for="name">NAME</label>
			</div>
			<div class="int-area">
				<input type="password" name="pw" id="pw" maxlength="15" autocomplete="off" required />
				<label for="pw">PASSWORD</label>
			</div>
			<div class="int-area">
				<input type="password" name="pw_ch" id="pw_ch" maxlength="15" autocomplete="off" required />
				<label for="pw_ch">PASSWORD_CHECK</label>
			</div>
			
			<br/><br/>
			<select name="locationName" id="locationName">
				<option value="국가를 선택하세요">국가를 선택하세요</option>
				<option value="Austria">Austria</option>
				<option value="Australia">Australia</option>
				<option value="Belgium">Belgium</option>
				<option value="Canada">Canada</option>
				<option value="Chile">Chile</option>
				<option value="Colombia">Colombia</option>
				<option value="CzechRepublic">CzechRepublic</option>
				<option value="Denmark">Denmark</option>
				<option value="Estonia">Estonia</option>
				<option value="Finland">Finland</option>
				<option value="France">France</option>
				<option value="Germany">Germany</option>
				<option value="Greece">Greece</option>
				<option value="Hungary">Hungary</option>
				<option value="Iceland">Iceland</option>
				<option value="Ireland">Ireland</option>
				<option value="Israel">Israel</option>
				<option value="Italy">Italy</option>
				<option value="Japan">Japan</option>
				<option value="Korea">Korea</option>
				<option value="Latvia">Latvia</option>
				<option value="Lithuania">Lithuania</option>
				<option value="Luxembourg">Luxembourg</option>
				<option value="Mexico">Mexico</option>
				<option value="theNetherlands">theNetherlands</option>
				<option value="NewZealand">New Zealand</option>
				<option value="Norway">Norway</option>
				<option value="Poland">Poland</option>
				<option value="Portugal">Portugal</option>
				<option value="Slovak">Slovak</option>
				<option value="Republic">Republic</option>
				<option value="Slovenia">Slovenia</option>
				<option value="Spain">Spain</option>
				<option value="Sweden">Sweden</option>
				<option value="Switzerland">Switzerland</option>
				<option value="Turkey">Turkey</option>
				<option value="theUnitedKingdom">the United Kingdom</option>
				<option value="theUnitedStates">the United States</option>
			</select>
		</form>
		<div class="btn-area">
			<button class="button" name="registerBtn" id="registerBtn" onclick="doRegister();">JOIN US</button>
		</div>
	</div>
	</section>

	<!-- javascript -->
    <script type="text/javascript">
	
    function doRegister(){
		console.log("doRegister");
		var frm = document.getElementById("register");
		frm.work_div.value ="doRegister";
		
		var email = frm.email.value;
		console.log("email:"+email);
		
	 	if(null === email || email.trim().length==0){
    		document.getElementById("email").focus();
    		alert("E-MAIL을 입력 하세요.");
    		return;
    	}
		
		var name = frm.name.value;
		console.log("name:"+name);
		
	 	if(null === name || name.trim().length==0){
    		document.getElementById("name").focus();
    		alert("NAME을 입력 하세요.");
    		return;
    	}
		
		var pw = frm.pw.value;
		console.log("pw:"+pw);
		
	 	if(null === pw || pw.trim().length==0){
    		document.getElementById("pw").focus();
    		alert("PASSWORD를 입력 하세요.");
    		return;
    	}
	 	
		var pw_ch = frm.pw_ch.value;
		console.log("pw_ch:"+pw_ch);
		
	 	if(null === pw_ch || pw_ch.trim().length==0){
    		document.getElementById("pw_ch").focus();
    		alert("PASSWORD_CHECK를 입력 하세요.");
    		return;
    	}
	 	
	 	if(pw != pw_ch){
	 		document.getElementById("pw_ch").focus();
	 		alert("PASSWORD와 PASSWORD_CHECK가 일치하지 않습니다.");
	 		//$('#pw').val('');
	 		//$('#pw_ch').val('');
	 		return;
	 	}
		
		var frm2  = document.getElementById("locationName");
		var location = frm2.options[frm2.selectedIndex].value;
		
		if(location.trim() === "국가를 선택하세요"){
			document.getElementById("locationName").focus();
			alert("국가를 선택 하세요.");
			return;
		}
	
		console.log("location:"+location);
		
		if( false ==confirm("가입 하시겠습니까?") ) return;

		
		frm.submit();
	
	}
    
/*     function dupleEmailCheck(){
    	console.log("dupleEmailCheck");
    	
		var frm = document.getElementById("register");
		frm.work_div.value ="dupleEmailCheck";
		console.log("frm.work_div.value:"+frm.work_div.value);
		
		
		var email = frm.email.value;
		console.log("email:"+email);
    	
		//frm.submit();
	
    } */
    </script>
</body>
</html>