<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>锋迷网-订单预览</title>
<link href="${pageContext.request.contextPath}/assters/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/assters/js/jquery-3.4.1.min.js"></script>
</head>
<body style="background-color:#f5f5f5">
<%@ include file="header.jsp"%>
<div class="container" style="background-color: white;">
	<div class="row" style="margin-left: 40px">
		<h3>订单预览<small>温馨提示：请添加你要邮递到的地址</small></h3>
	</div>
	<form action="addOrder" method="post">
	<div class="row" style="margin-top: 40px;">
	<input type="hidden" name="t" value="${type}">
		<div class="col-md-10 col-md-offset-1">
			<table class="table table-bordered table-striped table-hover">
 				<tr>
 					<th>序号</th>
 					<th>商品名称</th>
 					<th>价格</th>
 					<th>数量</th>
 					<th>小计</th>
 				</tr>
 				<c:set value="0" var="sum"></c:set>
 				<c:forEach items="${cartList}" var="c" varStatus="i">
	 				<tr>
	 					<th>${i.count}</th>
	 					<th>${c.name}</th>
	 					<th>${c.price}</th>
	 					<th>${c.num}</th>
	 					<th>${c.money}</th>
	 				</tr>
	 				<c:set var="sum" value="${sum+c.money}"></c:set>
 				</c:forEach>
 				<tr>
 				 <td colspan="5">
 				 	<h5>收货地址</h5>
 				 	<select name="aid" style="width:60%" class="form-control">
 				 		<c:forEach items="${addList}" var="a" varStatus="ai">
 				 			<option value="${a.id}">${a.name}&nbsp;&nbsp;${a.phone}&nbsp;&nbsp;${a.detail}</option>
 				 		</c:forEach>
 				 	</select>
 				 	<c:if test="${empty addList}">
 				 		<a href="self_info.jsp">添加收货地址</a>
 				 	</c:if>
 				 </td>
 				</tr>
			</table>
		</div>
		
	</div>
	<hr>
	<div class="row">
		<div style="margin-left: 40px;">	  
	            <h4>商品金额总计：<span id="total" class="text-danger"><b>￥&nbsp;&nbsp;${sum}</b></span></h4>
		</div>
	</div>
	<div class="row pull-right" style="margin-right: 40px;">
		 <div style="margin-bottom: 20px;">
	            <button  id="btn_add" class="btn  btn-danger btn-lg" type="submit">提交订单</button>
	     </div>
	</div>
	</form>
</div>
<!-- 底部 -->
<%@ include file="footer.jsp"%>
</body>
</html>