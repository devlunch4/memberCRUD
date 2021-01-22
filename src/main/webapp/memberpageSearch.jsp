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

<title>회원 리스트</title>

<!-- Font Awesome Icons -->
<link rel="stylesheet"
	href="./resources/bootstrap/plugins/fontawesome-free/css/all.min.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="./resources/bootstrap/dist/css/adminlte.min.css">

<script src="<c:url value="/js/jquery.min.js" />"></script>

<!--  -->
<script type="text/javascript">
	//해당 사용자 클릭했을 경우
	$(function() {
		$(".user").on("click", function() {
			var userid = $(this).data("userid");
			$("#userid").val(userid);
			$("#frm").attr("action", "${cp}/member");
			$("#frm").submit();
		});

		//페이지 보기 변경 작업
		$("#pagesize").on("change", function() {
			$("#frm2").attr("action", "${cp}/pagingUserSearch");
			$("#frm2").submit();
		});
		// $("select").val(${param.lang }); //==> $("select").val(en); 오류발생
		$("#pagesize").val(${pagevo.pageSize });//==> $("select").val("en"); 정상 처리

		//검색
		$("#searchBtn").on("click", function() {
			searchType = $("#searchType").val();
			keyword = $("#keyword").val();
			$("#searchType").val(searchType);
			$("#keyword").val(keyword);
			
			$("#frm2").attr("action", "${cp}/pagingUserSearch");
			$("#frm2").submit();
		});
		$("#pagesize").val(${pagevo.pageSize });
		$("#keyword").val(${keyword });
		$("#searchType").val(${searchType });
		$("#page").val(${pagevo.page });
 
	});
</script>
</head>
<body class="hold-transition sidebar-mini">

	<form id="frm">
		<input type="hidden" id="userid" name="userid" value=""> <input
			type="hidden" id="keyword" name="keyword" value="" /> <input
			type="hidden" id="searchType" name="searchType" value="" />
	</form>



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
								<h1>회원 리스트</h1>
							</div>
							<div class="col-sm-6">
								<ol class="breadcrumb float-sm-right">
									<li class="breadcrumb-item">회원리스트</li>
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
												<th>아이디</th>
												<th>패스워드</th>
												<th>별명</th>
												<th>도로주소</th>
												<th>등록날짜</th>
												<!-- yyyy-MM-dd  -->
											</tr>
											<c:forEach items="${userList }" var="user" varStatus="loop">
												<tr class="user" data-userid="${user.userid }">
													<td>${user.userid }</td>
													<td>${user.pass }</td>
													<td>${user.alias }</td>
													<td>${user.addr1 }${user.addr2 }</td>
													<td><fmt:formatDate value="${user.reg_dt }"
															pattern="yyyy-MM-dd" /></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								<!-- col-sm-12 -->
							</div>
							<!-- row -->
						</div>


						<!-- card-body -->
						<div class="card-footer">
							<nav aria-label="member list Navigation">
								<ul class="pagination justify-content-center m-0">
									<li class="page-item"><a class="page-link"
										href="${cp }/pagingUserSearch?page=1&pageSize=${pagevo.pageSize }&keyword=${keyword }&searchType=${searchType }"><i
											class="fas fa-angle-double-left"></i></a></li>


									<li class="page-item"><a class="page-link"
										href="${cp }/pagingUserSearch?page=<c:choose>
<c:when test="${pagevo.getPage()- 1 <= 0 }">1</c:when>
<c:otherwise>${pagevo.getPage()- 1}</c:otherwise>
</c:choose>&pagesize=${pagevo.pageSize }&keyword=${keyword }&searchType=${searchType }"><i
											class="fas fa-angle-left"></i></a></li>

									<c:forEach begin="1" end="${pagination }" var="i">
										<c:choose>
											<c:when test="${pagevo.page == i }">

												<li class="page-item active"><a class="page-link"
													href="#">${i }</a></li>
											</c:when>
											<c:otherwise>
												<li class="page-item"><a class="page-link"
													href="${cp }/pagingUserSearch?page=${i }&pagesize=${pagevo.pageSize }&keyword=${keyword }&searchType=${searchType }">${i }</a></li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<li class="page-item"><a class="page-link"
										href="${cp }/pagingUserSearch?page=<c:choose>
<c:when test="${pagevo.getPage()+ 1 >= pagination }">${pagination }</c:when>
<c:otherwise>${pagevo.getPage()+ 1}</c:otherwise>
</c:choose>&pagesize=${pagevo.pageSize }&keyword=${keyword }&searchType=${searchType }"><i
											class="fas fa-angle-right"></i></a></li>
									<li class="page-item"><a class="page-link"
										href="${cp }/pagingUserSearch?page=${pagination }&pagesize=${pagevo.pageSize }&keyword=${keyword }&searchType=${searchType }"><i
											class="fas fa-angle-double-right"></i></a></li>
								</ul>
							</nav>
						</div>
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