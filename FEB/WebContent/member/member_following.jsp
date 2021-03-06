<%@page import="com.sist.feb.profile.image.domain.ProfileImageVO"%>
<%@page import="com.sist.feb.member.service.MemberService"%>
<%@page import="com.sist.feb.storage.domain.MemberPostVO"%>
<%@page import="com.sist.feb.member.dao.MemberDao"%>
<%@page import="com.sist.feb.member.domain.MemberVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sist.feb.follow.domain.FollowVO"%>
<%@page import="java.util.List"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!-- core -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/cmn/common.jsp" %>
<%
	MemberVO loginMember = (MemberVO) session.getAttribute("memberInfo");
	MemberVO mem = new MemberVO();
	MemberDao memberDao = new MemberDao();	

	int member_no = 0;
	if(null != request.getParameter("no_to_follow")){
		member_no = Integer.parseInt(request.getParameter("no_to_follow"));
		mem = new MemberVO(member_no, "", "", "", "", "", "");
		mem = memberDao.doSelectOne(mem);
	}
	
	List<FollowVO> resultList = new ArrayList<FollowVO>();
	if(null != request.getAttribute("list")){
		resultList = (List<FollowVO>)request.getAttribute("list");	
	}
	
	String intro = "";
	String buttonMethod = "";
	String buttonName = "";
	if(null != request.getParameter("work_type") && "following".equals(request.getParameter("work_type"))){
		intro = " 님의 팔로잉 페이지 입니다.";
		buttonMethod = "unfollow";
		buttonName = "언팔로우";
	} else if(null != request.getParameter("work_type") && "followed".equals(request.getParameter("work_type"))){
		intro = " 님의 팔로워 페이지 입니다.";
		buttonMethod = "follow";
		buttonName = "팔로우";
	} else if(null != request.getParameter("work_type") && "unfollow".equals(request.getParameter("work_type"))){
		intro = " 님의 팔로잉 페이지 입니다.";
		buttonMethod = "unfollow";
		buttonName = "언팔로우";
	} else if(null != request.getParameter("work_type") && "follow".equals(request.getParameter("work_type"))){
		intro = " 님의 팔로워 페이지 입니다.";
		buttonMethod = "follow";
		buttonName = "팔로우";
	}
	else {
		intro = "오류";
		buttonMethod = "basic";
		buttonName = "오류";
	}
	
	int loginMemberNo = loginMember.getMember_no();
	int InquireMemberNo = member_no;
	
	request.setAttribute("loginMemberNo", loginMemberNo);
	request.setAttribute("InquireMemberNo", InquireMemberNo);
	request.setAttribute("buttonName", buttonName);
	request.setAttribute("buttonMethod", buttonMethod);
	
	MemberService service = new MemberService();
	
