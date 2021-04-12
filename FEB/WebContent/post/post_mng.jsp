<%@page import="com.sist.feb.follow.domain.FollowVO"%>
<%@page import="com.sist.feb.member.cmn.StringUtil"%>
<%@page import="com.sist.feb.member.service.MemberService"%>
<%@page import="com.sist.feb.member.domain.MemberVO"%>
<%@page import="java.util.List"%>
<%@page import="com.sist.feb.picture.domain.PictureVO"%>
<%@page import="java.io.File"%>
<%@page import="com.sist.feb.post.domain.PostPicVO"%>
<%@page import="com.sist.feb.post.domain.PostVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/cmn/common.jsp"%>
<!-- core태그 -->
<%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 국제화 태그 -->
<%@ taglib prefix= "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %><!-- jstl안먹을때 이거 넣어줌 -->

<%
	String cateDiv ="";
	PostPicVO vo = (PostPicVO)request.getAttribute("vo");
	List<PictureVO> list = (List<PictureVO>)request.getAttribute("list");
	cateDiv=Integer.toString(vo.getCategorySeq());
	
	MemberVO loginMember = (MemberVO) session.getAttribute("memberInfo");
	if(null == session.getAttribute("memberInfo")){
		loginMember = new MemberVO(0, "", "", "", "", "", "");
	}
	
	MemberService service = new MemberService();
	MemberVO postMember = new MemberVO(vo.getMemberNo(), "", "", "", "", "", "");
	postMember = service.doSelectOne(postMember);
	
	String profileImagePath = StringUtil.changePath(postMember.getMember_no());
	request.setAttribute("profileImagePath", profileImagePath);
	request.setAttribute("loginMemberNo", loginMember.getMember_no());
	request.setAttribute("postMemberNo", postMember.getMember_no());
	
	FollowVO followVO = new FollowVO(0, postMember.getMember_no(), "", "", "", 1);
	
	int followCheckflag = service.doCheckFollowing(followVO, loginMember);
	request.setAttribute("followCheckflag", followCheckflag);
	
	int saveCheckFlag = service.doCheckSave(vo, loginMember);
	int likeCheckFlag = service.doCheckLike(vo, loginMember);
	
	request.setAttribute("saveCheckFlag", saveCheckFlag);
	request.setAttribute("likeCheckFlag", likeCheckFlag);
	
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Enhance_project_upload</title>
	<!-- jQuery -->
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>	
	<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
	<script src="/FEB/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="./post_style.css">
	<style type="text/css">
		.button:hover {
			box-shadow: 0.5px 0.5px 2px;
		}
	</style>
</head>

