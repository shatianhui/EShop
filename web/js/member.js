var xmlHttpRequest;
var codeFlag=false;
function createXmlHttpRequest() {
    if(window.XMLHttpRequest){//IE游览器
        xmlHttpRequest=new XMLHttpRequest();
    }else{//非IE游览器
        xmlHttpRequest=new ActiveXObject("Microsoft.XMLHTTP");
    }
}
function validateMid() {
    return validateEmpty("mid");
}
function validatePas() {
    return validateEmpty("password");
}
function validateCode() {
   if(validateEmpty("code")){//验证码输入正确
       //进行Ajax异步验证
       var code=document.getElementById("code").value;
       createXmlHttpRequest();//调用函数，创建XMLHttpRequest对象
       xmlHttpRequest.open("post","./pages/memberServletFront/checkCode?code="+code);
       xmlHttpRequest.onreadystatechange=function () {
           if(xmlHttpRequest.status==200){//服务器处理正常
               if(xmlHttpRequest.readyState==4){
                   //alert(xmlHttpRequest.responseText);
                   codeFlag=xmlHttpRequest.responseText=="true";
                   var obj = document.getElementById(code) ;	// 取得对象
                   var msg = document.getElementById("codeMsg") ;
                   if(codeFlag){//验证码正确
                       //obj.className = "right" ;	// 更换使用的样式
                       if (msg != null) {
                           msg.innerHTML = "<font color='green'>验证码输入正确！</font>" ;
                       }
                   }else{
                       //obj.className = "wrong" ;	// 更换使用的样式
                       if (msg != null) {
                           msg.innerHTML = "<font color='red'>验证码输入不正确！</font>";
                           document.getElementById("codeImg").src="./pages/image.jsp?p="+Math.random();//地址变化才变化
                           document.getElementById("code").value="";//内容清空
                       }
                   }

               }
           }
       };//回调函数
       xmlHttpRequest.send(null);
   }else{
       return false;
   }
}
function validateLogin() {
    return validateMid()&&validatePas()&&codeFlag;
}
function validateName() {
    return validateEmpty("name");
}
function validatePhone() {
    return validateEmpty("phone");
}
function validateAddress() {
    return validateEmpty("address");
}
function validateUpdate() {
    return validateName()&&validatePhone()&&validateAddress();
}