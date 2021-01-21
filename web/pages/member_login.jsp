<%--
  Created by IntelliJ IDEA.
  User: 沙天慧
  Date: 2020/1/9
  Time: 16:52
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path=request.getContextPath();
    String basePath=request.getScheme()+"://"+request.getServerName()+
            ":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>微商城</title>
    <script type="text/javascript" src="<%=basePath%>js/member.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/common.js"></script>
</head>
<body>
<jsp:include page="/pages/header.jsp"></jsp:include>
<div id="mainDiv">
    <form action="pages/memberServletFront/login" method="post" onsubmit="return validateLogin()">
        <table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2">
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td colspan="3" align="center">用户登录</td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>用户ID:</td>
                <td><input type="text" name="mid" id="mid" class="init" onblur="validateMid()"></td>
                <td width="40%"><span id="midMsg"></span></td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>密码:</td>
                <td><input type="password" name="password" id="password" class="init" onblur="validatePas()"></td>
                <td><span id="passwordMsg"></span></td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>验证码:</td>
                <td>
                    <input type="code" name="code" id="code" maxlength="4" size="4" class="init" onblur="validateCode()">
                    <img src="<%=basePath%>pages/image.jsp" id="codeImg">
                </td>
                <td><span id="codeMsg"></span></td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td colspan="3">
                    <input type="checkbox" name="reme" id="reme" value="777660000">记住密码
                </td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td colspan="3">
                    <input type="submit" value="登录">
                    <input type="reset" value="注销">
                </td>
            </tr>
        </table>
    </form>
</div>
<jsp:include page="/pages/footer.jsp"></jsp:include>
</body>
</html>