<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
	<title>锋迷网-在线支付</title>	
	<script type="text/javascript">
		function tz() {
			location.href="getOrderList";
		}
	</script>
</head>
<%@ include file="header.jsp" %>
<body>
	<div class="container">
		<div class="row">
			<div class="">
				<div class="panel panel-default"  style="margin: 0 auto;width: 95%;">
				  <div class="panel-heading">
				    <h3 class="panel-title"><span class="glyphicon glyphicon-yen"></span>&nbsp;&nbsp;在线支付
				    	<span class="pull-right"><a href="${pageContext.request.contextPath }/getOrderList">返回订单列表</a>
				    	</span>
				    </h3>
				  </div>
				  <div class="panel-body">	 
				  	<form  action="${pageContext.request.contextPath}/payServlet" method="post">
					<table class="table table-bordered table-striped table-hover">
						<tr>
							<td colspan="1">订单号:</td>
							<td colspan="3"><input type="text" class="form-control" name="orderid" value="${oid}" readonly="readonly"></td>
						</tr>
						<tr>
							<td colspan="1">支付金额:</td>
							<td colspan="3">
								<div class="input-group" style="width: 200px;">
							      <input type="text" class="form-control"  name="money" value="1">
							      <div class="input-group-addon"><span class="glyphicon glyphicon-yen"></span></div>
						      	</div>
							</td>
						</tr>
						<tr><td colspan="4" class="alert-danger"><strong>请您选择在线支付银行</strong></td></tr>
						<tr>
						  <td><INPUT TYPE="radio" NAME="pd_FrpId" value="CMBCHINA-NET">招商银行</td>
						  <td><INPUT TYPE="radio" NAME="pd_FrpId" value="ICBC-NET">工商银行</td>
						  <td><INPUT TYPE="radio" NAME="pd_FrpId" value="ABC-NET">农业银行</td>
						  <td><INPUT TYPE="radio" NAME="pd_FrpId" value="CCB-NET">建设银行 </td>
						</tr>
						<tr>
						  <td><INPUT TYPE="radio" NAME="pd_FrpId" value="CMBC-NET">中国民生银行总行</td>
						  <td><INPUT TYPE="radio" NAME="pd_FrpId" value="CEB-NET" >光大银行 </td>
						  <td><INPUT TYPE="radio" NAME="pd_FrpId" value="BOCO-NET">交通银行</td>
						  <td><INPUT TYPE="radio" NAME="pd_FrpId" value="SDB-NET">深圳发展银行</td>
						</tr>
						<tr>
						  <td><INPUT TYPE="radio" NAME="pd_FrpId" value="BCCB-NET">北京银行</td>
						  <td><INPUT TYPE="radio" NAME="pd_FrpId" value="CIB-NET">兴业银行 </td>
						  <td><INPUT TYPE="radio" NAME="pd_FrpId" value="SPDB-NET">上海浦东发展银行 </td>
						  <td><INPUT TYPE="radio" NAME="pd_FrpId" value="ECITIC-NET">中信银行</td>
						</tr>
						<tr>
						<td><INPUT TYPE="radio" NAME="pd_FrpId" value="WX">微信</td>
						  <td><INPUT TYPE="radio" NAME="pd_FrpId" value="ZFB">支付宝 </td>
						</tr>
					</table>
					<div class="pull-right" style="margin-right: 30px;">
						<input type="button" onclick="tz()" value="返回我的订单" class="btn btn-warning btn-lg">
					</div>
					</form>
				 </div>
			   </div>
			</div>			
		</div>	
	</div>
	<!-- 底部 -->
   <%@ include file="footer.jsp"%>	
</body>
</html>