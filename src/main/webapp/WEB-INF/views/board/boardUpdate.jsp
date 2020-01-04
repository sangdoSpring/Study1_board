<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Form</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/base.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>


<script type="text/javascript">
	$(document).ready(function() {

		$("#updateBtn").on("click", function() {
				$("#detailForm").attr("action", "/boardUpdate");
				$("#detailForm").submit();
			})

	})
</script>


<body>
	<div class="container">
		<form id="detailForm" method="post">
			 <input id="b_idx" name="b_idx" type="hidden" value=<c:out value= '${boardVO.b_idx}'/>>
			<!-- <cout /> 태그 자체가 문자열이라 ""로 따로 감싸면 오류난다. -->

			<div class="page-header">
				<h1>게시글 수정</h1>
			</div>

			<table class="table">
				<tr>
					<td>게시판 명</td>
					<td><c:out value="${boardVO.b_boardType}" /></td>
					<td>글번호</td>
					<td><c:out value="${boardVO.b_idx}" /></td>
					<td>작성자</td>
					<td><c:out value="${boardVO.b_userId}" /></td>
					<td>작성일</td>
					<td><c:out value="${boardVO.b_createDate}" /></td>
					<c:set var="updateDate" value="${boardVO.b_updateDate}" />
					<c:if test="${not empty updateDate}">
						<td>수정일</td>
						<td><c:out value="${boardVO.b_updateDate}" /></td>
					</c:if>
				</tr>
			</table>

			<table class="table">
				<tr>
					<th style="padding: 13px 0 0 15px;">제목</th>
					<td><input name="b_title" value = <c:out value='${boardVO.b_title}' /> /></td>
				</tr>

				<tr>
					<th style="padding: 13px 0 0 15px;">내용</th>
					<td><textarea id="b_content" name="b_content" type="text"
							class="col-md-1 form-control input-sm" maxlength="140" rows="7"
							style="height: 200px;"><c:out
								value="${boardVO.b_content}" />
				</textarea> <span class="help-block"></span></td>
				</tr>

				<tr>
					<td></td>
					<td></td>
				</tr>
			</table>



			<div class="pull-left">
				<a href="/notice" class="btn btn-default">목록으로</a>
			</div>

			<div class="pull-right">
				<!-- index 없으면 작성 form이기 때문에 저장 버튼 활성화 -->
				<button id="updateBtn" class="btn btn-primary detail_submit">수정저장</button>
			</div>
		</form>
	</div>


	<div></div>
</body>
</html>