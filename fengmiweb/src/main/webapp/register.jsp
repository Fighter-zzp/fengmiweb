<%--
  Created by IntelliJ IDEA.
  User: 佐斯特勒
  Date: 2019/9/26
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/assters/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/assters/js/jquery-3.4.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assters/css/login.css">
    <script type="text/javascript">
        $(function(){
            //校验的是用户名是否存在
            $("#username").change(function(){
                //使用ajax 做username 的异步验证 checkUsername?username=xxxx
                $.get("checkUserName","name="+this.value,function(data){
                    if(data.code===1){
                        $("#usernameMsg").html("用户名已经存在").css("color","red");
                        $("#registerBtn").attr("disabled",true);
                    }else{
                        $("#usernameMsg").html("用户名可用").css("color","green");
                        $("#registerBtn").removeAttr("disabled");
                    }
                })
            });
            //校验邮箱是否重复
            $("#email").change(function(){
                //使用ajax 做username 的异步验证 checkUsername?username=xxxx
                $.get("checkEmail","email="+this.value,function(data){
                    if(data.code===1){
                        $("#emailMsg").html("邮箱已经存在").css("color","red");
                        $("#registerBtn").attr("disabled",true);
                    }else{
                        $("#emailMsg").html("邮箱可用").css("color","green");
                        $("#registerBtn").removeAttr("disabled");
                    }
                })
            });
        })
    </script>
    <title>锋迷网-注册</title>
</head>
<body>
<div class="regist">
    <div class="regist_center">
        <div class="regist_top">
            <div class="left fl"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;加入我们</div>
            <div class="right fr">
                <a href="index.jsp" target="_black">锋迷网</a>
            </div>
            <div class="clear"></div>
            <div class="xian center"></div>
        </div>
        <div class="center-block" style="margin-top: 80px;">
            <form class="form-horizontal" action="userRegister" method="post">
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
                        <p class="text-danger"><span id="helpBlock1" class="help-block">请输入6位以上字符</span></p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">确认密码：</label>
                    <div class="col-sm-8" style="width: 40%">
                        <input type="password" class="form-control col-sm-10"
                               placeholder="请确认密码……" />
                    </div>
                    <div class="col-sm-2">
                        <p class="text-danger"><span id="helpBlock2" class="help-block ">两次密码要输入一致哦</span></p>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">邮箱：</label>
                    <div class="col-sm-8" style="width: 40%">
                        <input type="text" name="email" id="email" class="form-control col-sm-10"
                               placeholder="请输入邮箱……" />
                    </div>
                    <div class="col-sm-2">
                        <p class="text-danger"><span id="emailMsg" class="help-block ">填写正确邮箱格式</span></p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">性别：</label>
                    <div class="col-sm-8" style="width: 40%">
                        <label class="radio-inline">
                            <input type="radio" name="gender" checked="checked" value="男">男
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="gender" value="女">女
                        </label>
                    </div>
                    <div class="col-sm-2">
                        <p class="text-danger"><span id="helpBlock3" class="help-block ">你是帅哥 还是美女</span></p>
                    </div>
                </div>
                <hr>
                <div class="form-group">
                    <div class="col-sm-7 col-sm-push-2">
                        <input id="registerBtn" type="submit" value="注册" class="btn btn-primary  btn-lg" style="width: 200px;" disabled/> &nbsp; &nbsp;
                        <input type="reset" value="重置" class="btn btn-default  btn-lg" style="width: 200px;"  />
                    </div>
                </div>
                <div>${registerError}</div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
