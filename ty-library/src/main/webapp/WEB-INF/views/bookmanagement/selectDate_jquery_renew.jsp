<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.css">
	<script src="/lib/datepicker/bootstrap-datepicker.js"></script>
	<script src="/lib/datepicker/bootstrap-datepicker.css"></script>
	
	<title>대여 기간 선택</title>
	
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
		.datepicker td, .datepicker th {
		    width: 2.5rem;
		    height: 2.5rem;
		    font-size: 0.85rem;
		}
		
		.datepicker {
		    margin-bottom: 3rem;
		}
		
		body {
		    min-height: 100vh;
		    background-color: #fafafa;
		}
		
		.input-group {
		    border-radius: 30rem;
		}
		
		input.form-control {
		    border-radius: 30rem 0 0 30rem;
		    border: none;
		}
		
		input.form-control:focus {
		    box-shadow: none;
		}
		
		input.form-control::placeholder {
		    font-style: italic;
		}
		
		.input-group-text {
		    border-radius: 0 30rem 30rem 0;
		    border: none;
		}
		
		.datepicker-dropdown {
		    box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
		}
	</style>

</head>

<body>
	<div class="container-xxl position-relative bg-white d-flex p-0">

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
								<h2 class="mb-0 text-center">연장</h2>
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
						<div class="bg-light rounded p-4">
							<div class="d-flex align-items-center justify-content-between mb-4">
								<h6 class="mb-0">| 대여 연장 선택</h6>
							</div>
							<ul class="mb-0 text-left">
								<li>기본 연장 기간은 2주입니다.</li>
								<li>기본 연장 기간을 초과하여 연장하기를 원하신다면, 하단 달력에서 반납 일자를 선택하여 주시길
									바랍니다.</li>
								<li>최대 연장 기간은 기존 선택하신 반납 일자부터 다음달 까지입니다.</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="container-fluid pt-4 px-4">
				<div class="row g-4">
					<div class="col-sm-12 col-xl-6">
						<div class="bg-light rounded p-4">
						
				            <!-- Date Picker Input -->
				            <div class="form-group mb-4">
				                <div class="datepicker date input-group p-0 shadow-sm">
				                    <input type="text" placeholder="대여 기간을 선택하세요" class="form-control py-2.2 px-4" id="datepicker">
				                    <div class="input-group-append"><span class="input-group-text px-4"><i>선택</i></span></div>
				                </div>
				                <div class="text-center">
				                	<p class="font-italic text-muted mb-0">선택 반납 일자</p> 
				                	<h4 id="pickedDate" class="font-weight-bold text-uppercase mb-3"></h4>
				                </div>
				            </div><!-- DEnd ate Picker Input -->

						</div>
					</div>
				</div>
			</div><br>
			
			<div class="text-center">
				<button type="button" class="btn btn-outline-primary m-2" onclick="location='/tylibrary/renew/check'">다음</button>
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
				</div>
			</div>
			<!-- Footer End --><br><br>
			
			<div class="text-center">
					<a href="/tylibrary/logout">로그아웃</a>
				</div>
			</div>
			<!-- Content End -->

			<!-- Back to Top -->
		</div>
		
	<script>
		$(function() {
			
			//기존 대여 날짜 받아오기 ********************************
			let rrd_str = JSON.stringify(${recent_return_date});
			
			let recent_return_date1 = new Date(rrd_str.slice(0,4) + '/' + rrd_str.slice(4, 6) + '/' + rrd_str.slice(6,8));
			let recent_return_date2 = new Date(rrd_str.slice(0,4) + '/' + rrd_str.slice(4, 6) + '/' + rrd_str.slice(6,8));
			
			// 기본 연장 기간 설정
			let basic_return_date = new Date(recent_return_date1.setDate(recent_return_date1.getDate()+14));
			// 최대 연장 기간 설정
			let max_return_date = new Date(recent_return_date2.setMonth(recent_return_date2.getMonth()+1));
			
			// 날짜 포맷 변경 (: yyyy/MM/dd)
			let rrd = rrd_str.slice(0,4) + '/' + rrd_str.slice(4, 6) + '/' + rrd_str.slice(6,8)
			let brd = basic_return_date.getFullYear()+"/"+leftPad(basic_return_date.getMonth()+1)+"/"+leftPad(basic_return_date.getDate());
			let mrd = max_return_date.getFullYear()+"/"+leftPad(max_return_date.getMonth()+1)+"/"+leftPad(max_return_date.getDate());
			
			// datepicker 설정
			$('#datepicker').datepicker({
				clearBtn : true,
				dateFormat : "yy/mm/dd",
				showMonthAfterYear:true,
				yearSuffix: "년",
				monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
				dayNamesMin: ['일','월','화','수','목','금','토'],
				minDate: rrd,
				maxDate: mrd
			});
			
			// 기본 대여 기간 설정 및 데이터 전송     
			$('#datepicker').datepicker('setDate', brd );
			
			var arr = new Array();
			arr.push(brd);
			$.ajax({
			    url: "/tylibrary/renew/due",
			    data: {arr,arr},
			    dataType : 'json',
			    type: "POST",
			    success : function(data){
			    },
			    error : function(data){		
			    }
			}); 
	
			// 날짜 선택
			$('#datepicker').on('change', function() {
				var pickedDate = $('input').val();
				$('#pickedDate').html(pickedDate);
	
				var arr = new Array();
				arr.push(pickedDate);
				$.ajax({
				    url: "/tylibrary/renew/due",
				    data: {arr,arr},
				    dataType : 'json',
				    type: "POST",
				    success : function(data){
				    },
				    error : function(data){		
				    }
				});
			});
		});
		
		// 공백에 '0' 삽입하는 메소드
        function leftPad(value) {
            if (value < 10) {
                value = "0" + value;
                return value;
            }
            return value;
        }
	</script>

	<!-- JavaScript Libraries -->
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