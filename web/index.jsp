<%--
  Created by IntelliJ IDEA.
  User: 沙天慧
  Date: 2020/1/7
  Time: 12:58
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
  <jsp:include page="/pages/header.jsp"></jsp:include>
  <div id="mainDiv">
    首页信息
  </div>
  <jsp:include page="/pages/footer.jsp"></jsp:include>
  </body>
</html>
