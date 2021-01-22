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

<title>구현 현황 확인</title>

<!-- Font Awesome Icons -->
<link rel="stylesheet"
	href="./resources/bootstrap/plugins/fontawesome-free/css/all.min.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="./resources/bootstrap/dist/css/adminlte.min.css">

</head>
<body class="hold-transition sidebar-mini">
	<div class="wrapper">
		<!-- Navbar -->
		<%@ include file="/common/navi.jsp"%>
		<!-- Main Sidebar Container -->
		<%@ include file="/common/mainside.jsp"%>


		<div id="if_list_div"
			style="position: relative; padding: 0; overflow: scroll; height: 750px;">
			<div class="content-wrapper" style="min-height: 584px;">
				<!-- Content Header (Page header) -->
				<section class="content-header">
					<div class="container-fluid">
						<div class="row md-2">
							<div class="col-sm-6">
								<h1>회원리스트</h1>회원 상세 정보는 회원 관리에서 사용자 클릭
							</div>
							<div class="col-sm-6">
								<ol class="breadcrumb float-sm-right">
									<li class="breadcrumb-item">완료 내역</li>
									<li class="breadcrumb-item">목록</li>
								</ol>
							</div>
						</div>
					</div>
				</section>
				<!-- Main content -->
				<section class="content">
					<div class="card">
						<!-- card header -->
						<%@ include file="/common/cardheadersearchbar.jsp"%>

						<div class="card-body" style="text-align: center;">
							<div class="row">
								<div class="col-sm-12">
									<table class="table table-bordered">
										<tbody>
											<tr>
												<th>수행</th>
												<th>완료</th>
											</tr>

											<tr>
												<th>개인적 추가!!! 스크롤 구현</th>
												<th>o</th>
											</tr>

											<tr>
												<td>로그인 성공 시 관리페이지 이동</td>
												<td>o</td>
											</tr>
											<tr>
												<td>로그인 실패시 사용자 아이디 태그 입력</td>
												<td>o</td>
											</tr>
											<tr>
												<td>쿠키사용</td>
												<td>o</td>
											</tr>
											<tr>
												<td>회원등록 버튼 > 회원 등록페이지 이동</td>
												<td>o</td>
											</tr>
											<tr>
												<td>회원관리 버튼 > 회원 쪽페이지 5명씩</td>
												<td>o</td>
											</tr>
											<tr>
												<td>사용자 검색구분 버튼 > 아이디/이름/별명</td>
												<td>o</td>
											</tr>
											<tr>
												<td>검색어</td>
												<td>o</td>
											</tr>
											<tr>
												<td>사용자 아이디 클릭시 상세정보 이동</td>
												<td>o</td>
											</tr>
											<tr>
												<td>페이징 처리 [\&lt;&lt;\ &lt;\ 번호 &gt;\ &gt;&gt;\]</td>
												<td>o</td>
											</tr>
											<tr>
												<td>등록날짜 포맷 yyyy-MM-dd</td>
												<td>o</td>
											</tr>


											<tr>
												<td>아이디 패스워드 이름 입력 필수</td>
												<td>o</td>
											</tr>
											<tr>
												<td>프로필 사진 선택시 이미지태그에 미리불러오기 기능</td>
												<td>o</td>
											</tr>

											<tr>
												<td>주소입력 API 이용</td>
												<td>o</td>
											</tr>

											<tr>
												<td>잘못된 입력/ 필수값 미입력 경고</td>
												<td>o</td>
											</tr>
											<tr>
												<td>취소버튼시 공백 초기화</td>
												<td>o</td>
											</tr>

											<!-- 회원등록 -->

											<tr>
												<td>취소버튼시 공백 초기화</td>
												<td>o</td>
											</tr>
											<tr>
												<td>사용자 정보 문자 표현</td>
												<td>o</td>
											</tr>
											<tr>
												<td>사용자 이미지 표현</td>
												<td>o</td>
											</tr>
											<tr>
												<td>화면상 등록버튼 수정버튼</td>
												<td>o</td>
											</tr>
											<tr>
												<td>수정버튼 클릭시 이동</td>
												<td>o</td>
											</tr>
											<tr>
												<td>삭제버튼 추가 및 삭제 성공/실패 처리</td>
												<td>o</td>
											</tr>

											<!-- 화면 수정 -->
											<tr>
												<td>아이디 수정 불가</td>
												<td>o</td>
											</tr>
											<tr>
												<td>수정 버튼을 통한 수정</td>
												<td>o</td>
											</tr>
											<tr>
												<td>수정 시 패스워드와 이름은 필수값</td>
												<td>o</td>
											</tr>
											<tr>
												<td>사진 변경시 확인</td>
												<td>o</td>
											</tr>
											<tr>
												<td>취소 버튼시 이동</td>
												<td>o</td>
											</tr>
										</tbody>
									</table>
								</div>
								<!-- col-sm-12 -->
							</div>
							<!-- row -->
						</div>
						<!-- card-body -->
						<!-- card-footer -->
					</div>
					<!-- card  -->
				</section>
			</div>
		</div>
	</div>



	<!-- Main Footer -->
	<%@ include file="/common/mainfooter.jsp"%>
	<!-- ./wrapper -->

	<!-- REQUIRED SCRIPTS -->
	<%@ include file="/common/jqBootLte.jsp"%>
</body>
</html>







