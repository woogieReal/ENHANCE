<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
		<title>project view</title>
		<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@300;400&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/42ce6799d7.js" crossorigin="anonymous"></script>
		<script src="https://kit.fontawesome.com/2d323a629b.js" 
		crossorigin="anonymous"></script>
	<link rel="stylesheet" href="project_views_style.css">
</head>
<body>

				<!-- 헤더 -->
<header>

    	<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@300;400&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/42ce6799d7.js" crossorigin="anonymous"></script>
</head>
<style>
body{
    margin:0;
}

a{
	text-decoration:none;
	color:white;
	padding: 4px 6px;
}

.navbar{
    display: flex;
    justify-content:space-between;
    align-items: center;
    background-color: #263343;
    padding: 4px 6px;
}
.navbar_logo{
font-size:20px;
	color:white;
}

.navbar_logo i{
	color:#d49466;
}

.navbar_menu{
	display:flex;
	font-size:20px;
	list-style:none;
	padding-left:0;
}

.navbar_menu li{
	padding: 4px 6px;
}
.navbar_menu li:hover{
	background-color:#d49466;
	border-radius:4px;
}
.navbar_icons{
	font-size:20px;
	list-style:none;
	color:white;
	display:flex;
	
}
.navbar_icons li{
	padding: 4px 6px;
}


.navbar_icons li:hover{
	background-color:#d49466;
	border-radius:4px;
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
       		 <li><a href="">로그아웃</a></li>
            <li><i href="" class="fas fa-user-circle"></i></li>
        </ul>
    </nav>
</body>
</header>
							<!-- 헤더 끝 -->
							
							
							
	<font face="sans-serif" color="black" size="5" align="center">
		<h1></h1>
	</font>
	<font face="sans-serif" color="black" size="3" align="center">
	
	</font>
<!-- 사진 -->
	<table align="center">
		<tr>
			<td>
				<img src="/FEB/member/img/creative02.JPG" height="750" width="1000"/>
			</td>
			</tr>
			<tr>
			<td>
				<img src="/FEB/member/img/creative03.JPG" height="750" width="1000"/>
			</td>
			</tr>
			<tr>
			<td>
				<img src="/FEB/member/img/creative04.JPG" height="750" width="1000"/>
			</td>
			</tr>
			<tr>
			<td>
				<img src="/FEB/member/img/creative05.JPG" height="750" width="1000"/>
			</td>
			</tr>
			
	</table>

	
	
	<hr></hr>
<!-- 프로젝트제목 -->
<!--  <section class="info">
	<div class="title">
	<li>프로젝트 제목</li>
	
	</div>  -->
<!-- 좋아요 저장 팔로우 -->
<table align="center">

	<ul class="action" align="center" justify-content: space-between;>
		<li><button><i class="fas fa-thumbs-up"></i><span>like</span></button></li>
	 <hr></hr>
	 <li><button><i class="fas fa-user-plus"></i><span>follow</span></button></li>
	 	 <hr></hr>
	 	 <li><button><i class="fas fa-bookmark"></i><span>store</span></button></li>
	</ul>
	
	
</table>
		<section class="comment" align="center" >
    <h2>댓글 COMMENT</h2>
    
     <!-- 덧글 입력하는 폼 -->
                <form action="./insertCommentAction.jsp" method="post">
                    <div class="comment_box" id="comment_box">
                        
                    <input type="text" class="comment_box" name="title" id="title" placeholder="이 프로젝트에 대해 어떻게 생각하십니까?">
                    <button type="button" class="button" id="btnSave">댓글게시</button>
                    </div>
                  
                </form>

    
           <table class="commentlist" style="width:50;" >
			<td style="width: 15%;" float:left>작성자 이메일 : dmfsdfs@naver.com</td>
			<td style="width: 15%;" float:right>작성일 : 2021/03/02 16:49:02</td>
				</table>
			
			 <table class="commentlist" style="width:50;"float:left >
			<td style="width: 33%;">댓글내용 ex:) Excellent work! And stunning presentation!!🔥 Looking forward to more from you :) </td>
				
				</table>
			  <table class="commentbutton">
      		 <button type="button" class="button2" id="btnSave">삭제</button>
      		 </table> 
			
			
			
   			</section>

					<!-- 덧글창 css -->
          <style>


 .action{width: 1000px; height: 100px;  margin:0 auto;
}

.comment_box{
	
	text-align:center;
        width: 1000px;
        font-size:10px;
        	margin:0 auto;
          }
      
.comment {
text-align:center;
    margin: 0;
    padding: .3rem;
    font: 1rem 'Fira Sans', sans-serif;
	margin:0 auto;
}

.comment > h2,
.commentlist {

    margin: .5rem;
    padding: .3rem;
  font-size:10px;
    width: 1000px;
	margin:0 auto;
}

.commentlist {
    background: right/contain content-box border-box no-repeat
        url('/media/examples/rain.svg') white;
}

.commentlist > h2,
.commentlist > td {
    margin: .2rem;
     font-size:10px;
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
	 font-size:3px;
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
.commentbutton{
float:left;
}
          </style>      

</body>
</html>