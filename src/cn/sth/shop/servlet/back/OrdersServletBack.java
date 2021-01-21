package cn.sth.shop.servlet.back;

import cn.sth.shop.exception.EmptyShopcarException;
import cn.sth.shop.exception.UnCompleteMemberInfomationException;
import cn.sth.shop.exception.UnEnoughAmountException;
import cn.sth.shop.factory.ServiceBackFactory;
import cn.sth.shop.factory.ServiceFrontFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

/**
 * ClassName:MemberServletFront
 * Package:cn.sth.shop.servlet.front
 * Description:
 *
 * @Date:2020/1/8 14:26
 * Author:沙天慧
 */
@WebServlet(name = "OrdersServletBack",urlPatterns = "/pages/back/admin/orders/OrdersServletBack/*")
public class OrdersServletBack extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path="/pages/errors.jsp";
        String status=request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1);
        if(status!=null){
            if("list".equals(status)){
                path=this.list(request);
            } else if ("show".equals(status)) {
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
        int currentPage=1;
        int lineSize=5;
        String column=null;
        String keyWord=null;
        String columnData="购买用户:mid";
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
            column="mid";
        }
        if (keyWord==null){
            keyWord="";
        }
        String mid= (String) request.getSession().getAttribute("mid");
        try{
            Map<String,Object> map= ServiceBackFactory.getOrdersServiceBackInstance().list(currentPage,lineSize,column,keyWord);
            request.setAttribute("allOrders",map.get("allOrders"));
            request.setAttribute("allRecorders",map.get("ordersCount"));
        }catch (Exception e){
            e.printStackTrace();
        }
        request.setAttribute("currentPage",currentPage);
        request.setAttribute("lineSize",lineSize);
        request.setAttribute("column",column);
        request.setAttribute("keyWord",keyWord);
        request.setAttribute("columnData",columnData);
        request.setAttribute("url","/pages/back/admin/orders/OrdersServletBack/list");
        return "/pages/back/admin/orders/orders_list.jsp";
    }
    public String show(HttpServletRequest request){
        int oid=Integer.parseInt(request.getParameter("oid"));
        String mid= (String) request.getSession().getAttribute("mid");
        try {
            request.setAttribute("orders",ServiceBackFactory.getOrdersServiceBackInstance().show(oid));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/pages/back/admin/orders/orders_show.jsp";
    }
}
