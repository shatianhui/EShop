package cn.sth.shop.servlet.front;

import cn.sth.shop.factory.ServiceBackFactory;
import cn.sth.shop.factory.ServiceFrontFactory;
import cn.sth.shop.util.CookieUtil;
import cn.sth.shop.util.MD5Code;
import cn.sth.shop.util.validate.ValidateUtil;
import cn.sth.shop.vo.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * ClassName:MemberServletFront
 * Package:cn.sth.shop.servlet.front
 * Description:
 *
 * @Date:2020/1/8 14:26
 * Author:沙天慧
 */
@WebServlet(name = "GoodsServletFront",urlPatterns = "/pages/front/goods/GoodsServletFront/*")
public class GoodsServletFront extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path="/pages/errors.jsp";
        String status=request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1);
        System.out.println(status);
        if(status!=null){
            if("list".equals(status)){
                path = this.list(request) ;
            }else if ("show".equals(status)){
                path=this.show(request);
            }
        }
        request.getRequestDispatcher(path).forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
    public String list(HttpServletRequest request){
        String iid=request.getParameter("iid");
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
        Map<String,Object> map=null;
        if(iid==null||"0".equals(iid)){//属于查询全部
            try {
                map=ServiceFrontFactory.getIGoodsServiceFrontInstance().list(currentPage,lineSize,column,keyWord);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {//要根据item进行查询
            try{
                map= ServiceFrontFactory.getIGoodsServiceFrontInstance().listByItem(Integer.parseInt(iid),currentPage,lineSize,column,keyWord);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        request.setAttribute("allItems",map.get("allItems"));
        request.setAttribute("allGoods",map.get("allGoods"));
        request.setAttribute("allRecorders",map.get("goodsCount"));
        request.setAttribute("currentPage",currentPage);
        request.setAttribute("lineSize",lineSize);
        request.setAttribute("column",column);
        request.setAttribute("keyWord",keyWord);
        request.setAttribute("columnData",columnData);
        request.setAttribute("paramName","iid");
        request.setAttribute("paramValue",iid);
        request.setAttribute("url","/pages/back/admin/goods/GoodsServletBack/list");
        return "/pages/front/goods/goods_list.jsp";
    }
    public String show(HttpServletRequest request){
        String msg=null;
        String url=null;
        String referer=request.getHeader("referer");//取得之前的路径
        String gid=request.getParameter("gid");
        if(ValidateUtil.validateRegex(gid,"\\d+")){
            try {
                request.setAttribute("goods",ServiceFrontFactory.getIGoodsServiceFrontInstance().show(Integer.parseInt(gid)));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "/pages/front/goods/goods_show.jsp";
        }else{
            msg="你所选择的商品信息有问题，请重新选择！";
            url="/pages/front/goods/GoodsServletFront"+referer.substring(referer.lastIndexOf("/"));
            return "/pages/forward.jsp";
        }

    }
}
