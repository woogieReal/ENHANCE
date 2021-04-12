<%@page import="com.sist.feb.member.domain.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- core -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	MemberVO loginMember = (MemberVO) session.getAttribute("memberInfo");
	if(null == session.getAttribute("memberInfo")){
		loginMember = new MemberVO(0, "", "", "", "", "", "");
	}
	
	request.setAttribute("loginMemberNo", loginMember.getMember_no());
%>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
		<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
		<script src="/FEB/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="/FEB/cmn/header_style.css">
	</head>
	<body style="margin: 0px;">
		<div class="header_entire">

				<!-- url jstl -->
				<c:url var="eMain" value="/category/category.do">
					<c:param name="work_div" value="doCateRetrieve"></c:param>
				</c:url>
				<c:url var="ePost" value="/post/post_reg.jsp">
				</c:url>
		
				<!-- 로고 이미지 -->
				<div class="logo_box">
					<a class="nav-link" href='<c:out value="${eMain }"/>'>
						<img alt="Brand" src="/FEB/cmn/e_copy.png" style="width: 40px;" />
					</a>
				</div>
				<!--// 로고 이미지 -->
				
				<c:if test="${loginMemberNo != 0 }">
					<div class="left_align text_div" style="margin-left: 40px;">	
					 	<a class="nav-link" href='<c:out value="${ePost }"/>'>프로젝트 만들기</a>
					</div>	
				</c:if>
				

				<div class="right_align text_div" >
					  <!--Profile -->
					<c:url var="eLoginProfile" value="/member/member.do">
						<c:param name="work_type" value="member_post"></c:param>
						<c:param name="login_user" value="${sessionScope.memberInfo.member_no }"></c:param>
						<c:param name="member_no" value="${sessionScope.memberInfo.member_no }"></c:param>
					</c:url>
					
					<c:url var="eLogoutProfile" value="/member/member_login.jsp">
					</c:url>
					
				 	<c:choose>
				 		<c:when test="${null != sessionScope.memberInfo }">
					 		<a class="nav-link" href='<c:out value="${eLoginProfile }"/>'>
					 		 <span class="glyphicon glyphicon-eye-open" aria-hidden="true">Profile</span>
					 		</a>
						 </c:when>
						 
						 <c:otherwise>
					 		<a class="nav-link" href='<c:out value="${eLogoutProfile }"/>'>
					 			<span class="glyphicon glyphicon-eye-close" aria-hidden="true">Profile</span>
					 		</a>
						 </c:otherwise>
				 	</c:choose>
					<!--// Profile -->
				</div>	
				<div class="right_align text_div">
					 <!-- Login&out -->
					 <c:url var="eLogin" value="/member/member_login.jsp">
					</c:url>
					<c:url var="eLogout" value="/member/member.login">
						<c:param name="work_div" value="logout"></c:param>
					</c:url>
				 	<c:choose>
				 		<c:when test="${null != sessionScope.memberInfo }">
					 		<a class="nav-link" href='<c:out value="${eLogout }"/>'>
					 		 <span class="glyphicon glyphicon-log-out" aria-hidden="true">Logout</span>
					 		</a>
						 </c:when>
						 <c:otherwise>
					 		<a class="nav-link" href='<c:out value="${eLogin }"/>'>
					 		 <span class="glyphicon glyphicon-log-in" aria-hidden="true">Login</span>
					 		</a>
						 </c:otherwise>
				 	</c:choose>
					<!--// Login&out -->
				</div>
		</div>
	</body>
</html>