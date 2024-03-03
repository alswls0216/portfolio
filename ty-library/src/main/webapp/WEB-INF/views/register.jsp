<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">


<head>
<style>
/* CSS for positioning the video and canvas elements */
#video-container {
	position: relative;
}

#video {
	position: absolute;
	top: 0;
	left: 0;
}
</style>
<meta charset="utf-8">
<title>도서 등록</title>
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript"
	src="https://unpkg.com/@zxing/library@latest/umd/index.min.js"></script>
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
								<h2 class="mb-0 text-center">도서 등록</h2>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- Sale & Revenue End -->


			<!-- Sales Chart Start -->
			<div class="container-fluid pt-4 px-4"
				style="width: 100%; height: 40%; display: none" id="scan">
				<div class="row g-4" style="height: 100%">
					<div class="col-sm-12 col-xl-12" style="height: 100%">
						<div id="video-container"
							class="bg-light rounded d-md-flex align-items-center p-4"
							style="height: 100%; border: 2px solid gray; overflow: hidden;">
							<video id="video" style="object-fit: cover;"></video>
						</div>
						<button id="switchButton" class="btn btn-primary m-2 w-100"
							style="margin: 0 !important; margin-top: 0.2em !important;">화면전환</button>
					</div>
				</div>
			</div>
			<div class="container-fluid pt-4 px-4">
				<div class="row g-4">
					<div class="col-sm-12 col-xl-6">
						
					</div>
					
					<!-- Sales Chart End -->
					<div class="col-sm-12 col-xl-6">
						<div class="bg-light rounded p-4">

							<form action="/tylibrary/admin/selectBookInfo" method="post">

								<table class="table table-borderless small">
									<tbody>
										<tr>
											<td>

												<div class="form-floating mb-3">

													<input type="text" class="form-control"
														id="floatingInputISBN" placeholder="ISBN" name="isbn">
													<label for="floatingInputISBN">ISBN</label>

												</div> <!-- isbn잘못 입력시 -->
												<div>
													<span id="result_checkPsw" style="font-size: 12px"></span>
												</div>


											</td>
											
											<td>
												<div class="d-flex align-items-center">
													<!-- 수평으로 정렬하는 div -->
													<button type="button" class="btn btn-secondary m-2 isbnBtn"
														id="clickIsbn">Search</button>
													<button type="button" class="btn btn-secondary"
														id="scanIsbn">
														<img src="/img/scan.png" style="width: 20px; height: 20px;" />
													</button>
												</div>
											</td>
										</tr>





										<tr>
											<td colspan="2">
												<div class="form-floating mb-3">
													<input type="text" class="form-control" value=""
														name="title" id="floatingInputTitle" placeholder="도서명">
													<label for="floatingInputTitle">도서명</label>
												</div>
											</td>
											<td></td>
										</tr>
										<tr>
											<td colspan="2">
												<div class="form-floating mb-3">
													<input type="text" class="form-control" value=""
														name="author" id="floatingInputAuthor" placeholder="저자명">
													<label for="floatingInputAuthor">저자명</label>
												</div>
											</td>
											<td></td>
										</tr>
										<tr>
											<td colspan="2">
												<div class="form-floating mb-3">
													<input type="text" class="form-control" value=""
														name="publisher" id="floatingInputPublisher"
														placeholder="출판사"> <label
														for="floatingInputPublisher">출판사</label>
												</div>
											</td>
											<td></td>
										</tr>
										<tr>
											<!-- 
                           <td colspan="2">
                                 <div class="form-floating mb-3">
                                    <input type="text" class="form-control" value="" name="category"
                                       id="floatingInputCategory" placeholder="카테고리"> <label
                                       for="floatingInputCategory">카테고리</label>
                                 </div>
                              </td>
                              <td></td>
                              <tr>
                              <td colspan="2">
                               -->
											<td colspan="2">
												<div class="form-floating mb-3">
													<select class="form-select" name="category"
														id="floatingInputCategory" aria-label="카테고리 선택">
														<option value="" disabled selected></option>
														<!-- 다른 옵션들을 추가하세요 -->
														<option value="일반 서적">일반 서적</option>
														<option value="기술 서적">기술 서적</option>

													</select> <label for="floatingInputCategory">카테고리</label>
												</div>
											</td>
											<td></td>
										<tr>
											<td colspan="2">

												<button class="btn btn-primary w-100 m-2" type="submit">등록</button>

											</td>
										</tr>


									</tbody>
								</table>
								<input type="text" id="qrcode" name="qrcode" value=""
									style="display: none;" />
							</form>
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

	<script type="text/javascript">
	$("#qrcode").val(window.location.host);
	
	const scan = document.getElementById("scan");
	const switching = document.getElementById("switchButton");
	let i = 0;
	
	$("#scanIsbn").click(function () {
		const codeReader = new ZXing.BrowserMultiFormatReader();
		codeReader.listVideoInputDevices().then((videoInputDevices) => {//사용가능한 카메라가 있는지 찾아 videoInputDevices로 배열생성 
			let numOfCamera = videoInputDevices.length;
			if (numOfCamera<1){
				alert ("카메라가 있는 디바이스로 접속해주세요");
			}else{
				scan.style.display="block";
			}
			let selectedDeviceId = videoInputDevices[i].deviceId;
			$("#switchButton").off('click').on('click', () => {
				i++;
				codeReader.reset();
				if(i == numOfCamera){
					i = 0;
					selectedDeviceId = videoInputDevices[i].deviceId
					}else{
						selectedDeviceId = videoInputDevices[i].deviceId;
						}
				codeReader.decodeFromVideoDevice(selectedDeviceId, 'video', (result, err) => {
					if (result) {
							$('#floatingInputISBN').val(result.text);
							scan.style.display="none";
							i=0;
							codeReader.reset();
						}
					if (err && !(err instanceof ZXing.NotFoundException)) {
						console.error(err);
						document.getElementById('result').textContent = err;
						}
					})
					})
					switching.click();
			}).catch((err) => {console.error(err)})
		})
</script>
	
	<script>
   $("#clickIsbn").click(function() {
      const isbn = $("#floatingInputISBN").val();
      $.ajax({
         type : 'get', // 타입 (get, post, put 등등)    
         url : '/tylibrary/admin/searchIsbn',
         // 요청할 서버url   
         async : true, // 비동기화 여부 (default : true)   

         //dataType : 'String', // 데이터 타입 (html, xml, json, text 등등)    
         data : {
            name : isbn
         },
         success : function(data) {

            $('input[name=title]').attr('value', data.title);
            $('input[name=author]').attr('value', data.author);
            $('input[name=publisher]').attr('value', data.publisher);

            sessionStorage.setItem('title', data.title);
            sessionStorage.setItem('author', data.author);
            sessionStorage.setItem('publisher', data.publisher);
         
         },
         error : function(request, status, error) { // 결과 에러 콜백함수        
            console.log(error)
         }
      })
   })
</script>

	<!-- isbn잘못 입력시 상태창 -->
	<script>

$("#floatingInputISBN").blur(function () {
   let isbnCheck = /^.{13}$/;   
    let result = "";

    if (!isbnCheck.test($("#floatingInputISBN").val())) {
      result =
        "잘못입력되었습니다.";
      $("#result_checkPsw").html(result).css("color", "red");
      $("#floatingInputISBN").val("");
    } else {
      result = "해당 도서를 조회합니다.";
      $("#result_checkPsw").html(result).css("color", "green");
    }
  });
  
</script>

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