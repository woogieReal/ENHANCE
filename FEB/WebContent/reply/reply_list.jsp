<%@page import="com.sist.feb.member.cmn.StringUtil"%>
<%@page import="com.sist.feb.reply.domain.ReplyMemberVO"%>
<%@page import="com.sist.feb.member.domain.MemberVO"%>
<%@page import="com.sist.feb.post.domain.PostVO"%>
<%@page import="java.util.List"%>
<%@page import="com.sist.feb.reply.domain.ReplyVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/cmn/common.jsp" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%
	
	PostVO param = (PostVO)request.getAttribute("param");

%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>reply</title>
 <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>	   
</head>
<body>

<!-- 덧글창 css -->
<style>

.action{
	width: 1000px; height: 100px;  margin:0 auto;
}

.reply_box{
	
	text-align:center;
    width: 800px;
    font-size:15px;
    margin:0 auto;
}
      
 .reply {
	text-align:center;
    margin: 0;
    padding: .3rem;
    font: 1rem 'Fira Sans', sans-serif;
	margin:0 auto;
} 

.reply > h2,
.reply_list {

    margin: .5rem;
    padding: .5rem;
  	font-size:15px;
    width: 1000px;
	margin:0 auto;
}

 .reply_list {
    background: right/contain content-box border-box no-repeat
        url('/media/examples/rain.svg') white;
} 

.reply_list > h2,
.reply_list > td {
    margin: .1rem;
    font-size:50px;
}
.button {
	background-color: #263343;
	font-weight: bold;
	color:#ffffff;
	border: 0.1em solid gray;
	border-radius: 10px;
	padding: 5px;
	margin: 5px;
	width: 60px;
	font-size:5px;
}

.button2{

background-color: #263343;
	font-weight: bold;
	color:#ffffff;
	border: 0.1em solid gray;
	border-radius: 6px;
	padding: 5px;
	margin: 5px;
	width: 40px;
	font-size:3px;
	}
	.replytbutton{
	float:left;
	}


	.reply_list{
		width: 800px;
		height: 50px;

	}	
	
	.left_box{
	width: 150px;
	height: 50px;
	/* top right left bottom  */
	margin: 0px 5px 0px 10px;
	padding: 3px;
	float: left;
	

	}	
	
	.middle_box{
	width: 400px;
	height: 50px;
	/* top right left bottom  */
	margin: 0px;
	padding: 0px;
	float: left;


	}	
	
	.right_box{
	width: 100px;
	height: 50px;
	margin: 0px 10px 0px 10px;
	padding: 0px;
	float: right;


	}
	
	.info_box{
	width: 400px;
	height: 20px;
	margin: 0px 0px 10px 0px;
	padding: 0px;
	float: left;
	font-size: 17px;
	font-weight: bold


	}
	
	.contents_box{
	width: 400px;
	height: 20px;
	margin: 0px 0px 0px 0px;
	padding: 0px;
	float: left;


	}
	
	.date_box{
	width: 100px;
	height: 20px;
	margin: 0px 0px 0px 0px;
	padding: 0px;
	float: left;


	}
	
	.delete_box{
	width: 100px;
	height: 20px;
	margin: 0px 0px 0px 0px;
	padding: 0px;
	float: left;


	}
</style>      

<form action="<%=cPath %>/reply/reply.do" id="replyForm" name="replyForm" method="post">
 <input type="hidden" name="work_div" value=""/>
    <%--  <c:if test="${not empty sessionScope.loginId}"> --%>
    <div id="reply">
 	<section class="replyForm">
 			<div class="reply_box">
    		<input type="text" class="reply_box"  name="contents" id="contents" class="form-control" placeholder="이 프로젝트에 대해 어떻게 생각하십니까?" />
    		 <input type="button" class="button" onclick="replyInsert();" value="댓글달기" />
    		 <input type="hidden" onclick="doRetrieve();"  value="조회" />
    		 </div>
    </section>
    </div>
	<%-- </c:if> --%>



<hr/>

	<table id="reply_table"  class="reply_list" style="width:50;" >
	</table> 

