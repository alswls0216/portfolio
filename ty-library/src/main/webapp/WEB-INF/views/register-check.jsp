<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.4.0/jspdf.umd.min.js"></script>
<head>


<title>도서 등록</title>
<meta charset="UTF-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<style>
    .text-image {
        font-size: 12px;
        color: black;
        position: absolute;
        left: 10px;
        top: 110px;
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
				<a href="/tylibrary/admin" class="navbar-brand d-flex d-lg-none me-4">
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
								<h2 class="mb-0 text-center">등록이 완료되었습니다.</h2>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- Sale & Revenue End -->


			<!-- Sales Chart Start -->
			<div class="container-fluid pt-4 px-4">
				<div class="row g-4">
					<div class="col-sm-12 col-xl-6 text-center">
						<div
							class="d-flex align-items-center justify-content-between mb-4">
							<h6 class="mb-0">| QR코드</h6>

						</div>
						<img
							src="${pageContext.request.contextPath}/tylibrary/admin/produceBookQR"
							alt="QR Code">
					</div>
					<!-- Sales Chart End -->
					<div class="col-sm-12 col-xl-6">
						<div class="bg-light rounded p-4">

							<form action="/selectBookInfo" method="post">

								<table class="table table-borderless small">
									<tbody>

										<td colspan="2">

											<button onclick="printQRAsPDF()"
												class="btn btn-primary w-100 m-2" type="button">Print
												QR as PDF</button>

										</td>

									</tbody>
								</table>
							</form>
						</div>
					</div>
				</div>
			</div>
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


	<!-- JavaScript Libraries -->
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
	<script src="/lib/chart/chart.min.js"></script>
	<script src="/lib/easing/easing.min.js"></script>
	<script src="/lib/waypoints/waypoints.min.js"></script>
	<script src="/lib/owlcarousel/owl.carousel.min.js"></script>
	<script src="/lib/tempusdominus/js/moment.min.js"></script>
	<script src="/lib/tempusdominus/js/moment-timezone.min.js"></script>
	<script src="/lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>




	<!-- 프린트pdf버튼 스크립트 -->
<script>
    function printQRAsPDF() {

        var title = sessionStorage.getItem('title');
        var author = sessionStorage.getItem('author');
        var publisher = sessionStorage.getItem('publisher');

        // QR 코드 이미지를 동적으로 생성
        var qrImage = new Image();
        qrImage.src = "${pageContext.request.contextPath}/tylibrary/admin/produceBookQR";

        // 이미지 로딩 후 캔버스에 QR 코드 이미지와 텍스트 추가
        qrImage.onload = function() {
            // 캔버스 생성
            var canvas = document.createElement('canvas');
            canvas.width = qrImage.width;
            canvas.height = qrImage.height + 90; // 텍스트를 표시할 공간 추가

            var ctx = canvas.getContext('2d');

            // QR 코드 이미지 그리기
            ctx.drawImage(qrImage, 0, 0);

            // title, author, publisher 텍스트 추가
            ctx.font = '12px Arial';
            ctx.fillStyle = 'black';
            ctx.fillText("Title: " + title, 10, qrImage.height + 20);
            ctx.fillText("Author: " + author, 10, qrImage.height + 40);
            ctx.fillText("Publisher: " + publisher, 10, qrImage.height + 60);

            // 이미지를 새 창으로 열기
            var dataURL = canvas.toDataURL('image/png');
            var pdf = new window.jspdf.jsPDF();
            pdf.addImage(dataURL, 'PNG', 10, 10, 90, 90); // QR 코드 이미지 영역
            pdf.output('dataurlnewwindow');
        };
    }
</script>



	<!-- Template Javascript -->
	<script src="/js/main.js"></script>

</body>

</html>