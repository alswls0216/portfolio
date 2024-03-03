<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Page Error</title>
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
</head>

<body>
    <div class="container-xxl position-relative bg-white d-flex p-0">
        <!-- Sign In Start -->
        <div class="container-fluid">
            <div class="row h-100 align-items-center justify-content-center" style="min-height: 100vh;">
                <div class="col-12 col-sm-8 col-md-6 col-lg-5 col-xl-4">
                    <div class="bg-light rounded p-4 p-sm-5 my-4 mx-3">
                        <div class="d-flex align-items-center justify-content-between mb-3">
                            <a href="/tylibrary/books/${b_id}" class="">
                                <h3 class="text-primary"></i>TY Library</h3>
                            </a>
                        </div>
                        <div class="form-floating mb-4">
                            <c:if test="${error_type eq 'input_error'}">
								<p class="text-center mb-0">잘못된 접근입니다.<br/>다시 시도해주세요.</p>
							</c:if>
							<c:if test="${error_type eq 'cant_rent'}">
								<p class="text-center mb-0">이미 대여 중인 도서입니다.<br/>다시 시도해주세요.</p>
							</c:if>
							<c:if test="${error_type eq 'cant_renew'}">
								<p class="text-center mb-0">잘못된 접근입니다. [사번 오류]<br/><br/>사번을 올바르게 입력하거나<br/>'양도' 메뉴를 이용해주세요.</p>
							</c:if>
							<c:if test="${error_type eq 'cant_assign'}">
								<p class="text-center mb-0">잘못된 접근입니다. [사번 오류]<br/><br/>사번을 올바르게 입력하거나<br/>'연장' 메뉴를 이용해주세요.</p>
							</c:if>
							<c:if test="${error_type eq 'error_assign'}">
								<p class="text-center mb-0">양도 정보가 없거나<br/>양도가 이미 완료되었습니다.</p>
							</c:if>
							<c:if test="${error_type eq 'error_renew'}">
								<p class="text-center mb-0">양도 정보가 없거나<br/>연장이 이미 완료되었습니다.</p>
							</c:if>
						<%
						// error_type 세션값 삭제
						session.removeAttribute("error_type");
						%>
                            
                        </div>
							<button type="button" class="btn btn-primary py-3 w-100 mb-4" onclick="location='/tylibrary/books/${b_id}'">도서 페이지로 이동</button>

                    </div>
                </div>
            </div>
        </div>
        <!-- Sign In End -->
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