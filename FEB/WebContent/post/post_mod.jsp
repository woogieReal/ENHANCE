<%@page import="com.sist.feb.post.domain.PostVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/cmn/common.jsp"%>
<%
	PostVO outVO = (PostVO)request.getAttribute("outVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Enhance_project_upload</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!--  자바스크립트 -->
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" 
crossorigin="anonymous"></script>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" 
crossorigin="anonymous">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@300;400&display=swap" rel="stylesheet">
<script src="https://kit.fontawesome.com/42ce6799d7.js" crossorigin="anonymous"></script>

<!-- css -->
<style>
	body {
		padding-bottom: 30px;
	}
	
	.container {
		padding-top: 100px;
		color: black;
	}
	
	.button {
		background-color: #263343;
		font-weight: bold;
		color: #ffffff;
		border: 0.1em solid gray;
		border-radius: 6px;
		padding: 5px;
		margin: 5px;
		width: 60px;
	}
	
	.button1 {
		background-color: #f5f5f5;
		font-weight: bold;
		border: 0.1em solid gray;
		border-radius: 6px;
		padding: 4px;
		margin: 10px;
		width: 100px;
	}
	
	.form-control {
		margin: 5px;
		padding: 12px;
		border: 0.1em solid gray;
		border-radius: 5px;
		resize: none;
		width: 1100px;
	}
	
	
	
	a {
		text-decoration: none;
		color: white;
		padding: 8px 12px;
	}
	
	.navbar {
		display: flex;
		justify-content: space-between;
		align-items: center;
		background-color: #263343;
		padding: 8px 12px;
	}
	
	.navbar_logo {
		font-size: 29px;
		color: white;
	}
	
	.navbar_logo i {
		color: #d49466;
	}
	
	.navbar_menu {
		display: flex;
		font-size: 22px;
		list-style: none;
		padding-left: 0;
		margin-top: 20px;/* 추가함 */
		float: left;
	}
	
	.navbar_menu li {
		padding: 8px 12px;
	}
	
	.navbar_menu li:hover {
		background-color: #d49466;
		border-radius: 4px;
	}
	
	.navbar_icons {
		font-size: 22px;
		list-style: none;
		color: white;
		display: flex;
		margin-top: 20px;/* 추가함 */
	}
	
	.navbar_icons li {
		/* padding: 5px 12px; */
		padding: 5px 12px;
	}
	
	/* 해시태그 css  */
	ul.tag_list {
        padding: 16px 0;
    }

    .tag_list > li {
        display: inline-block;
        margin: 0 5px;
        font-size: 14px;
        letter-spacing: -.5px;
    }
    
    ul li.tag-item {
        padding: 4px 8px;
        background-color: #777;
        color: #000;
    }
    
    .tag-item:hover {
        background-color: #262626;
        color: #fff;
    }
    
     .del-btn {
        font-size: 12px;
        font-weight: bold;
        cursor: pointer;
        margin-left: 8px;
    }
    /* 해시태그 css  */
    
    .box-radio-input input[type="radio"]{
	  display:none; /* 라디오타입 동그라미 선택지 없애는거 */
	}
	
	.box-radio-input input[type="radio"] + span{
	
	  display:inline-block;
	  background:none;
	  border:1px solid #dfdfdf;  
	  padding:0px 10px;
	  text-align:center;
	  height:35px;
	  line-height:33px;
	  font-weight:500;
	  cursor:pointer;
	}
	
	.box-radio-input input[type="radio"]:checked + span{
	  border:1px solid #23a3a7;
	  background:#23a3a7;
	  color:#fff;
	}
	
	.magin >label {
		margin-left: 5px;
	}
    
</style>

</head>

<body>
	<!-- 헤더 -->
	<header>
		<nav class="navbar">
			<div class="navbar_logo">
				<ul class="navbar_menu">
					<i class="fab fa-blogger fa-2x"></i> <a href="">Enhance</a> 
					<li><a href="">당신을 위한</a></li>
					<li><a href="">탐색</a></li>
				</ul>
			</div>
			<ul class="navbar_icons">
				<li><a href="">프로젝트만들기</a></li>
				<li><a href="">로그인</a></li>
				<li><a href="">등록</a></li>
				<li><i class="fas fa-user-circle fa-2x"></i></li>
			</ul>
		</nav>
	</header>
	<!-- 헤더 -->

	<article> 
		<div class="container" role="main"> 
			<h2>Project Modify</h2>
			<hr></hr>
			<form  action="<%=cPath %>/post/post.do" method="post" id="regform" name="regform" > <!-- ${pageContext.request.contextPath} --> 
				<input type="hidden" name="work_type" value=""/>
				<div class="mb-3"> 
					<label for="title">프로젝트 제목</label> 
					<input value="<%=outVO.getTitle() %>" type="text" maxlength="100" class="form-control" name="title" id="title" placeholder="제목을 입력해 주세요"> 
				</div>
					<div class="mb-3 magin">
					<p for="category">분야 선택</p>
					<label class="box-radio-input">
						<input type="radio" name="category" id="category" value="architecture">
						<span>건축</span>
					</label>
					<label class="box-radio-input">
						<input type="radio" name="category" id="category" value="product_design">
						<span>산업디자인</span>
					</label>
					<label class="box-radio-input">
						<input type="radio" name="category" id="category" value="advertisement">
						<span>광고</span>
					</label>
					<label class="box-radio-input">
						<input type="radio" name="category" id="category" value="art">
						<span>미술</span>
					</label>
					<label class="box-radio-input">
						<input type="radio" name="category" id="category" value="3d_art">
						<span>3D아트</span>
					</label>
					<label class="box-radio-input">
						<input type="radio" name="category" id="category" value="ux_ui">
						<span>UI/UX</span>
					</label>
					<label class="box-radio-input">
						<input type="radio" name="category" id="category" value="fashion">
						<span>패션</span>
					</label>
					<label class="box-radio-input">
						<input type="radio" name="category" id="category" value="illustration">
						<span>일러스트레이션</span>
					</label>
					<label class="box-radio-input">
						<input type="radio" name="category" id="category" value="graphic">
						<span>그래픽디자인</span>
					</label>
					<label class="box-radio-input">
						<input type="radio" name="category" id="category" value="photography">
						<span>포토그래피</span>
					</label>
				</div>
				<div class="mb-3"> 
					<label for="contents">내용</label> 
					<textarea class="form-control" rows="10" cols="40" name="contents" id="contents" placeholder="내용을 입력해 주세요"><%=outVO.getContents() %></textarea> 
				</div> 
				<div id="attachFileDiv">
					<input type="button" class="button1" value="파일 추가" onclick="attachFile.add()" style="margin-left: 7px">
					<!-- <button type="submit" class="button1">태그등록</button> -->
				</div>
				<ul class="tag_list" id="tag-list"></ul>
				<div>
					<input type="text" class="form-control" name="tag" id="tag" placeholder="태그를 등록해주세요"/> 
				</div>
				

			</form> 

			<div> 
				<button type="submit" class="button" id="doUpdate" onclick="doUpdate();">수정</button> 
				<button type="button" class="button" id="btnList">취소</button>  
			</div> 
		</div> 
	</article> 
</body>


<script type="text/javascript">
	/* id는 자바 스크립트에서 다룰려고 지정하는 것이고 name은 파라미터 전송을 하기 위해서 지정하는 것이다. */

	$(document).ready(function() {
		console.log("1.document ready: 최초수행!");
		
	});//--document ready
	
	function doUpdate() {
		
		var frm = document.getElementById("regform") //<form>태그에 주어진 ID를 가진 요소의 참조를 반환합니다.
		frm.work_type.value = "doUpdate";
		console.log("doUpdate")
		
		var title = $("#title").val();//jQuery방식 id선택자
		console.log("title: "+title);
		if (null === title || title.trim().length ==0 ) {
			document.getElementById("title").focus();
			alert("제목을 입력해 주십시오");
			return; //내용이 입력되지 않으면 처음으로 돌아감
		}
		
		var category = $('input:radio[name=category]:checked').val(); //선택된 radio의 value가져오기
		console.log("category"+category)
		if (category == undefined) {
			alert("분야를 선택해 주십시오");
			return;
		}
		
		var contents = frm.contents.value; //javaScript방식 id선택자
		if (null === contents || contents.trim().length ==0) {
			document.getElementById("contents").focus();
			alert("내용을 입력해 주십시오.")
			return;
		}
		
		//태그----------------------------------------------------------------
		 	var tag = {};
	        var counter = 0;

	        // 태그를 추가한다.
	        function addTag (value) {
	            tag[counter] = value; // 태그를 Object 안에 추가
	            counter++; // counter 증가 삭제를 위한 del-btn 의 고유 id 가 된다.
	        }

	        // 최종적으로 서버에 넘길때 tag 안에 있는 값을 array type 으로 만들어서 넘긴다.
	        function marginTag () {
	            return Object.values(tag).filter(function (word) {
	                return word !== "";
	            });
	        }
	   
	        // 서버에 넘기기
	        $("#tag-form").on("submit", function (e) {
	            var value = marginTag(); // return array
	            $("#rdTag").val(value); 

	            $(this).submit();
	        });

	        $("#tag").on("keypress", function (e) {
	            var self = $(this);

	            // input 에 focus 되있을 때 엔터 및 스페이스바 입력시 구동
	            if (e.key === "Enter" || e.keyCode == 32) {

	                var tagValue = self.val(); // 값 가져오기

	                // 값이 없으면 동작 ㄴㄴ
	                if (tagValue !== "") {
	                    // 같은 태그가 있는지 검사한다. 있다면 해당값이 array 로 return 된다.
	                    var result = Object.values(tag).filter(function (word) {
	                        return word === tagValue;
	                    })
	                
	                    // 태그 중복 검사
	                    if (result.length == 0) { 
	                        $("#tag-list").append("<li class='tag-item'>"+tagValue+"<span class='del-btn' idx='"+counter+"'>x</span></li>");
	                        addTag(tagValue);
	                        self.val("");
	                    } else {
	                        alert("태그값이 중복됩니다.");
	                    }
	                }
	                e.preventDefault(); // SpaceBar 시 빈공간이 생기지 않도록 방지
	            }
	        });

	        // 삭제 버튼 
	        // 삭제 버튼은 비동기적 생성이므로 document 최초 생성시가 아닌 검색을 통해 이벤트를 구현시킨다.
	        $(document).on("click", ".del-btn", function (e) {
	            var index = $(this).attr("idx");
	            tag[index] = "";
	            $(this).parent().remove();
	        });
	//태그----------------------------------------------------------------
		
		if (false == confirm("수정하시겠습니까?"))
			return;
		
	
		$.ajax({
			type: "POST",
			url: "<%=cPath%>/post/post.do",
			asyn: "true",
			dataType : "html",
			data : {
				work_type : "doUpdate",
				title : $("#title").val(),
				category : $("#category").val(),
				contents : $("#contents").val()
			},
			//통신 성공 시 처리
			success : function(data) {
				var jsonObj = JSON.parse(data);
				console.log("success data: "+data);
				
				if(null != jsonObj && jsonObj.msgId == "1") {
					alert(jsonObj.msgContents);
				}
			},
			//실패했을 시 처리
			error : function(data) {
				console.log("error data: " + data);
			},
			//성공,실패 관계없이 무조건 실행
			complete: function(data) {
				console.log("data: "+data);
			}
		});
		
		//동기식으로 실행(BoardController와 연동)
		frm.submit();
		
	}//--doInsert (javascript&jQuery&Ajax)

	       

</script>



</html>