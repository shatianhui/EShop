<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 沙天慧
  Date: 2020/1/8
  Time: 15:34
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path=request.getContextPath();
    String basePath=request.getScheme()+"://"+request.getServerName()+
            ":"+request.getServerPort()+path;

%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>微商城</title>
</head>
<body>
    <div id="headerDiv">
        <img src="<%=basePath%>/images/logo.jpg" style="width: 15%;height:12%">
        <a href="<%=basePath%>/pages/front/goods/GoodsServletFront/list">商品列表</a>
        <a href="<%=basePath%>/pages/front/ShopcarServletFront/list">我的购物车</a>
        <c:if test="${mid!=null}">
            <a href="<%=basePath%>/pages/front/member/MemberInfoServletFront/updatePre">个人信息</a>
            <img src="<%=basePath%>/upload/member/${photo}"style="width: 32px;height:32px">
            <a href="<%=basePath%>/pages/front/orders/OrdersServletFront/list">个人订单</a>
            <a href="<%=basePath%>/pages/memberServletFront/logout">安全退出</a>
        </c:if>
        <c:if test="${mid==null}">
            <a href="<%=basePath%>/pages/member_login.jsp">登录</a>
            <a href="<%=basePath%>/pages/member_register.jsp">注册</a>
        </c:if>
    </div>
</body>
</html>