</form>	

  
  <script type="text/javascript">
	$(document).ready(function (){
		console.log("1. document:최초수행");
		doRetrieve();
	});//--document ready
	
	
	 function doRetrieve() {
		   //console.log("doRetrieve");  
		  // alert("doRetrieve()");
		
		   
            $.ajax({
                type:"get",
                url:"<%=cPath %>/reply/reply.do",
                dataType:"html",// JSON
                data:{
                	work_div:"doRetrieve",
                    "reply_no": $("#reply_no").val(),
                    "post_no": $("#post_no").val(),
                    "contents": $("#contents").val(),
                    "reg_dt": $("#reg_dt").val(),
                    "member_no": $("#member_no").val(),
                    "name": $("#name").val(),
                    "email": $("#email").val(),
                    "path" : $("#path").val()
					
                },
                success: function(data){//통신이 성공적으로 이루어 졌을때 받을 함수
                    //alert("data:"+data);
                   
                	var jsonObj = $.parseJSON(data);
                	console.log(jsonObj);
                    
                    var html = "";
                    //기존tbody 하위 삭제
                    $("#reply_table").empty();
                    
                    if(jsonObj.length < 1){
                     html += '<tr>';
                     html += '<td class="text-center" colspan="99">등록된 댓글이 없습니다.</td>';
                     html += '</tr>';
                    }else{
                   
                      $.each(jsonObj,function(key,value) {
                      //console.log('reply_no'+':'+value.reply_no);
                      html +="<div class='reply_list' >";
                      html +=	"<div class='left_box' name='path' id='path'>";
                      html +=		"<img alt='' style='width: 50px; height: 50px; object-fit: cover; border-radius: 10px;' ' src="+value.path+" />";
                      html +=	"</div >";
                     
                      html +=	"<div class='middle_box'>";
                     
                      html +=		"<div class='info_box'>";
                      html +=			"<td name='name' id='name'>"+value.name+"</td>";
                      html +=				"<br/>";
                      //html +=			"<td name='email' id='email'>"+value.email+"</td>";
                      html +=		"</div >";
                   
                      
                      html +=	"<div class='contents_box'>";
                      html +=		"<td>"+value.contents+"</td>";
                      html +=	"</div >";
                      html +=	"</div >";
                      
                      html +=	"<div class='right_box'>";
                      html +=		"<div class='date_box'>";
                      html +=			"<td >"+value.reg_dt+"</td>";
                      html +=		"</div >";
                      html +=		"<div class='delete_box'>";
                      html +=			"<input type='button' class='button2' value='삭제' onclick='doDelete("+value.reply_no+")'/>";
                      html +=		"</div >";
                      html +=	"</div >";
                      
                      html +="</div >";
                      
                     // html +="<td class='text-center' name='member_no' id='member_no'>"+value.member_no+"</td>";
                     // html +="<td name='reply_no' id='reply_no'>"+value.reply_no+"</td>";
    
                     });

                    }
                    $("#reply_table").append(html);
            
                },
                complete: function(data){//무조건 수행
                 
                },
                error: function(xhr,status,error){
                 
                }
           }); 
  }

  
  
  
  function replyInsert(){
	    
	    console.log("replyInsert");
	    
	    var frm = document.getElementById("replyForm");
	   
	    var contents = frm.contents.value;
	    console.log("contents:"+contents);
    	if(null === contents || contents.trim().length==0){
    		document.getElementById("contents").focus();
    		alert("댓글을 입력 하세요.")
    		return;   		
    	}
    	
    	if(  false ==confirm("등록 하시겠습니까?") )return;
    	
    	
    	
    	$.ajax({
    		type: "get",
    		url:"<%=cPath %>/reply/reply.do",
    		asyn:"true",
    		dataType:"html",
    		data:{
    			work_div:"doInsert",
    			post_no:$("#post_no").val(),
    			contents: $("#contents").val()
    			
    		},
    		success:function(data){//통신 성공
    			var jsonObj = JSON.parse(data)
    			
    			console.log("success data:"+data);
    			//success data:{"msgId":"1","msgContents":"등록 성공","num":0,"totalCnt":0,"msgFlag":0}    		
    		    if(null !=jsonObj && jsonObj.msgId=="1"){
    		    	
    		    	alert(jsonObj.msgContents);
    		    	
    		    	//댓글리스트로 가는거 
    		    	doRetrieve();
    		    	
    		    	$("#contents").val("");
      				
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
  

   function doDelete(reply_no){
  
		console.log("doDelete");
		console.log("reply_no:"+reply_no);
		
    	var frm = document.getElementById("replyForm");
    	frm.work_div.value ="doDelete";
    	console.log("frm.work_div.value :"+frm.work_div.value);
    	
		
		if(null === reply_no || reply_no.length==0){
			alert("reply_no를 확인하세요");
			return ;
		}
		
		if(false==confirm("삭제하시겠습니까?")) return;
		
		$.ajax({
	  		type: "get",
	  		url:  "<%=cPath %>/reply/reply.do",
	  		asyn: "true",
	  		dataType : "html",
	  		data:{
	  			work_div:"doDelete",
	  			reply_no : reply_no
	  		},
  		success:function(data){//통신 성공
  			//alert("data"+data);
  			var jsonObj = JSON.parse(data);
  			console.log("success data"+data);
  			if(null != jsonObj && jsonObj.msgId=="1"){
  				$("#contents").val("");
  				alert(jsonObj.msgContents);
  				
  				doRetrieve();
  				//댓글리스트로 가는거 
  				
  			}else{//실패
  				alert(jsonObj.msgContents);
  			}
  		},
  		error:function(data){//실패시 처리
  			console.log("error data"+data);
  		},
  		complete:function(data){//성공/실패와 관계없이 수행!
  			//console.log("data"+data);
  		}
  	});
			
  
 } 

</script>     
</body>
</html>