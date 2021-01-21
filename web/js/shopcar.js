var allPrice=0.0;
window.onload=function () {
    document.getElementById("result").innerHTML="<font color='blue'>总价格:"+allPrice+"</font>";
}
function calGoods(gid) {
    var price=parseFloat(document.getElementById("price-"+gid).innerHTML);
    var count=parseInt(document.getElementById(gid).value);
    allPrice=allPrice+(price*count);
    document.getElementById("cal-"+gid).innerHTML="<font color='blue'>"+(price*count)+"</font>";
   if(document.getElementById("result")!=undefined){
       document.getElementById("result").innerHTML="<font color='blue'>总价格:"+allPrice+"</font>";
   }
}

function addBut(gid) {
    var price=parseFloat(document.getElementById("price-"+gid).innerHTML);
    var count=parseInt(document.getElementById(gid).value);
    allPrice=allPrice-(price*count);
    count++;
    document.getElementById(gid).value=count;
    calGoods(gid);
}
function subBut(gid) {
    var price=parseFloat(document.getElementById("price-"+gid).innerHTML);
    var count=parseInt(document.getElementById(gid).value);
    allPrice=allPrice-(price*count);
    if(count>0){
        count--;
        document.getElementById(gid).value=count;
        calGoods(gid);
    }
}