<%@ page import="java.util.ArrayList" %>
<%@ page import="source.entities.Article" %>
<%@ page import="source.service.ArticleService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <div id="my-gallery-container">
        <%
            ArrayList<Article> allArticleList = ArticleService.getArticleList(null);
            int count;
            String someContent;

            for (Article article : allArticleList) {
                count = (int) Math.round(article.getArticle_content().length() * 0.1);
                someContent = article.getArticle_content();
        %>
        <div class="item">
            <a href="javascript:doPost('${pageContext.request.contextPath}/VisitController',{'handle':'visitOtherBlog','id':'<%=article.getId()%>','article_id':'<%=article.getArticle_id()%>'})"
               style="text-decoration: none;color: black;">
                <p><span>标题:</span><%=article.getTitle()%></p>
                <p><%=someContent.substring(0, count)%>...</p>
                <p><span>来自:</span><%=article.getId()%></p>
                <p><span>发布时间: </span><%=article.getLast_date()%></p>
            </a>
        </div>
        <%
            }
        %>
    </div>
</div>

