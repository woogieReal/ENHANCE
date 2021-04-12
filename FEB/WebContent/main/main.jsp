<%@page import="com.sist.feb.search.domain.SearchDetailVO"%>
<%@page import="com.sist.feb.member.cmn.StringUtil"%>
<%@page import="com.sist.feb.member.domain.MemberVO"%>
<%@page import="com.sist.feb.category.domain.SearchVO"%>
<%@page import="com.sist.feb.category.domain.CatePostVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- core -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/cmn/common.jsp" %>
<%
	//LOG.debug("cPATH:"+cPath);
 	List<CatePostVO> resultList = (List<CatePostVO>)request.getAttribute("list");
	LOG.debug("list ::"+resultList); 
	
	//param
	SearchVO paramTmp = (SearchVO)request.getAttribute("param");
	SearchVO param = new SearchVO();
	
	try{
		param.setSelectDiv(paramTmp.getSelectDiv());
	} catch(NullPointerException e){
		param.setSelectDiv("doCateRetrieve");
	}
	
	
	SearchDetailVO searchParam = new SearchDetailVO();
	
	if(null == request.getAttribute("searchParam")){
		searchParam.setSearchDiv("전체");
		searchParam.setSearchWord("");
	} else if(null != request.getAttribute("searchParam")){
		searchParam = (SearchDetailVO)request.getAttribute("searchParam");
	}
	
	
	LOG.debug("param:"+param);
	//호출 URL
	String url = cPath+"/category/category.do";
	LOG.debug("url:"+url);
	
	/* request.setAttribute("lista", list); */
	String flagStr = StringUtil.nvl(request.getParameter("flag"), "1");
	
	int flag = Integer.parseInt(flagStr);
	
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ENHANCE MAIN</title>
		<!-- jQuery -->
		<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>	
		
		<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
		<script src="/FEB/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="/FEB/main_css/style.css">
	</head>
	<body>
		<!-- Start of Container -->
		<div id="wrap container">
		<!-- header 모양 가져오기 -->
	  	<jsp:include page="/cmn/header.jsp"></jsp:include>
	  	
		
		<!-- Start of main-select box -->
			<form action="/FEB/category/category.do"  id="retrieve" name="retrieve" method="get">
				<!-- hidden -->
				<input type="hidden" name="cateRetrieve" value="<%=param.getSelectDiv() %>"  />
				<input type= "hidden" name="work_div"	value="" />
				<input type= "hidden" name="start_flag" id="start_flag"	value="<%=flag %>" />
				<!-- //hidden -->
					
					
				<!-- Start of main-post -->
				<main id="main_post" class="frame">
					
					<div class="search_div_box">
						<!-- Search Word -->	
						<div class="search_div" style="float: right;">
							<select name="search_select" id="search_select" class="select_box" >
								<option value="전체" <%if(null != searchParam.getSearchDiv() && "전체".equals(searchParam.getSearchDiv())){ out.print("selected");} %> >전체</option>
								<option value="제목" <%if(null != searchParam.getSearchDiv() && "제목".equals(searchParam.getSearchDiv())){ out.print("selected");} %> >제목</option>
								<option value="작성자" <%if(null != searchParam.getSearchDiv() && "작성자".equals(searchParam.getSearchDiv())){ out.print("selected");} %> >작성자</option>
							</select>
							<input type="text" name="search_word" id="search_word" value=""/>
							<input type="submit" name="search_button" id="search_button" onclick="doSearch();" value="검색" />
						</div>
						<!--// Search Word -->
						
						<!-- selectbox -->
						<div class="search_div" style="float: left;">
							<select name="selectDiv" id="selectDiv" onchange="doCateRetrieve();" class="select_box" >
									<option value="">모든 카테고리 조회</option>
									<option value="architecture" <%if(null != param.getSelectDiv() && "architecture".equals(param.getSelectDiv())){ out.print("selected");} %>>architecture</option>
									<option value="product_design" <%if(null != param.getSelectDiv() && "product_design".equals(param.getSelectDiv())){ out.print("selected");} %>>product_design</option>
									<option value="advertisement" <%if(null != param.getSelectDiv() && "advertisement".equals(param.getSelectDiv())){ out.print("selected");} %>>advertisement</option>
									<option value="art" <%if(null != param.getSelectDiv() && "art".equals(param.getSelectDiv())){ out.print("selected");} %>>art</option>
									<option value="3d_art" <%if(null != param.getSelectDiv() && "3d_art".equals(param.getSelectDiv())){ out.print("selected");} %>>3d_art</option>
									<option value="ux_ui" <%if(null != param.getSelectDiv() && "ux_ui".equals(param.getSelectDiv())){ out.print("selected");} %>>ux_ui</option>
									<option value="fashion" <%if(null != param.getSelectDiv() && "fashion".equals(param.getSelectDiv())){ out.print("selected");} %>>fashion</option>
									<option value="illustration" <%if(null != param.getSelectDiv() && "illustration".equals(param.getSelectDiv())){ out.print("selected");} %>>illustration</option>
									<option value="graphic" <%if(null != param.getSelectDiv() && "graphic".equals(param.getSelectDiv())){ out.print("selected");} %>>graphic</option>
									<option value="photography" <%if(null != param.getSelectDiv() && "photography".equals(param.getSelectDiv())){ out.print("selected");} %>>photography</option>
							</select>
							<!-- Button area -->
						    <!-- <div class="row">
						    	<input type="button" value="조회" onclick="doCateRetrieve();" />
						     </div> -->
						    <!-- //Button area -->
						</div>
						<!-- //selectbox -->
					</div>
					
	    
				<!-- ~를 위한 추천 메세지 띄우기 -->
				<div class="main_page_title">
					<h2 class="main_page_title_word">
					<c:choose>
						<c:when test="${null != sessionScope.memberInfo }"><!-- 로그인 했을때 -->
								${sessionScope.memberInfo.name}님을 위한 추천
						</c:when>
						<c:otherwise><!-- 로그인 안했을때 -->
							당신을 위한 추천
						</c:otherwise>
					</c:choose>
					</h2>
				</div>
				
				<div class="main_item_box">
					<div class="main_post_list">
					<c:choose>
						<c:when test="${list.size()>=0 }">
							<c:forEach var="vo" items="${list}">
									<div class="main_post_box_one">
									<a class="link_post" href="${cPath}/post/post.do?work_type=doSelectOne&post_no=${vo.postNo}"></a>
										<img src="${vo.path}" style="object-fit: cover; padding: 0px; border-radius: 7px; " class="main_post_item_img"/> <!-- 게시물 이미지 -->
										<ul class="main_post_item_one">
											<li class="member_no" style="display:none;" ><c:out value="${vo.memberNo}"/></li> <!-- 게시한 멤버번호 -->
											<li class="pic_group" style="display:none" ><c:out value="${vo.picGroup}"/></li> <!-- 게시물 픽쳐그룹 -->
											<li class="MemberName"><c:out value="${vo.memberName}"/></li> <!--멤버이름-->
											<li class="cateName"><c:out value="${vo.cateName}"/></li> <!-- 카테고리 분야명-->
											<li class="title"><c:out value="${vo.title}"/></li> <!-- 제목 -->
											<li class="readCnt"><c:out value="${vo.readCnt}"/>명이 조회함</li> <!-- 조회수 default = 0 -->
										</ul>
									</div>
									
								</c:forEach>
							</c:when>
						<c:otherwise>
			     			<ul><li>No data found</li></ul>
			     		</c:otherwise>
		     		</c:choose>
					</div>
				</div>
				
			</main>
			<!-- End of main-post -->
			</form>
			<!-- End of main-select box  -->
		<!-- footer -->		
			<jsp:include page="/cmn/footer.jsp" ></jsp:include>
		<!--// footer -->
		</div>
		<!-- End of Container -->
		 <!--// contents -->
		 
    <script type="text/javascript">
		
		//카테고리 영역선택 
		 function doCateRetrieve(){
			 console.log("doCateRetrieve");
			 var frm = document.getElementById("retrieve");
			 console.log("frm:"+frm);
			 frm.work_div.value ="doCateRetrieve";
			 console.log("frm.work_div.value:"+frm.work_div.value);
			 frm.submit();
		 }//--doCateRetrieve
		 
		 function moveToOtherUserProfile(param){
	  		 console.log("profile_image");
	  		 var frm = document.getElementById("folFrm");
	  		 var member_no = param;
	  		 console.log("member_no: "+member_no);
	  		
			 var listUrl ="${cPath}/member/member.do?work_type=member_post&member_no="+member_no;
			 console.log("listUrl: "+listUrl);
			 window.location.href=listUrl;
		 }//--moveToOtherUserProfile
	 
		 function doSearch(){
			 console.log("doSearch");
			 var frm = document.getElementById("retrieve");
			 frm.work_div.value ="doSearch";
			 
		 }  
		
    </script>
	</body>
</html>