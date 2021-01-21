<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String showUrl=basePath+"pages/front/goods/GoodsServletFront/show";
	String listUrl=basePath+"pages/front/goods/GoodsServletFront/list";
	String addCarUrl=basePath+"pages/front/ShopcarServletFront/insert";
%>
<html>
<head>
<title>商品分页</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<script type="text/javascript" src="<%=basePath%>js/common.js"></script>
	<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<script>
	function goList(url,iid) {
		window.location=url+"?iid="+iid;
	}
</script>
<jsp:include page="/pages/header.jsp"></jsp:include>
	<div id="mainDiv">
		<h1>商品列表</h1>
		<select onchange="goList('<%=listUrl%>',this.value)">
			<option value="0">查看全部商品</option>
			<c:forEach items="${allItems}" var="item">
				<option value="${item.iid}"${item.iid==paramValue?"selected":""}>${item.title}</option>
			</c:forEach>
		</select>
		<br>
		<br>
		<c:if test="${allGoods!=null}" var="res">
			<div id="splitSearchDiv">
				<jsp:include page="/pages/split_page_plugin_search.jsp"/>
				<br>
			</div>
			<c:forEach items="${allGoods}" var="goods">
				<table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2">
				<tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
					<td rowspan="4">
						<a href="<%=showUrl%>?gid=${goods.gid}">
						<img src="<%=basePath%>upload/goods/${goods.photo}" style="width: 70px;height: 70px">
						</a>
					</td>
				</tr>
				<tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
					<td width="100px"><strong>商品名称：</strong></td>
					<td width="190px"><a href="<%=showUrl%>?gid=${goods.gid}">${goods.name}</a></td>
					<td width="100px"><strong>上架日期：</strong></td>
					<td width="200px">${goods.pubdate}</td>
					<td width="100px"><strong>商品价格：</strong></td>
					<td width="50px">${goods.price}</td>
					<td width="100px"><strong>游览次数：</strong></td>
					<td width="50px">${goods.browse}</td>
				</tr>
				<tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
					<td>商品详情</td>
					<td colspan="5">
						${goods.note}
					</td>
					<td colspan="2"><a href="<%=addCarUrl%>?gid=${goods.gid}">加入购物车</a></td>
				</tr>
				</table>
			</c:forEach>
			<div id="splitBarDiv" style="float:right">
				<jsp:include page="/pages/split_page_plugin_bars.jsp"/>
			</div>
		</c:if>
	</div>
<jsp:include page="/pages/footer.jsp"></jsp:include>
</body>
</html>
