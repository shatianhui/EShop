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
    String updateUrl=basePath+"pages/back/admin/item/ItemServletBack/update";
    String deleteUrl=basePath+"pages/back/admin/item/ItemServletBack/delete?p=p";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>商品分类</title>
    <script type="text/javascript" src="<%=basePath%>js/common.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/item.js"></script>
</head>
<body>
<div id="mainDiv">
    <c:if test="${allItems!=null}">
        <table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2">
        <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
            <td colspan="3" align="center"><span class="title">商品类型列表</span></td>
        </tr>
        <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
            <td width="15%"><input type="checkbox" onclick="checkboxSelect(this,'tiid')"></td>
            <td width="70%">类型</td>
            <td width="15%">操作</td>
        </tr>
        <c:forEach items="${allItems}" var="item">
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td><input type="checkbox" name="tiid" id="tiid" value="${item.iid}"></td>
                <td >
                    <input type="text" name="title-${item.iid}" id="title-${item.iid}" value="${item.title}">
                    <span  id="title-${item.iid}Msg"></span>
                </td>
                <td><input type="button" value="修改" onclick="goUpdate(${item.iid})"></td>
            </tr>
        </c:forEach>
        </table>
        <input type="button" value="删除商品分类信息" onclick="deleteAll('<%=deleteUrl%>','ids','tiid')">
    </c:if>
</div>
<form id="itemForm" method="post" action="<%=updateUrl%>">
    <input type="hidden" name="iid" id="iid">
    <input type="hidden" name="title" id="title">
</form>
</body>
</html>
