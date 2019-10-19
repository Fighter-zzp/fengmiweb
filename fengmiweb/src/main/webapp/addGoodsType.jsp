<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${pageContext.request.contextPath }/assters/css/bootstrap.min.css" />
<script src="${pageContext.request.contextPath }/assters/js/jquery-3.4.1.min.js"></script>
<script src="${pageContext.request.contextPath }/assters/js/bootstrap.min.js"></script>
<title>锋迷网-添加商品类型</title>
</head>
<body>
<div style="width:98%;margin-left: 1%;">
	<div class="panel panel-default">
		<div class="panel-heading">
			添加商品类型
		</div>
		<div class="panel-body">
			<form action="${pageContext.request.contextPath }/goodTypeAdd" method="post">
				<div class="row">
					<div class="form-group form-inline">
						<span>所属类型</span>
						<select name="parentName">
							<option value="1">--请选择--</option>
							<c:forEach items="${gtlist }" var="type">
								<c:if test="${type.level <=2}">
									<option value="${type.name }">${type.name }</option>
								</c:if>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="row">
					<div class="form-group form-inline">
						<span>种类名称</span>
						<input type="text" name="name" class="form-control">
					</div>
				</div>
				<div class="row">
					<div class="btn-group">
						<button type="reset" class="btn btn-default">清空</button>
						<button type="submit" class="btn btn-default">添加</button>
					</div>
				</div>
			</form>
			<p style="color:red">${msg}</p>
		</div>
	</div>
</div>
</body>
</html>