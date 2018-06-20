<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-default navbar-static-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="homePage.jsp">BLOG</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul id="myTab" class="nav navbar-nav">
                <li><a id="a_home" href="${pageContext.request.contextPath}/PageController?handle=jumpHome&id=<%=request.getAttribute("id")%>">HOME</a></li>
                <%if (session.getAttribute("owner") != null) {%>
                <li><a id="a_myBlog" href="${pageContext.request.contextPath}/PageController?handle=jumpMyBlog&id=<%=session.getAttribute("owner")%>">MyBlog</a></li>
                <%}%>
            </ul>
            <form class="navbar-form navbar-left">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
            <%--<form id="exit" action="${pageContext.request.contextPath}/UserController" method="post"></form>--%>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="javascript:doPost('${pageContext.request.contextPath}/UserController',{'opt':'logout'})">LogIn</a></li>
                <li><a id="signUp" href="javascript:doPost('${pageContext.request.contextPath}/UserController',{'opt':'logout'})">SignUp</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"
                       role="button" aria-haspopup="true" aria-expanded="false">
                        More <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Community</a></li>
                        <li><a href="#">Service</a></li>
                        <li><a href="#">Club</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="javascript:doPost('${pageContext.request.contextPath}/UserController',{'opt':'logout'})">Exit</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
