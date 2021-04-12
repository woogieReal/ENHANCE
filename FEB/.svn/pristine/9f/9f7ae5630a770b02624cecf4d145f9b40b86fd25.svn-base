<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>

	.reply_list{
		width: 1000px;
		height: 300px;
		outline: 1px solid black;
		margin-left: 260px;
	
	}	
	
	.left_box{
	width: 200px;
	height: 300px;
	outline: 2px solid red;
	/* top right left bottom  */
	margin: 0px 25px 0px 25px;
	padding: 0px;
	float: left;
	display: inline-block;

	}	
	
	.middle_box{
	width: 600px;
	height: 300px;
	outline: 2px solid blue;
	/* top right left bottom  */
	margin: 0px;
	padding: 0px;
	float: left;
	display: inline-block;

	}	
	
	.right_box{
	width: 100px;
	height: 300px;
	outline: 2px solid green;
	margin: 0px 25px 0px 25px;
	padding: 0px;
	float: right;
	display: inline-block;

	}
	
	.info_box{
	width: 600px;
	height: 140px;
	outline: 2px solid green;
	margin: 0px 0px 10px 0px;
	padding: 0px;
	float: left;
	display: inline-block;

	}
	
	.contents_box{
	width: 600px;
	height: 150px;
	outline: 2px solid green;
	margin: 0px 0px 0px 0px;
	padding: 0px;
	float: left;
	display: inline-block;

	}
	
	.date_box{
	width: 100px;
	height: 140px;
	outline: 2px solid green;
	margin: 0px 0px 10px 0px;
	padding: 0px;
	float: left;
	display: inline-block;

	}
	
	.delete_box{
	width: 100px;
	height: 150px;
	outline: 2px solid green;
	margin: 0px 0px 0px 0px;
	padding: 0px;
	float: left;
	display: inline-block;

	}
	
	
	
</style>
<title>댓글만</title>
</head>
<body>
<section class="comment" align="center" >
    <h2>댓글 COMMENT</h2>
    
     <!-- 덧글 입력하는 폼 -->
                <form action="./insertCommentAction.jsp" method="post">
                    <div class="comment_box" id="comment_box">
                        
                    <input type="text" class="comment_box" name="title" id="title" placeholder="이 프로젝트에 대해 어떻게 생각하십니까?">
                    <button type="button" class="button" id="btnSave">댓글게시</button>
                    </div>
                  
                </form>

	<div class="reply_list">
	
		<div class="left_box">
		//프로필사진
		</div>
		
		<div class="middle_box">
			<div class="info_box">//이름, //이메일</div>
			<div class="contents_box">댓글내용</div>
		</div>
		
		
		
		<div class="right_box">
			<div class="date_box"></div>
			<div class="delete_box"></div>
		</div>
	
	</div>
			
			
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