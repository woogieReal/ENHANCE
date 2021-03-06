<%@page import="com.sist.feb.member.cmn.StringUtil"%>
<%@page import="java.io.File"%>
<%@page import="com.sist.feb.profile.image.domain.ProfileImageVO"%>
<%@page import="com.sist.feb.member.dao.MemberDao"%>
<%@page import="com.sist.feb.member.test.MemberServiceTestMain"%>
<%@page import="com.sist.feb.member.service.MemberService"%>
<%@page import="com.sist.feb.member.domain.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!-- core -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/cmn/common.jsp" %>
<%
	MemberVO loginMember = (MemberVO) session.getAttribute("memberInfo");
	if(null == session.getAttribute("memberInfo")){
		loginMember = new MemberVO(0, "", "", "", "", "", "");
	}
	
	MemberVO member = new MemberVO(44, "이름", "이메일", "비밀번호", "위치", "자기소개", "등록일");
	
	MemberDao memberdao = new MemberDao();
	MemberService service = new MemberService();
	MemberServiceTestMain servTestMain = new MemberServiceTestMain();
	
	int myFollowingCount = 0;
	int myFollowedCount = 0;
	
	if(null != request.getParameter("member_no")){
		
		int memberNo = Integer.parseInt(StringUtil.nvl(request.getParameter("member_no"), "0"));
		member = new MemberVO(memberNo, "", "", "", "", "", "");
		member =  memberdao.doSelectOne(member);
		myFollowingCount = memberdao.doCountMyFollowing(member);
		myFollowedCount = memberdao.doCountMyFollowed(member);
		
	}
	
	String changePage = "member_post.jsp";
	if(null != request.getParameter("send_type")){
		changePage = request.getParameter("send_type");
	}
	
	int loginMemberNo = loginMember.getMember_no();
	
	int InquireMemberNo = member.getMember_no();
	
	request.setAttribute("loginMemberNo", loginMemberNo);
	request.setAttribute("InquireMemberNo", InquireMemberNo);
	
	//================================================================
	ProfileImageVO imageVO = service.doInquireProfileImage(member);
	
	int flag = service.doCheckProfileImage(member);
	String image_path = "";
	
	//등록한 프로필 이미지가 있을 때만 수행
	if(flag == 1){
		image_path = cPath + imageVO.getPath().substring(imageVO.getPath().indexOf("pictures") - 1).replace(File.separator, "/") + File.separator + imageVO.getSaveFileNm();
	}
	
	request.setAttribute("image_path", image_path);
	request.setAttribute("flag", flag);
	//========================================================================
	
	String testPath = StringUtil.changePath(member.getMember_no());
	request.setAttribute("testPath", testPath);		
