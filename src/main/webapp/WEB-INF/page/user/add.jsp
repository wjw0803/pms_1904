<%--
  Created by IntelliJ IDEA.
  User: dj
  Date: 2020/1/15
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/res/js/jquery-1.12.4.min.js"></script>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form id="fm">
    用户名<input type="text" name="userName"><br/>
    密码<input type="text" name="pwd"><br/>
    邮箱<input type="text" name="email"><br/>
    手机号<input type="text" name="phone"><br/>
    年龄<input type="text" name="age"><br/>
    <input type="hidden" name="isDel" value="1"><br/>
    <input type="button" value="新增" onclick="add()">
</form>

</body>
<script>

    function add() {
        $.post("<%=request.getContextPath()%>/user/add",
            $("#fm").serialize(),
            function (data) {
                if (data.code != 200) {
                    alert(data.msg)
                    return;
                }
                alert(data.msg)
                window.location.href = "<%=request.getContextPath()%>/mp/toShow";
            });
    }

</script>
</html>
