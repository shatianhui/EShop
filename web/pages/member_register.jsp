<%--
  Created by IntelliJ IDEA.
  User: 沙天慧
  Date: 2020/1/8
  Time: 15:32
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
    <script type="text/javascript" src="<%=basePath%>js/member_register.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/common.js"></script>
</head>
<body>
<jsp:include page="/pages/header.jsp"></jsp:include>
<div id="mainDiv">
    <form action="pages/memberServletFront/register" method="post" onsubmit="return validateRegister()">
        <table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2">
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td colspan="3" align="center">用户注册</td>
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
                <td colspan="3">
                    <input type="submit" value="注册">
                    <input type="reset" value="注销">
                </td>
            </tr>
        </table>
    </form>
</div>
<jsp:include page="/pages/footer.jsp"></jsp:include>
</body>
</html>
