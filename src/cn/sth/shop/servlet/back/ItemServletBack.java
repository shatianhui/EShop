package cn.sth.shop.servlet.back;


import cn.sth.shop.factory.ServiceBackFactory;
import cn.sth.shop.util.validate.ValidateUtil;
import cn.sth.shop.vo.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * ClassName:ItemServletBack
 * Package:cn.sth.shop.servlet.back
 * Description:
 *
 * @Date:2020/1/12 17:22
 * Author:沙天慧
 */
@WebServlet(name = "ItemServletBack",urlPatterns = "/pages/back/admin/item/ItemServletBack/*")
public class ItemServletBack extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path="/pages/errors.jsp";
        String status=request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1);
        System.out.println(status);
        if(status!=null){
            if("insert".equals(status)){
                path = this.insert(request) ;
            } else if ("update".equals(status)){
                path=this.update(request);
            } else if ("list".equals(status)) {
                path=this.list(request);
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
    public String insert(HttpServletRequest request){
        String msg=null;
        String url=null;
        String title=request.getParameter("title");
        if(ValidateUtil.validateEmpty(title)){
            try{
                Item vo=new Item();
                vo.setTitle(title);
                if(ServiceBackFactory.getItemServiceBackInstance().insert(vo)){
                    msg="商品分类信息增加成功！";
                }else {
                    msg="商品分类增加失败，请确认输入数据是否正确！";
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            msg="商品分类增加失败，请确认输入数据是否正确！";

        }
        url="/pages/back/admin/item/item_insert.jsp";
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }
    public String update(HttpServletRequest request){
        String msg=null;
        String url=null;
        String iid=request.getParameter("iid");
        String title=request.getParameter("title");
        if(ValidateUtil.validateEmpty(iid)&&ValidateUtil.validateEmpty("title")){
            Item vo=new Item();
            vo.setIid(Integer.parseInt(iid));
            vo.setTitle(title);
            try {
                if(ServiceBackFactory.getItemServiceBackInstance().update(vo)){
                    msg="商品分类信息更新成功！";
                }else{
                    msg="商品分类信息更新失败！";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
           msg="要更新的分类数据错误，请重新确认！";
        }
        url="/pages/back/admin/item/ItemServletBack/list";
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }
    public String delete(HttpServletRequest request){
        String msg=null;
        String url=null;
        String ids=request.getParameter("ids");
        if(ValidateUtil.validateEmpty("ids")){
            String result[]=ids.split("\\|");
            Set<Integer> all=new HashSet<Integer>();
            for(int i=0;i<result.length;i++){
                all.add(Integer.parseInt(result[i]));
            }
            try {
                if(ServiceBackFactory.getItemServiceBackInstance().delete(all)){
                    msg="商品分类信息删除成功！";
                }else {
                    msg="商品分类信息删除失败！";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            msg="要删除的数据错误，请重新确认！";
        }
        url="/pages/back/admin/item/ItemServletBack/list";
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }
    public String list(HttpServletRequest request){
        try {
            request.setAttribute("allItems",ServiceBackFactory.getItemServiceBackInstance().list());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/pages/back/admin/item/item_list.jsp";
    }
}
