<%@page import="com.sist.feb.member.cmn.StringUtil"%>
<%@page import="com.sist.feb.member.dao.MemberDao"%>
<%@page import="com.sist.feb.member.test.MemberServiceTestMain"%>
<%@page import="com.sist.feb.member.service.MemberService"%>
<%@page import="com.sist.feb.member.domain.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!-- core -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/cmn/common.jsp" %>
<%
	MemberVO loginMember = (MemberVO) session.getAttribute("memberInfo");

	/* doSelectOne */
	int memberNo = Integer.parseInt(StringUtil.nvl(request.getParameter("member_no"), "0"));
	MemberVO member = new MemberVO(memberNo, "", "", "", "", "", "");
	
	MemberService service = new MemberService();
	MemberServiceTestMain servTestMain = new MemberServiceTestMain();
	
	MemberDao memberdao = new MemberDao();
	member =  memberdao.doSelectOne(member);
	
	int loginMemberNo = loginMember.getMember_no();
	int InquireMemberNo = memberNo;
	
	request.setAttribute("loginMemberNo", loginMemberNo);
	request.setAttribute("InquireMemberNo", InquireMemberNo);
	
%>

<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <title>member_profile</title>
    <link rel="stylesheet" href="./member_style.css">
    <script type="text/javascript">
    	function doUpdate(){
    		console.log("doUpdate");
    		var frm = document.getElementById("infoFrm");
    		frm.submit();
			
    		alert("수정 성공"); 
    		
    	}
    </script> 
  </head>
  <body>
  
  <jsp:include page="/cmn/header.jsp"></jsp:include>
  
    <div class="entire_div">
    	<div class="side_div_profile">
    		
    	</div>
    	<div class="profile_div">
    		<form 
    			action="<%=cPath %>/member/member.do" 
    			id="infoFrm" 
    			name="infoFrm"
    			method="GET" 
    		>    		
    		
    			<c:choose>
            		<c:when test="${loginMemberNo == InquireMemberNo}">
    			
		    			<!-- hidden -->
		    			<input type="hidden" name="work_type" value="update" />
		    			<input type="hidden" name="reg_dt" value="<%= member.getReg_dt() %>" />
		    			<!-- //hidden -->
		    			
		    			<div class="page_title">
		    				<span class="page_title_name">회원정보</span>
		    				<button name="doUpdate_button" class="button" id="doUpdate_button" value="clicked" onclick="doUpdate();">수정완료</button><br/>
		    				<hr/>
		    			</div>

		    			<div class="list_name">번호</div>
		    			<input type="text" class="text_box_profile" name="member_no" id="member_no" readonly="readonly" value="<%= member.getMember_no() %>"/>
		    			<div class="list_name">이름</div>
		    			<input type="text" class="text_box_profile"  name="name" id="name" value="<%= member.getName() %>"/><br/>
		    			<div class="list_name">이메일</div>
		    			<input type="text" class="text_box_profile"  name="email" id="email" readonly="readonly" value="<%= member.getEmail() %>"/><br/>
		    			<div class="list_name">비밀번호</div>
		    			<input type="password" class="text_box_profile" name="pw" id="pw" readonly="readonly" value="<%= member.getPw() %>"/><br/>
		    			<div class="list_name">위치</div>
		    			<input list="location_list" class="text_box_profile" name="location" id="location" value="<%= member.getLocation() %>"/>
		    				<datalist id="location_list">
								<option value="Austria"></option>
								<option value="Australia"></option>
								<option value="Belgium"></option>
								<option value="Canada"></option>
								<option value="Chile"></option>
								<option value="Colombia"></option>
								<option value="Czech Republic"></option>
								<option value="Denmark"></option>
								<option value="Estonia"></option>
								<option value="Finland"></option>
								<option value="France"></option>
								<option value="Germany"></option>
								<option value="Greece"></option>
								<option value="Hungary"></option>
								<option value="Iceland"></option>
								<option value="Ireland"></option>
								<option value="Israel"></option>
								<option value="Italy"></option>
								<option value="Japan"></option>
								<option value="Korea"></option>
								<option value="Latvia"></option>
								<option value="Lithuania"></option>
								<option value="Luxembourg"></option>
								<option value="Mexico"></option>
								<option value="the Netherlands"></option>
								<option value="New Zealand"></option>
								<option value="Norway"></option>
								<option value="Poland"></option>
								<option value="Portugal"></option>
								<option value="Slovak"></option>
								<option value="Republic Slovenia"></option>
								<option value="Spain"></option>
								<option value="Sweden"></option>
								<option value="Switzerland"></option>
								<option value="Turkey"></option>
								<option value="the United Kingdom"></option>
								<option value="the United States"></option>												
		    				</datalist><br/>    			
		    			<div class="list_name">자기소개</div>
		    			<textarea class="text_box_profile" name="intro" id="intro" rows="30" cols="70" maxlength="1000"><%= member.getIntro() %></textarea>
		    			
		    			
            		</c:when>
            		<c:when test="${loginMemberNo != InquireMemberNo}">
            			<!-- hidden -->
		    			<input type="hidden" name="work_type" value="update" />
		    			<input type="hidden" name="reg_dt" value="<%= member.getReg_dt() %>" />
		    			<!-- //hidden -->
		    			
		    			<div class="page_title">
		    				<span class="page_title_name">회원정보</span>
		    				<button name="doUpdate_button" class="button" id="doUpdate_button" value="clicked" >뒤로가기</button><br/>
		    				<hr/>
		    			</div>
		
		    			<div class="list_name">번호</div>
		    			<input type="text" class="text_box_profile" name="member_no" id="member_no" readonly="readonly" value="<%= member.getMember_no() %>"/>
		    			<div class="list_name">이름</div>
		    			<input type="text" class="text_box_profile"  name="name" id="name" value="<%= member.getName() %>" readonly="readonly"/><br/>
		    			<div class="list_name">이메일</div>
		    			<input type="text" class="text_box_profile"  name="email" id="email" readonly="readonly" value="<%= member.getEmail() %>"/><br/>
		    			<div class="list_name">비밀번호</div>
		    			<input type="password" class="text_box_profile" name="pw" id="pw" readonly="readonly" value="<%= member.getPw() %>"/>
		    			<div class="list_name">위치</div>
		    			<input list="location_list" class="text_box_profile" name="location" id="location" value="<%= member.getLocation() %>" readonly="readonly"/>
		    				<datalist id="location_list">
								<option value="Austria"></option>
								<option value="Australia"></option>
								<option value="Belgium"></option>
								<option value="Canada"></option>
								<option value="Chile"></option>
								<option value="Colombia"></option>
								<option value="Czech Republic"></option>
								<option value="Denmark"></option>
								<option value="Estonia"></option>
								<option value="Finland"></option>
								<option value="France"></option>
								<option value="Germany"></option>
								<option value="Greece"></option>
								<option value="Hungary"></option>
								<option value="Iceland"></option>
								<option value="Ireland"></option>
								<option value="Israel"></option>
								<option value="Italy"></option>
								<option value="Japan"></option>
								<option value="Korea"></option>
								<option value="Latvia"></option>
								<option value="Lithuania"></option>
								<option value="Luxembourg"></option>
								<option value="Mexico"></option>
								<option value="the Netherlands"></option>
								<option value="New Zealand"></option>
								<option value="Norway"></option>
								<option value="Poland"></option>
								<option value="Portugal"></option>
								<option value="Slovak"></option>
								<option value="Republic Slovenia"></option>
								<option value="Spain"></option>
								<option value="Sweden"></option>
								<option value="Switzerland"></option>
								<option value="Turkey"></option>
								<option value="the United Kingdom"></option>
								<option value="the United States"></option>												
		    				</datalist><br/>    			
		    			<div class="list_name">자기소개</div>
		    			<textarea class="text_box_profile" name="intro" id="intro" rows="30" cols="70" maxlength="1000" readonly="readonly"><%= member.getIntro() %></textarea>
		    				
            		</c:when>
            	</c:choose>
    		</form>
    			
    	
    	</div>
    	
    	<c:if test="${loginMemberNo == InquireMemberNo}">
    		<button class="button" onclick="showPopup();" style="margin-top: 393px; margin-left: -80px;" >변경</button>
    	</c:if>
    	
    </div>
	



  </body>
  <script type="text/javascript">
  	function showPopup(){
  		window.open("member_popup.jsp","popup","width=100, height=100, left=450, top=100");
  	}
  
  
  </script>
  
  
  
  
  
</html>