<body>
	<!-- 헤더 -->
	<jsp:include page="/cmn/header.jsp"></jsp:include>
	<!-- 헤더 -->

	<div class="wrap1">
		<div class= "text-right">
			<button type="button" class="list_button" id="move_to_list" style="float: left;">목록</button>  
		 </div>
		 
		<form  action="<%=cPath %>/post/post.do" method="post" id="regform" name="regform" > <!-- ${pageContext.request.contextPath} --> 
			<input type="hidden" name="work_type" value=""/>
		 	<input type="hidden" name="post_no" id="post_no" value="<%=vo.getPostNo() %>"/><!-- 게시글 번호로 조회 -->
			
			<c:if test="${loginMemberNo == postMemberNo && loginMemberNo != 0 }">
				<button type="button" class="list_button" name="delete_post_button" id="delete_post_button" style="float: right;" onclick="delete_post();">삭제</button>
			</c:if>
			<br/><br/> 
			
			<h2><%=vo.getTitle() %></h2>
			<hr></hr>
			
			
			
			<br/>	
			<div class="mb-3 magin">
				
				<label class="box-radio-input">
					<input type="radio" name="category" id="category" value="1" 
					<c:if test="${vo.getCategorySeq()=='1'}">selected</c:if>>
					<span>건축</span>
				</label>
				<label class="box-radio-input">
					<input type="radio" name="category" id="category" value="2"
					<c:if test="${vo.getCategorySeq()=='2'}">selected</c:if>>
					<span>산업디자인</span>
				</label>
				<label class="box-radio-input">
					<input type="radio" name="category" id="category" value="3"
					<c:if test="${vo.getCategorySeq()=='3'}">selected</c:if>>
					<span>광고</span>
				</label>
				<label class="box-radio-input">
					<input type="radio" name="category" id="category" value="4"
					<c:if test="${vo.getCategorySeq()=='4'}">selected</c:if>>
					<span>미술</span>
				</label>
				<label class="box-radio-input">
					<input type="radio" name="category" id="category" value="5"
					<c:if test="${vo.getCategorySeq()=='5'}">selected</c:if>>
					<span>3D아트</span>
				</label>
				<label class="box-radio-input">
					<input type="radio" name="category" id="category" value="6"
					<%if (null !=cateDiv && "6".equals(cateDiv)){out.print("selected");} %>>
					<span>UI/UX</span>
				</label>
				<label class="box-radio-input">
					<input type="radio" name="category" id="category" value="7"
					<%if (null !=cateDiv && "7".equals(cateDiv)){out.print("selected");} %>>
					<span>패션</span>
				</label>
				<label class="box-radio-input">
					<input type="radio" name="category" id="category" value="8"
					<%if (null !=cateDiv && "8".equals(cateDiv)){out.print("selected");} %>>
					<span>일러스트레이션</span>
				</label>
				<label class="box-radio-input">
					<input type="radio" name="category" id="category" value="9"
					<%if (null !=cateDiv && "9".equals(cateDiv)){out.print("selected");} %>>
					<span>그래픽디자인</span>
				</label>
				<label class="box-radio-input">
					<input type="radio" name="category" id="category" value="10"
					<%if (null !=cateDiv && "10".equals(cateDiv)){out.print("selected");} %>>
					<span>포토그래피</span>
				</label>
			</div>
			<br/><br/>
				<div class="contents_left"> 
					<% 
						for(int i = list.size()-1; i >= 0; i--) {
					%>
					<div>
						<img alt="이미지" src="<%=list.get(i).getPath()%>" style="width: 650px;"  >
					</div>
					<%
						}
					%>
						
						<p><%=vo.getContents() %></p>
				</div>
			</form> 
			
			<!-- postMemFrm -->
			<form 
		  		action="<%=cPath %>/member/member.do"
		  		id="postMemFrm"
		  		name="postMemFrm"
		  		method="GET"
		  	>
				<input type="hidden" name="work_type" value="" />
		  		<input type="hidden" name="member_no" value="${loginMemberNo }"/>
		  		<input type="hidden" name="following_no" value="${postMemberNo }"/>
				
		  		<input type="hidden" name="post_no" value="<%=vo.getPostNo() %>"/>
		  		<input type="hidden" name="pic_group" value="<%=vo.getPicGroup() %>"/>
		  		<input type="hidden" name="store_type" value=""/>
		  		
				<div class="contents_right" >
					<!-- member_info -->
	    			<div class="member_info">
	    		
		    			<div class="member_post_box">
		    				<img src="${profileImagePath }" id="posting_member_profile_image" onclick="MoveToMemberProfile();" alt="image" style="width: 205px; height: 205px; object-fit: cover; border-radius: 10px; "/>
		    			</div>
		    		
		          		<div class="text_box_info_name"><%= postMember.getName() %></div>
		          		<div class="text_box_info_detail"><%= postMember.getEmail() %></div>
		          		<div class="text_box_info_detail"><%= postMember.getLocation() %></div>
		          		
		          		<br/>
						
						
						<c:if test="${loginMemberNo != postMemberNo && loginMemberNo != 0 }">
							<c:choose>
								<c:when test="${followCheckflag == 1}">
									<div name="unfollow_button_div" id="unfollow_button_div">
									
									</div>
								</c:when>
								<c:otherwise>
									<div name="follow_button_div" id="follow_button_div">
									
									</div>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${saveCheckFlag == 1}">
									<div name="unsave_button_div" id="unsave_button_div">
									
									</div>
								</c:when>
								<c:otherwise>
									<div name="save_button_div" id="save_button_div">
									
									</div>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${likeCheckFlag == 1}">
									<div name="dislike_button_div" id="dislike_button_div">
									
									</div>
								</c:when>
								<c:otherwise>
									<div name="like_button_div" id="like_button_div">
									
									</div>
								</c:otherwise>
							</c:choose>
							
						</c:if>
						
				  		<br/><br/><br/>
	        	</div>
	        	<!--// member_info  -->
					
			</div>
		</form>
		<!--// postMemFrm -->
		
	</div> 
	<div class="wrap2" >
  		<jsp:include page="/reply/reply_list.jsp" />
 	</div>
	
	