%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>member_follow</title>
    <link rel="stylesheet" href="./member_style.css">
    
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>	
	<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
	<script src="/FEB/js/bootstrap.min.js"></script>
    
  </head>
  <body>
    <div style="width: 800px;">
    	<div style="margin-bottom: 20px;"><span style="font-weight: bold;"><%= mem.getName()%></span><%= intro%></div>
    	
    	<%
    		if(null != resultList && resultList.size() > 0){
    	%>
    	
    	<%		
    			for(FollowVO vo : resultList){
    				
    				int flag = service.doCheckFollowing(vo, loginMember);
    				request.setAttribute("flag", flag);
    				
    				//==============================================================================
     				MemberVO imgMember = new MemberVO(vo.getFollowingNo(), "", "", "", "", "", "");
    				
    				ProfileImageVO imageVO = service.doInquireProfileImage(imgMember);
    				
    				int imgflag = service.doCheckProfileImage(imgMember);
    				String imagePath = "";
    				
    				//등록한 프로필 이미지가 있을 때만 수행
    				if(imgflag == 1){
    					imagePath = cPath + imageVO.getPath().substring(imageVO.getPath().indexOf("pictures") - 1).replace(File.separator, "/") + File.separator + imageVO.getSaveFileNm();
    				}
    				
    				request.setAttribute("imagePath", imagePath);
    				request.setAttribute("imgflag", imgflag); 
    				//=================================================================================
    				
    	%>
    		<div class="foll_info_box" style="background-color: white;">
    			<div class="left_box">
    				
    				<div id="profile_image_div" class="member_post_box" style="width: 50px; height: 100px; float: left; /* outline: 1px solid black; */ ">
	    				<c:choose>
	    					<c:when test="${imgflag == 1}">
	    						<img src="${imagePath }" onclick="moveToOtherUserProfile(<%= vo.getFollowingNo()%>);" alt="image" style="width: 50px; height: 50px; object-fit: cover; border-radius: 10px; margin-top: 10px; "/>
	    					</c:when>
	    					<c:when test="${imgflag == 0}">
	    						<img src="/FEB/pictures/2021/03/nothing.jpg" onclick="moveToOtherUserProfile(<%= vo.getFollowingNo()%>);" alt="image" style="width: 50px; height: 50px; object-fit: cover; border-radius: 10px; margin-top: 10px;"/>
	    					</c:when>
	    				</c:choose>
    				</div>
    				
    				
	    			<div class="foll_name">
	    				<%=vo.getName() %>
	    			</div>
	    			<div class="foll_subInfo">
		    			<%=vo.getLocation() %>
		    			<%=vo.getFollowDt() %><br/>    			
	    			</div>
	    			<form 
	            	action="<%=cPath %>/member/member.do" 
	    			id="folFrm" 
	    			name="folFrm"
	    			method="GET">
	    				<input type="hidden" name="work_type" id="work_type" value="<%= buttonMethod %>" />
	    				<input type="hidden" name="fol_num" id="fol_num" value="<%= vo.getFollowNo() %>" />
	    				<input type="hidden" name="member_no" id="member_no" value="<%= member_no%>" />
	    				<input type="hidden" name="following_no" id="following_no" value="<%= vo.getFollowingNo()%>" />
					
						<c:if test="${loginMemberNo == InquireMemberNo}">
							<c:choose>
								<c:when test="${buttonName == '팔로우'}">
									<c:choose>
										<c:when test="${flag == 1}">
											<button class="button" 
													name="send_type" 
													id="send_type" 
													style="width: 80px;"
													value="member_following.jsp"
													onclick="unfollow();"
													disabled="disabled"
												>
													팔로우 중
											</button>									
										</c:when>
										<c:otherwise>
											<button class="button" 
													name="send_type" 
													id="send_type" 
													style="width: 80px;"
													value="member_following.jsp"
													onclick="<c:out value="${buttonMethod }"></c:out>();" 
												>
													<c:out value="${buttonName }"></c:out>
											</button>										
										</c:otherwise> 
									</c:choose>
								</c:when>
								<c:when test="${buttonName == '언팔로우'}">
									<button class="button" 
											name="send_type" 
											id="send_type" 
											style="width: 80px;"
											value="member_following.jsp"
											onclick="<c:out value="${buttonMethod }"></c:out>();" 
										>
											<c:out value="${buttonName }"></c:out>
									</button>
								</c:when>
							</c:choose>
						
						</c:if>		
	    			</form>	
	    			
	    			<img src="./img/follower.png" alt="follower_image" style="width: 20px; margin-top: 5px;"/>
	    			<%=vo.getTheirFollowedCount() %>
    			</div>
    			
		
		<%
		int no = vo.getFollowingNo();
		MemberVO member = new MemberVO(no, "", "", "", "", "", "");
		MemberDao dao = new MemberDao();
		List<MemberPostVO> theirList = (List<MemberPostVO>)dao.doInquireTheirPost(member);	
		
		if(null != theirList && theirList.size() > 0){
			
			for(MemberPostVO vo2 : theirList){
				LOG.debug("vo2.getPath(): "+vo2.getPath());
				
		%>
				
				<div class="right_box">
			    	<img src="<%=vo2.getPath() %>" alt="image" class="their_post_image" />
				</div>
				
				
		<%		
				
    					}
    				}
		%>
		
			</div>
			<br/>
		
		<%
    			}
    		} else {
		%>
    		
    		<span>팔로우한 유저가 없습니다.</span>
    		
    	<%
    		}
    	%>
    
    
    </div>
  </body>
  <script type="text/javascript">
  	function unfollow(){
  		console.log("unfollow");
 		var frm = document.getElementById("folFrm");
  	}  
  	function follow(){
  		console.log("follow");
 		var frm = document.getElementById("folFrm");
  	}
	
  	function moveToOtherUserProfile(param){
  		console.log("profile_image");
  		var frm = document.getElementById("folFrm");
  		var member_no = param;
  		console.log("member_no: "+member_no);
  		
		var listUrl ="${cPath}/member/member.do?work_type=member_post&member_no="+member_no;
		console.log("listUrl: "+listUrl);
		window.location.href=listUrl;
  	}
  	
/*   	$("#profile_image_div > img").on("click", function(e) {
		console.log("img");
		
	}); */
  	
  	
  	
  </script>
</html>