%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>member_main</title>
    <link rel="stylesheet" href="./member_style.css">
  </head>
  <body style="background-color: #fafafa; color: black; ">
  
	<jsp:include page="/cmn/header.jsp"></jsp:include>

    <div class="entire_div">
    	<div class="side_div_main">
    	
    		<!-- member_info -->
    		<div class="member_info">
    		
    			<div class="member_post_box">
    				<c:choose>
    					<c:when test="${flag == 1}">
    						<img src="${image_path }" alt="image" style="width: 205px; height: 205px; object-fit: cover; border-radius: 10px; margin-top: -20px;"/>
    					</c:when>
    					<c:when test="${flag == 0}">
    						<img src="/FEB/pictures/2021/03/nothing.jpg" alt="image" style="width: 205px; height: 205px; object-fit: cover; border-radius: 10px; margin-top: -20px;"/>
    					</c:when>
    				</c:choose>
    			</div>
	    		
	    		<c:if test="${loginMemberNo == InquireMemberNo}">
	    			<button class="button" onclick="showPopup();" style="width: 200px; margin-left: 15px;">프로필 사진 변경</button>
	    		</c:if>    		
    		
          		<div class="text_box_info_name"><%= member.getName() %></div>
          		<div class="text_box_info_detail"><%= member.getEmail() %></div>
          		<div class="text_box_info_detail"><%= member.getLocation() %></div>
          		<div class="text_box_info_detail"><%= member.getReg_dt() %></div><br/>
          		<form 
            		action="<%=cPath %>/member/member_profile.jsp" 
    					  id="infoFrm" 
    					  name="infoFrm"
    					  method="GET">
           			<!-- <div class="list_name">번호</div> -->
          	  		<input type="hidden" class="text_box_main" name="member_no" id="member_no" value="<%= member.getMember_no() %>"  style="width: 100px;"/>
            		<input type="submit" class="button" id="edit_profile_button" style="width: 200px; background-color: #304ffe; color: white; border: none; height: 35px;"
            		
            		<c:choose>
            			<c:when test="${loginMemberNo == InquireMemberNo}">
            				value="내 프로필 편집" 
            			</c:when>
            			<c:when test="${loginMemberNo != InquireMemberNo}">
            				value="프로필 보기" 
            			</c:when>
            		</c:choose>
            		
          			/>
          			
          		</form>
          		<br/>
		  		<form 
		  			action="<%=cPath %>/member/member.do"
		  			id="followFrm"
		  			name="followFrm"
		  			method="GET"
		  		>
		  			<input type="hidden" name="work_type" value="basic" />
		  			<input type="hidden" name="member_no" value="<%= member.getMember_no() %>"/>
		  			<div class="list_name follow_count" >팔로잉</div>
		  			<button class="button follow_button" name="send_type" value="member_following.jsp" onclick="doInqFollowing();"><%= myFollowingCount %></button><br/>
		  			<div class="list_name follow_count" >팔로워</div><br/>
		  			<button class="button follow_button" name="send_type" value="member_following.jsp" onclick="doInqFollowed();" style="margin-top: -30px;"><%= myFollowedCount %></button><br/>
		  		</form>		
		  		<br/><br/>
        	</div>
        	<!--// member_info  -->
        	
    	</div>
    	
     	<div class="contents_div">
     		<form 
            	action="<%=cPath %>/member/member.do" 
    			id="navFrm" 
    			name="navFrm"
    			method="GET">
	    		<div class="nav_main">
	    			<input type="hidden" name="work_type" value="basic2" />
	    			<input type="hidden" name="member_no" value="<%= member.getMember_no() %>"/>
	    			<button class="button" class="nav_button" style="width: 80px;" name="send_type" value="member_post.jsp" onclick="doInqPost();">작업</button>
					<button class="button" class="nav_button" style="width: 100px;" name="send_type" value="member_storage.jsp" onclick="doInqSave();">무브보드</button>
					<button class="button" class="nav_button" style="width: 80px;" name="send_type" value="member_storage.jsp" onclick="doInqLike();">평가</button>
					<!-- <hr/> -->
	    		</div>
	    	</form>	
	    	
			<div class="changed_div">
				<jsp:include page="<%=changePage%>" flush="false">
          			<jsp:param name="no_to_follow" value="<%=member.getMember_no() %>"/>
        		</jsp:include>
			</div>
				
    	</div>
    
    
    </div>


  </body>
  <script type="text/javascript">
  
  
  	//frm.submit() 은 넣지 않는다.
  	//button을 누른 것 만으로도 submit이 된다.
  	//button을 form 밖으로 빼는 것이 이상적
 	
  	function doInqFollowing(){
 		console.log("doInqFollowing");
 		var frm = document.getElementById("followFrm");
 		frm.work_type.value = "following";
 		console.log("work_type: "+work_type);
 		
 		
 	}
 	function doInqFollowed(){
 		console.log("doInqFollowed");
 		var frm = document.getElementById("followFrm");
 		frm.work_type.value = "followed";
 		console.log("work_type: "+work_type);
 		
 		
 	}
 	function doInqPost(){
 		console.log("doInqPost");
 		var frm = document.getElementById("navFrm");
 		frm.work_type.value = "member_post";
 		console.log("work_type: "+work_type);
 		
 		
 	}
 	function doInqSave(){
 		console.log("doInqSave");
 		var frm = document.getElementById("navFrm");
 		frm.work_type.value = "save";
 		console.log("work_type: "+work_type);
 		
 		
 	}
 	function doInqLike(){
 		console.log("doInqLike");
 		var frm = document.getElementById("navFrm");
 		frm.work_type.value = "like";
 		console.log("work_type: "+work_type);
 		
 	}
 	function showPopup(){
 		console.log("showPopup");
 		window.open("member_popup2.jsp","popup","width=100, height=100, left=450, top=100");
 	}
 	
  
  </script>
  
</html>