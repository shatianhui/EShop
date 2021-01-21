var xmlHttpRequest;
function createXmlHttpRequest() {
    if(window.XMLHttpRequest){//IE游览器
        xmlHttpRequest=new XMLHttpRequest();
    }else{//非IE游览器
        xmlHttpRequest=new ActiveXObject("Microsoft.XMLHTTP");
    }
}
function loadCity() {//加载城市信息
    var pid=document.getElementById("pid").value;
    alert(pid);
    if(pid!="0") {//选定了内容
        createXmlHttpRequest();//调用函数，创建XMLHttpRequest对象
        alert("__-");
        xmlHttpRequest.open("post", "pages/CityServlet?pid=" + pid);
        alert("====");
        xmlHttpRequest.onreadystatechange= function () {
            alert(xmlHttpRequest.status);
            if (xmlHttpRequest.status == 200) {//服务器处理正常
                if (xmlHttpRequest.readyState == 4) {
                    //开始进行dom解析
                    alert(xmlHttpRequest.responseText);
                    var allCity=xmlHttpRequest.responseText;
                    var cids=allCity.getElementsByTagName("cid");
                    var titles=allCity.getElementsByTagName("title");
                    var cidElement=document.getElementById("cid");
                    cidElement.length=1;//数据清空
                    for(var i=0;i<cids.length;i++){
                        var optionElement=document.createElement("option");
                        optionElement.setAttribute("value",cids[i].firstChild.nodeValue);
                        optionElement.appendChild(document.createTextNode(titles[i]).firstChild.nodeValue);
                        cidElement.appendChild(optionElement);
                    }
                }
            }
        };
        xmlHttpRequest.send(null);
    }
}