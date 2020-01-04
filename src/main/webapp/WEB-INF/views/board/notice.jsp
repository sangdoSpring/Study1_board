<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Board List</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <!-- css, jquery 등 화면 그리는 것에 필요한 자바스크립트 파일 또는 중요js파일은 head 선언해준다
    	 그 외에 기능적으로 필요한 자바스크립트 파일(클릭이벤트 등)은 바디 아래부분에 통상적으로 선언한다
    	 그 이유는 모두 헤더에 들어가있으면 처음 화면 그릴때 그려지는 시간이 오래걸리기 때문에 헤더부분엔
    	 중요한것만 선언함 -->
    
</head>
<body>

<!--nav-->
<div >
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a href="/" class="navbar-brand" style="text-decoration:none;">
                    <img src="/images/spring_boot_gray.png" class="img-rounded" style="display:inline-block;height:100%;margin-right:5px" />
                    <span style="vertical-align:middle;">Spring Community Web</span>
                </a>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="/logout">LOGOUT</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>
<!--/nav-->

<div class="container">
    <div class="page-header">
        <h1>게시글 목록</h1>
    </div>

	       	<input type="text" name="search" id="search" placeholder="검색어를 입력하세요" style= width:70%;></input>
	       	<button data-page="search" class="btn btn-primary pageMove">검색</button>

    <div class="pull-right" style="width: 100px; margin: 10px 0;">
        <button data-page="insert" class="btn btn-primary btn-block pageMove">등록</button>
        <button data-page="deleteChk" class="btn btn-block pageMove">선택삭제</button>
        <!--  data-page="deleteChk" >> 데이터 속성을 사용하는 것  data-사용자지정속성이름="기능명(사용자지정)" 
        	  key&value 역할을 함 -->     	
    </div>
    
    <div>
    <form id="boardFrm">
        <table class="table table-hover">
            <thead>
	            <c:forEach var="list" items="${pagingList}">
	            	<tr>
	            		<td>
	            			<input type="checkbox" name="boardChk" value="<c:out value='${list.b_idx}'/>">
	            			<!-- checkbox는 값 여러개 넘어가는 것이니 name에 배열표시해줘야 여러개의 값이 다 담겨서 넘어감
	            				배열표시 안해주면 맨 위의 한개 값만 넘어감 
	            				value에 체크박스 선택한 게시글번호를 넣어서 배열에 게시글 번호가 저장되도록 함-->
	            		</td>
	            		<td>
	            			<fmt:parseNumber value="${list.b_idx}" integerOnly="true"/>
	            		</td>
	            		<td>
	            			<c:out value="${list.b_userId}"/>
	            		</td>
	            		<td>
	            			<c:out value="${list.b_boardType}"/>
	            		</td>
	            		<td>
 	            			<a href="boardDetail?b_idx=<c:out value='${list.b_idx}'/>&&page=<c:out value='${resMap.page}'/>" >
	            				<c:out value="${list.b_title}"/>
	            			</a> 
	            		</td>
	            		<td>
	            			<c:out value="${list.b_createDate}"/>
	            		</td>
	            		<td>
	            			<c:out value="${list.b_updateDate}"/>
	            		</td>
	            	</tr>
	            </c:forEach>
            </thead>
          </table>
        </form>
        
        <div class="content">
                     <ul class="pagination">
                        <c:if test="${resMap.pageGroup > 1}">
                           <li><a href="#" onclick="fnGoPaging(<c:out value="${resMap.prePage}" />)">«</a></li>
                        </c:if>
                        <c:forEach var="i" begin="${resMap.startPage}" 
                                 end="${resMap.endPage > resMap.totalPage ? resMap.totalPage : resMap.endPage }">
                           <%-- choose는 하이라이트를 위해 필요--%>
                           <c:choose>
                        <c:when test="${resMap.page eq i}">
                           <li class="active">
                              <a href="#" onclick="fnGoPaging(${i});">${i }</a>
                           </li>
                        </c:when>
                        <c:otherwise>
                           <li>
                              <a href="#" onclick="fnGoPaging(${i});">${i }</a>
                           </li>                           
                        </c:otherwise>                           
                           </c:choose>
                        </c:forEach>
                        <c:if test="${resMap.nextPage <= resMap.totalPage}">
                           <li><a href="#" onclick="fnGoPaging(<c:out value="${resMap.nextPage}" />)">»</a></li>
                        </c:if>
                     </ul>
                  </div>
        
    </div>
</div>


<!--footer-->
<div >
    <footer class="footer">
        <div class="container">
            <p>Copyright 2017 young891221. All Right Reserved. Designed by ssosso</p>
        </div>
    </footer>
</div>
<!--/footer-->
</body>

