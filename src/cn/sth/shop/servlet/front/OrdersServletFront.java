package cn.sth.shop.servlet.front;

import cn.sth.shop.exception.EmptyShopcarException;
import cn.sth.shop.exception.UnCompleteMemberInfomationException;
import cn.sth.shop.exception.UnEnoughAmountException;
import cn.sth.shop.factory.DAOFactory;
import cn.sth.shop.factory.ServiceBackFactory;
import cn.sth.shop.factory.ServiceFrontFactory;
import cn.sth.shop.util.validate.ValidateUtil;

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
@WebServlet(name = "OrdersServletFront",urlPatterns = "/pages/front/orders/OrdersServletFront/*")
public class OrdersServletFront extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path="/pages/errors.jsp";
        String status=request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1);
        if(status!=null){
            if("insert".equals(status)){
                path = this.insert(request) ;
            }else if("list".equals(status)){
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
    public String insert(HttpServletRequest request){
        String msg=null;
        String url=null;
        String mid= (String) request.getSession().getAttribute("mid");
        try {
            if(ServiceFrontFactory.getIOrdersServiceFrontInstance().insert(mid)){
                msg="订单创建成功！";
                url="/index.jsp";
            }
        } catch (UnCompleteMemberInfomationException e) {
            msg="个人信息不完整，无法进行订单创建！";
            url="/pages/front/member/MemberInfoServletFront/updatePre";
            e.printStackTrace();
        } catch (UnEnoughAmountException e) {
            msg="商品库存量不足，无法进行购买！";
            url="/pages/front/ShopcarServletFront/list";
            e.printStackTrace();
        } catch (EmptyShopcarException e) {
            msg="购物车为空，请先将商品加入购物车！";
            url="/pages/front/goods/GoodsServletFront/list";
            e.printStackTrace();
        } catch (SQLException e) {
            msg="订单创建失败";
            e.printStackTrace();
        }
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }
    public String list(HttpServletRequest request){
        int currentPage=1;
        int lineSize=5;
        String column=null;
        String keyWord=null;
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
            Map<String,Object> map=ServiceFrontFactory.getIOrdersServiceFrontInstance().listByMember(mid,currentPage,lineSize);
            request.setAttribute("allOrders",map.get("allOrders"));
            request.setAttribute("allRecorders",map.get("ordersCount"));
        }catch (Exception e){
            e.printStackTrace();
        }
        request.setAttribute("currentPage",currentPage);
        request.setAttribute("lineSize",lineSize);
        request.setAttribute("column",column);
        request.setAttribute("keyWord",keyWord);
        request.setAttribute("url","/pages/front/orders/OrdersServletFront/list");
        return "/pages/front/orders/orders_list.jsp";
    }
    public String show(HttpServletRequest request){
        int oid=Integer.parseInt(request.getParameter("oid"));
        String mid= (String) request.getSession().getAttribute("mid");
        try {
            request.setAttribute("orders",ServiceFrontFactory.getIOrdersServiceFrontInstance().show(mid,oid));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/pages/front/orders/orders_show.jsp";
    }
}
