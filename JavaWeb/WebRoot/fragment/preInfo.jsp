<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="panel panel-default">
    <div class="panel-heading panel-heading-style">
        <h3 class="panel-title">个人信息区</h3>
    </div>
</div>
<div class="panel-body">
    <%--个人信息区域--%>
    <ul class="list-group">
        <li class="list-group-item"><span class="glyphicon glyphicon-user"></span>
            &nbsp;&nbsp;<%=request.getAttribute("id")%>
        </li>
        <li class="list-group-item"><span class="glyphicon glyphicon-envelope"></span>
            &nbsp;&nbsp;<%=request.getAttribute("email")%>
        </li>
        <li class="list-group-item"><span class="glyphicon glyphicon-pencil"></span>
            &nbsp;&nbsp;<%=request.getAttribute("nickname")%>
        </li>
        <li class="list-group-item"><span>SEX:</span>
            &nbsp;&nbsp;<%=request.getAttribute("sex")%>
        </li>
        <li class="list-group-item"><span>AGE:</span>
            &nbsp;&nbsp;<%=request.getAttribute("age")%>
        </li>
    </ul>
</div>
