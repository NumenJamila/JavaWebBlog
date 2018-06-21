<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>欢迎登录博客</title>
    <link href="resources/css/reset.css" rel="stylesheet" type="text/css">
    <link href="resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="resources/css/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="resources/css/login.css" rel="stylesheet" type="text/css">
    <link href="resources/css/register.css" rel="stylesheet" type="text/css">
</head>
<body>
<nav id="nav">
    <ul>
        <li><a href="#home" class="main-link"></a></li>
    </ul>
</nav>
<section id="home">
    <h1>博客</h1>
    <div class="container">
        <div class="form row">
            <form id="login_form" class="form-horizontal col-sm-offset-3 col-md-offset-3" method="post"
                  action="${pageContext.request.contextPath}/UserController">
                <h3 class="form-title">登录你的账户</h3>
                <input type="hidden" name="opt" value="login">
                <div class="col-sm-9 col-md-9">
                    <div class="form-group">
                        <i class="fa fa-user"></i>
                        <input class="form-control required" type="text" placeholder="用户名" name="id" autofocus maxlength="20">
                    </div>
                    <div class="form-group">
                        <i class="fa fa-lock"></i>
                        <input class="form-control required" type="password" placeholder="密码" name="password" maxlength="8" autocomplete="off">
                    </div>
                    <div class="form-group">                        
                        <hr/>
                        <a href="javascript:" id="register_btn">创建一个账户</a>
                    </div>
                    <div class="form-group">
                        <input type="submit" class="btn btn-success pull-right"  value="登录">
                    </div>
                </div>
            </form>
        </div>

        <div class="form row">
            <form class="form-horizontal col-sm-offset-3 col-md-offset-3" id="register_form"
                  method="post" action="${pageContext.request.contextPath}/UserController">
                <h3 class="form-title">正在创建一个账户</h3>
                <input type="hidden" name="opt" value="register">
                <div class="col-sm-9 col-md-9">
                    <div class="form-group">
                        <i class="fa fa-user"></i>
                        <input class="form-control required" type="text" placeholder="用户名" name="id"
                               autofocus>
                    </div>
                    <div class="form-group">
                        <i class="fa fa-lock"></i>
                        <input class="form-control required" type="password" placeholder="密码"
                               id="register_password" name="password" autocomplete="off">
                    </div>
                    <div class="form-group">
                        <i class="fa fa-check"></i>
                        <input class="form-control required" type="password" placeholder="再次输入密码"
                               name="rpassword" autocomplete="off">
                    </div>
                    <div class="form-group">
                        <i class="fa fa-envelope"></i>
                        <input class="form-control required" type="text" placeholder="邮箱" name="email">
                    </div>
                    <div class="form-group">
                        <input type="submit" class="btn btn-success pull-right" value="注册">
                        <input type="submit" class="btn btn-info pull-left" id="back_btn" value="返回">
                    </div>
                </div>
            </form>
        </div>
    </div>
</section>
<div id="iealert"
     style="width:100%; background:#D35400; text-align:center; line-height:2.5; color:#fff; clear:both; position:fixed; top:0; left:0; z-index:9999; display:none;">
    您的浏览器版本较低，将影响本站点浏览效果，建议您升级浏览器到高级版本。
</div>
<script type="text/javascript" src="resources/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="resources/js/register.js"></script>
<script type="text/javascript">
    $(function() {
        if (!window.localStorage) {
            document.getElementById("iealert").style.display = "block";
        }

        $("#register_btn").click(function() {
            $("#register_form").css("display", "block");
            $("#login_form").css("display", "none");
        });
        $("#back_btn").click(function() {
            $("#register_form").css("display", "none");
            $("#login_form").css("display", "block");
        });
    });
</script>
</body>
</html>
