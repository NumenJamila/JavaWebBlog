/**
 * Created by sophia on 21/07/2017.
 */
//设置a为doPost方法
function doPost(to, p) {
    var myForm = document.createElement("form");
    myForm.method = "post";
    myForm.action = to;
    for (var i in p) {
        var myInput = document.createElement("input");
        myInput.setAttribute("name", i);
        myInput.setAttribute("value", p[i]);
        myForm.appendChild(myInput);
    }
    document.body.appendChild(myForm);
    myForm.submit();
    document.body.removeChild(myForm);
}

function doLoadComments(id) {
    var xmlHttp = new XMLHttpRequest(); //创建ajax实例对象
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            document.getElementById("commentsContent").innerHTML=xmlHttp.responseText;
        }
        console.log(xmlHttp.responseText);
    };
    // var commentTepl = '    <div class="comment" id="">+
    //
    //
    //     '</div>';
    xmlHttp.open('GET', "CommentController?handle=loadComments&id="+id);
    xmlHttp.send();
}

