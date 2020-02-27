<%--
  Created by IntelliJ IDEA.
  User: dj
  Date: 2020/1/16
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/res/js/jquery-1.12.4.min.js"></script>
<html>
<head>
    <title>登陆</title>
</head>
<body>

<form id="fm">
    用户名/手机号/邮箱<input type="text" name="userName"><br/>
    密码<input type="text" name="pwd"><br/>
    <input type="button" value="登陆" onclick="login()">
</form>

</body>
<script>
    function login() {
        $.post("<%=request.getContextPath()%>/user/login",
            $("#fm").serialize(),
            function (data) {
                if (data.code != 200) {
                    alert(data.msg)
                    return;
                }
                alert(data.msg)
                window.location.href = "<%=request.getContextPath()%>/index/toIndex";

            });
    }
</script>
</html>
