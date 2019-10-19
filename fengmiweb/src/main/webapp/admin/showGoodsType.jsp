 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${pageContext.request.contextPath }/assters/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath }/assters/js/jquery-3.4.1.min.js"></script>
<script src="${pageContext.request.contextPath }/assters/js/bootstrap.min.js"></script>
<title>锋迷网-商品类型</title>
</head>
<body>
<div class="row" style="width:98%;margin-left: 1%;">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				商品类型
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<div class="form-group form-inline">
							<span>商品等级</span>
							<input type="text" name="flag" class="form-control">
						</div>
					</div>
					<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<div class="form-group form-inline">
							<span>商品名称</span>
							<input type="text" name="name" class="form-control">
						</div>
					</div>
					<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
						<button type="button" class="btn btn-primary" id="search"><span class="glyphicon glyphicon-search">搜索</span></button>
					</div>
				</div>
				<div style="height: 400px;overflow: scroll;">
				<table id="tb_list" class="table table-striped table-hover table-bordered">
					<tr>
						<td>序号</td>
						<td>类型</td>
						<td>等级</td>
						<td>所属类型</td>
						<td>操作</td>
					</tr>
					<c:forEach items="${gtlist}" var="gtype" varStatus="i">
					<tr>
						<td>${i.count}</td>
						<td>${gtype.name}</td>
						<td>${gtype.level}</td>
						<td>${gtype.parentName}</td>
						<td>
							<!-- <button>修改</button>&nbsp;&nbsp; --><button onclick="deleteGoodsType(${gtype.id})">删除</button>
						</td>
					</tr>
					</c:forEach>
				</table>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">

//条件查询
$(function(){
	//给查询按钮 添加 点击事件
	$("#search").click(function(){
		var flag = $("input[name='flag']").val();
		if (flag==="") flag = 1;
		var name = $("input[name='name']").val();
		location.href = "${pageContext.request.contextPath}/selectByNameFlag?flag="+flag+"&name="+name;
	})
});
//连接servlet 获取 数据
	function deleteGoodsType(count){
		$.ajax({
			url:"${pageContext.request.contextPath}/delGoodsType",
			method:"get",
			data:{"count":count},
			success:function(data){
				location.href = "${pageContext.request.contextPath}/getGoodsType";
			},
			error:function(XMLHttpRequest,textStatus,errorThrown){
				alert("失败"+XMLHttpRequest.status+":"+textStatus+":"+errorThrown);
			}
		});
	}
</script>
</body>
</html>