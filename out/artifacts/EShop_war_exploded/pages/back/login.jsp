<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String loginUrl = basePath + "pages/back/AdminLoginServletBack/login";
%>
<title>网站管理员登陆</title>
<base href="<%=basePath%>">
<style type="text/css">
    <!--
    body {
        margin-left: 0px;
        margin-top: 0px;
        margin-right: 0px;
        margin-bottom: 0px;
        background-color: #1D3647;
    }

    -->
</style>
<script type="text/javascript" src="<%=basePath%>js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>js/admin.js"></script>

<link href="<%=basePath%>images/skin.css" rel="stylesheet" type="text/css">
<body>
<table width="100%" height="166" border="0" cellpadding="0"
       cellspacing="0">
    <tr>
        <td height="42" valign="top">
            <table width="100%" height="42"
                   border="0" cellpadding="0" cellspacing="0" class="login_top_bg">
                <tr>
                    <td width="1%" height="21">&nbsp;</td>
                    <td height="42">&nbsp;</td>
                    <td width="17%">&nbsp;</td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td valign="top">
            <table width="100%" height="532" border="0"
                   cellpadding="0" cellspacing="0" class="login_bg">
                <tr>
                    <td width="49%" align="right">
                        <table width="91%" height="532"
                               border="0" cellpadding="0" cellspacing="0" class="login_bg2">
                            <tr>
                                <td height="138" valign="middle" align="right"><img
                                        src="<%=basePath%>images/logo.png" width="279" height="68"></td>
                            </tr>

                        </table>
                    </td>
                    <td width="1%">&nbsp;</td>
                    <td width="50%" valign="bottom">
                        <table width="100%"
                               height="59" border="0" align="center" cellpadding="0"
                               cellspacing="0">
                            <tr>
                                <td width="4%">&nbsp;</td>
                                <td width="96%" height="38"><span class="login_txt_bt">管理员后台登录</span></td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td height="21">
                                    <table cellSpacing="0" cellPadding="0"
                                           width="100%" border="0" id="table211" height="328">
                                        <tr>
                                            <td height="164" colspan="2" align="middle">
                                                <form name="myform" action="<%=loginUrl%>" method="post"
                                                      onsubmit="return validateLogin()">
                                                    <table cellSpacing="0" cellPadding="0" width="100%"
                                                           border="0" height="143" id="table212">
                                                        <tr>
                                                            <td width="13%" height="38" class="top_hui_text"><span
                                                                    class="login_txt">管理员：&nbsp;&nbsp; </span></td>
                                                            <td height="38" colspan="2" class="top_hui_text">
                                                                <input name="aid" id="aid" class="editbox4" value=""
                                                                       size="20" onblur="validateAid()">
                                                            </td>
                                                            <td>
                                                                <span id="aidMsg"></span>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td width="13%" height="35" class="top_hui_text"><span
                                                                    class="login_txt"> 密 码： &nbsp;&nbsp; </span></td>
                                                            <td height="35" colspan="2" class="top_hui_text">
                                                                <input
                                                                        class="editbox4" type="password" size="20"
                                                                        name="password" id="password"
                                                                        onblur="validatePas()"></td>
                                                            <td><span id="passwordMsg"></span></td>
                                                        </tr>
                                                        <tr>
                                                            <td width="13%" height="35"><span
                                                                    class="login_txt">验证码：</span></td>
                                                            <td height="35" colspan="2" class="top_hui_text"><input
                                                                    class="wenbenkuang" name="code" type="text" value=""
                                                                    maxLength="4" size="10" id="code"
                                                                    onblur="validateCode()"><img
                                                                    src="<%=basePath%>pages/image.jsp"
                                                                    onclick="changeCode(this)"></td>
                                                            <td><span id="codeMsg"></span></td>
                                                        </tr>
                                                        <tr>
                                                            <td height="35">&nbsp;</td>
                                                            <td width="20%" height="35"><input name="Submit"
                                                                                               type="submit"
                                                                                               class="button"
                                                                                               id="Submit" value="登 陆">
                                                            </td>
                                                            <td width="67%" class="top_hui_text" colspan="2"><input
                                                                    name="cs" type="reset" class="button" id="cs"
                                                                    value="取 消"></td>
                                                        </tr>
                                                    </table>
                                                    <br>
                                                </form>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="433" height="164" align="right" valign="bottom"><img
                                                    src="<%=basePath%>images/login-wel.gif" width="242" height="138">
                                            </td>
                                            <td width="57" align="right" valign="bottom">&nbsp;</td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td height="15">
            <table width="100%" border="0" cellspacing="0"
                   cellpadding="0" class="login-buttom-bg">
                <tr>
                    <td align="center"><span class="login-buttom-txt">Copyright
								&copy; 2015-2020 Dance Shop</span></td>
                </tr>
            </table>
        </td>
    </tr>
</table>