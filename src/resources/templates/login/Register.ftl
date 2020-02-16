<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">

    <link rel="stylesheet" href="../../bootstrap-3.3.4/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../../Flat-UI-master/dist/css/flat-ui.min.css"/>
    <script src="../../Flat-UI-master/dist/js/vendor/jquery.min.js"></script>
    <script src="../../bootstrap-3.3.4/dist/js/bootstrap.min.js"></script>
    <script src="../../Flat-UI-master/dist/js/flat-ui.min.js"></script>
    <title></title>
    <style>
        .row{
            margin-left: 20px;
            margin-right: 20px;;
        }
    </style>
</head>
<body>
<!-- Static navbar -->
<div class="navbar navbar-default navbar-static-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">这什么东西</span>
            </button>
            <a class="navbar-brand" href="/sell/buyer/product/list?categoryType=1">图书商城</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/sell/buyer/product/list?categoryType=1">首页</a></li>
                <li><a href="Order.ftl">我的订单</a></li>
                <li><a href="UserInfo.ftl">个人中心</a></li>
                <li><a href="FriendLink.ftl">友情链接</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right hidden-sm">
                <#if Session["user"]?exists>
                    <li>欢迎您：${user.username}</li>
                <#else>
                    <li><a href="/sell/seller/login/start">登录</a></li>
                </#if>
                <li><a href="/sell/seller/login/registerInit">注册</a></li>
                <li>
                    <a href="/sell/buyer/cart/list?userId=${(user.userId)!}"><span class="glyphicon glyphicon-shopping-cart">购物车</span></a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</div>
<!--content-->
<div class="container">
    <div class="row thumbnail">
        <div class="col-sm-12">
             <h1 class="text-center" style="    margin-bottom: 30px">用户注册</h1>
        </div>
     <div style="color: red">
         ${msg!}
     </div>
        <div class="col-sm-6">
            <form class="form-horizontal caption" method="post" action="/sell/seller/login/register">
                <div class="form-group">
                    <label for="username" class="col-sm-3 control-label">用户名</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" name="username" id="username" placeholder="用户名">
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-3 control-label">密码</label>
                    <div class="col-sm-8">
                        <input type="password" class="form-control" name="password" id="password" placeholder="密码">
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-3 control-label">确认密码</label>
                    <div class="col-sm-8">
                        <input type="password" class="form-control" name="password2" id="password2" placeholder="确认密码">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inlineRadio1" class="col-sm-3 control-label">性别</label>
                    <div class="col-sm-8">
                        <label class="radio-inline">
                            <input type="radio" name="gender" id="inlineRadio1" value="男">男
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="gender" id="inlineRadio2" value="女">女
                        </label>
                    </div>

                </div>
                <div class="form-group">
                    <label for="telephone" class="col-sm-3 control-label">电话</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" name="telephone" id="telephone" placeholder="电话号码">
                    </div>
                </div>
                <div class="form-group">
                    <label for="address" class="col-sm-3 control-label">地址</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" name="address" id="address" placeholder="地址">
                    </div>
                </div>
                <div class="form-group">
                    <label for="email" class="col-sm-3 control-label">邮箱</label>
                    <div class="col-sm-8">
                        <input type="email" class="form-control" name="email" id="email" placeholder="邮箱">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-5">
                        <button type="submit" class="btn btn-success btn-block" onclick="return insertManager()">注册</button>
                    </div>
                </div>
            </form>
        </div>


    </div>
</div>

<script type="text/javascript">
    function insertManager() {
        var password = document.getElementById("password").value;
        var repassword = document.getElementById("repassword").value;
        if(password!=repassword){
            window.alert("您输入的新密码与确认密码确认不一致");
            signupForm.repassword.focus();
            return false;

        }
        return true;
    }
</script>

</body>
</html>