<%@ page import="cn.sth.shop.vo.Item" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    String addCarUrl=basePath+"pages/front/ShopcarServletFront/insert";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>商品详细信息</title>
    <link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<%=basePath%>js/common.js"></script>
</head>
<body>
<script type="text/javascript">
    function preview(file) {
        var prevDiv = document.getElementById('preview');
        if (file.files && file.files[0]) {
            var reader = new FileReader();
            reader.onload = function(evt) {
                prevDiv.innerHTML = '<img src="' + evt.target.result + '" />';
            }
            reader.readAsDataURL(file.files[0]);
        } else {
            prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
        }
    }
</script>
<div id="mainDiv">
    <c:if test="${goods!=null}">
        <table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2">
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td colspan="3" align="center"><span class="title">商品详细信息</span></td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td width=30px rowspan="9"><div id="preview"><img src="<%=basePath%>upload/goods/${goods.photo}"></div></td>
                <td >商品名称：</td>
                <td>${goods.name}</td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>所属分类：</td>
                <td>${goods.item.title}</td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>商品价格：</td>
                <td>${goods.price}</td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>浏览次数：</td>
                <td>${goods.browse}</td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>上架日期：</td>
                <td>${goods.pubdate}</td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>库存数量：</td>
                <td>${goods.amount}</td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td >商品描述：</td>
                <td>${goods.note}</td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td align="center" colspan="2"><a href="<%=addCarUrl%>?gid=${goods.gid}">加入购物车</a></td>
            </tr>
        </table>
    </c:if>
</div>
</body>
</html>
