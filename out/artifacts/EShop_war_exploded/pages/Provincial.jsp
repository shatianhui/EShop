<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 沙天慧
  Date: 2020/1/26
  Time: 11:24
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path=request.getContextPath();
    String basePath=request.getScheme()+"://"+request.getServerName()+
            ":"+request.getServerPort()+path;

%>
<html>
<head>
    <title>地址级联菜单</title>
    <script type="text/javascript" src="<%=basePath%>/js/provincial.js"></script>
</head>
<body>
    <div>
        省份：<select id="pid" onchange="loadCity()">
        <option value="0">========请选择所在省份==========</option>
        <c:if test="${allProvincial!=null}" var="res">
            <c:forEach items="${allProvincial}" var="provincial">
                <option value="${provincial.pid}">${provincial.title}</option>
            </c:forEach>
        </c:if>
    </select>
        城市：<select id="cid">
        <option value="0">==========请选择所在城市============</option>
    </select>
    </div>
</body>
</html>
