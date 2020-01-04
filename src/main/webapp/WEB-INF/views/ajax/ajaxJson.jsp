<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>ajax & json 테스트 화면</h1>
<button id="btn1">누르시오~~</button>

</body>

<script type="text/javascript">

$(document).ready(function(){
	
	$("#btn1").on("click",function(){
		
		var obj = {name : "홍길동"};
		// {}이 부분을 기호로 인식해서 서버에 오브젝트를 전송할때 바뀌어버린다.
		//그래서 이걸
		
		
		
		//JS 자체에서 JSON 내장 객체를 가지고 있고, 주요 기능 'JSON.parse()', 'JSON.stringfy()' 함수사용가능
		//반드시 jquery가 선언된 상태여야만 사용이 가능하다
		$.ajax({
            type:"POST",                               //method 타입
            url:"/ajaxTest",                           //어떤 requestMapping으로 보낼 것인가 선언
            data : JSON.stringify(obj),				   //json객체 타입으로 내가 서버에 넘기고자하는 데이터 (json이 아니어도 어떤 데이터 형태든지 보낼 수 있다)
            dataType : "json",					 	   //어떤 데이터로 받을것인가 
            contentType:"application/json",			   //송신할때 어떤 데이터 타입으로 보내서 통신할건지
            success: function(data){				   //통신이 성공적으로 완료되는 경우 이 부분 실행 
            										   //파라미터의 값은 실행결과값이 자동으로 들어가므로 파라미터 이름은 사용자정의
                console.log(data);
            },
            error: function(xhr, status, error) {	   //통신에러 뜨는 경우 에러부분 실행 
                alert(error);
            }  
            
            //JSON.stringify(obj) obj를 문자열로 바꿔달라는 함수
       });
	});	
});


</script>

</html>