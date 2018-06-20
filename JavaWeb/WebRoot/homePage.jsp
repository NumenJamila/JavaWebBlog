<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>BLOG</title>
    <link rel="stylesheet" href="resources/css/reset.css" type="text/css">
    <link rel="stylesheet" href="resources/css/bootstrap-3.3.7-dist/css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="resources/css/css.css" type="text/css">
</head>
<body>
<header>
    <%@include file="fragment/navBar.jsp"%>
</header>
<div class="contain">
            <%--瀑布流展示区域--%>
            <%@include file="fragment/waterFallPage.jsp" %>
</div>
<footer>
</footer>
<script src="resources/js/jquery-3.1.1.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/mp.mansory.js"></script>
<script src="resources/js/main.js"></script>
<script>
    $(function () {
        //标签active
        $("#a_myBlog").click(function () {
            $("#a_home").parent().removeClass("li_active");
            $(this).parent().addClass("li_active");
        });

        //瀑布流布局
        $("#my-gallery-container").mpmansory(
            {
                childrenClass: 'item',    // 网格元素的class。
                columnClasses: 'padding', //添加到网格上的额外的class。
                breakpoints: {             //设置浏览器在相应断点时显示的列数。
                    lg: 3,                //视口尺寸大于1200像素。
                    md: 4,                //视口尺寸大于992像素，小于1200像素。
                    sm: 6,                //视口尺寸大于720像素，小于992像素。
                    xs: 12                //视口尺寸小于720像素。
                },
                distributeBy: {
                    order: false,         //如果设置为true，网格项按默认的顺序排列。
                    height: true,        //如果设置为true，没列的高度使用分配项的最小高度。
                    attr: 'data-order',   //使用data-order属性来排序。
                    attrOrder: 'asc'      //升序或降序：'asc'/'desc'。
                },
                onload: function (items) {

                }
            }
        );
    });
</script>
</body>
</html>