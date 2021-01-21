package cn.sth.shop.servlet.front;

import cn.sth.shop.factory.ServiceFrontFactory;
import cn.sth.shop.util.CookieUtil;
import cn.sth.shop.util.ShopcarCookieUtil;
import cn.sth.shop.util.validate.ValidateUtil;
import cn.sth.shop.vo.Goods;
import cn.sth.shop.vo.Member;
import cn.sth.shop.vo.Shopcar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * ClassName:MemberServletFront
 * Package:cn.sth.shop.servlet.front
 * Description:
 *
 * @Date:2020/1/8 14:26
 * Author:沙天慧
 */
@WebServlet(name = "ShopcarServletFront",urlPatterns = "/pages/front/ShopcarServletFront/*")
public class ShopcarServletFront extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path="/pages/errors.jsp";
        String status=request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1);
        System.out.println(status);
        if(status!=null){
            if("insert".equals(status)){
                path = this.insert(request,response) ;
            }else if("list".equals(status)){
                path=this.list(request);
            }else if("update".equals(status)){
                path=this.update(request,response);
            }else if("delete".equals(status)){
                path=this.delete(request,response);
            }
        }
        request.getRequestDispatcher(path).forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
    public String insert(HttpServletRequest request,HttpServletResponse response){
        String msg=null;
        String url=null;
        int gid=Integer.parseInt(request.getParameter("gid"));
        String mid=(String)request.getSession().getAttribute("mid");
        Shopcar vo=new Shopcar();
        Member member=new Member();
        member.setMid(mid);
        vo.setMember(member);
        Goods goods=new Goods();
        goods.setGid(gid);
        vo.setGoods(goods);
        try {
            if(ServiceFrontFactory.getIShopCarServiceFrontInstance().addCar(vo)){
                msg="商品成功加入购物车！";
            }else{
                msg="购物车添加失败！";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String referer=request.getHeader("referer");
        url="/pages/front/goods/GoodsServletFront"+referer.substring(referer.lastIndexOf("/"));
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }
    public String list(HttpServletRequest request){
        String mid=(String)request.getSession().getAttribute("mid");
            try {
                Map<String,Object> map=ServiceFrontFactory.getIShopCarServiceFrontInstance().listCar(mid);
                request.setAttribute("allGoods",map.get("allGoods"));
                request.setAttribute("allCars",map.get("allShopcars"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        return "/pages/front/shopcar/car_list.jsp";
    }
    public String update(HttpServletRequest request,HttpServletResponse response){
        String msg=null;
        String url=null;
        String mid=(String)request.getSession().getAttribute("mid");
        Map<Integer,Integer> map=new HashMap<Integer, Integer>();
        Set<Shopcar> all=new HashSet<Shopcar>();
        Enumeration<String> enu=request.getParameterNames();
        while (enu.hasMoreElements()){
            String temp=enu.nextElement();
            int gid=Integer.parseInt(temp);
            int count=Integer.parseInt(request.getParameter(temp));
            Shopcar vo=new Shopcar();
            Member member=new Member();
            member.setMid(mid);
            vo.setMember(member);
            Goods goods=new Goods();
            goods.setGid(gid);
            vo.setGoods(goods);
            vo.setAmount(count);
            all.add(vo);
        }
        try {
            if (ServiceFrontFactory.getIShopCarServiceFrontInstance().update(mid,all)){
                msg="购物车信息更新成功！";
            }else {
                msg="购物车信息更新失败！";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        url="/pages/front/ShopcarServletFront/list";
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }
    public String delete(HttpServletRequest request,HttpServletResponse response){
        String msg=null;
        String url=null;
        String mid=(String)request.getSession().getAttribute("mid");
        String ids=request.getParameter("ids");
        String result[]=ids.split("\\|");
        Set<Integer> set=new HashSet<Integer>();
        for(int i=0;i<result.length;i++){
            set.add(Integer.parseInt(result[i]));
        }
        try {
            if(ServiceFrontFactory.getIShopCarServiceFrontInstance().deleteCar(mid,set)){
                msg="购物车商品删除成功！";
            }else{
                msg="购物车商品删除失败！";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        url="/pages/front/ShopcarServletFront/list";
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }

}
