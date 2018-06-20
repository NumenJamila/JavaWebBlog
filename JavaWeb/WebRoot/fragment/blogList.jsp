<%@ page import="java.util.ArrayList" %>
<%@ page import="source.entities.Article" %>
<%@ page import="source.service.ArticleService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<ul id="blogShow" class="nav nav-stacked">
    <%
        String id = (String) request.getAttribute("id");//利用ID查询文章列表
        request.setAttribute("id",id);
        ArrayList<Article> arrayList = ArticleService.getArticleList(id);
        for (Article article : arrayList) {
    %>
    <li><a id="link"
           href="javascript:doPost('${pageContext.request.contextPath}/ArticleController',{'handle':'blogShow','article_id':'<%=article.getArticle_id()%>','id':'<%=request.getAttribute("id")%>'})">
        <%=article.getTitle()%>
        <%=article.getFirst_date()%>
    </a></li>
    <%
        }
    %>
</ul>

