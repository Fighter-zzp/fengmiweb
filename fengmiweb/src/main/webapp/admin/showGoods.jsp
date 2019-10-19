<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath }/assters/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath }/assters/js/jquery-3.4.1.min.js"></script>
    <script src="${pageContext.request.contextPath }/assters/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath }/assters/js/DatePicker.js"></script>
    <script type="text/javascript">
        function deleteGoods(id) {
            location.href = "${pageContext.request.contextPath}/goodsDeleteById?id=" + id;
        }
    </script>
    <title>锋迷网-商品列表</title>
</head>
<body>
<div class="row" style="width:98%;margin-left: 1%;">
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                商品列表
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
                        <div class="form-group form-inline">
                            <span>商品名称</span>
                            <input type="text" name="name" class="form-control">
                        </div>
                    </div>
                    <div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
                        <div class="form-group form-inline">
                            <span>上架时间</span>
                            <input type="text" readonly="readonly" name="pubdate" class="form-control"
                                   onclick="setday(this)">
                        </div>
                    </div>
                    <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
                        <button type="button" class="btn btn-primary" id="search"><span
                                class="glyphicon glyphicon-search"></span></button>
                    </div>
                </div>
                <div style="height: 400px;overflow: scroll;">
                    <table id="tb_list" class="table table-striped table-hover table-bordered">
                        <tr>
                            <td>序号</td>
                            <td>商品名称</td>
                            <td>价格</td>
                            <td>图片</td>
                            <td>上架时间</td>
                            <td>类型</td>
                            <td>操作</td>
                        </tr>
                        <c:forEach items="${goodsList}" var="goods" varStatus="i">
                            <tr>
                                <td>${i.count}</td>
                                <td>${goods.name}</td>
                                <td>${goods.price}</td>
                                <td><img src="${pageContext.request.contextPath}/assters/images/${goods.picture}"
                                         width="40px" height="40px"/></td>
                                <td>${goods.pubdate}</td>
                                <td>${goods.typeName}</td>
                                <td>
                                    <button onclick="deleteGoods('${goods.id}')">删除</button>
                                    <a tabindex="0" id="example${goods.id}" class="btn btn-primary btn-xs"
                                       role="button" data-toggle="popover"
                                       data-trigger="focus"
                                       data-placement="left"
                                       data-content="${goods.intro}">描述</a>
                                    <script type="text/javascript">
                                        $(function () {
                                            $("#example${goods.id}").popover();
                                        })
                                    </script>
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
    $(function () {
        //给查询按钮 添加 点击事件
        $("#search").click(function () {
            var pubdate = $("input[name='pubdate']").val();
            var name = $("input[name='name']").val();
            location.href = "${pageContext.request.contextPath}/selectByNameAndPub?pubdate=" + pubdate + "&name=" + name;
        })

    })
</script>
</body>
</html>