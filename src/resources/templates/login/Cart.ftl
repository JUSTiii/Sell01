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
        .row {
            margin-top: 20px;;
        }

        .center {
            text-align: center;
        }

        .pagination {
            background: #cccccc;
        }
    </style>
    <style>
        .row {
            margin-left: 20px;
            margin-right: 20px;;
        }

        .line-center {
            line-height: 50px;
            text-align: center;
        }

        .row input {
            width: 50px;
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
                    <li>欢迎您：${user.username}<a href="/sell/seller/login/loginOut">注销</a></li>
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
    <div class="row thumbnail center">
        <div class="col-sm-12">
            <h1 class="text-center" style="margin-bottom: 30px">购物车</h1>
        </div>
        <div class=" list-group">

            <div class="col-sm-12 thumbnail">
                <div class="col-sm-4 line-center">图书</div>
                <div class="col-sm-1 line-center">单价</div>
                <div class="col-sm-4 line-center">数量</div>
                <div class="col-sm-2 line-center">小计</div>
                <div class="col-sm-1 line-center">操作</div>
            </div>

            <div class="col-sm-12  list-group-item">
                <#list userCartList.content as userCartList>
                    <div class="col-sm-1 line-center" style="width: 50px;height: 50px;">

                        <img src="${userCartList.productIcon}" style="height: 100%;" alt=""/>
                    </div>
                    <div class="col-sm-3 line-center">${userCartList.productName}</div>
                    <div class="col-sm-1 line-center">${userCartList.productPrice}</div>
                    <div class="col-sm-4 line-center">
                        <button type="button" class="btn btn-default">
                            <span class="glyphicon glyphicon-minus" aria-hidden="true"></span>
                        </button>
                        <input type="text" class="small" value="${userCartList.productQuantity}"/>
                        <button type="button" class="btn btn-default">
                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                        </button>
                    </div>
                    <div class="col-sm-2 line-center">${userCartList.productQuantity*userCartList.productPrice}</div>
                    <div class="col-sm-1 line-center"><a class="btn btn-danger"
                                                         href="/sell/buyer/cart/delete?cartId=${(userCartList.cartId)!}">删除</a>
                    </div>
                </#list>
            </div>

            <nav class="center">
                <ul class="pagination  pagination-lg">
                    <li>
                        <a href="/sell/buyer/cart/list?page=1&userId=${(user.userId)!}" aria-label="Previous">
                            <span aria-hidden="true">首页</span>
                        </a>
                    </li>

                    <#if currentPage lte 1>
                        <li><a href="#">上一页</a></li>
                    <#else>
                        <li><a href="/sell/buyer/cart/list?page=${currentPage-1}&userId=${(user.userId)!}">上一页</a></li>
                    </#if>
                    <#list 1..userCartList.getTotalPages() as index>
                        <#if currentPage==index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else>
                            <li><a href="/sell/buyer/cart/list?page=${index}&userId=${(user.userId)!}">${index}</a></li>
                        </#if>

                    </#list>
                    <#if currentPage gte userCartList.getTotalPages()>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/sell/buyer/cart/list?page=${currentPage+1}&userId=${(user.userId)!}">下一页</a></li>
                    </#if>
                    <li>
                        <a href="/sell/buyer/cart/list?page=${userCartList.totalPages}&userId=${(user.userId)!}"
                           aria-label="Next">
                            <span aria-hidden="true">末页</span>
                        </a>
                    </li>
                </ul>
            </nav>


            <div class="col-sm-12 thumbnail">
                <div class=" col-sm-offset-4 col-sm-2 text-right">总数：</div>
                <div class="col-sm-2">${totalNums!}</div>
                <div class="col-sm-2 text-right">总价：</div>
                <div class="col-sm-2">${cartAmount!}</div>
            </div>
        </div>
        <div class="col-sm-offset-7 col-sm-5" style="padding: 30px;">
            <div class="col-sm-6 btn btn-success btn-block"><a href="/sell/buyer/product/list?categoryType=1">继续购物</a>
            </div>
            <form method="post" action="/sell/buyer/order/create">
                <input type="hidden" name="name" value="${(user.username)!}">
                <input type="hidden" name="phone" value="${(user.telephone)!}">
                <input type="hidden" name="address" value="${(user.address)!}">
                <input type="hidden" name="userId" value="${(user.userId)!}">
                <input type="hidden" name="items" value=${items}>
                <button type="submit" class="col-sm-6  btn btn-success btn-block">提交订单</button>
            </form>
        </div>
    </div>
</div>


<!--footer-->
</body>
</html>