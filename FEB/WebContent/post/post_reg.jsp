<%@page import="com.sist.feb.picture.domain.PictureVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/cmn/common.jsp"%>
<!-- core태그 -->
<%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 국제화 태그 -->
<%@ taglib prefix= "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Enhance_project_upload</title>
	<!-- jQuery -->
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	
	<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
	<script src="/FEB/js/bootstrap.min.js"></script>

<style>
	
	.container {
		margin: 60px;
		
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
		width: 1050px;
	}
	
	/* ---해시태그 css ----  */
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
    /* //---해시태그 css ---- */
    
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
	  	<jsp:include page="/cmn/header.jsp"></jsp:include>
	<!-- 헤더 -->

	<article> 
		<div class="container" role="main"> 
			<h2>Project Upload</h2>
			<hr></hr>
			<form  action="<%=cPath %>/post/post.do" method="post" id="regform" name="regform" > 
				<input type="hidden" name="work_type" value=""/>
				<input type="hidden" name="fileList" id="fileList" value=""/>
				<input type="hidden" name="memberInfo" id="memberInfo" value=""/>
				<!-- title, category, contents -->
				<div class="mb-3"> 
					<label for="title">프로젝트 제목</label> 
					<input type="text" maxlength="100" class="form-control" name="title" id="title" placeholder="제목을 입력해 주세요"> 
				</div>
					<div class="mb-3 magin">
					<p for="category">분야 선택</p>
					<label class="box-radio-input">
						<input type="radio" name="category" id="category" value="1">
						<span>건축</span>
					</label>
					<label class="box-radio-input">
						<input type="radio" name="category" id="category" value="2">
						<span>산업디자인</span>
					</label>
					<label class="box-radio-input">
						<input type="radio" name="category" id="category" value="3">
						<span>광고</span>
					</label>
					<label class="box-radio-input">
						<input type="radio" name="category" id="category" value="4">
						<span>미술</span>
					</label>
					<label class="box-radio-input">
						<input type="radio" name="category" id="category" value="5">
						<span>3D아트</span>
					</label>
					<label class="box-radio-input">
						<input type="radio" name="category" id="category" value="6">
						<span>UI/UX</span>
					</label>
					<label class="box-radio-input">
						<input type="radio" name="category" id="category" value="7">
						<span>패션</span>
					</label>
					<label class="box-radio-input">
						<input type="radio" name="category" id="category" value="8">
						<span>일러스트레이션</span>
					</label>
					<label class="box-radio-input">
						<input type="radio" name="category" id="category" value="9">
						<span>그래픽디자인</span>
					</label>
					<label class="box-radio-input">
						<input type="radio" name="category" id="category" value="10">
						<span>포토그래피</span>
					</label>
				</div><br/>
				
				<textarea class="form-control" rows="10" cols="40" name="contents" id="contents" placeholder="내용을 입력해 주세요"></textarea> 
				<!--// title, category, contents -->
				 
				 <!-- 이미지file등록 -->
 				<div id="attachFileDiv">
					<input type="button" class="button1" value="파일 등록"  onclick="showPopup(this.form);">
					
				</div> 
				<!--// 이미지file등록 -->
				
				<!-- 태그등록 -->
				<div>
					<button type="button" class="button1" id="tag-form">태그 등록</button>
					<input type ="hidden" value="" name="tagArr" id="tagArr" />
					<ul id="tag-list"></ul>
				</div>
				<div>
					<input type="text" class="form-control" id="tag" id="tag" placeholder="태그를 등록해주세요" />
				</div>
				<!--// 태그등록 -->
			</form>
			<br/>
			
			<!-- 게시물 저장 후 상세게시글 이동 or 취소 후 목록이동 -->
			<div> 
				<button type="button" class="button" onclick="doInsert();">저장</button> 
				<button type="button" class="button" onclick="moveToList();">취소</button>  
			</div> 
			<!--// 게시물 저장 후 상세게시글 이동 or 취소 후 목록이동 -->
		</div> 
	</article> 
</body>


<script type="text/javascript">
	/* id는 자바 스크립트에서 다룰려고 지정하는 것이고 name은 파라미터 전송을 하기 위해서 지정하는 것이다. */

	$(document).ready(function() {
		console.log("1.document ready: 최초수행!");
		
		
	});//--document ready
	
	function doInsert() {
		
		var frm = document.getElementById("regform") //<form>태그에 주어진 ID를 가진 요소의 참조를 반환합니다.
		frm.work_type.value = "doInsert";
		console.log("doInsert")
		
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
		
/* 		var file = frm.file.value;
		if (null === file || file.trim().length==0) {
			alert("이미지를 등록해 주십시오.")
		} */
		
		var tagArr = frm.tagArr.value;

		
		if (false == confirm("저장하시겠습니까?"))
			return;
		
	
 		$.ajax({
			type: "POST",
			url: "<%=cPath%>/post/post.do",
			asyn: "true",
			dataType : "html",
			data : {
				work_type : "doInsert",
				title : $("#title").val(),
				//category : $("#category").val(),
				category :category,
				contents : $("#contents").val(),
				//file 	 : $("#file").val(),
				tagArr : $("#tagArr").val(),
				fileList : $("#fileList").val()
			},
			//통신 성공 시 처리
			success : function(data) {
				var jsonObj = JSON.parse(data);
				console.log("success data: "+data);
				
				if(null != jsonObj && jsonObj.msgId == "1") {
					alert(jsonObj.msgContents);
					
					moveToList();
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
		
		//동기식으로 실행(PostController와 연동)
		//frm.submit(); //ajax사용할때는 frm.submit을 하면 안된다(두번 전송됨)
		
	}//--doInsert (javascript&jQuery&Ajax)
	
	
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
        $("#tag-form").on("click", function (e) {
            var value = marginTag(); // return array
            $("#tagArr").val(value); 

			alert($("#tagArr").val());
			
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
		
		
		//팝업 창 띄우기
		 function showPopup(frm) { 
			console.log('showPopup()');
		
			var title ="파일 업로드";
			var option  ="toolbar=0,scrollbars=no,resizable=no,status=yes,width=1000,height=300,left=100,top=50";
			
			window.open("",title,option);
			
			frm.target = title;
			frm.method = "get";
			frm.action = "<c:url value='/post/file_upload_popup.jsp' />";
			frm.submit();
		}


		//popup에서 가져온 값 처리
		function setSendChild(param) {
			alert("이미지 등록이 완료되었습니다!");
			
 			var jsonString = JSON.stringify(param);//Object를 String으로 받아와줌
			console.log("jsonString:::::::"+jsonString);
			$("#fileList").val(jsonString); 
			
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
		

</script>



</html>