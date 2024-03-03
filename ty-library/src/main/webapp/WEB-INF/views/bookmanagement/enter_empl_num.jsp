<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">

<title>로그인</title>

<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">

<!-- Favicon -->
<link href="/img/favicon.ico" rel="icon">

<!-- Google Web Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&display=swap" rel="stylesheet">

<!-- Icon Font Stylesheet -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

<!-- Libraries Stylesheet -->
<link href="/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
<link href="/lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

<!-- Customized Bootstrap Stylesheet -->
<link href="/css/bootstrap.min.css" rel="stylesheet">

<!-- Template Stylesheet -->
<link href="/css/style.css" rel="stylesheet">

	<style>
		input, password {
		  width: 230px;
		  height: 32px;
		  font-size: 15px;
		  border: 0;
		  border-radius: 15px;
		  outline: none;
		  padding-left: 10px;
		  background-color: rgb(233, 233, 233);
		}
	</style>

</head>

<body>
	<div class="container-xxl position-relative bg-white d-flex p-0">
		<!-- Spinner Start -->
		<div id="spinner"
			class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
			<div class="spinner-border text-primary"
				style="width: 3rem; height: 3rem;" role="status">
				<span class="sr-only">Loading...</span>
			</div>
		</div>
		<!-- Spinner End -->


		<!-- Content Start -->
		<div class="content">
			<!-- Navbar Start -->
			<nav
				class="navbar navbar-expand bg-light navbar-light sticky-top px-4 py-0">
				<a href="/tylibrary/books/${b_id}" class="navbar-brand d-flex d-lg-none me-4">
					<h2 class="text-primary mb-0">TY Library</h2>
				</a>
			</nav>
			<!-- Navbar End -->


			<!-- Sale & Revenue Start -->
			<div class="container-fluid pt-4 px-4">
				<div class="row g-4">
					<div class="col-sm-6 col-xl-3">
						<div class="bg-light rounded d-md-flex align-items-center p-4">
							<div class="ms-3">
								<h2 class="mb-0 text-center">로그인 (사번)</h2>
								<div>
									<span id="result_checkPsw" style="font-size: 12px"></span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- Sale & Revenue End -->

			<!-- Sales Chart Start -->
			<div class="container-fluid pt-4 px-4">
				<div class="row g-4">
					<div class="col-sm-12 col-xl-6">
						<div class="bg-light text-center rounded p-4">
							<div class="d-flex align-items-center justify-content-between mb-4">
								<h6 class="mb-0">| 사번 입력</h6>
							</div>
								<div class="form-floating mb-3">
									ID&nbsp;&nbsp;:&nbsp;&nbsp;<input type="text" id="e_id" name="e_id" placeholder="사번" required>
									<div><span id="result_checkID" style="font-size: 12px"></span></div>
									<br>
									PW&nbsp;&nbsp;:&nbsp;&nbsp;<input type="password" id="e_password" name="e_password" placeholder="비밀번호" required>
									<div><span id="result_checkPW" style="font-size: 12px"></span></div>
								</div>
								<button type="submit" class="btn btn-outline-primary m-2" id="login_btn">로그인</button>
							</div>
						</div>
					<!-- Sales Chart End -->
					</div>
				</div>
				
			<!-- Footer Start -->
			<div class="container-fluid pt-4 px-4">
				<div class="bg-light rounded-top p-4">
					<div class="row">
						<div class="col-12 col-sm-6 text-center text-sm-end">
							<!--/*** This template is free as long as you keep the footer author’s credit link/attribution link/backlink. If you'd like to use the template without the footer author’s credit link/attribution link/backlink, you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". Thank you for your support. ***/-->
							Designed By <a href="https://htmlcodex.com">HTML Codex</a> </br>
							Distributed By <a class="border-bottom"
								href="https://themewagon.com" target="_blank">ThemeWagon</a>
						</div>
					</div>
				</div>
			</div>
			<!-- Footer End -->
			
		</div>
		<!-- Content End -->

		<!-- Back to Top -->
	</div>

	<!-- JavaScript Libraries -->
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
	<script src="/lib/chart/chart.min.js"></script>
	<script src="/lib/easing/easing.min.js"></script>
	<script src="/lib/waypoints/waypoints.min.js"></script>
	<script src="/lib/owlcarousel/owl.carousel.min.js"></script>
	<script src="/lib/tempusdominus/js/moment.min.js"></script>
	<script src="/lib/tempusdominus/js/moment-timezone.min.js"></script>
	<script src="/lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>
	
	<script>
		$("#login_btn").click(function() {
			
		    var arr = new Array();
		    arr.push($("#e_id").val());
		    arr.push($("#e_password").val());
		    
		    var url;
		    if('${management_type}'=='rent')
		    	url = "/tylibrary/rent/loginProcess";
		    else if('${management_type}'=='assign')
		    	url = "/tylibrary/assign/loginProcess";
		    else if('${management_type}'=='renew')
		    	url = "/tylibrary/renew/loginProcess";
		    else if('${management_type}'=='login')
		    	url = "/tylibrary/loginProcess";
		    
		    $.ajax({
			    url : url,
			    type : 'POST',   
			    async : true,   
			    dataType : 'json',   
			    data: {arr,arr},
			    success : function(data) {
			    },
			    error : function(data) {      
			    	var d = JSON.parse(JSON.stringify(data));
				    var error_msg = d['responseText'];
				    
				    if(error_msg=="아이디를 다시 입력해 주세요."){
				    	$("#result_checkID").html(error_msg).css("color", "red");
				    } else if (error_msg=="비밀번호를 다시 입력해 주세요."){
				    	$("#result_checkPW").html(error_msg).css("color", "red");
				    } else {
				    	location.replace(d['responseText']);
				    }	
			    }
		    })
		 });
	</script>

	<!-- Template Javascript -->
	<script src="/js/main.js"></script>
	
</body>

</html>