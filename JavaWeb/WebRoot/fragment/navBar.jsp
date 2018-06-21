<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-default navbar-static-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="homePage.jsp">JavaWeb博客</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul id="myTab" class="nav navbar-nav">
                <li><a id="a_home" href="${pageContext.request.contextPath}/PageController?handle=jumpHome&id=<%=request.getAttribute("id")%>">首页</a></li>
                <%if (session.getAttribute("owner") != null) {%>
                <li><a id="a_myBlog" href="${pageContext.request.contextPath}/PageController?handle=jumpMyBlog&id=<%=session.getAttribute("owner")%>">我的博客</a></li>
                <%}%>
            </ul>
            <form class="navbar-form navbar-left" method="post" action="${pageContext.request.contextPath}/PageController?handle=search&id=<%=request.getAttribute("id")%>">
                <div class="form-group">
                    <input name="keyword" type="text" class="form-control" placeholder="输入关键词">
                </div>
                <input type="submit" class="btn btn-default"></button>
            </form>
            <%--<form id="exit" action="${pageContext.request.contextPath}/UserController" method="post"></form>--%>
            <ul class="nav navbar-nav navbar-right">
            	<%if ((session.getAttribute("owner") == null)){%>
                <li><a href="javascript:doPost('${pageContext.request.contextPath}/UserController',{'opt':'logout'})">登录</a></li>
                <li><a id="signUp" href="javascript:doPost('${pageContext.request.contextPath}/UserController',{'opt':'logout'})">注册</a></li>
                <%}%>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"
                       role="button" aria-haspopup="true" aria-expanded="false">
                        	更多 <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Community</a></li>
                        <li><a href="#">Service</a></li>
                        <li><a href="#">Club</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="javascript:doPost('${pageContext.request.contextPath}/UserController',{'opt':'logout'})">退出</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
