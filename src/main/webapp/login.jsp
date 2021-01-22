<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MEMBER LOGIN</title>
<%@ include file="/common/jqboot.jsp"%>
<!-- js-cookie@ & script -->
<%@ include file="/common/loginscript.jsp"%>

<script type="text/javascript">
	$(function() {
		//userid rememberme 쿠키를 확인하여 존재할 경우 값설정, 체크
		if (Cookies.get("userid") != undefined) {
			$("#userid").val(Cookies.get("userid"));
			$("#rememberme").prop("checked", true);
		}
		//signin 아이디를 select
		$("#signin").on("click", function() {
			//rememberme 체크박스가 체크 되어있는지 확인
			//체크되어있을 경우
			if ($("#rememberme").is(":checked") == true) {
				//userid input에 있는 값을 userid쿠키로 저장
				Cookies.set("userid", $("#userid").val());
				//rememberme 쿠키로 y 값을 저장
				Cookies.set("rememberme", "Y");
			}
			//해제 되어있는 경우 : 더이상 사용하지 않는 다는 의미이므로 userid, rememberme 쿠키 삭제
			else {
				Cookies.remove("userid");
				Cookies.remove("rememberme");
			}
			//form 태그를 이용하여 signin 요청 [[[무조건 submit으로 설정할 필요는 없다. ]]]
			$("#frm").submit();
		});
	});
</script>
</head>
<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<a href="#"><b>관리자 로그인</b></a>
		</div>
		<!-- /.login-logo -->
		<div class="card">
			<div class="card-body login-card-body">
				<p class="login-box-msg">Sign in to start your session</p>
				<form id="frm"
					action="<%=request.getContextPath()%>/loginController"
					method="post">
					<div class="form-group has-feedback">
						<input type="text" class="form-control" name="userid" id="userid"
							placeholder="아이디를 입력하세요." value=""> <span
							class="glyphicon glyphicon-envelope form-control-feedback"></span>
					</div>
					<div class="form-group has-feedback">
						<input type="password" class="form-control" name="pass" id="pass"
							placeholder="패스워드를 입력하세요." value="sallyPass"> <span
							class="glyphicon glyphicon-lock form-control-feedback"></span>
					</div>
					<div class="row">
						<div class="col-sm-8">
							<div class="checkbox icheck">
								<label> <input type="checkbox" name="rememberme"
									id="rememberme" value=""> Remember Me
								</label>
							</div>
						</div>
						<!-- /.col -->
						<div class="col-sm-4">
							<button type="button" class="btn btn-primary btn-block btn-flat"
								id="signin">로그인</button>
						</div>
						<!-- /.col -->
					</div>
				</form>

			</div>
			<!-- /.login-box-body -->
		</div>
	</div>
	<!-- /.login-box -->
</body>
</html>
