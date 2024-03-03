<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">


<head>

<meta charset="utf-8">
<title>도서 삭제</title>
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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
								<h2 class="mb-0 text-center">도서 삭제</h2>
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
						<div
							class="d-flex align-items-center justify-content-between mb-4">
							<h6 class="mb-0">| 도서 삭제</h6>
						</div>
					</div>
					<!-- Sales Chart End -->
					<div class="col-sm-12 col-xl-6">
						<div class="bg-light rounded p-4">
						
							<form action="/tylibrary/admin/filteredToDelete" method="post">
								<table class="table table-borderless text-center">
									<thead>
									</thead>
									<tbody>
										<tr>
											<td><select class="form-select form-select-sm mb-3"
												aria-label=".form-select-sm category" name="category">
													<option selected value="일반 서적">일반 서적</option>
													<option value="기술 서적">기술 서적</option>
											</select></td>
											<td><select class="form-select form-select-sm mb-3"
												aria-label=".form-select-sm search" name="searchBy">
													<option selected value="title">도서명</option>
													<option value="author">저자명</option>
											</select></td>
										</tr>
										<tr>
											<td colspan="2"><input
												class="form-control form-control-sm" type="text"
												placeholder="검색어를 입력하세요"
												aria-label=".form-control-sm search" name="searchKey">
											</td>
											<td></td>
										</tr>
										<tr>
											<td colspan="2">
												<button type="submit" class="btn btn-primary w-100 m-2">검색</button>
											</td>
											<td></td>
										</tr>
									</tbody>
								</table>
							</form>
							<div class="col-sm-12 col-xl-6">
								<div class="bg-light text-center rounded p-4">
									<div
										class="d-flex align-items-center justify-content-between mb-4">
										<h6 class="mb-0">도서 정보</h6>
									</div>
									<table class="table small">
										<thead class="small">
											<tr>
												<th scope="col">#</th>
												<th scope="col">도서명</th>
												<th scope="col">저자명</th>
												<th scope="col">삭제</th>
											
											</tr>
										</thead>
									</table>
								</div>
							</div>
							<!-- 
							<form action="/bringBooksInfo" method="post">

								<table class="table table-borderless small">
									<tbody>
										<tr>
											<td>

												<div class="form-floating mb-3">

													<input type="text" class="form-control"
														id="floatingInputISBN" placeholder="ISBN" name="isbn">
													<label for="floatingInputISBN">ISBN으로 검색</label>
												</div>
											</td>
											<td><button type="button"
													class="btn btn-secondary m-2 isbnBtn" id="clickIsbn">Search</button></td>

										</tr>
										<tr>
											<td colspan="2">

																<table class="table">
																	<thead>
																		<tr>
																			<th scope="col">번호</th>
																			<th scope="col">제목</th>
																			<th scope="col">도서 ID</th>

																			<th scope="col"></th>
																		</tr>
																	</thead>
																	<tbody id="content">
																	</tbody>
																</table>
											</td>
										</tr>
									</tbody>
								</table>
							</form> -->
						</div>
					</div>
				</div>
			</div>

		</div>
		<!-- Content End -->


		<!-- Back to Top -->
	</div>
	<!-- 
	<script type="text/javascript">
		$("#clickIsbn")
				.click(
						function() {
							const isbn = $("#floatingInputISBN").val();
							$
									.ajax({
										type : 'post', // 타입 (get, post, put 등등)    
										url : '/tylibrary/admin/bringBooksInfo',
										// 요청할 서버url   
										async : true, // 비동기화 여부 (default : true)   

										//dataType : 'String', // 데이터 타입 (html, xml, json, text 등등)    
										data : {
											isbn : isbn
										},
										success : function(data) {
											console.log(" ajax 통신성공!!");
											console.log(data);
											$('#content').empty();
											var content = '';
											var num = 1;
											for (var i = 0; i < data.length; i++) {
												content += '<tr>'
												content += '<td>' + num
														+ '</td>'
												content += '<td>'
														+ data[i].title
														+ '</td>'
												content += '<td>'
														+ data[i].b_id
														+ '</td>'
												content += '<td><button type="button" class="btn btn-sm btn-danger rounded-pill deleteBtn" data-b_id="' + data[i].b_id + '">삭제</button></td>'
												content += '</tr>'
												num++;
											}

											$('#content').append(content);

										},
										error : function(request, status, error) { // 결과 에러 콜백함수        
											console.log(error)
										}
									})
						})
	</script>
 -->
 <!-- 
	<script type="text/javascript">
		$(document)
				.on(
						'click',
						'.deleteBtn',
						function() {
							var b_idToDelete = $(this).data('b_id');
							console.log(b_idToDelete);
							// 현재 페이지 URL 저장

							var previousUrl = window.location.href;
							// 삭제 확인 페이지로 이동

							window.location.href = '/tylibrary/admin/delete-check?b_id='
									+ b_idToDelete;
						});
	</script>
 -->
	<!-- 바로삭제 
<script>
    $(document).on('click', '.deleteBtn', function () {
        var b_idToDelete = $(this).data('b_id');
        var currentRow = $(this); // 현재 행을 저장하는 변수 추가

        // 서버로 도서 삭제 요청 보내기
        $.ajax({
            type: 'post',
            url: '/deleteBook', 
            async: true,
            data: {
                b_id: b_idToDelete
            },
            success: function (data) {
                console.log("도서 삭제 성공!!");
                // 서버에서 성공적으로 삭제되면 해당 행을 화면에서도 삭제
                currentRow.closest('tr').remove(); // 수정된 부분
            },
            error: function (request, status, error) {
                console.log(error);
            }
        });
    });
</script>
-->
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

	<!-- Template Javascript -->
	<script src="/js/main.js"></script>
</body>

</html>