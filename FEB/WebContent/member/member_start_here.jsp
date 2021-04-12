<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ include file="/cmn/common.jsp" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>member_start_here.jsp</title>
    <link rel="stylesheet" href="./member_style.css">
  </head>
  <body>
	<form 
       action="<%=cPath %>/member/member.do" 
       id="logFrm" 
       name="logFrm"
       method="GET"
     >
     	<input type="hidden" name="work_type" id="work_type" value="login" />
       	<div class="list_name">로그인할 유저</div> 
       	<input type="text" name="login_user" id="login_user" />
        <div class="list_name">조회할 유저</div>        
        <input type="text" name="member_no" id="member_no" />
    	<input type="submit" />
    
    </form>
    




  </body>
</html>