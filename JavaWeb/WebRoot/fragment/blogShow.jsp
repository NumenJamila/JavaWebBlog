<%@ page import="source.service.ArticleService" %>
<%@ page import="source.entities.Article" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="blogShow" action="${pageContext.request.contextPath}/ArticleController?id=<%=request.getAttribute("id")%>"
      method="post"></form>
<!--博文展示模块-->
<div id="blogShowContent" class="tab-content">
    <div role="article" class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">
                <button id="returnList" type="submit" class="clearButton"
                        form="blogShow" name="handle" value="returnBlogList" style="float: left;background-color: #F5F5F5">
                    <span class="glyphicon glyphicon-backward"></span>&nbsp;&nbsp;Return
                </button>
                <%=request.getAttribute("title")%>
                <%if ((session.getAttribute("owner")).equals(request.getAttribute("id"))) {%>
                <button id="editBlog" type="button" class="clearButton"
                        style="float: right;background-color: #F5F5F5" data-toggle="modal" data-target="#editBlogModal">
                    <span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;Edit
                </button>
                <%}%>
            </h3>
        </div>
        <div class="panel-body"><%=request.getAttribute("article_content")%>
        </div>
        <div class="panel-footer">
            <span style="margin-right: 50px;">创建时间：<%=request.getAttribute("first_date")%></span>
            <span>修改时间：<%=request.getAttribute("last_date")%></span>
            <%if ((session.getAttribute("owner")).equals(request.getAttribute("id"))) {%>
            <button id="deleteBlog" type="submit" class="clearButton"
                    form="blogShow" name="handle" value="deleteBlog" style="float: right;background-color: #F5F5F5;line-height: 100%">
                <span class="glyphicon glyphicon-remove"></span>&nbsp;&nbsp;Delete Blog
            </button>
            <%}%>
        </div>
    </div>
</div>
<button id="loadComments" type="button" onclick="doLoadComments('<%=request.getAttribute("id")%>')" class="btn btn-default no-radius">Load Comments</button>
<div id="commentsContent">
</div>
<!-- 编辑博文模态框（Modal） -->
<div class="modal fade" id="editBlogModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Online Editor</h4>
            </div>
            <div class="modal-body">
                <div class="modal-body">
                    <!--文本编辑框-->
                    <div class="content">
                        <div class="container-fluid">
                            <div id='pad-wrapper'>
                                <div id="editparent">
                                    <div id='editControls'
                                         style='text-align:center; padding:5px;'>
                                        <div class='btn-group'>
                                            <a class='btn' data-role='undo' href='#'><i
                                                    class='glyphicon glyphicon-menu-left'></i></a>
                                            <a class='btn' data-role='redo' href='#'><i
                                                    class='glyphicon glyphicon-menu-right'></i></a>
                                        </div>
                                        <div class='btn-group'>
                                            <a class='btn' data-role='bold' href='#'><b>Bold</b></a>
                                            <a class='btn' data-role='italic'
                                               href='#'><em>Italic</em></a>
                                            <a class='btn' data-role='underline'
                                               href='#'><u><b>U</b></u></a>
                                            <a class='btn' data-role='strikeThrough'
                                               href='#'><s>abc</s></a>
                                        </div>
                                        <div class='btn-group'>
                                            <a class='btn' data-role='justifyLeft' href='#'><i
                                                    class='glyphicon glyphicon-align-left'></i></a>
                                            <a class='btn' data-role='justifyCenter' href='#'><i
                                                    class='glyphicon glyphicon-align-center'></i></a>
                                            <a class='btn' data-role='justifyRight' href='#'><i
                                                    class='glyphicon glyphicon-align-right'></i></a>
                                            <a class='btn' data-role='justifyFull' href='#'><i
                                                    class='glyphicon glyphicon-align-justify'></i></a>
                                        </div>
                                        <div class='btn-group'>
                                            <a class='btn' data-role='indent' href='#'><i
                                                    class='glyphicon glyphicon-indent-right'></i></a>
                                            <a class='btn' data-role='outdent' href='#'><i
                                                    class='glyphicon glyphicon-indent-left'></i></a>
                                        </div>
                                        <div class='btn-group'>
                                            <a class='btn' data-role='insertUnorderedList' href='#'><i
                                                    class='glyphicon glyphicon-th-list'></i></a>
                                            <a class='btn' data-role='insertOrderedList' href='#'><i
                                                    class='glyphicon glyphicon-list'></i></a>
                                        </div>
                                        <div class='btn-group'>
                                            <a class='btn' data-role='h1' href='#'>h<sup>1</sup></a>
                                            <a class='btn' data-role='h2' href='#'>h<sup>2</sup></a>
                                            <a class='btn' data-role='p' href='#'>p</a>
                                        </div>
                                        <div class='btn-group'>
                                            <a class='btn' data-role='subscript' href='#'><i
                                                    class='glyphicon glyphicon-subscript'></i></a>
                                            <a class='btn' data-role='superscript' href='#'><i
                                                    class='glyphicon glyphicon-superscript'></i></a>
                                        </div>
                                    </div>
                                    <form id="updateBlog" action="${pageContext.request.contextPath}/ArticleController"
                                          method="post"></form>
                                    <input form="updateBlog" type="hidden" name="handle" value="editBlogOption">
                                    <input form="updateBlog" type="text" class="titleEditor" name="blogTitle"
                                           value="<%=request.getAttribute("title")%>">
                                    <textarea form="updateBlog" class="contentEditor"
                                              name="blogContent"><%=request.getAttribute("article_content")%></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <input form="updateBlog" type="submit" class="btn btn-primary" value="Save">
                </div>
            </div>
        </div>
    </div>
</div>