<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Form</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/base.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
</head>
<body>


	<div class="container">
	<form action="/boardInsert" method="post">
		<div class="page-header">
			<h1>게시글 등록</h1>
		</div>
		<br> <input id="b_idx" type="hidden">
		<input id="b_userId" name="b_userId" type="hidden" value="admin">

		<table class="table">
			<tr>
				<th style="padding: 13px 0 0 15px;">게시판 선택</th>
				<td>
					<div class="pull-left">
						<select id="b_boardType" name="b_boardType" class="form-control input-sm">
							<option>--분류--</option>
							<option value="notice">공지사항</option>
							<option value="free">자유게시판</option>
						</select>
					</div>
				</td>
			</tr>

			<tr>
				<th style="padding: 13px 0 0 15px;">제목</th>
				<td><input id="b_title" name="b_title" type="text" class="col-md-1 form-control input-sm"></td>
			</tr>

			<tr>
				<th style="padding: 13px 0 0 15px;">내용</th>
				<td><textarea id="b_content" name="b_content" type="text" class="col-md-1 form-control input-sm" maxlength="140" rows="7" style="height: 200px;">
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
			<button id="insert" type="submit" class="btn btn-primary">저장</button>
		</div>
		</form>
	</div>
	

	<div></div>
</body>
</html>