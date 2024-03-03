<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>

<html>
<head>
    <title>QR 코드 생성 및 저장</title>
    <!-- qr코드 라이브러리 cdn -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/qrcodejs/1.0.0/qrcode.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<!-- qr코드 그려지는 div  -->	
    <div id="qrcode" style="display: block"></div>
    <!-- id앞에 부분 url 자동으로 매칭되서 들어감 -->
    <input type="text" id="url-front" style="width: 350px ; display: none" >
    <!-- qr에 넣을 id 부분 -->
    <input type="text" id="text-input" placeholder="QR 코드에 담을 Id를 입력하세요"  style="width: 150px"><br>
	<!-- url 완성 -->
    <input type="text" id="url-full" placeholder="전체 url" style="width: 400px" readonly>
    <!-- 버튼 클릭시 qr코드 생성 -->
    <button id="generate-button">QR 코드 생성</button><br>
    <!-- 자동으로 id로 파일명 정해지나 수동으로 선택가능 -->
    <input type="text" id="file-name" placeholder="QR코드 파일 이름을 입력하세요"> 
    <!-- 다운로드시 위에서 정한 파일명으로 파일 다운로드됨 -->   
    <button id="save-button">QR 코드 저장</button>
    <!-- 디비에서 books에서 id전부 가지고 옴 -->
    <button id="get-ids">id가져오기</button>
    <!-- 가지고 온 id들로 qr만들고 저장함 -->
	<button id="auto-gen">자동생성</button>
    <script type="text/javascript">
    	// 엘리먼트 변수에 할당
        const qrcodeDiv = document.getElementById("qrcode");
        const urlFront = document.getElementById("url-front");
        const textInput = document.getElementById("text-input");
        const urlFull = document.getElementById("url-full");
        const textFileName = document.getElementById("file-name");
        const generateButton = document.getElementById("generate-button");
        const saveButton = document.getElementById("save-button");
        const getIds = document.getElementById("get-ids");
        const autoGen = document.getElementById("auto-gen");
        
        // 조회한 아이디 담을 배열과 반복 돌릴 변수 i
		let ids = "";
		let i = 0;
		
		
		
        
        //url front 원하는 값 넣으면됨 현재는 현재 페이지 http:~~~~~~~~ /+ books?id= 로 정해놨음
        urlFront.value = window.location.href.split('qrgen')[0] + "books/"
        let qrcodeInstance;
		function makeURL(){
			//http:~~~~~~~~ + /books?id= + textInput(id)
			urlFull.value= urlFront.value + textInput.value;
		}
		
		//클릭시 qr코드 만들어줌
        generateButton.addEventListener("click", function () {
            const text = urlFront.value + textInput.value;
            if (text) {
                qrcodeDiv.innerHTML = "";
                //실질적으로 qr 만드는 부분 qrcodeDiv에 text를 담음 text는 만들어진 url임
                qrcodeInstance = new QRCode(qrcodeDiv, text);
                //파일명에 id를 가져다 박음
                textFileName.value = text.split('/books/')[1];
                //전체 url 보여줌
                urlFull.value=text;
            }
        });
		//버튼 클릭시 만들어진 qr코드가 저장됨
        saveButton.addEventListener("click", function () {
            if (qrcodeInstance) {
            	//qrdiv에 존재하는 캔버스를 따와서 dataURL로 변환
                const canvas = qrcodeDiv.querySelector("canvas");
                const imageDataURL = canvas.toDataURL("image/png");

                //a태그 만들고 dataURL로 변한된 정보를 다운로드함
                const a = document.createElement("a");
                //a태그에 속성 정함 링크
                a.href = imageDataURL;
                //파일명
    			a.download ="qrcode.png"
                if(textFileName.value){
                a.download = textFileName.value+".png";
                textInput.value = null;
                }
                //정해진 속성 클릭해서 다운로드
                a.click();
                textInput.focus();
            }
        });
        
      //편의를 위해 만든기능 볼 필요 없음
        getIds.addEventListener("click",function () {
        	 $.ajax({
                 url: "/tylibrary/get-ids", // 컨트롤러 메서드의 URL
                 type: "GET",
                 dataType: "json",
                 success: function (data) {
                     ids = data;
                     console.log(data);
                 },
                 error: function () {
                     alert("데이터를 가져오는 중에 오류가 발생했습니다.");
                 }
             });
        });
		
		
        autoGen.addEventListener("click", function() {
            let i = 0;
            let interval = setInterval(function() {
                textInput.value = ids[i];
                makeURL();
                if (i === ids.length - 1) { // 배열 인덱스가 길이보다 하나 작은지 확인
                    clearInterval(interval);
                    i = 0;
                } else {
                    i++;
                }
                generateButton.click();
                saveButton.click();
            }, 500);
        });

    </script>
</body>
</html>

