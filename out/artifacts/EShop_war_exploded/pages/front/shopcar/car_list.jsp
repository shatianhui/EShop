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
    String deleteUrl=basePath+"/pages/front/ShopcarServletFront/delete?p=p";
    String updateUrl=basePath+"/pages/front/ShopcarServletFront/update";
    String ordersUrl=basePath+"/pages/front/orders/OrdersServletFront/insert";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>微商城</title>
    <script type="text/javascript" src="<%=basePath%>js/common.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/shopcar.js"></script>
</head>
<body>
<jsp:include page="/pages/header.jsp"></jsp:include>
<div id="mainDiv">
    <h1>我的购物车</h1>
    <c:if test="${allGoods!=null}" var="res">
        <form action="<%=updateUrl%>" method="post">
            <table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2">
                <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                    <td><input type="checkbox" onclick="checkboxSelect(this,'gid')"></td>
                    <td>图片</td>
                    <td>名称</td>
                    <td>价格</td>
                    <td>数量</td>
                    <td>总价</td>
                </tr>
                <c:forEach items="${allGoods}" var="goods">
                    <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                        <td><input type="checkbox" id=gid" name="gid" value="${goods.gid}"></td>
                        <td><img src="<%=basePath%>upload/goods/${goods.photo}" style="width: 50px;height: 50px"></td>
                        <td>${goods.name}</td>
                        <td><span id="price-${goods.gid}">${goods.price}</span></td>
                        <td>
                            <input type="button" value="-" onclick="subBut(${goods.gid})">
                            <input type="text" value="${allCars[goods.gid]}" size="5" name="${goods.gid}"
                                   id="${goods.gid}">
                            <input type="button" value="+" onclick="addBut(${goods.gid})">
                        </td>
                        <td><span id="cal-${goods.gid}"></span>
                            <script type="text/javascript">
                                calGoods(${goods.gid});
                            </script>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <input type="button" value="删除购物车商品" onclick="deleteAll('<%=deleteUrl%>','ids','gid')">
            <input type="submit" value="修改购买商品数量">
            <span id="result"></span>
            <a href="<%=ordersUrl%>">购买</a>
        </form>
    </c:if>
    <jsp:include page="/pages/footer.jsp"></jsp:include>
</div>
</body>
</html>
