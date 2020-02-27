<%--
  Created by IntelliJ IDEA.
  User: dj
  Date: 2020/1/15
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/res/js/jquery-1.12.4.min.js"></script>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form id="fm">
    <input type="text" name="id" value="${user.id}"><br/>
    用户名<input type="text" name="userName" value="${user.userName}"><br/>
    密码<input type="text" name="pwd" value="${user.pwd}"><br/>
    邮箱<input type="text" name="email" value="${user.email}"><br/>
    手机号<input type="text" name="phone" value="${user.phone}"><br/>
    <input type="button" value="修改" onclick="update()">
</form>

</body>
<script>

    function update() {
        $.post("<%=request.getContextPath()%>/user/update",

            $("#fm").serialize(),
            function (data) {
                if (data.code != 200) {
                    alert(data.msg)
                    return;
                }
                alert(data.msg)
                window.location.href = "<%=request.getContextPath()%>/user/toShow";
            });
    }

</script>
</html>
