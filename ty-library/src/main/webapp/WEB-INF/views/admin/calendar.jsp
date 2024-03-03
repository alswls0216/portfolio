<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

<title>대여 기간 선택</title>
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


<style>
@import
	url('https://fonts.googleapis.com/css?family=Questrial&display=swap');

td {
	width: 30px;
	height: 30px;
}

.Calendar {
	text-align: center;
}

.Calendar>thead>tr:first-child>td {
	font-family: 'Questrial', sans-serif;
	font-size: 1.1em;
	font-weight: bold;
}

.Calendar>thead>tr:last-child>td {
	font-family: 'Questrial', sans-serif;
	font-weight: 600;
}

.Calendar>tbody>tr>td {
	font-family: 'Montserrat', sans-serif;
	height: 35px;
	width: 35px;
	border-radius: 35px;
	transition-duration: .2s;
	line-height: 35px;
	margin: 2.5px;
	padding : 0 ;
	text-align: center;
}

.disable {
	color: lightgray;
	margin : 0;
}

.today {
	background-color: #C3C3C3;
	color: #fff;
	font-weight: 600;
	cursor: pointer;
	margin : 0;
	border-radius: 1em 0 0 1em;
}

.selectable {
	ackground-color: #FFFFFF;
	cursor: pointer;
}

.futureDay:hover {
	background: #eee;
}

.futureDay.choiceDay, .today.choiceDay {
	background: #0A174E;
	color: #fff;
	font-weight: 600;
	cursor: pointer;
	margin : 0;
}
.rentperiod{
	background: #7FABD6;
	color: #fff;
	font-weight: 400;
	margin : 0;
	
}
.choiceDay{
	background-color: #009CFF ;
	color: #fff;
	font-weight: 600;
	cursor: pointer;
	margin : 0;
	border-radius: 0 1em 1em 0;
}
</style>


<script type="text/javascript">
window.onload = function () { buildCalendar(); }    // 웹 페이지가 로드되면 buildCalendar 실행

let month; let year; let choiceDay; let maxDay; let day; let defaultDay; 
let today = new Date(); 
today.setHours(0,0,0,0);
choiceDay = new Date(today);
choiceDay.setDate(today.getDate()+14);//현재날 +14이 기본선택이며 최저 선택
defaultDay = new Date(choiceDay);
maxDay = new Date(defaultDay);
maxDay.setDate(defaultDay.getDate()+14);//기본 선택 +14일이 최대 선택 가능날
let firstDate = new Date(today.getFullYear(), today.getMonth(), 1);     
let lastDate = new Date(today.getFullYear(), today.getMonth() + 1, 0)
//console.log 빠르게 찍기위해 만든 함수
const c = a => console.log(a);

c(today);c(defaultDay);c(choiceDay);c(maxDay);c(firstDate);c(lastDate);
c("기초설정확인=====오늘.기본.선택.최대.첫날.마지막날=========");
// 달력 생성 : 해당 달에 맞춰 테이블을 만들고, 날짜를 채워 넣는다.
function buildCalendar() {
	
	
    let tbody_Calendar = document.querySelector(".Calendar > tbody");
    document.getElementById("calYear").innerText = firstDate.getFullYear();             // 연도 숫자 갱신
    document.getElementById("calMonth").innerText = leftPad(firstDate.getMonth()+1);  // 월 숫자 갱신

    year = document.getElementById("calYear").innerText;
    month = document.getElementById("calMonth").innerText;
    
    while (tbody_Calendar.rows.length > 0) {                        // 이전 출력결과가 남아있는 경우 초기화
        tbody_Calendar.deleteRow(tbody_Calendar.rows.length - 1);
    }

    let nowRow = tbody_Calendar.insertRow();        // 첫번째 행 추가           

    for (let j = 0; j < firstDate.getDay(); j++) {  // 이번달 1일의 요일만큼
        let nowColumn = nowRow.insertCell();        // 열 추가
    }

    for (let nowDay = new Date(firstDate); nowDay <= lastDate; nowDay.setDate(nowDay.getDate() + 1)) {   // day는 날짜를 저장하는 변수, 이번달 마지막날까지 증가시키며 반복  
    	
        let nowColumn = nowRow.insertCell();        // 새 열을 추가하고

        let newDIV = document.createElement("p");
        newDIV.innerHTML = leftPad(nowDay.getDate());        // 추가한 열에 날짜 입력
        nowColumn.appendChild(newDIV);
		
        //선택가능한 날 
        
        //선택 불가능한 날 = 주말 + 과거 + maxDay 이후
        if (nowDay.getDay() == 6) {                 // 토요일인 경우
            nowRow = tbody_Calendar.insertRow();
            newDIV.className="disable";// 새로운 행 추가
        } else if(nowDay.getDay()== 0) {
    		newDIV.className="disable";
    	} else if (nowDay < today) { 
            newDIV.className = "disable";
        } else if (nowDay > maxDay){
        	newDIV.className = "disable";
        } else if (maxDay >= nowDay && nowDay > today){                                     
        	newDIV.className= "selectable";
            newDIV.onclick = function () { choiceDate(this, year, month); }
        }
		
		if(nowDay > today && nowDay < choiceDay){
        	newDIV.classList.add("rentperiod");        	
        } else if (nowDay - today == 0 ) { 	// 오늘인 경우           
            newDIV.className = "today";
        } else if (choiceDay - nowDay == 0) {  //choiceDay 설정          
            newDIV.className = "choiceDay";
        }		
    }
}

