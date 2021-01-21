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
    String updateUrl=basePath+"pages/front/member/MemberInfoServletFront/update";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>微商城</title>
    <script type="text/javascript" src="<%=basePath%>js/common.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/member.js"></script>
    <link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css">
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
    <jsp:include page="/pages/header.jsp"></jsp:include>
    <div id="mainDiv">
        <c:if test="${member!=null}">
            <form action="<%=updateUrl%>" method="post" enctype="multipart/form-data" onsubmit="return validateUpdate()">
                <table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2" >
                    <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                        <td colspan="4" align="center">更新用户的个人信息</td>
                    </tr>
                    <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                        <td align="center"><strong>用户ID:</strong></td>
                        <td>${member.mid}</td>
                        <td width="250px"></td>
                        <td rowspan="6"><div id="preview"><img src="<%=basePath%>upload/member/${member.photo}" style="width: 100%;height: 100%"></div></td>
                    </tr>
                    <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                        <td align="center"><strong>姓名:</strong></td>
                        <td><input type="text" id="name" name="name" value="${member.name}" onblur="validateName()"></td>
                        <td><span id="nameMsg"></span> </td>
                    </tr>
                    <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                        <td align="center"><strong>电话:</strong></td>
                        <td><input type="text" id="phone" name="phone" value="${member.phone}" onblur="validatePhone()"></td>
                        <td><span id="phoneMsg"></span> </td>
                    </tr>
                    <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                        <td align="center"><strong>地址:</strong></td>
                        <td><input type="text" id="address" name="address" value="${member.address}" onblur="validateAddress()"></td>
                        <td><span id="addressMsg"></span> </td>
                    </tr>
                    <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                        <td><strong>注册日期:</strong></td>
                        <td colspan="2">${member.regdate}</td>
                    </tr>
                    <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                        <td><strong>个人照片:</strong></td>
                        <td colspan="2"><input type="file" onchange="preview(this)" name="photo" id="photo"></td>
                    </tr>
                    <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                        <td colspan="4">
                            <input type="hidden" name="oldPic" id="oldPic" value="${member.photo}">
                            <input type="submit" value="更新">
                            <input type="reset" value="重置">
                        </td>
                    </tr>
                </table>
            </form>
        </c:if>
    </div>
    <jsp:include page="/pages/footer.jsp"></jsp:include>
</body>
</html>
