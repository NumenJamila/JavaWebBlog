<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul id="menu" class="mfb-component--br mfb-zoomin" data-mfb-toggle="hover">
    <li class="mfb-component__wrap">
        <a href="#" class="mfb-component__button--main">
            <span class="glyphicon glyphicon-plus letCenter"></span>
        </a>
        <ul class="mfb-component__list">
            <%if ((session.getAttribute("owner")).equals(request.getAttribute("id"))) {%>
            <li>
                <a href="#" data-mfb-label="New Blog" class="mfb-component__button--child"
                   data-toggle="modal" data-target="#newBlogModal">
                    <span class="glyphicon glyphicon-book letCenter"></span>
                </a>
            </li>
            <%}%>
            <li>
                <a href="#" data-mfb-label="New Comment" class="mfb-component__button--child"
                   data-toggle="modal" data-target="#newCommentModal">
                    <span class="glyphicon glyphicon-comment letCenter"></span>
                </a>
            </li>
        </ul>
    </li>
</ul>
<!-- 新建博文模态框（Modal） -->
<div class="modal fade" id="newBlogModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">写一个博客</h4>
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
                                    <form id="newBlog"
                                          action="${pageContext.request.contextPath}/ArticleController?id=<%=request.getAttribute("id")%>"
                                          method="post"></form>
                                    <input form="newBlog" type="hidden" name="handle" value="newBlogOption">
                                    <input form="newBlog" type="text" class="titleEditor" name="blogTitle">
                                    <textarea form="newBlog" class="contentEditor" name="blogContent"></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <input form="newBlog" type="submit" class="btn btn-primary" value="发布">
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 新建评论模态框（Modal） -->
<div class="modal fade" id="newCommentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">发表评论</h4>
            </div>
            <div class="modal-body">
                <form id="newComment" action="${pageContext.request.contextPath}/CommentController"
                      method="post"></form>
                <span>用户名：<%=session.getAttribute("owner")%><br></span>
                <div>评论内容：</div>
                <input form="newComment" type="hidden" name="handle" value="newComment">
                <input form="newComment" type="hidden" name="owner" value="<%=session.getAttribute("owner")%>">
                <input form="newComment" type="hidden" name="id" value="<%=request.getAttribute("id")%>">
                <input form="newComment" type="hidden" name="article_id"
                       value="<%=session.getAttribute("article_id")%>">
                <textarea form="newComment" name="commentContent" class="contentEditor"
                          style="min-height:50px;"></textarea><br>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <input form="newComment" type="submit" class="btn btn-primary" value="发表">
            </div>
        </div>
    </div>
</div>