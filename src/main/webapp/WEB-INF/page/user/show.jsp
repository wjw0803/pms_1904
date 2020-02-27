<%--
  Created by IntelliJ IDEA.
  User: dj
  Date: 2020/1/15
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/res/js/jquery-1.12.4.min.js"></script>
<html>
<head>
    <title>Title</title>
</head>
<body>

<input type="button" value="新增" onclick="toAdd()">

<form id="fm">
    <input type="hidden" id="pageNo" name="pageNo" value="1">
    用户名<input type="text" name="userName"><br/>
    年龄开始<input type="text" name="startAge">~<input type="text" name="endAge"><br/>
    <input type="button" value="查询" onclick="search()">

    <table>
        <tr>
            <th>id</th>
            <th>姓名</th>
            <th>密码</th>
            <th>邮箱</th>
            <th>手机号</th>
            <th>年龄</th>
        </tr>
        <tbody id="tb">

        </tbody>
    </table>
    <div id="pageInfo"></div>
</form>

</body>
<script>


    $(function () {
        search()
    })

    function search() {

        $.post("<%=request.getContextPath()%>/user/show",

            $("#fm").serialize(),

            function (data) {

                var totalNum = data.data.pages;
                /*alert(data.data.pages+"总页数");*/

                var html = "";

                for (var i = 0; i < data.data.records.length; i++) {

                    var u = data.data.records[i];
                    html += "<tr>";
                    html += "<td>" + u.id + "</td>";
                    html += "<td>" + u.userName + "</td>";
                    html += "<td>" + u.pwd + "</td>";
                    html += "<td>" + u.email + "</td>";
                    html += "<td>" + u.phone + "</td>";
                    html += "<td>" + u.age + "</td>";
                    html += "<td><input type='button' value='修改' onclick='toUpd(" + u.id + ")'></td>";
                    html += "<td><input type = 'button'  value='删除' onclick='del(" + u.id + ")'></td>";
                    html += "</tr>";
                }

                $("#tb").html(html)
                var pageNo = $("#pageNo").val()
                var pageNo2 = parseInt(pageNo)
                var pageHtml = "<td><input type = 'button' value='上一页' onclick='pageLimit(" + (pageNo2 - 1) + "," + (totalNum) + ")'></td>";
                pageHtml += "<td><input type = 'button' value='下一页' onclick='pageLimit(" + (pageNo2 + 1) + "," + (totalNum) + ")'></td>";
                $("#pageInfo").html(pageHtml);
            });


    }

    //分页
    function pageLimit(pageNo2, totalNum) {
        if (pageNo2 < 1) {
            alert("首页");
            return;
        }
        if (pageNo2 > totalNum) {
            alert("尾页");
            return;
        }
        /*alert(pageNo2)*/
        $("#pageNo").val(pageNo2)
        search();
    }

    //去修改
    function toUpd(id) {
        alert(id)
        window.location.href = "<%=request.getContextPath()%>/user/toUpd?id=" + id;
    }

    //删除
    function del(id) {
        alert(id)
        $.post("<%=request.getContextPath()%>/user/del",
            {"id": id, "isDel": 0},
            function (data) {
                if (data.code != 200) {
                    alert(data.msg)
                    return;
                }
                alert(data.msg)
                window.location.href = "<%=request.getContextPath()%>/user/toShow";
            });
    }

    //去新增
    function toAdd() {
        window.location.href = "<%=request.getContextPath()%>/user/toAdd";
    }

</script>
</html>
