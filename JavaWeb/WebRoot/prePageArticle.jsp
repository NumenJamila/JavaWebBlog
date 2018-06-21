<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>博客</title>
    <link rel="stylesheet" href="resources/css/reset.css" type="text/css">
    <link rel="stylesheet" href="resources/css/bootstrap-3.3.7-dist/css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="resources/css/mfb.css" type="text/css">
    <link rel="stylesheet" href="resources/css/css.css" type="text/css">
</head>
<body>
<header>
    <%@include file="fragment/navBar.jsp" %>
</header>

<div class="contain">
    <aside>
        <%@include file="fragment/preInfo.jsp" %>
        <%--留言板区域--%>
        <%@include file="fragment/messageBoard.jsp" %>

    </aside>
    <section>
        <article>
            <%--博文区域--%>
            <%@include file="fragment/blogShow.jsp" %>
        </article>
    </section>
</div>
<div style="display: none">
    <div class="comment" id="">

    </div>
</div>
<%@include file="fragment/newContentBtn.jsp" %>
<script src="resources/js/jquery-3.1.1.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/mfb.js"></script>
<script src="resources/js/main.js"></script>
<script>
    $(function () {
        //标签active
        $("#a_home").click(function () {
            $("#a_myBlog").parent().removeClass("li_active");
            $("#a_home").parent().addClass("li_active");
        });

        //返回博文列表按钮
        $('#return').click(function () {
            $('#blogShow').css("display", "block");
            $(this).css("display", "none");
            $('#edit').css("display", "none");
            $('#blogShowContent').css("display", "none");
            $('#new').css("display", "block");
        });

        //点击博文列表展示博文
        $("#blogShow").find("a").click(function (e) {
            this.parentNode.parentNode.style.display = 'none';
            $('#return').css("display", "inline");
            $('#edit').css("display", "inline");
            $('#blogShowContent').css("display", "block");
            $('#new').css("display", "none");
            e.preventDefault();
            $(this).tab("show");
        });

        //标签页
        $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
            // 获取已激活的标签页的名称
            var activeTab = $(e.target).text();
            // 获取前一个激活的标签页的名称
            var previousTab = $(e.relatedTarget).text();
            $(".active-tab span").html(activeTab);
            $(".previous-tab span").html(previousTab);
        });

        //在线编辑器
        $("#editControls").find("a").click(function () {
            switch ($(this).data('role')) {
                case 'h1':
                case 'h2':
                case 'p':
                    document.execCommand('formatBlock', false, '<' + $(this).data('role') + '>');
                    break;
                default:
                    document.execCommand($(this).data('role'), false, null);
                    break;
            }
        });
    });
</script>
</body>
</html>
