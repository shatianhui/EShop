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
    String insertUrl=basePath+"pages/back/admin/goods/GoodsServletBack/insert";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>商品增加</title>
    <link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<%=basePath%>js/common.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/goods.js"></script>
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
    <form action="<%=insertUrl%>" method="post" onsubmit="return validateInsert()" enctype="multipart/form-data">
        <table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2">
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td colspan="4" align="center"><span class="title">增加商品</span></td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td width=10px>商品名称：</td>
                <td width=15px><input type="text" name="name" id="name" class="init" onblur="validateName()"></td>
                <td width=15px><span id="nameMsg"></span></td>
                <td width=30px rowspan="6"><div id="preview"></div></td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>所属分类：</td>
                <td>
                    <c:if test="${allItems!=null}" var="res">
                        <select name="iid" id="iid">
                            <c:forEach items="${allItems}" var="item">
                                <option value="${item.iid}">${item.title}</option>
                            </c:forEach>
                        </select>
                    </c:if>
                </td>
                <td width="40%"><span id="iidMsg"></span></td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>商品价格：</td>
                <td><input type="text" name="price" id="price" class="init" onblur="validatePrice()"></td>
                <td><span id="priceMsg"></span></td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>库存数量：</td>
                <td><input type="text" name="amount" id="amount" class="init" onblur="validateAmount()"></td>
                <td><span id="amountMsg"></span></td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>商品图片：</td>
                <td><input type="file" name="photo" id="photo" class="init" onchange="preview(this)"></td>
                <td><span id="photoMsg"></span></td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>发布状态：</td>
                <td>
                    <input type="radio" name="status"  class="init" value="0">下架
                    <input type="radio" name="status"  class="init" value="1" checked>上架
                </td>
                <td><span id="statusMsg"></span></td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>商品描述：</td>
                <td colspan="4">
                    <textarea name="note" id="note" class="init" cols="70" rows="5"></textarea>
                </td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td colspan="4">
                    <input type="submit" value="增加">
                    <input type="reset" value="取消">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
