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
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>微商城</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
    <script type="text/javascript">
        window.alert("${msg}");
        window.location="<%=basePath%>${url}";
    </script>
</body>
</html>