</body>


<script type="text/javascript">
/* id는 자바 스크립트에서 다룰려고 지정하는 것이고 name은 파라미터 전송을 하기 위해서 지정하는 것이다. */

	$(document).ready(function() {
		console.log("1.document ready: 최초수행!");
		
		// name이 category고, value가 cateDiv인 라디오 버튼 체크
		$('input:radio[name=category]:input[value="${vo.getCategorySeq()}"]').attr("checked", true);
		
		doShowButton();
	});//--document ready
	
	function doShowButton(){
		console.log("doShowButton()");
		
		var html = "";
		html += "				<input type='button' class='button' value='팔로우 중'";
		html += "						id='unfollow_button'";
		html += "						style='width: 90px; font-size: 1.0em; color: black; background-color: gold; font-weight: bold; border: 0.1em solid gray; border-radius: 10px; padding: 5px; margin: 10px;'";
		html += "						onclick='unfollowInPost();'";
		html += "					/>";
		$("#unfollow_button_div").append(html);
		
		var html2 = "";
		html2 += "				<input type='button' class='button' value='팔로우' ";
		html2 += "						id='follow_button'";
		html2 += "						style='width: 80px; font-size: 1.0em; color: black; background-color: white; font-weight: bold; border: 0.1em solid gray; border-radius: 10px; padding: 5px; margin: 10px;'";
		html2 += "						onclick='followInPost();'";
		html2 += "					/>";
		$("#follow_button_div").append(html2);
		
		var html3 = "";
		html3 += "				<input type='button' class='button' value='저장'";
		html3 += "						id='unsave_button'";
		html3 += "						style='width: 90px; font-size: 1.0em; color: white; background-color: #23A3A7; font-weight: normal; border: 0.1em solid lightblue; border-radius: 10px; padding: 5px; margin: 10px; float: left;'";
		html3 += "						onclick='unsave();'";
		html3 += "					/>";
		$("#unsave_button_div").append(html3);
		
		var html4 = "";
		html4 += "				<input type='button' class='button' value='저장'";
		html4 += "						id='save_button'";
		html4 += "						style='width: 90px; font-size: 1.0em; color: black; background-color: white; font-weight: bold; border: 0.1em solid gray; border-radius: 10px; padding: 5px; margin: 10px; float: left;'";
		html4 += "						onclick='save();'";
		html4 += "					/>";
		$("#save_button_div").append(html4);
		
		var html5 = "";
		html5 += "				<input type='button' class='button' value='좋아요'";
		html5 += "						id='dislike_button'";
		html5 += "						style='width: 90px; font-size: 1.0em; color: white; background-color: #23A3A7; font-weight: normal; border: 0.1em solid lightblue; border-radius: 10px; padding: 5px; margin: 10px; float: left;'";
		html5 += "						onclick='dislike();'";
		html5 += "					/>";
		$("#dislike_button_div").append(html5);
		
		var html6 = "";
		html6 += "				<input type='button' class='button' value='좋아요'";
		html6 += "						id='like_button'";
		html6 += "						style='width: 90px; font-size: 1.0em; color: black; background-color: white; font-weight: bold; border: 0.1em solid gray; border-radius: 10px; padding: 5px; margin: 10px; float: left;'";
		html6 += "						onclick='like();'";
		html6 += "					/>";
		$("#like_button_div").append(html6);
		
	}
	
	function dislike(){
		console.log("dislike()");
		var frm = document.getElementById("postMemFrm");
		frm.work_type.value = "canselSaveOrLikePost";
		frm.store_type.value = 2;
		
		$.ajax({
    		type: "get",
    		url:"<%=cPath %>/member/member.do",
    		asyn:"true",
    		dataType:"html",
    		data:{
    			work_type:frm.work_type.value,
    			store_type:frm.store_type.value,
    			post_no:frm.post_no.value,
    			member_no:frm.member_no.value,
    			pic_group:frm.pic_group.value
    		},
    		success:function(data){//통신 성공
				var jsonObj = JSON.parse(data)
    			
    			console.log("success data:"+data);
    			//success data:{"msgId":"1","msgContents":"등록 성공","num":0,"totalCnt":0,"msgFlag":0}    		
    		    if(null !=jsonObj && jsonObj.msgId=="1"){
    		    	
    		    	//alert(jsonObj.msgContents);
    		    	$("#like_button_div").empty();
    		    	$("#dislike_button_div").empty();
    		    	
    		    	var html6 = "";
    				html6 += "				<input type='button' class='button' value='좋아요'";
    				html6 += "						id='like_button'";
    				html6 += "						style='width: 90px; font-size: 1.0em; color: black; background-color: white; font-weight: bold; border: 0.1em solid gray; border-radius: 10px; padding: 5px; margin: 10px; float: left;'";
    				html6 += "						onclick='like();'";
    				html6 += "					/>";
    				
    				$("#like_button_div").append(html6);
    				$("#dislike_button_div").append(html6);
    		    	
    		    }    			
    			
    		},
    		error:function(data){//실패시 처리
    			console.log("error data:"+data);
    		},
    		complete:function(data){//성공/실패와 관계없이 수행!
    			//console.log("data:"+data);
    		}
    		
    	});
		
	}
	
	function unsave(){
		console.log("unsave()");
		var frm = document.getElementById("postMemFrm");
		frm.work_type.value = "canselSaveOrLikePost";
		frm.store_type.value = 1;
		
		$.ajax({
    		type: "get",
    		url:"<%=cPath %>/member/member.do",
    		asyn:"true",
    		dataType:"html",
    		data:{
    			work_type:frm.work_type.value,
    			store_type:frm.store_type.value,
    			post_no:frm.post_no.value,
    			member_no:frm.member_no.value,
    			pic_group:frm.pic_group.value
    		},
    		success:function(data){//통신 성공
				var jsonObj = JSON.parse(data)
    			
    			console.log("success data:"+data);
    			//success data:{"msgId":"1","msgContents":"등록 성공","num":0,"totalCnt":0,"msgFlag":0}    		
    		    if(null !=jsonObj && jsonObj.msgId=="1"){
    		    	
    		    	//alert(jsonObj.msgContents);
    		    	$("#save_button_div").empty();
    		    	$("#unsave_button_div").empty();
    		    	
    		    	var html4 = "";
    				html4 += "				<input type='button' class='button' value='저장'";
    				html4 += "						id='save_button'";
    				html4 += "						style='width: 90px; font-size: 1.0em; color: black; background-color: white; font-weight: bold; border: 0.1em solid gray; border-radius: 10px; padding: 5px; margin: 10px; float: left;'";
    				html4 += "						onclick='save();'";
    				html4 += "					/>";
    				
    				$("#save_button_div").append(html4);
    				$("#unsave_button_div").append(html4);
    		    	
    		    }    			
    			
    		},
    		error:function(data){//실패시 처리
    			console.log("error data:"+data);
    		},
    		complete:function(data){//성공/실패와 관계없이 수행!
    			//console.log("data:"+data);
    		}
    		
    	});
		
	}
	
	function like(){
		console.log("like()");
		var frm = document.getElementById("postMemFrm");
		frm.work_type.value = "saveOrLikePost";
		frm.store_type.value = 2;
		
		$.ajax({
    		type: "get",
    		url:"<%=cPath %>/member/member.do",
    		asyn:"true",
    		dataType:"html",
    		data:{
    			work_type:frm.work_type.value,
    			store_type:frm.store_type.value,
    			post_no:frm.post_no.value,
    			member_no:frm.member_no.value,
    			pic_group:frm.pic_group.value
    		},
    		success:function(data){//통신 성공
				var jsonObj = JSON.parse(data)
    			
    			console.log("success data:"+data);
    			//success data:{"msgId":"1","msgContents":"등록 성공","num":0,"totalCnt":0,"msgFlag":0}    		
    		    if(null !=jsonObj && jsonObj.msgId=="1"){
    		    	
    		    	//alert(jsonObj.msgContents);
    		    	$("#like_button_div").empty();
    		    	$("#dislike_button_div").empty();
    		    	
    		    	var html5 = "";
    				html5 += "				<input type='button' class='button' value='좋아요'";
    				html5 += "						id='dislike_button'";
    				html5 += "						style='width: 90px; font-size: 1.0em; color: white; background-color: #23A3A7; font-weight: normal; border: 0.1em solid lightblue; border-radius: 10px; padding: 5px; margin: 10px; float: left;'";
    				html5 += "						onclick='dislike();'";
    				html5 += "					/>";
    				
    				$("#like_button_div").append(html5);
    				$("#dislike_button_div").append(html5);
    		    	
    		    }    			
    			
    		},
    		error:function(data){//실패시 처리
    			console.log("error data:"+data);
    		},
    		complete:function(data){//성공/실패와 관계없이 수행!
    			//console.log("data:"+data);
    		}
    		
    	});
		
	}
	
	//저장
	function save(){
		
		console.log("save()");
		var frm = document.getElementById("postMemFrm");
		frm.work_type.value = "saveOrLikePost";
		frm.store_type.value = 1;
		
		$.ajax({
    		type: "get",
    		url:"<%=cPath %>/member/member.do",
    		asyn:"true",
    		dataType:"html",
    		data:{
    			work_type:frm.work_type.value,
    			store_type:frm.store_type.value,
    			post_no:frm.post_no.value,
    			member_no:frm.member_no.value,
    			pic_group:frm.pic_group.value
    		},
    		success:function(data){//통신 성공
				var jsonObj = JSON.parse(data)
    			
    			console.log("success data:"+data);
    			//success data:{"msgId":"1","msgContents":"등록 성공","num":0,"totalCnt":0,"msgFlag":0}    		
    		    if(null !=jsonObj && jsonObj.msgId=="1"){
    		    	
    		    	//alert(jsonObj.msgContents);
    		    	$("#save_button_div").empty();
    		    	$("#unsave_button_div").empty();
    		    	
    		    	var html3 = "";
    				html3 += "				<input type='button' class='button' value='저장'";
    				html3 += "						id='unsave_button'";
    				html3 += "						style='width: 90px; font-size: 1.0em; color: white; background-color: #23A3A7; font-weight: normal; border: 0.1em solid lightblue; border-radius: 10px; padding: 5px; margin: 10px; float: left;'";
    				html3 += "						onclick='unsave();'";
    				html3 += "					/>";
    				
    				$("#save_button_div").append(html3);
    				$("#unsave_button_div").append(html3);
    		    	
    		    }    			
    			
    		},
    		error:function(data){//실패시 처리
    			console.log("error data:"+data);
    		},
    		complete:function(data){//성공/실패와 관계없이 수행!
    			//console.log("data:"+data);
    		}
    		
    	});
		
	}
	
	//언팔로우
	function unfollowInPost(){
		console.log("unfollowInPost()");
		var frm = document.getElementById("postMemFrm");
		frm.work_type.value = "unfollowInPost";
		
		$.ajax({
    		type: "get",
    		url:"<%=cPath %>/member/member.do",
    		asyn:"true",
    		dataType:"html",
    		data:{
    			work_type:frm.work_type.value,
    			following_no:frm.following_no.value,
    			member_no:frm.member_no.value
    		},
    		success:function(data){//통신 성공
				var jsonObj = JSON.parse(data)
    			
    			console.log("success data:"+data);
    			//success data:{"msgId":"1","msgContents":"등록 성공","num":0,"totalCnt":0,"msgFlag":0}    		
    		    if(null !=jsonObj && jsonObj.msgId=="1"){
    		    	
    		    	//alert(jsonObj.msgContents);
    		    	$("#follow_button_div").empty();
    		    	$("#unfollow_button_div").empty();
    		    	
    		    	var html2 = "";
    				html2 += "				<input type='button' class='button' value='팔로우' ";
    				html2 += "						id='follow_button'";
    				html2 += "						style='width: 80px; font-size: 1.0em; color: black; background-color: white; font-weight: bold; border: 0.1em solid gray; border-radius: 10px; padding: 5px; margin: 10px;'";
    				html2 += "						onclick='followInPost();'";
    				html2 += "					/>";
    				$("#follow_button_div").append(html2);
    				$("#unfollow_button_div").append(html2);
    		    	
    		    }    			
    			
    		},
    		error:function(data){//실패시 처리
    			console.log("error data:"+data);
    		},
    		complete:function(data){//성공/실패와 관계없이 수행!
    			//console.log("data:"+data);
    		}
    		
    	});
		
	}
	
	//팔로우
	function followInPost(){
		console.log("followInPost()");
		var frm = document.getElementById("postMemFrm");
		frm.work_type.value = "followInPost";
		
		$.ajax({
    		type: "get",
    		url:"<%=cPath %>/member/member.do",
    		asyn:"true",
    		dataType:"html",
    		data:{
    			work_type:frm.work_type.value,
    			following_no:frm.following_no.value,
    			member_no:frm.member_no.value
    		},
    		success:function(data){//통신 성공
				var jsonObj = JSON.parse(data)
    			
    			console.log("success data:"+data);
    			//success data:{"msgId":"1","msgContents":"등록 성공","num":0,"totalCnt":0,"msgFlag":0}    		
    		    if(null !=jsonObj && jsonObj.msgId=="1"){
    		    	
    		    	//alert(jsonObj.msgContents);
    		    	$("#follow_button_div").empty();
    		    	$("#unfollow_button_div").empty();
    		    	
    		    	var html = "";
    				html += "				<input type='button' class='button' value='팔로우 중'";
    				html += "						id='unfollow_button'";
    				html += "						style='width: 90px; font-size: 1.0em; color: black; background-color: gold; font-weight: bold; border: 0.1em solid gray; border-radius: 10px; padding: 5px; margin: 10px;'";
    				html += "						onclick='unfollowInPost();'";
    				html += "					/>";
    				$("#follow_button_div").append(html);
    				$("#unfollow_button_div").append(html);
    		    	
    		    }    			
    			
    		},
    		error:function(data){//실패시 처리
    			console.log("error data:"+data);
    		},
    		complete:function(data){//성공/실패와 관계없이 수행!
    			//console.log("data:"+data);
    		}
    		
    	});
		
	}
	
	function delete_post(){
		console.log("delete_post");
		var frm = document.getElementById("regform");
		frm.work_type.value = "doDelete";
		frm.submit();
	}
	
	//목록화면으로 이동
	function moveToList() {
		var listUrl = "<%=cPath%>/category/category.do?work_div=doCateRetrieve"
		console.log("-listUrl-" + listUrl);
		window.location.href = listUrl;

	}

	//목록에 Event감지 (moveToList 목록으로 이동)
	$("#move_to_list").on("click", function(e) {
		console.log("move_to_list");
		moveToList();
	});
	
	//수정페이지 이동-----------------------------------------------------
	function moveToMod() {
		var modUrl = "<%=cPath%>/post/post.do?work_type=doUpdate"
		console.log("-modUrl-"+modUrl);
		window.location.href = modUrl;
	}

	//수정페이지 이동-----------------------------------------------------
	
	//프로필페이지로 이동--------------------------------------------------
	function MoveToMemberProfile() {
		var frm = document.getElementById("postMemFrm");
		frm.work_type.value = "member_post";
		frm.member_no.value = frm.following_no.value;
		frm.submit();
	}
	//프로필페이지로 이동--------------------------------------------------
	       
	
	

</script>



</html>