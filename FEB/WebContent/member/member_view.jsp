<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ include file="/cmn/common.jsp" %>
<%
	String code = request.getParameter("send_type");
	String viewPageUri = "";
	if(code.equals("following")){
		viewPageUri = "member_following.jsp";
	} else if(code.equals("followed")){
		viewPageUri = "member_following.jsp";
	} 
	
	//viewPageUri = cPath + "/WebContent/member/"+viewPageUri;
	LOG.debug("viewPageUri: "+viewPageUri);
	
%>

<jsp:forward page="<%=viewPageUri %>"></jsp:forward>