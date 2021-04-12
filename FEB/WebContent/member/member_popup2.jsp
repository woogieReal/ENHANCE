<%@page import="com.sist.feb.member.dao.MemberDao"%>
<%@page import="com.sist.feb.member.domain.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!-- core -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/cmn/common.jsp" %>
<%
	MemberVO loginMember = (MemberVO) session.getAttribute("memberInfo");
	int loginMemberNo = loginMember.getMember_no();
	String loginMemberPw = loginMember.getPw();
	
	request.setAttribute("loginMemberNo", loginMemberNo);

%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>프로필 사진 변경</title>
    <link rel="stylesheet" href="./member_style.css">
  
  	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>	
	<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
	<script src="/FEB/js/bootstrap.min.js"></script>
  
  </head>
  <body onload="window.resizeTo(500,530)">
    <div class="list_name" style="margin-left: 12px;"><h2>프로필 사진 변경</h2></div>
    <hr /><br/>
    <div style="margin-left: 35px;">
    	<form 
            action="<%=cPath %>/member/profile.do" 
    		id="imgFrm" 
    		name="imgFrm"
    		method="POST"
    		enctype="multipart/form-data" >
    		<input type="hidden" name="member_no" id="member_no" value="<%=loginMemberNo %>"/>
    		<div class="list_name">새로운 프로필 이미지를 등록 해주세요!</div>
    		<input type="file" class="text_box_main" name="profile_image" id="profile_image" style="width: 250px;" />
    		<br/><br/>
    	</form>	
    		<!-- onclick="doChangeProfileImage();" -->
    		<input type="button" class="button" id="doChangeProfileImage" value="변경"  />
    </div>
  </body>
  <script type="text/javascript">
  
  	$("#doChangeProfileImage").on("click", function(e){
  		console.log("doChangeProfileImage");
  		var frm = document.getElementById("imgFrm");
 		var form = new FormData(document.getElementById("imgFrm"));
  		
 		var memberNo = frm.member_no.value;
 		var profileImage = frm.profile_image.value;
 		
 		console.log("memberNo: "+memberNo);
 		console.log("profileImage: "+profileImage);
 		
 		if(!profileImage){
 			console.log("등록한 이미지가 없음");
 			close();
 		}
 		
 		$.ajax({
    		url:"/FEB/member/profile.do",
    		data:form,
    		dataType:'text',
    		type: 'POST',
    		asyn:'false',
    		processData: false,
    		contentType: false,
    		success:function(response){ //수정 성공
    			alert("수정이 완료되었습니다.");
    			close();
    		},
    		error:function(jqXHR){//실패시 처리
    			console.log("error");
    		}
    		
    	}); //--ajax
  	
  	}); //-- 변경
  	
  	
  	function doChangeProfileImage(){
  		console.log("doChangeProfileImage");
  		var frm = document.getElementById("imgFrm");
 		
 		console.log("work_type: "+frm.work_type.value);
 		
 		var memberNo = frm.member_no.value;
 		var profileImage = frm.profile_image.value;
 		
 		console.log("memberNo: "+memberNo);
 		console.log("profileImage: "+profileImage);
 		
 		if(!profileImage){
 			console.log("등록한 이미지가 없음");
 			close();
 		}
 		
 		$.ajax({
    		type: "POST",
    		url:"/FEB/member/profile.do",
    		asyn:"false",
    		dataType:"html",
    		data:{
    			profile_image: profileImage,
    			member_no: memberNo
    		},
    		success:function(data){//수정 성공
    			console.log("ajax 통신 성공");
    			console.log("success data:"+data);
    			alert("수정이 완료되었습니다.");
    		},
    		error:function(data){//실패시 처리
    			console.log("error:"+data);
    		},
    		complete:function(data){//성공/실패와 관계없이 수행!
    			console.log("data:"+data);
    		}
    		
    	}); //--ajax
 		
 		//frm.submit();
 		
  	}
  
  </script>

  
  
</html>