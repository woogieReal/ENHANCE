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
    <title>비밀번호 변경</title>
    <link rel="stylesheet" href="./member_style.css">
    
  	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>	
	<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
	<script src="/FEB/js/bootstrap.min.js"></script>    
    
  </head>
  <body onload="window.resizeTo(500,530)">
    <div class="list_name" style="margin-left: 12px;"><h2>비밀번호 변경</h2></div>
    <hr /><br/>
    <div style="margin-left: 35px;">
    	<form 
            action="<%=cPath %>/member/member.do" 
    		id="pwFrm" 
    		name="pwFrm"
    		method="GET">
    		<input type="hidden" name="work_type" id="work_type" value="change_pw" />
    		<input type="hidden" name="member_no" id="member_no" value="<%=loginMemberNo %>"/>
    		<input type="hidden" name="real_pw" id="real_pw" value="<%=loginMemberPw %>"/>
    		<div class="list_name">현재 비밀번호</div>
    		<input type="password" class="text_box_main" name="curr_pw" id="curr_pw" style="width: 250px;" />
    		<div class="list_name">새로운 비밀번호</div>
    		<input type="password" class="text_box_main" name="new_pw" id="new_pw" style="width: 250px;" />
    		<div class="list_name">비밀번호 확인</div>
    		<input type="password" class="text_box_main" name="check_pw" id="check_pw" style="width: 250px;" />
    		<br/><br/>
    	</form>	
    	<!-- onclick="doChangePw();" -->
    		<input type="button" class="button" id="doChangePw" value="수정" />
    </div>
  </body>
  <script type="text/javascript">
  	
  	$("#doChangePw").on("click", function(e){
  		console.log("doChangePw");
  		
  		var frm = document.getElementById("pwFrm");
  		
  		var real_pw = frm.real_pw.value;
 		var curr_pw = frm.curr_pw.value;
 		var new_pw = frm.new_pw.value;
 		var check_pw = frm.check_pw.value;
 		var member_no = frm.member_no.value;
 		var work_type = frm.work_type.value;

 		var form = new FormData(document.getElementById("pwFrm"));
 		
 		console.log("real_pw: "+real_pw);
 		console.log("curr_pw: "+curr_pw);
 		console.log("new_pw: "+new_pw);
 		console.log("check_pw: "+check_pw);
 		console.log("member_no: "+member_no);
 		console.log("work_type: "+work_type);
 		
 		if(real_pw == curr_pw){
 			
 			if(new_pw == check_pw){
 				
 				$.ajax({
 		    		type: 'GET',
 		    		url:"/FEB/member/member.do",
 		    		asyn:'false',
 		    		dataType:'html',
 		    		data:{
 		    			member_no:member_no,
 		    			new_pw:new_pw,
 		    			work_type:work_type
 		    		},
 		    		success:function(data){ //수정 성공
 		    			alert("수정이 완료되었습니다.");
 		    			close();
 		    		},
 		    		error:function(jqXHR){//실패시 처리
 		    			console.log("error");
 		    		}
 		    		
 		    	}); //--ajax
 				
 			} else {
 				alert("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
 			}
 		} else {
 			alert("현재 비밀번호가 올바르지 않습니다.");
 		}
 		
 		
 		
 		
  	});
  	
  	function doChangePw(){
  		console.log("doChangePw");
 		var frm = document.getElementById("pwFrm");
 		var real_pw = frm.real_pw.value;
 		var curr_pw = frm.curr_pw.value;
 		var new_pw = frm.new_pw.value;
 		var check_pw = frm.check_pw.value;
 		
 		console.log("real_pw: "+real_pw);
 		console.log("curr_pw: "+curr_pw);
 		console.log("new_pw: "+new_pw);
 		console.log("check_pw: "+check_pw);
 		
 		if(real_pw == curr_pw){
 			if(new_pw == check_pw){
 				frm.submit();
 				alert("비밀번호 변경이 완료되었습니다!");
 			} else {
 				alert("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
 			}
 		} else {
 			alert("현재 비밀번호가 올바르지 않습니다.");
 		}
 		
  	}
  
  </script>

  
  
</html>