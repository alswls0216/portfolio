<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<title>확정</title>
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
		.td {
			word-break: keep-all;
			text-align: left;
			vertical-align : middle;
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
			<nav class="navbar bg-light navbar-light sticky-top px-4 py-0">
				<a href="/tylibrary/books/${b_id}" class="navbar-brand d-flex d-lg-none me-4">
					<h2 class="text-primary mb-0">TY Library</h2>
				</a>
				<ul class="nav justify-content-end">
					<li style="font-size: 11px; vertical-align: middle; text-align: right">
						<c:if test="${employee.e_id eq null}">
							<a style="font-size: 13px; vertical-align: middle; text-align: right" href="/tylibrary/login">로그인</a>
						</c:if>
						<c:if test="${employee.e_id ne null}">
							사원 번호: ${employee.e_id} | 사원명 : ${employee.e_name}
						</c:if>
					</li>
				</ul>
			</nav>
			<!-- Navbar End -->


			<!-- Sale & Revenue Start -->
			<div class="container-fluid pt-4 px-4">
				<div class="row g-4">
					<div class="col-sm-6 col-xl-3">
						<div class="bg-light rounded d-md-flex align-items-center p-4">
							<div class="ms-3">
								<c:if test="${management_type eq 'rent'}">
			                       <h2 class="mb-0 text-center">대여</h2>
			                    </c:if>
			                    <c:if test="${management_type eq 'renew'}">
			                       <h2 class="mb-0 text-center">연장</h2>
			                    </c:if>
			                    <c:if test="${management_type eq 'assign'}">
			                       <h2 class="mb-0 text-center">양도</h2>
			                    </c:if>
			                    <c:if test="${management_type eq 'return'}">
			                       <h2 class="mb-0 text-center">반납</h2>
			                    </c:if>
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
							
								<c:if test="${management_type eq 'rent'}">
									<h6 class="mb-0">| 대여 완료</h6></div>
									<p>대여 기간</p>
									<p><strong>${rent_date} ~ ${due_date}</strong></p>
								</c:if>
								<c:if test="${management_type eq 'renew'}">
									<h6 class="mb-0">| 연장 완료</h6></div>
									<p>대여 기간</p>
									<p><strong>${rent_date} ~ ${due_date}</strong></p>
								</c:if>
								<c:if test="${management_type eq 'assign'}">
									<h6 class="mb-0">| 양도 완료</h6></div>
									<p>대여 기간</p>
									<p><strong>${rent_date} ~ ${due_date}</strong></p>
								</c:if>
								<c:if test="${management_type eq 'return'}">
									<h6 class="mb-0">| 반납 완료</h6></div>
									<p>반납 일자</p>
									<p><strong>${rent_date}</strong></p>
								</c:if>
								
								<br><hr style="color:gray"><br>

								<div class="d-flex align-items-center justify-content-between mb-4">
									<h6 class="mb-0">| 도서 정보</h6>
								</div>
								
								<table class="table table-borderless">
									<thead>
									</thead>
									<tbody>
										<tr>
											<th scope="row" width="80px">도서명</th>
											<td></td>
											<td width="200px">${bookInfo.title}</td>
										</tr>
										<tr>
											<th scope="row" width="80px">지은이</th>
											<td></td>
											<td width="200px">${bookInfo.author}</td>
										</tr>
									</tbody>
								</table>
							</div>
							
							<div class="text-center"><br>
								<c:if test="${management_type eq 'rent'}">
					                <h6>대여가 완료되었습니다.</h6>
					            </c:if>
					            <c:if test="${management_type eq 'renew'}">
					                 <h6>대여기간 연장이 완료되었습니다.</h6>
					            </c:if>
					            <c:if test="${management_type eq 'assign'}">
					                 <h6>양도가 완료되었습니다.</h6>
					            </c:if>
					            <c:if test="${management_type eq 'return'}">
					                 <h6>반납이 완료되었습니다.</h6>
					            </c:if>

					            <button type="button" class="btn btn-outline-primary m-2" onclick="location.href='search'">도서 조회 홈으로</button>
							</div>								
						</div>
					</div>
					<!-- Sales Chart End -->
					
			<!-- Footer Start -->
			<div class="container-fluid pt-4 px-4">
				<div class="bg-light rounded-top p-4">
					<div class="row">
						<div class="col-12 col-sm-6 text-center text-sm-end">
							<!--/*** This template is free as long as you keep the footer author’s credit link/attribution link/backlink. If you'd like to use the template without the footer author’s credit link/attribution link/backlink, you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". Thank you for your support. ***/-->
							Designed By <a href="https://htmlcodex.com">HTML Codex</a> </br>
							Distributed By <a class="border-bottom" href="https://themewagon.com" target="_blank">ThemeWagon</a>
						</div>
					</div>
				</div><br>
				
				<div class="text-center">
					<a href="/tylibrary/logout">로그아웃</a>
				</div>
			</div>
		<!-- Footer End -->
		<!-- Content End -->
		<!-- Back to Top -->

	<!-- JavaScript Libraries -->
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
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