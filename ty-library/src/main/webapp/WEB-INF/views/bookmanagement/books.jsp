<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	
	<title>도서 정보</title>
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
				<a href="/tylibrary/search" class="navbar-brand d-flex d-lg-none me-4">
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
								<h2 class="mb-0 text-center">도서 정보</h2>
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
						
							<div class="d-flex align-items-center mb-4">
								<a class="mb-0" style="font-size:25px">|&nbsp;&nbsp;</a><h6 class="mb-0" style="word-break:keep-all; text-align:left">${bookInfo.title}</h6>
							</div>
							
							<table class="table table-borderless">
								<thead>
								</thead>
								<tbody>
									<tr>
										<th scope="row" width="80px">지은이</th>
										<td></td>
										<td>${bookInfo.author}</td>
									</tr>
									<tr>
										<th scope="row" width="80px">출판사</th>
										<td></td>
										<td>${bookInfo.publisher}</td>
									</tr>
									<tr>
										<th scope="row" width="80px">카테고리</th>
										<td></td>
										<td>${bookInfo.category}</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<!-- Sales Chart End -->

					<div class="col-sm-12 col-xl-6">
						<div class="text-center rounded">					
							<c:if test="${rent_availability eq 'A'}">
			                    <button type="button" class="btn btn-outline-primary m-2" id="rent" onclick="location.href='/tylibrary/rent/enter_empl_num'">대여</button>
			                </c:if>
			                <c:if test="${rent_availability eq 'R'}">
			                    <button type="button" class="btn btn-outline-primary m-2" id="renew" onclick="location.href='/tylibrary/renew/enter_empl_num'">연장</button>
								<button type="button" class="btn btn-outline-primary m-2" id="assign" onclick="location.href='/tylibrary/assign/enter_empl_num'">양도</button>
								<button type="button" class="btn btn-outline-primary m-2" id="return" 
									<c:choose>
										<c:when test="${adminId eq 'admin'}"></c:when>
						                <c:otherwise>style="display:none"</c:otherwise>
									</c:choose>								
									onclick="/tylibrary/return/check">반납
								</button>
			                </c:if>
						</div>
					</div>	
				</div>
			</div>

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
				</div>
			</div>
			<!-- Footer End --><br>
			
			<div class="text-center">
				<c:if test="${employee eq null}">
					<a href="/tylibrary/login">로그인</a>
				</c:if>
				<c:if test="${employee ne null}">
					<a href="/tylibrary/login">로그아웃</a>
				</c:if>
			</div>
		</div>
		<!-- Content End -->

		<!-- Back to Top -->
	</div>

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