<script type="text/javascript">
$(document).ready(function(){
	
	
	//페이지이동
	$(".pageMove").on("click",function(){
		var page = $(this).data("page");
		//위에 지정한 data속성의 이름
		
		if(page == "insert") {
			//해당 data속성의 값			
			
			$(":input").removeAttr("name");
			// : 는 선택자를 의미 jQuery 선택자로 구글링해서 알아보기.
			// id선택시 #, 클래스 선택시 ., 태그선택시 :????이건가 어쨋든 input선택자라함
			// insert는 가지고 들어갈 값이 없으므로 input으로 넘어갈 수 있는 값들을 지우라는 명령어임
			
			$("#boardFrm").attr("action","/boardInsertInit");
			$("#boardFrm").submit();
			
			
		} else if(page == "deleteChk") {
			
			$("#boardFrm").attr("action","/boardDelete");
			$("#boardFrm").submit();
		} else if(page == "search") {
			
			console.log($("#search").val());
			
			var searchObj = { 
					keyword : $("#search").val(),
					page	: "<c:out value='${resMap.page}'/>"		
			};
			
			$.ajax({
				type		: "POST",
				url			: "/boardSearch",
				data		: JSON.stringify(searchObj),
				dataType	: "json",
				contentType : "application/json",
				success		: function(data) {

					console.log(data);
					
					var searchList = data.searchList;
					
					$(".table").children().children().remove();
					
					searchList.forEach(function(list) {
						
						var tr		= document.createElement("tr"),
							td_1		= document.createElement("td"),
							td_2		= document.createElement("td"),
							td_3		= document.createElement("td"),
							td_4		= document.createElement("td"),
							td_5		= document.createElement("td"),
							td_6		= document.createElement("td"),
							td_7		= document.createElement("td"),
							input	= document.createElement("input"),
							aTag	= document.createElement("a");
							
							
							input.setAttribute("type","checkbox");
							input.setAttribute("name","boardChk");
							input.setAttribute("value",list.b_idx);
	
							td_1.append(input);
							td_2.append(list.b_idx);
							td_3.append(list.b_userId);
							td_4.append(list.b_boardType);
							
							aTag.setAttribute("href","boardDetail?b_idx="+"'"+list.b_idx+"'/>");
							aTag.append(list.b_title);
							
							td_5.append(aTag);
							td_6.append(list.b_createDate.date.year+"-"+list.b_createDate.date.month+"-"+list.b_createDate.date.day);
							//위에 jsp에서는 cout을 사용해서 객체 안의 값을 꺼내와서 문제없었지만
							//json으로 받은걸 뿌리려니 DATE타입 오브젝트 그대로 나와서 이렇게 일일히 지정해줘야하는것,
							
							if(list.b_updateDate != undefined){
							
								td_7.append(list.b_updateDate.date.year+"-"+list.b_updateDate.date.month+"-"+list.b_updateDate.date.day);
							}
							
							tr.append(td_1);
							tr.append(td_2);
							tr.append(td_3);
							tr.append(td_4);
							tr.append(td_5);
							tr.append(td_6);
							tr.append(td_7);
							
							console.log(tr);
							$(".table").children().append(tr);
					});
					
				$(".pagination").children().remove();
				
				var ulTag = $(".pagination");
				
                 if (data.pageGroup > 1) {
                	 var pre_li	= document.createElement("li"),
	 					pre_a	= document.createElement("a");
	 				
	 				pre_a.setAttribute("href","#");
	 				pre_a.setAttribute("onclick","fnGoPaging("+data.prePage+")");
	 				pre_li.append(a);
	 				
	 				ulTag.append(pre_li);
                	 
                 }
				
				var endPage = (data.endPage > data.totalPage) ? data.totalPage : data.endPage;
				
				for (var i = data.startPage; i <= endPage; i++) {
					
					var li = document.createElement("li"),
						a	= document.createElement("a");
					
					if (data.page == i) {
						li.setAttribute("class","active");
					}
					
					a.setAttribute("href","#");
					a.setAttribute("onclick","fnGoPaging("+i+")");
					li.append(a);
					
					ulTag.append(li);
						
				}
						
				if (data.nextPage <= data.totalPage) {
					var next_li	= document.createElement("li"),
						next_a	= document.createElement("a");
					
					next_a.setAttribute("href","#");
					next_a.setAttribute("onclick","fnGoPaging("+data.nextPage+")");
					next_li.append(next_a);
					
					ulTag.append(next_li);
				}
				
				
					
					
				},
				error		: function(xhr, status, error){
					alert(error);
				}
			});
			
		}
		
	})
		//onclick 이벤트는 동적으로 생성되는 태그에 대해 인식하지 못함
		//.on("이벤트명")해서 쓰는 이벤트는 동적으로 생성되는 태그도 인식해서 제어가 가능. 이걸 더 권장
		//대상.on("이벤트명",실행시킬함수)
		
})

function fnGoPaging(page) {
   location.href = "/notice?page=" + page;
}
</script>


</html>
