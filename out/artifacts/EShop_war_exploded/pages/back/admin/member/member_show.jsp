<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 沙天慧
  Date: 2020/1/7
  Time: 16:40
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path=request.getContextPath();
    String basePath=request.getScheme()+"://"+request.getServerName()+
            ":"+request.getServerPort()+path+"/";
    String updateStatusUrl=basePath+"pages/back/admin/member/MemberServletBack/updateStatus";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>用户显示</title>
    <script type="text/javascript" src="<%=basePath%>js/common.js"></script>
</head>
<body>
    <div id="mainDiv">
        <c:if test="${member!=null}">
            <form action="<%=updateStatusUrl%>" method="post">
            <table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2" >
                <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                    <td colspan="3" align="center">用户的完整信息</td>
                </tr>
                <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                    <td align="center"><strong>用户名:</strong></td>
                    <td>${member.mid}</td>
                    <td rowspan="7"><img src="<%=basePath%>upload/member/${member.photo}" style="width: 150px;height: 250px"></td>
                </tr>
                <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                    <td align="center"><strong>姓名:</strong></td>
                    <td>${member.name}</td>
                </tr>
                <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                    <td align="center"><strong>电话:</strong></td>
                    <td>${member.phone}</td>
                </tr>
                <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                    <td align="center"><strong>地址:</strong></td>
                    <td>${member.address}</td>
                </tr>
                <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                    <td><strong>注册日期:</strong></td>
                    <td>${member.regdate}</td>
                </tr>
                <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                    <td><strong>用户状态:</strong></td>
                    <td>
                        <select id="type" name="type">
                            <option value="lock"${member.status==0 ? "selected":""}>锁定</option>
                            <option value="active"${member.status==1 ? "selected":""}>激活</option>
                        </select>
                    </td>
                </tr>
                <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                    <td colspan="3">
                        <input type="hidden" name="ids" id="ids" value="${member.mid}">
                        <input type="submit" value="修改用户状态">
                    </td>
                </tr>
            </table>
            </form>
        </c:if>
    </div>
</body>
</html>
