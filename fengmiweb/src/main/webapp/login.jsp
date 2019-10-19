<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="${pageContext.request.contextPath}/assters/css/bootstrap.min.css" rel="stylesheet">
	<script type="text/javascript" src="${pageContext.request.contextPath}/assters/js/jquery-3.4.1.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assters/css/login.css">
	<title>锋迷网-登陆</title>
</head>
<body style="background-image: url('${pageContext.request.contextPath}/assters/images/image/qf.jpg')">
	<div class="regist" style="height: 360px">
		<div class="regist_center">
			<div class="regist_top">
				<div class="left fl"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;欢迎登录</div>
				<div class="right fr">
					<a href="index.jsp" target="_black">锋迷网</a>
				</div>
				<div class="clear"></div>
				<div class="xian center"></div>
			</div>
			<div class="center-block" style="margin-top: 80px;">
				<form class="form-horizontal" action="userLogin" method="post">
					<input type="hidden" name="redirect" value="${url}">
					<div class="form-group">
						<label class="col-sm-2 control-label">用户名：</label>
						<div class="col-sm-8" style="width: 40%">
							<input type="text" id="username" name="username" class="form-control col-sm-10"
								placeholder="请输入用户名……" />
						</div>
						<div class="col-sm-2">
						<p class="text-danger"><span class="help-block " id="usernameMsg"></span></p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">密码：</label>
						<div class="col-sm-8" style="width: 40%">
							<input type="password" name="password" 
								class="form-control col-sm-10" placeholder="请输入密码……" />
						</div>
						<div class="col-sm-2">
						<p class="text-danger"><span id="helpBlock" class="help-block ">请不输入6位以上字符</span></p>
						</div>
					</div>
					<hr>
					<div class="form-group">
						<div class="col-sm-7 col-sm-push-2">
							<input id="registerBtn" type="submit" value="登录" class="btn btn-primary  btn-lg" style="width: 200px;"/> &nbsp; &nbsp;
							<input type="reset" value="重置" class="btn btn-default  btn-lg" style="width: 200px;"  />
						</div>
					</div>
					<div>${loginError}</div>
				</form>
			</div>
		</div>
	</div>	
</body>
</html>