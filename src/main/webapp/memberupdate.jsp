<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="x-ua-compatible" content="ie=edge">

<title>회원 등록</title>

<!-- Font Awesome Icons -->
<link rel="stylesheet"
	href="./resources/bootstrap/plugins/fontawesome-free/css/all.min.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="./resources/bootstrap/dist/css/adminlte.min.css">


<!-- 주소설정 -->
<script src="<c:url value="/js/jquery.min.js" />"></script>
<!-- 주소 입력 부분/ DAUM API 활용 -->
<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	$(function() {
		$("#addrBtn").on("click", function() {
			new daum.Postcode({
				oncomplete : function(data) {
					$("#addr1").val(data.roadAddress); // 도로명 주소
					$("#zipcode").val(data.zonecode); // 우편주소
					$("#addr2").focus();
				}
			}).open();
		});
	});
</script>


</head>

<body class="hold-transition sidebar-mini">
	<div class="wrapper">
		<!-- Navbar -->
		<%@ include file="/common/navi.jsp"%>
		<!-- Main Sidebar Container -->
		<%@ include file="/common/mainside.jsp"%>

		<div id="if_list_div"
			style="position: relative; padding: 0; overflow: hidden;">
			<!-- Content Wrapper. Contains page content -->
			<div class="content-wrapper">

				<!-- Main content -->
				<section class="content register-page" style="height: 100%;">
					<div class="container-fluid">
						<div class="login-logo">
							<b>회원 정보 수정</b>
						</div>

						<!-- form start -->
						<div class="card">
							<div class="register-card-body">
								<form role="form" class="form-horizontal"
									action="${cp }/userModify" method="post"
									enctype="multipart/form-data">
									<input type="hidden" name="userid" value="${user.userid}" /> <input
										type="hidden" name="filename" value="${user.filename}" /> <input
										type="hidden" name="realfilename"
										value="${user.realfilename }" /> <input type="hidden"
										name="reg_dt" value="2020.01.21" />

									<div class="input-group mb-3">
										<div class="mailbox-attachments clearfix"
											style="text-align: center; width: 100%;">
											<div class="mailbox-attachment-icon has-img" id="pictureView"
												style="border: 1px solid green; height: 200px; width: 140px; margin: 0 auto;">
												<img src="/profile?userid=${user.userid }"
													id="pictureViewImg" style="width: 100%; height: 100%;" />
											</div>
											<div class="mailbox-attachment-info">
												<div class="input-group input-group-sm">
													<input id="profile" class="form-control" type="file"
														name="profile" accept=".gif, .jpg, .png"
														style="height: 37px;" />
												</div>
											</div>
										</div>
										<br />
									</div>

									<div class="form-group row">
										<label for="id" class="col-sm-3" style="font-size: 0.9em;">
											<span style="color: red; font-weight: bold;">*</span>아이디
										</label>
										<div class="col-sm-9 input-group-sm">
											<input name="userId" type="text" class="form-control"
												id="userId" placeholder="회원 Id" value="${user.userid}"
												readonly="readonly">
										</div>
									</div>

									<!-- 									<div class="form-group row">
										<label class="col-sm-3" style="font-size: 0.9em;"> 아이디
										</label>
										<div class="col-sm-9 input-group input-group-sm">
											<span class="input-group-append-sm">brown</span>
										</div>
									</div> -->

									<div class="form-group row">
										<label for="pass" class="col-sm-3" style="font-size: 0.9em;">
											<span style="color: red; font-weight: bold;">*</span>패스워드
										</label>
										<div class="col-sm-9 input-group-sm">
											<input class="form-control" name="pass" type="password"
												id="pass" placeholder="비밀번호" value="${user.pass }"
												required="required" />
										</div>
									</div>

									<div class="form-group row">
										<label for="name" class="col-sm-3" style="font-size: 0.9em;">
											<span style="color: red; font-weight: bold;">*</span>이 름
										</label>
										<div class="col-sm-9 input-group-sm">
											<input class="form-control" name="userNm" type="text"
												id="userNm" placeholder="이름" value="${user.usernm }"
												required="required" />
										</div>

									</div>
									<div class="form-group row">
										<label for="alias" class="col-sm-3" style="font-size: 0.9em;">별명</label>
										<div class="col-sm-9 input-group-sm">
											<input class="form-control" name="userAlias" type="text"
												id="userAlias" placeholder="별명" value="${user.alias }">
										</div>
									</div>
									<div class="form-group row">
										<label for="addr1" class="col-sm-3 control-label">주소</label>
										<div class="col-sm-3 input-group-sm">
											<input name="addr1" type="text" class="form-control"
												id="addr1" placeholder="주소" value="${user.addr1 }" readonly>
										</div>
										<div class="col-sm-3 input-group-sm">
											<input name="addr2" type="text" class="form-control"
												id="addr2" placeholder="상세주소" value="${user.addr2 }">
										</div>

										<div class="col-sm-2 input-group-sm">
											<input name="zipcode" type="text" class="form-control"
												id="zipcode" placeholder="우편번호" value="${user.zipcode }"
												readonly>
										</div>
										<div class="col-sm-1 input-group-sm">
											<span class="input-group-append-sm">
												<button type="button" id="addrBtn"
													class="btn btn-info btn-sm btn-append">주소검색</button>
											</span>
										</div>
									</div>

									<div class="card-footer">
										<div class="row">
											<div class="col-sm-6">
												<button type="submit" id="registBtn" class="btn btn-info">수정
													완료</button>
											</div>

											<div class="col-sm-6">
												<button type="button" id="cancelBtn"
													class="btn btn-default float-right"
													onClick="history.go(-1)">수정 취소</button>
											</div>

										</div>
									</div>
								</form>
							</div>
							<!-- register-card-body -->
						</div>
					</div>
				</section>
				<!-- /.content -->
			</div>
			<!-- /.content-wrapper -->
		</div>
	</div>

	<!-- Main Footer -->
	<%@ include file="/common/mainfooter.jsp"%>

	<!-- ./wrapper -->

	<!-- REQUIRED SCRIPTS -->
	<%@ include file="/common/jqBootLte.jsp"%>

	<script>
		$(document).ready(function() {
			// picture input의 파일 변경시 이벤트 
			$("#profile").change(function() {
				readURL(this);
			});
		});

		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();

				reader.onload = function(e) {
					$('#pictureViewImg').attr('src', e.target.result);
				}
				reader.readAsDataURL(input.files[0]);
			}
		}
	</script>
</body>
</html>