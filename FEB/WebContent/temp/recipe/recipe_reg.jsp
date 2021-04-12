<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- core태그 -->
<%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 국제화 태그 -->
<%@ taglib prefix= "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.82.0">
    <title>레시피 등록</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/checkout/">

    

    <!-- Bootstrap core CSS -->
<link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>

    
    <!-- 사용자 정의 스타일 입력 스타일시트 -->
    <link href="woogie.css" rel="stylesheet">

  </head>
  <body class="bg-light">
    
	<div class="container">
	  <main>
	    <div class="py-5 text-center">
	      <img class="d-block mx-auto mb-4" src="../image_source/good_food-wallpaper-800x480.jpg" alt="" style="height: 100px; border-radius: 10px;" >
	      <h2>레시피 등록</h2><br/>
	      <p class="lead">레시피 등록화면 입니다. 안내에 따라 각 항목을 정확히 작성바랍니다.</p>
	    </div>
	
	    <div class="row g-5">
	
	      <!-- 이미지 등록 칸으로 만들 곳 -->
	      <div class="col-md-5 col-lg-4 order-md-last">
	
	        <form>
	
	          <h4 class="d-flex justify-content-between align-items-center mb-3">
	            <span class="text-primary">이미지</span>
	            
	            <!-- 등록한 이미지가 몇 개인지 보여 줄 칸 -->
	            <span class="badge bg-primary rounded-pill">3</span>
	
	            <button type="button" class="btn btn-outline-primary me-2" onclick="showPopup(this.form);">추가</button>
	
	          </h4>
	
	          <ul class="list-group mb-3">
	            <li class="list-group-item d-flex justify-content-between lh-sm">
	              <div>
	                <h6 class="my-0">Product name</h6>
	                <small class="text-muted">Brief description</small>
	              </div>
	              <span class="text-muted">$12</span>
	            </li>
	          </ul>
	
	        </form>
	      </div>
	      <!--// 이미지 등록 칸으로 만들 곳 -->
	
	      <div class="col-md-7 col-lg-8">
	        <!-- <h4 class="mb-3">레시피 제목</h4> -->
	        <form class="needs-validation" novalidate>
	          <div class="row g-3">
	
	            <div class="col-12">
	              <label for="recipe_title" class="form-label">레시피 이름<span class="text-muted"></span></label>
	              <input type="text" class="form-control" id="recipe_title" placeholder="레시피 이름을 입력해 주세요" required>
	              <div class="invalid-feedback">
	                레시피 이름을 입력 해주세요.
	              </div>
	            </div>
	
	            <div class="col-12">
	              <label for="recipe_contents" class="form-label">레시피 내용</label>
	              <textarea class="form-control" id="recipe_contents" rows="10" placeholder="레시피 순서는 아라비아 숫자를 사용해 주세요" required></textarea>
	              <div class="invalid-feedback">
	                레시피 내용을 입력 해주세요.
	              </div>
	            </div>
	
	            <div class="col-12">
	              <label for="recipe_ingredients" class="form-label">레시피 재료<span class="text-muted"></span></label>
	              <input type="text" class="form-control" id="recipe_ingredients" placeholder="레시피 재료를 입력해주세요. 각 재료는 꼭 쉼표(,) 로 구분해야 합니다" required>
	              <div class="invalid-feedback">
	                레시피 재료를 입력 해주세요.
	              </div>
	            </div>
	          </div>
	
	          <hr class="my-4">
	
	          <button class="w-100 btn btn-primary btn-lg" type="submit">레시피 등록</button>
	        </form>
	      </div>
	    </div>
	  </main>
	</div>
  <br/><br/><br/>

  <script src="../assets/dist/js/bootstrap.bundle.min.js"></script>

  <script src="form-validation.js"></script>
  </body>

  <script type="text/javascript">

  //팝업 창 띄우기
  function showPopup(frm) { 
			console.log('showPopup()');
		
			var title ="파일 업로드";
			var option  ="toolbar=0,scrollbars=no,resizable=no,status=yes,width=1000,height=300,left=100,top=50";
			
			window.open("",title,option);
			
			frm.target = title;
			frm.method = "get";
			frm.action = "<c:url value='file_upload_popup.jsp' />";
			frm.submit();
		}
    
  </script>

</html>
