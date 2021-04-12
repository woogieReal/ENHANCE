<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">
<title>Enhance_project_upload</title>



                                                     <!--  자바스크립트 -->
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">


                                             <!-- 파일 업로드 삭제 스크립트 -->
<script language="JavaScript">
    attachFile = {
        idx:0,
        add:function(){ // 파일필드 추가
            var o = this;
            var idx = o.idx;

            var div = document.createElement('div');
            div.style.marginTop = '3px';
            div.id = 'file' + o.idx;

            var dv = document.createElement('dv');
            dv.style.marginTop = '3px';
            dv.id = 'dv' + o.idx;

            var file = document.all ? document.createElement('<input name="files">') : document.createElement('input');
            file.type = 'file';
            file.name = 'files';
            file.size = '40';
            file.id = 'fileField' + o.idx;
            file.onchange = function(){o.prev(this,'dv'+idx)};

            var btn = document.createElement('input');
            btn.type = 'button';
            btn.value = '삭제';
            btn.onclick = function(){o.del(idx)};
            btn.style.marginLeft = '5px';



            div.appendChild(file);
            div.appendChild(btn);
            document.getElementById('attachFileDiv').appendChild(div);
                        document.getElementById('attachFileDiv').appendChild(dv);

            o.idx++;
        },
        del:function(idx){ // 파일필드 삭제
            if(document.getElementById('fileField' + idx).value != '' && !confirm('삭제 하시겠습니까?')){
                return;
            }
            document.getElementById('attachFileDiv').removeChild(document.getElementById('file' + idx));
                        document.getElementById('attachFileDiv').removeChild(document.getElementById('dv' + idx));
        },
        prev:function(targetObj,View_area){ // 이미지 미리보기
            var preview = document.getElementById(View_area); //div id
            alert(View_area);
           var ua = window.navigator.userAgent;
  //ie일때(IE8 이하에서만 작동)
    if (ua.indexOf("MSIE") > -1) {
        targetObj.select();
        try {
            var src = document.selection.createRange().text; // get file full path(IE9, IE10에서 사용 불가)
            var ie_preview_error = document.getElementById("ie_preview_error_" + View_area);


            if (ie_preview_error) {
                preview.removeChild(ie_preview_error); //error가 있으면 delete
            }

            var img = document.getElementById(View_area); //이미지가 뿌려질 곳

            //이미지 로딩, sizingMethod는 div에 맞춰서 사이즈를 자동조절 하는 역할
            img.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+src+"', sizingMethod='scale')";
        } catch (e) {
            if (!document.getElementById("ie_preview_error_" + View_area)) {
                var info = document.createElement("<p>");
                info.id = "ie_preview_error_" + View_area;
                info.innerHTML = e.name;
                preview.insertBefore(info, null);
            }
        }
  //ie가 아닐때(크롬, 사파리, FF)
    } else {
        var files = targetObj.files;
        for ( var i = 0; i < files.length; i++) {
            var file = files[i];
            var imageType = /image.*/; //이미지 파일일경우만.. 뿌려준다.
            var prevImg = document.getElementById("prev_" + View_area);
            if (!file.type.match(imageType)){
                preview.removeChild(prevImg);
                continue;
                }
             //이전에 미리보기가 있다면 삭제
            if (prevImg) {
                preview.removeChild(prevImg);
            }
            var img = document.createElement("img");
            img.id = "prev_" + View_area;
            img.classList.add("obj");
            img.file = file;
            img.style.width = '100px';
            img.style.height = '100px';
            preview.appendChild(img);
            if (window.FileReader) { // FireFox, Chrome, Opera 확인.
                var reader = new FileReader();
                reader.onloadend = (function(aImg) {
                    return function(e) {
                        aImg.src = e.target.result;
                    };
                })(img);
                reader.readAsDataURL(file);
            } else { // safari is not supported FileReader
                //alert('not supported FileReader');
                if (!document.getElementById("sfr_preview_error_"
                        + View_area)) {
                    var info = document.createElement("p");
                    info.id = "sfr_preview_error_" + View_area;
                    info.innerHTML = "not supported FileReader";
                    preview.insertBefore(info, null);
                }
            }
        }
    }

        }
    }
</script>
<script>
$(document).on('click', '#btnSave', function(e){

	e.preventDefault();

	

	$("#form").submit();

});



$(document).on('click', '#btnList', function(e){

	e.preventDefault();

	

	location.href="${pageContext.request.contextPath}/board/getBoardList";

});



</script>

												<!-- css -->
<style>

body {
 
  padding-bottom: 30px;

}

.container{
	padding-top:100px;
	color:black;
}

.button {
	background-color: #263343;
	font-weight: bold;
	color:#ffffff;
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

.mini-editor {
  height:5em;
  font-family:sans-serif;
}
</style>

</head>

<body>
                                                  <!-- 헤더 -->
<header>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@300;400&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/42ce6799d7.js" crossorigin="anonymous"></script>

		                                        <!-- 헤더 스타일 -->
<style>
body{
    margin:0;
}

a{
	text-decoration:none;
	color:white;
	padding:8px 12px;
}

.navbar{
    display: flex;
    justify-content:space-between;
    align-items: center;
    background-color: #263343;
    padding: 8px 12px;
}
.navbar_logo{
font-size:29px;
	color:white;
}

.navbar_logo i{
	color:#d49466;
}

.navbar_menu{
	display:flex;
	font-size:22px;
	list-style:none;
	padding-left:0;
}

.navbar_menu li{
	padding:8px 12px;
}
.navbar_menu li:hover{
	background-color:#d49466;
	border-radius:4px;
}
.navbar_icons{
	font-size:22px;
	list-style:none;
	
	color:white;
	display:flex;
	
}
.navbar_icons li{
	padding: 5px 12px;
}


</style>
<body>
    <nav class="navbar">
        <div class="navbar_logo">
            <i class="fab fa-blogger"></i>
            <a href="">Enhance</a>

        </div>

        <ul class="navbar_menu">
            <li><a href="">당신을 위한</a></li>
            <li><a href="">탐색</a></li>
        </ul>
        <ul class="navbar_icons">
         	<li><a href="">프로젝트만들기</a></li>
       		 <li><a href="">로그인</a></li>
        	<li><a href="">등록</a></li>
            <li><i class="fas fa-user-circle"></i></li>
        </ul>
    </nav>
</body>
</header>


	<article>

		<div class="container" role="main">

			<h2>Project Upload</h2>
<hr></hr>
			<form name="form" id="form" role="form" method="post" action="${pageContext.request.contextPath}/board/saveBoard">

				<div class="mb-3">

					<label for="title">프로젝트 제목</label>

					<input type="text" class="form-control" name="title" id="title" placeholder="제목을 입력해 주세요">

				</div>

				

				<div class="mb-3">

					<label for="reg_id">작성자</label>

					<input type="text" class="form-control" name="reg_id" id="reg_id" placeholder="이름을 입력해 주세요">

				</div>

				

				<div class="mb-3">

					<label for="content">내용</label>

					<textarea class="form-control" rows="5" name="content" id="content" placeholder="내용을 입력해 주세요" ></textarea>

				</div>
				
				
			
<body>

<div id="attachFileDiv">
<input type="button" class="button1" value="파일 추가" onclick="attachFile.add()" style="margin-left:7px">
</div>



<body>



			</form>
			<div >
	
				<button type="button" class="button" id="btnSave">저장</button>

				<button type="button" class="button" id="btnList">취소</button>




			</div>

		</div>

	</article>

</body>



</html>


