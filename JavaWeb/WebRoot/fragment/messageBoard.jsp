<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="panel panel-default">
    <div class="panel-heading panel-heading-style">
        <h3 class="panel-title">留言板</h3>
    </div>
</div>
<%-- 留言模态框（Modal）--%>
<div class="modal fade" id="messageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">Leave Message</h4>
            </div>
            <form id="doMessage" action="controller/doMessage.jsp" method="post">
                <div class="modal-body">
                    <input type="text" class="form-control" placeholder="<%=session.getAttribute("id")%>" disabled>
                    <textarea form="doMessage" class="contentEditor" name="messageContent"></textarea>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <input form="doMessage" type="submit" class="btn btn-primary" value="Save">
                </div>
            </form>
        </div>
    </div>
</div>