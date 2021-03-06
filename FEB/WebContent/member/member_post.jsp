<%@page import="com.sist.feb.member.cmn.StringUtil"%>
<%@page import="com.sist.feb.member.domain.MemberVO"%>
<%@page import="com.sist.feb.member.service.MemberService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sist.feb.storage.domain.MemberPostVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ include file="/cmn/common.jsp" %>
<%
	int member_no = 0;
/* 	if(null != request.getParameter("no_to_follow")){
		member_no = Integer.parseInt(request.getParameter("no_to_follow"));
	}  */
	
	if(null != request.getParameter("member_no")){
		member_no = Integer.parseInt(StringUtil.nvl(request.getParameter("member_no"), "0"));
	} else{
		member_no = Integer.parseInt(request.getParameter("no_to_follow"));
	}
	
	MemberService service = new MemberService();
	MemberVO memberTmp = new MemberVO(member_no, "", "", "", "", "", "");
	MemberVO member = service.doSelectOne(memberTmp);
	List<MemberPostVO> resultList = new ArrayList<MemberPostVO>();
	
	if(null != request.getAttribute("list")){
		resultList = (List<MemberPostVO>)request.getAttribute("list");	
	} else {
		resultList = service.doInquirePost(member);
	}


%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>member_follow</title>
    <link rel="stylesheet" href="./member_style.css">
  </head>
  <body>
    <div style="width: 800px;">
    	<div style="margin-bottom: 20px;"><span style="font-weight: bold;"><%=member.getName() %></span> 님의 작업물 입니다.</div>
    	
    	<%
    		if(null != resultList && resultList.size() > 0){
    			for(MemberPostVO vo : resultList){
    		
    	%>
			<div class="member_post_box">
				<img src="<%=vo.getPath() %>" alt="image" style="width: 240px; height: 240px; object-fit: cover; border-radius: 10px;"/>
			</div>
			
		<%
    			}
    		} else {
		%>
    		
    		<span>등록한 게시물이 없습니다.</span>
    		
    	<%
    		}
    	%>
    	
    
    </div>
  </body>
</html>