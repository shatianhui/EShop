package cn.sth.shop.servlet.back;

import cn.sth.shop.factory.ServiceBackFactory;
import cn.sth.shop.util.CookieUtil;
import cn.sth.shop.util.MD5Code;
import cn.sth.shop.util.validate.ValidateUtil;
import cn.sth.shop.vo.Admin;
import cn.sth.shop.vo.Goods;
import cn.sth.shop.vo.Item;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 * ClassName:AdminLoginServletBack
 * Package:cn.sth.shop.servlet.back
 * Description:
 *
 * @Date:2020/1/10 15:25
 * Author:沙天慧
 */
@WebServlet(name = "GoodsServletBack",urlPatterns = "/pages/back/admin/goods/GoodsServletBack/*")
public class GoodsServletBack extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path="/pages/errors.jsp";
        String status=request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1);
        System.out.println(status);
        if(status!=null){
            if("insertPre".equals(status)){
                path = this.insertPre(request) ;
            }else if("insert".equals(status)){
                path=this.insert(request,response);
            }else if("list".equals(status)){
                path=this.list(request);
            }else if("listStatus".equals(status)){
                path=this.listStatus(request);
            }else if("updateStatus".equals(status)){
                path=this.updateStatus(request);
            }else if("updatePre".equals(status)){
                path=this.updatePre(request);
            }else if("update".equals(status)){
                path=this.update(request,response);
            }else if("delete".equals(status)){
                path=this.delete(request);
            }
        }
        request.getRequestDispatcher(path).forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
    public String delete(HttpServletRequest request){
        String referer=request.getHeader("referer");//取得之前的路径
        String msg=null;
        String url=null;
        String ids=request.getParameter("ids");//包含有id:photo
        if(ValidateUtil.validateEmpty(ids)){
            Set<Integer> allIds=new HashSet<Integer>();
            Set<String> allPhotos=new HashSet<String>();
            String result[]=ids.split("\\|");
            for(int i=0;i<result.length;i++){
                String temp[]=result[i].split(":");
                allIds.add(Integer.parseInt(temp[0]));
                if(!"nophoto.jpg".equals(temp[1])){
                    allPhotos.add(temp[1]);
                }
            }
            try {
                if (ServiceBackFactory.getGoodsServiceBackInstance().delete(allIds)){
                    if(allPhotos.size()>0){
                        Iterator<String> iter=allPhotos.iterator();
                        while (iter.hasNext()){
                            String filePath=super.getServletContext().getRealPath("/upload/goods/")+iter.next();
                            File file=new File(filePath);
                            if(file.exists()){//存在
                                file.delete();//删除
                            }
                        }
                    }
                    msg="商品信息删除成功！";
                }else{
                    msg="商品信息删除失败！";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            msg="删除的数据有错误，请重新操作！";
        }
        url="/pages/back/admin/goods/GoodsServletBack"+referer.substring(referer.lastIndexOf("/"));
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }

    public  String insertPre(HttpServletRequest request){
        try {
            Map<String,Object> all=ServiceBackFactory.getGoodsServiceBackInstance().insertPre();
            request.setAttribute("allItems",all.get("allItems"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/pages/back/admin/goods/goods_insert.jsp";
    }
    public String insert(HttpServletRequest request,HttpServletResponse response){
        String msg=null;
        String url=null;
        SmartUpload smart=new SmartUpload();
        try {
            smart.initialize(super.getServletConfig(),request,response);//初始化
            smart.upload();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String iid=smart.getRequest().getParameter("iid");
        String name=smart.getRequest().getParameter("name");
        String price=smart.getRequest().getParameter("price");
        String amount=smart.getRequest().getParameter("amount");
        String note =smart.getRequest().getParameter("note");
        String status=smart.getRequest().getParameter("status");
        if(ValidateUtil.validateRegex(iid,"\\d+")&&ValidateUtil.validateEmpty(name)
                &&ValidateUtil.validateRegex(price,"\\d+(\\.\\d{1,2})?")
        &&ValidateUtil.validateRegex(amount,"\\d+")&&ValidateUtil.validateEmpty(note)
        &&ValidateUtil.validateRegex(status,"\\d")){
            Goods vo=new Goods();
            vo.setName(name);
            vo.setPrice(Double.parseDouble(price));
            vo.setAmount(Integer.parseInt(amount));
            vo.setNote(note);
            vo.setStatus(Integer.parseInt(status));
            vo.setPubdate(new Date());
            vo.setBrowse(0);
            Item item=new Item();
            item.setIid(Integer.parseInt(iid));
            vo.setItem(item);
            String aid=(String)request.getSession().getAttribute("aid");
            Admin admin=new Admin();
            admin.setAid(aid);
            vo.setAdmin(admin);
            try {
                if(smart.getFiles().getSize()>0){//有图片上传
                    if(smart.getFiles().getFile(0).getContentType().contains("image")){//是图片
                        vo.setPhoto(UUID.randomUUID()+"."+smart.getFiles().getFile(0).getFileExt());//生成文件名称
                    }else {
                        vo.setPhoto("nophoto.jpg");
                    }
                }else{
                    vo.setPhoto("nophoto.jpg");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(ServiceBackFactory.getGoodsServiceBackInstance().insert(vo)){
                    String filePath=super.getServletContext().getRealPath("/upload/goods/")+vo.getPhoto();
                    //String filePath=request.getSession().getServletContext().getRealPath("/upload/goods/")+vo.getPhoto();
                    System.out.println(filePath);
                    if(smart.getFiles().getSize()>0){//有图片上传
                        if(smart.getFiles().getFile(0).getContentType().contains("image")){//是图片
                            smart.getFiles().getFile(0).saveAs(filePath);
                        }
                    }
                    msg="商品信息发布成功!";
                }else{
                    msg="商品信息发布失败！";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            msg="商品增加数据出错，无法进行商品的信息发布！";
        }
        url="/pages/back/admin/goods/GoodsServletBack/insertPre";
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }
    public  String list(HttpServletRequest request){
        int currentPage=1;
        int lineSize=5;
        String column=null;
        String keyWord=null;
        String columnData="商品名称:name|发布管理员:aid";
        try{
            currentPage=Integer.parseInt(request.getParameter("cp"));
        }catch (Exception e){
        }
        try{
            lineSize=Integer.parseInt(request.getParameter("ls"));
        }catch (Exception e){
        }
        column=request.getParameter("col");
        keyWord=request.getParameter("kw");
        if(column==null){
            column="name";
        }
        if (keyWord==null){
            keyWord="";
        }
        try{
            Map<String,Object> map=ServiceBackFactory.getGoodsServiceBackInstance().list(currentPage,lineSize,column,keyWord);
            request.setAttribute("allGoods",map.get("allGoods"));
            request.setAttribute("allRecorders",map.get("goodsCount"));
        }catch (Exception e){
            e.printStackTrace();
        }
        request.setAttribute("currentPage",currentPage);
        request.setAttribute("lineSize",lineSize);
        request.setAttribute("column",column);
        request.setAttribute("keyWord",keyWord);
        request.setAttribute("columnData",columnData);
        request.setAttribute("url","/pages/back/admin/goods/GoodsServletBack/list");
        return "/pages/back/admin/goods/goods_list.jsp";
    }
    public  String listStatus(HttpServletRequest request){
        int currentPage=1;
        int lineSize=5;
        String column=null;
        String keyWord=null;
        String columnData="商品名称:name|发布管理员:aid";
        try{
            currentPage=Integer.parseInt(request.getParameter("cp"));
        }catch (Exception e){
        }
        try{
            lineSize=Integer.parseInt(request.getParameter("ls"));
        }catch (Exception e){
        }
        column=request.getParameter("col");
        keyWord=request.getParameter("kw");
        if(column==null){
            column="name";
        }
        if (keyWord==null){
            keyWord="";
        }
        int status=Integer.parseInt(request.getParameter("status"));
        try{
            Map<String,Object> map=ServiceBackFactory.getGoodsServiceBackInstance().listStatus(status,currentPage,lineSize,column,keyWord);
            request.setAttribute("allGoods",map.get("allGoods"));
            request.setAttribute("allRecorders",map.get("goodsCount"));
        }catch (Exception e){
            e.printStackTrace();
        }
        request.setAttribute("currentPage",currentPage);
        request.setAttribute("lineSize",lineSize);
        request.setAttribute("column",column);
        request.setAttribute("keyWord",keyWord);
        request.setAttribute("columnData",columnData);
        request.setAttribute("paramName","status");
        request.setAttribute("paramValue",String.valueOf(status));
        request.setAttribute("url","/pages/back/admin/goods/GoodsServletBack/list");
        return "/pages/back/admin/goods/goods_list.jsp";
    }
    public String updateStatus(HttpServletRequest request){
        String referer=request.getHeader("referer");//取得之前的路径
        String msg=null;
        String url=null;
        String ids=request.getParameter("ids");
        String type=request.getParameter("type");
        if(ValidateUtil.validateEmpty(ids)){
            String result[]=ids.split("\\|");//数据构成：id:photo
            Set<Integer> all=new HashSet<Integer>();
            for(int i=0;i<result.length;i++){
                String temp[]=result[i].split(":");
                all.add(Integer.parseInt(temp[0]));
            }
            boolean flag=false;
            try {
                if("up".equals(type)){
                    flag=ServiceBackFactory.getGoodsServiceBackInstance().updateUp(all);
                }
                if("down".equals(type)){
                    flag=ServiceBackFactory.getGoodsServiceBackInstance().updateDown(all);
                }
                if ("delete".equals(type)){
                    flag=ServiceBackFactory.getGoodsServiceBackInstance().updateDelete(all);
                }
                if(flag){
                    msg="商品状态更新成功！";
                }else {
                    msg="商品状态更新失败！";
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }else {
            msg="要更新的数据有错误，请重新操作！";
        }
        url="/pages/back/admin/goods/GoodsServletBack"+referer.substring(referer.lastIndexOf("/"));
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }
    public  String updatePre(HttpServletRequest request){
        String referer=request.getHeader("referer");//取得之前的路径
        String url="/pages/back/admin/goods/GoodsServletBack"+referer.substring(referer.lastIndexOf("/"));
        String gid=request.getParameter("gid");
        if (ValidateUtil.validateEmpty(gid)) {
            try {
                Map<String,Object> all=ServiceBackFactory.getGoodsServiceBackInstance().updatePre(Integer.parseInt(gid));
                request.setAttribute("allItems",all.get("allItems"));
                request.setAttribute("goods",all.get("goods"));
                request.setAttribute("back",url);//返回路径
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "/pages/back/admin/goods/goods_update.jsp";
        }else{
            request.setAttribute("msg","还未选择要更新的数据，请重新确认");
            request.setAttribute("url",url);
            return "/pages/forward.jsp";
        }
    }
    public String update(HttpServletRequest request,HttpServletResponse response){
        String msg=null;
        String url=null;
        SmartUpload smart=new SmartUpload();
        try {
            smart.initialize(super.getServletConfig(),request,response);//初始化
            smart.upload();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String oldPic=smart.getRequest().getParameter("oldPic");
        String gid=smart.getRequest().getParameter("gid");
        String iid=smart.getRequest().getParameter("iid");
        String name=smart.getRequest().getParameter("name");
        String price=smart.getRequest().getParameter("price");
        String amount=smart.getRequest().getParameter("amount");
        String note =smart.getRequest().getParameter("note");
        String status=smart.getRequest().getParameter("status");
        if(ValidateUtil.validateEmpty(gid)&&ValidateUtil.validateRegex(iid,"\\d+")&&ValidateUtil.validateEmpty(name)
                &&ValidateUtil.validateRegex(price,"\\d+(\\.\\d{1,2})?")
                &&ValidateUtil.validateRegex(amount,"\\d+")&&ValidateUtil.validateEmpty(note)
                &&ValidateUtil.validateRegex(status,"\\d")){
            Goods vo=new Goods();
            vo.setGid(Integer.parseInt(gid));
            vo.setName(name);
            vo.setPrice(Double.parseDouble(price));
            vo.setAmount(Integer.parseInt(amount));
            vo.setNote(note);
            vo.setStatus(Integer.parseInt(status));
            Item item=new Item();
            item.setIid(Integer.parseInt(iid));
            vo.setItem(item);
            try {
                if(smart.getFiles().getSize()>0){//有图片上传
                    if(smart.getFiles().getFile(0).getContentType().contains("image")){//是图片
                        if("nophoto.jpg".equals(oldPic)){//之前没有上传图片，需要重新生成名称
                            vo.setPhoto(UUID.randomUUID()+"."+smart.getFiles().getFile(0).getFileExt());//生成文件名称
                        }else{
                            vo.setPhoto(oldPic);
                        }
                    }else {
                        vo.setPhoto(oldPic);
                    }
                }else{
                    vo.setPhoto(oldPic);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(ServiceBackFactory.getGoodsServiceBackInstance().update(vo)){
                    String filePath=super.getServletContext().getRealPath("/upload/goods/")+vo.getPhoto();
                    if(smart.getFiles().getSize()>0){//有图片上传
                        if(smart.getFiles().getFile(0).getContentType().contains("image")){//是图片
                            smart.getFiles().getFile(0).saveAs(filePath);
                        }
                    }
                    msg="商品信息修改成功!";
                }else{
                    msg="商品信息修改失败！";
                }
                url=request.getParameter("back");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            msg="商品修改数据出错，无法进行商品的信息修改！";
            url=smart.getRequest().getParameter("back");
        }
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }
}
