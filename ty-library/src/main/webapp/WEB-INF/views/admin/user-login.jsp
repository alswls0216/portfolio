<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<title>관리자 로그인</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">

<!-- Favicon -->
<link href="/img/favicon.ico" rel="icon">

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


		<!-- Sign In Start -->
		<div class="container-fluid">
			<div class="row h-100 align-items-center justify-content-center"
				style="min-height: 100vh;">
				<div class="col-12 col-sm-8 col-md-6 col-lg-5 col-xl-4">
					<div class="bg-light rounded p-4 p-sm-5 my-4 mx-3">
						<div
							class="d-flex align-items-center justify-content-between mb-3">
							<span id="goadmin">
								<h3 class="text-primary">Log-In</h3>
							</span>
						</div>
						<div class="form-floating mb-3">
							<input type="text" class="form-control" id="e_id" name="e_id">
							<label for="e_id">Input Employee Number</label><span
								id="result_checkID" style="font-size: 12px"></span>
						</div>
						<div class="form-floating mb-3">
							<input type="text" class="form-control" id="e_password" name="e_password">
							<label for="e_password">Input Password</label><span
								id="result_checkPW" style="font-size: 12px">
						</div>
						<button type="submit" class="btn btn-primary py-3 w-100 mb-4"
							id="login_btn">로그인</button>
					</div>
				</div>
			</div>
		</div>
		<!-- Sign In End -->
	</div>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script type="text/javascript">
		let i = 0;
		$("#goadmin").click(function() {
			i++;
			console.log(i);

			if (i == 1) {
				setTimeout(function() {
					i = 0;
				}, 5000)
			}

			console.log(i);
			if (i == 5) {
				window.location.href = '/tylibrary/admin'; // 5번 클릭하면 로그아웃 페이지로 이동
			}
		});
		
$("#login_btn").click(function() {
			
		    let arr = new Array();
		    arr.push($("#e_id").val());
		    arr.push($("#e_password").val());
		    
		    let url= "/tylibrary/loginProcess";
		    
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
				    	window.location.href = "/tylibrary/home"
				    }	
			    }
		    })
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