// 날짜 선택
function choiceDate(newDIV, year, month) {
    if (document.getElementsByClassName("choiceDay")[0]) {                              // 기존에 선택한 날짜가 있으면
        document.getElementsByClassName("choiceDay")[0].classList.remove("choiceDay");  // 해당 날짜의 "choiceDay" class 제거
    }
    newDIV.classList.add("choiceDay");           // 선택된 날짜에 "choiceDay" class 추가
    day = newDIV.innerHTML;    
    choiceDay = new Date(year + "-" + month + "-" + newDIV.innerHTML)
    choiceDay.setHours(0,0,0,0);
    console.log(choiceDay);
    c("선택한날")
    // 선택한 연장 날짜
    buildCalendar();
}

// 이전달 버튼 클릭
function MoveMonth(a) {
	firstDate.setMonth(firstDate.getMonth()+a);
	lastDate.setMonth(lastDate.getMonth()+a);
	
	
	if (firstDate>maxDay){
		firstDate.setMonth(firstDate.getMonth() - a);
		lastDate.setMonth(lastDate.getMonth() - a);
		return "";
	}
	if (lastDate<today){
		firstDate.setMonth(firstDate.getMonth() - a);
		lastDate.setMonth(lastDate.getMonth() - a);
		return "";
	}
	buildCalendar();
}

// input값이 한자리 숫자인 경우 앞에 '0' 붙혀주는 함수
function leftPad(value) {
	
    if (value < 10) {
        value = "0" + value;
        return value;
    }
    return value;
}
</script>
</head>

<body>
	<div class="container-xxl position-relative bg-white d-flex p-0">

		<!-- Sidebar Start -->
		<div class="sidebar pe-4 pb-3">
			<nav class="navbar bg-light navbar-light">
				<a href="index.html" class="navbar-brand mx-4 mb-3">
					<h3 class="text-primary">
						<i class="fa fa-hashtag me-2"></i>TY Library
					</h3>
				</a>
				<div class="d-flex align-items-center ms-4 mb-4">
					<div class="position-relative">
						<img class="rounded-circle" src="/boot/img/user.jpg" alt=""
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
					<a href="index.html" class="nav-item nav-link active"><i
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
				<a href="index.html" class="navbar-brand d-flex d-lg-none me-4">
					<h2 class="text-primary mb-0">TY Library</h2>
				</a>
			</nav>
			<!-- Navbar End -->
			<div class="container-fluid pt-4 px-4">
				<div class="row g-4">
					<div class="col-sm-12 col-xl-12">
						<div class="bg-light rounded d-md-flex align-items-center p-4">
							<div style="margin: auto">
								<h2 class="mb-0 text-center"
									style="item-align: center !important;">대여</h2>
							</div>
						</div>
					</div>
				</div>
			</div>

			


			<!-- Sales Chart Start -->

			<div class="container-fluid pt-4 px-4">
				<div class="row g-4">
					<div class="col-sm-12 col-xl-6">
						<div class="bg-light rounded p-4">
							<div
								class="d-flex align-items-center justify-content-between mb-4">
								<h6 class="mb-0">| 대여 기간 선택</h6>
							</div>
							<ul class="mb-0 text-left">
								<li>기본 대여 기간은 2주입니다.</li>
								<li>기본 대여 기간을 초과하여 대여하기를 원하신다면, 하단 달력에서 반납 일자를 선택하여 주시길
									바랍니다.</li>
								<li>최대 대여 기간은 다음달 까지입니다.</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="container-fluid pt-4 px-4">
				<div class="row g-4">
					<div class="col-sm-12 col-xl-6">
						<div class="bg-light rounded p-4">
							<table class="Calendar" style="width : 100%;">
								<thead>
									<tr>
										<td onClick="MoveMonth(-1);" style="cursor: pointer;">&#60;</td>
										<td colspan="5"><span id="calYear"></span>년 <span
											id="calMonth"></span>월</td>
										<td onClick="MoveMonth(1);" style="cursor: pointer;">&#62;</td>
									</tr>
									<tr>
										<td>일</td>
										<td>월</td>
										<td>화</td>
										<td>수</td>
										<td>목</td>
										<td>금</td>
										<td>토</td>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<div class="text-center">
				<c:if test="${sort eq 'rent'}">
					<button type="button" class="btn btn-outline-primary m-2"
						onclick="location='/tylibrary/rent/check'">다음</button>
				</c:if>
				<c:if test="${sort eq 'assign'}">
					<button type="button" class="btn btn-outline-primary m-2"
						onclick="location='/tylibrary/assign/check'">다음</button>
				</c:if>
			</div>
			<!-- Sales Chart End -->

			<!-- Footer Start -->

			<div class="container-fluid pt-4 px-4">
				<div class="bg-light rounded-top p-4">
					<div class="row">
						<div class="col-12 col-sm-6 text-center text-sm-start">
							&copy; <a href="#">TY Library</a>, All Right Reserved.
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

	<!-- Template Javascript -->
	<script src="/js/main.js"></script>
</body>

</html>