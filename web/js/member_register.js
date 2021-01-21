var xmlHttpRequest;
var midFlag=false;
function createXmlHttpRequest() {
    if(window.XMLHttpRequest){//IE游览器
        xmlHttpRequest=new XMLHttpRequest();
    }else{//非IE游览器
        xmlHttpRequest=new ActiveXObject("Microsoft.XMLHTTP");
    }
}
function validateMid() {
    if(validateEmpty("mid")){//不为空
        var mid=document.getElementById("mid").value;
        createXmlHttpRequest();//调用函数，创建XMLHttpRequest对象
        xmlHttpRequest.open("post","./pages/memberServletFront/checkMid?mid="+mid);
        xmlHttpRequest.onreadystatechange=function () {
            if(xmlHttpRequest.status==200){//服务器处理正常
                if(xmlHttpRequest.readyState==4){
                    midFlag=xmlHttpRequest.responseText=="true";
                    var obj = document.getElementById(mid) ;	// 取得对象
                    var msg = document.getElementById("midMsg") ;
                    if(midFlag){
                        //obj.className = "right" ;	// 更换使用的样式
                        if (msg != null) {
                            msg.innerHTML = "<font color='green'>该ID可以使用！</font>" ;
                        }
                    }else{
                        //obj.className = "wrong" ;	// 更换使用的样式
                        if (msg != null) {
                            msg.innerHTML = "<font color='red'>该ID已存在！</font>";
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
function validatePas() {
    return validateEmpty("password");
}

function validateRegister() {
    return midFlag&&validatePas();
}