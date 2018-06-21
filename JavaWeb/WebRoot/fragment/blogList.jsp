<%@ page import="java.util.ArrayList" %>
<%@ page import="source.entities.Article" %>
<%@ page import="source.service.ArticleService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<ul  class="nav nav-stacked">
    <%
    	request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String id = (String) request.getAttribute("id");//利用ID查询文章列表
        request.setAttribute("id",id);
        ArrayList<Article> arrayList = ArticleService.getArticleList(id);
        for (Article article : arrayList) {
    %>
    <li><a href="javascript:doPost('${pageContext.request.contextPath}/ArticleController',{'handle':'blogShow','id':'<%=article.getId()%>','article_id':'<%=article.getArticle_id()%>'})"
               style="text-decoration: none;color: black;">
        <%=article.getTitle()%>
        <%=article.getFirst_date()%>
    </a></li>
    <%
        }
    %>
</ul>

