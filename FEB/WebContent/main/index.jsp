<%@page import="com.sist.feb.member.cmn.StringUtil"%>
<%@page import="com.sist.feb.member.domain.MemberVO"%>
<%@page import="com.sist.feb.category.domain.SearchVO"%>
<%@page import="com.sist.feb.category.domain.CatePostVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:forward page="/category/category.do">
	<jsp:param value="work_div" name="doCateRetrieve"/>
</jsp:forward>