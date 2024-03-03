<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<title>DASHMIN - Bootstrap Admin Template</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">

<!-- Favicon -->
<link href="img/favicon.ico" rel="icon">

<!-- Google Web Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&display=swap"
	rel="stylesheet">

<!-- Icon Font Stylesheet -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
	rel="stylesheet">

<!-- Libraries Stylesheet -->
<link href="/lib/owlcarousel/assets/owl.carousel.min.css"
	rel="stylesheet">
<link href="/lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css"
	rel="stylesheet" />

<!-- Customized Bootstrap Stylesheet -->
<link href="/css/bootstrap.min.css" rel="stylesheet">

<!-- Template Stylesheet -->
<link href="/css/style.css" rel="stylesheet">
<style>
/* Flexbox를 이용하여 셀렉트와 인풋 요소를 나란히 배치합니다. */
.form-row {
	display: flex;
	align-items: center; /* 요소들을 세로 중앙 정렬합니다. */
}

.form-row .form-select {
	flex: 0 0 40%;
	margin-right: 10px; /* 요소 사이 간격을 설정합니다. */
}

.form-row .form-control {
	flex: 0 0 60%;
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


		<!-- Sidebar Start -->
		<div class="sidebar pe-4 pb-3">
			<nav class="navbar bg-light navbar-light">
				<a href="/tylibrary/admin" class="navbar-brand mx-4 mb-3">
					<h3 class="text-primary">
						<i class="fa fa-hashtag me-2"></i>TY Library
					</h3>
				</a>
				<div class="d-flex align-items-center ms-4 mb-4">
					<div class="position-relative">
						<img class="rounded-circle" src="/img/user.jpg" alt=""
							style="width: 40px; height: 40px;">
						<div
							class="bg-success rounded-circle border border-2 border-white position-absolute end-0 bottom-0 p-1"></div>
					</div>
					<div class="ms-3">
						<h6 class="mb-0">Jhon Doe</h6>
						<span>Admin</span>
					</div>
				</div>
				<div class="navbar-nav w-100">
					<a href="/tylibrary/admin" class="nav-item nav-link active"><i
						class="fa fa-tachometer-alt me-2"></i>Dashboard</a>
					<div class="nav-item dropdown">
						<a href="#" class="nav-link dropdown-toggle"
							data-bs-toggle="dropdown"><i class="fa fa-laptop me-2"></i>Elements</a>
						<div class="dropdown-menu bg-transparent border-0">
							<a href="button.html" class="dropdown-item">Buttons</a> <a
								href="typography.html" class="dropdown-item">Typography</a> <a
								href="element.html" class="dropdown-item">Other Elements</a>
						</div>
					</div>
					<a href="widget.html" class="nav-item nav-link"><i
						class="fa fa-th me-2"></i>Widgets</a> <a href="form.html"
						class="nav-item nav-link"><i class="fa fa-keyboard me-2"></i>Forms</a>
					<a href="table.html" class="nav-item nav-link"><i
						class="fa fa-table me-2"></i>Tables</a> <a href="chart.html"
						class="nav-item nav-link"><i class="fa fa-chart-bar me-2"></i>Charts</a>
					<div class="nav-item dropdown">
						<a href="#" class="nav-link dropdown-toggle"
							data-bs-toggle="dropdown"><i class="far fa-file-alt me-2"></i>Pages</a>
						<div class="dropdown-menu bg-transparent border-0">
							<a href="signin.html" class="dropdown-item">Sign In</a> <a
								href="signup.html" class="dropdown-item">Sign Up</a> <a
								href="404.html" class="dropdown-item">404 Error</a> <a
								href="blank.html" class="dropdown-item">Blank Page</a>
						</div>
					</div>
				</div>
			</nav>
		</div>
		<!-- Sidebar End -->


		<!-- Content Start -->
		<div class="content">
			<!-- Navbar Start -->
			<nav
				class="navbar navbar-expand bg-light navbar-light sticky-top px-4 py-0">
				<a href="/tylibrary/admin" class="navbar-brand d-flex d-lg-none me-4">
					<h2 class="text-primary mb-0">TY Library</h2>
				</a>
			</nav>
			<!-- Navbar End -->


			<!-- Sale & Revenue Start -->
			<div class="container-fluid pt-4 px-4">
				<div class="row g-4">
					<div class="col-sm-12 col-xl-12">
						<div class="bg-light rounded d-md-flex align-items-center p-4">
							<div style="margin: auto">
								<h2 class="mb-0 text-center"
									style="item-align: center !important;">사원 관리</h2>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- Sale & Revenue End -->

			<div class="container-fluid pt-4 px-4">
				<div class="col-sm-12 col-xl-12">
					<div class="bg-light rounded h-100 p-4">
						<ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
							<li class="nav-item" role="presentation">
								<button class="nav-link active" id="pills-home-tab"
									data-bs-toggle="pill" data-bs-target="#pills-home"
									type="button" role="tab" aria-controls="pills-home"
									aria-selected="true">사원 조회</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link" id="pills-profile-tab"
									data-bs-toggle="pill" data-bs-target="#pills-profile"
									type="button" role="tab" aria-controls="pills-profile"
									aria-selected="false">사원 등록</button>
							</li>
						</ul>
						<div class="tab-content" id="pills-tabContent">
							<div class="tab-pane fade show active" id="pills-home"
								role="tabpanel" aria-labelledby="pills-home-tab">
								<form id="employee-search" method="POST"
									action="search-employees">
									<div class="form-row">
										<select class="form-select form-select-sm mb-3"
											aria-label=".form-select-sm category" name="category">
											<option selected value="Enum">사번</option>
											<option value="EName">이름</option>
										</select><input class="form-control-sm mb-3" type="text" id=""
											style="width: 60%" aria-label=".form-control-sm search"
											placeholder="검색어를 입력하세요" name="searchKey">
									</div>
									<button type="button" id="search-employee"
										class="btn btn-primary w-100">검색</button>

								</form>
								<br>
								<h6 class="mb-0">사원 정보</h6>
								<table class="table">
									<thead>
										<tr>
											<th scope="col">#</th>
											<th scope="col">사번</th>
											<th scope="col">이름</th>
											<th scope="col">비밀번호</th>
										</tr>
									</thead>
									<tbody id="content1">
									</tbody>
								</table>
							</div>



							<div class="tab-pane fade" id="pills-profile" role="tabpanel"
								aria-labelledby="pills-profile-tab">
								<form id="sing-up-form" method="POST"
									enctype="multipart/form-data" action="user-sign-up">

									<input type="text" class="form-control mb-3" id="Enum"
										name="ENum" placeholder="사번을 입력하세요" /> <input type="text"
										class="form-control mb-3" id="EName" name="EName"
										placeholder="이름을 입력하세요"> <input type="file"
										class="form-control mb-3" id="EFile" name="EFile">

									<button type="button" id="sing-up"
										class="btn btn-primary mb-3 w-100 ">등록</button>
								</form>
								<h6 class="mb-0">등록 완료</h6>
								<table class="table">
									<thead>
										<tr>
											<th scope="col">#</th>
											<th scope="col">사번</th>
											<th scope="col">이름</th>
										</tr>
									</thead>
									<tbody id="content2">
									</tbody>
								</table>



							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- Sales Chart Start -->

			<!-- Footer Start -->

			<div class="container-fluid pt-4 px-4">
				<div class="bg-light rounded-top p-4">
					<div class="row">
						<div class="col-12 col-sm-6 text-center text-sm-start">
							&copy; <a href="#">Your Site Name</a>, All Right Reserved.
						</div>
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
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script type="text/javascript">
	
	document.addEventListener('keydown', function(event) {
		  if (event.keyCode === 13) {
		    event.preventDefault();
		  };
		}, true);
	
	document.getElementById("sing-up").addEventListener("click", function() {
		let check = confirm("입력한 정보로 사원 등록을 진행 하시겠습니까?")
		if(check){
		let formData = new FormData($('#sing-up-form')[0]);
		$.ajax({
			url: 'user-sign-up',
			type: 'POST',
			data: formData,
			processData: false,
			contentType: false,
			success: function(response) {
				if(response.length){
				$('#content2').empty();
				let content = '';
				for (let i = 0; i < response.length; i++) {
					content += `
	        <tr>
	            <td>\${i+1}</td>
	            <td>\${response[i].e_id}</td>
	            <td>\${response[i].e_name}</td>
	        </tr>
	    `
				}
				$('#content2').html(content);
				$('#sing-up-form')[0].reset()
				alert('등록이 완료됬습니다.');
				}else{
					alert('잘못된 입력입니다.');
				}
			},
			error: function(xhr, status, error) {
				alert('잘못된 입력입니다.');
			}
		});
		}else{
			alert("등록이 취소됬습니다.")
		}
	});
	document.getElementById("search-employee").addEventListener("click",function(){
		let formData = $('#employee-search').serialize();
        $.ajax({
            url: 'search-employees', 
            type: 'POST',
            data: formData, 
            success: function(response) {
            	if(response.length){
            	$('#content1').empty();
				let content = '';
				for (let i = 0; i < response.length; i++) {
					content += `
	        <tr>
	            <td>\${i+1}</td>
	            <td>\${response[i].e_id}</td>
	            <td>\${response[i].e_name}</td>
	            <td><button type="button" class="btn btn-sm btn-danger rounded-pill resetBtn" data-e_id=\${response[i].e_id} data-e_name=\${response[i].e_name}>초기화</button></td>
	        </tr>
	    `
				}
				$('#content1').html(content);
				$('#employee-search')[0].reset()
            	}else{
            		alert('조회 결과가 없습니다.')
            	}
            },
            error: function(xhr, status, error) {
                alert('에러: ' + error); 
            }
        });
	})
	
	$(document).on('click', '.resetBtn', function() {
		let id = $(this).data('e_id');
		let name = $(this).data('e_name');
		let check = confirm('사번'+id+'\n'+name+'님의 비밀번호를 초기화 하시겠습니까?');
		if (check){
			$.ajax({
            url: 'reset-password?id='+id, 
            type: 'GET', 
            success: function(response) {
            	if (response == 1){
            		alert('비밀번호 초기화 완료');
            	}
            },
            erroe: function(){
            	
            }
            });		
		} else{
			alert('비밀번호 초기화 취소');
		}
		 			
	});
	
	
	</script>

	<!-- JavaScript Libraries -->

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
	<script src="/lib/chart/chart.min.js"></script>
	<script src="/lib/easing/easing.min.js"></script>
	<script src="/lib/waypoints/waypoints.min.js"></script>
	<script src="/lib/owlcarousel/owl.carousel.min.js"></script>
	<script src="/lib/tempusdominus/js/moment.min.js"></script>
	<script src="/lib/tempusdominus/js/moment-timezone.min.js"></script>
	<script src="/lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

	<!-- Template Javascript -->
	<script src="/js/main.js"></script>
</body>

</html>